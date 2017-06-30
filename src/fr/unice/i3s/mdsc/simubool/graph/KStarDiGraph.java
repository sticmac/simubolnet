package fr.unice.i3s.mdsc.simubool.graph;

import fr.unice.i3s.mdsc.simubool.graph.node.MultiPredicateNode;
import fr.unice.i3s.mdsc.simubool.graph.node.Node;
import fr.unice.i3s.mdsc.simubool.util.FunctionsIdSet;
import fr.unice.i3s.mdsc.simubool.util.function.Functions;
import fr.unice.i3s.mdsc.simubool.util.function.MultiPredicate;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KStarDiGraph extends DiGraph {
	private int order;

	public KStarDiGraph(int n) {
		super(n*n);

		this.order = n;
		int p = n+1;
		int o = n-1;
		int size = n*n;

		//initialize nodes
		//entered predicates here are defaults ones and not necessary the used ones later.
		for (int i = 0 ; i < size ; i++) {
			if (i % p == 0) {
				nodes[i] = new MultiPredicateNode(i, false, (b) -> b[0]);
			} else {
				nodes[i] = new MultiPredicateNode(i, false, (b) -> b[0] || b[1]);
			}
		}

		//arcs
		for (int i = 0 ; i <= o ; i++) {
			//internal
			for (int j = o ; j >= 1 ; j--) {
				this.addEdge(i*n + j, i*n + j - 1);
			}
			this.addEdge(i*n, i*n + o);

			//external
			for (int j = 0 ; j <= o ; j++) {
				if (j != i) {
					this.addEdge(i*p, i + j*n);
				}
			}
		}

	}

	public KStarDiGraph(KStarDiGraph kStarDiGraph) {
		this(kStarDiGraph.order);
	}

	public int getOrder() {
		return order;
	}

	public void setValues(int entry) {
		String binaryEntry = Integer.toBinaryString(entry);
		int i = 0;
		for ( ; i < binaryEntry.length() ; i++) {
			this.changeValueOf(i+1, i+1, binaryEntry.charAt(binaryEntry.length() - 1 - i) - '0' == 1);
		}
		for( ; i < order ; i++) {
			this.changeValueOf(i+1, i+1, false);
		}
	}

	public void setFunctions(FunctionsIdSet functions) {
		int[] values = functions.getValues();
		int i = 0;
		for ( ; i < values.length ; i++) {
			int inDegree = this.nodes[i].inDegree();
			if (inDegree == 1) {
				this.changeFunctionOf(i, Functions.predicates[values[i]]);
			} else { //inDegree == 2
				this.changeFunctionOf(i, Functions.biPredicates[values[i]]);
			}
		}
	}

	public void changeValueOf(int x, int y, boolean newValue) {
		int intKey = (x - 1) * order + (y - 1);
		this.changeValueOf(intKey, newValue);
	}

	public void changeFunctionOf(int i, MultiPredicate function) {
		((MultiPredicateNode)this.nodes[i]).setFunction(function);
	}

	public void updateAllValues() {
		Queue<Node> todo = new LinkedList<>();
		for (int i = 0 ; i < order ; i++) {
			todo.add(nodes[i*(order+1)].getAdjacents().get(0));
		}
		while (!todo.isEmpty()) {
			Node node = todo.poll();
			List<Node> parents = node.getParents();

			if (parents.size() == 2) { // indegree = 2, not representing
				node.updateValue(parents.get(0).getValue(), parents.get(1).getValue());
			} else if (parents.size() == 1) { // indegree = 1, representing
				node.updateValue(parents.get(0).getValue());
			} else {
				throw new UnsupportedOperationException("Node " + node.getId() + " cannot be updated because it has " + parents.size() + "parents (expected 1 or 2).");
			}

			// add first adjacent?
			Node adj = node.getAdjacents().get(0);
			if (adj.getId() % 4 != 0) {
				todo.add(adj);
			}
		}
	}

	public boolean isFixedPoint() {
		for (int i = 0 ; i < order ; i++) {
			Node node = nodes[i*(order+1)];
			boolean value = node.getValue();
			node.updateValue(node.getParents().get(0).getValue());
			if (value != node.getValue()) {
				return false;
			}
		}
		return true;
	}
}
