package fr.unice.i3s.mdsc.simubool.graph;

import fr.unice.i3s.mdsc.simubool.graph.node.MultiPredicateNode;
import fr.unice.i3s.mdsc.simubool.graph.node.Node;
import fr.unice.i3s.mdsc.simubool.util.Pair;
import fr.unice.i3s.mdsc.simubool.util.function.Functions;
import fr.unice.i3s.mdsc.simubool.util.function.MultiPredicate;

import java.util.*;

public class KStarDiGraph extends DiGraph {
	private int order;
	private Map<Pair<Integer, Integer>, Integer> indexes;

	public KStarDiGraph(int n) {
		super(n*(n-1));

		this.indexes = new HashMap<>();
		this.order = n;
		int size = n*(n-1);

		//affiliate couples and ids
		for (int i = 1 ; i <= order ; i++) {
			indexes.put(new Pair<>(i, i), (i-1)*(order-1));
			indexes.put(new Pair<>(i, i+1 <= order ? i+1 : i+1-order), (i-1) * (order-1));
			for (int j = 2 ; j < order ; j++) {
				indexes.put(new Pair<>(i, i + j <= order ? i + j : i + j - order), (i - 1) * (order - 1) + (j - 1));
			}
		}

		//initialize nodes
		//entered predicates here are defaults ones and not necessary the used ones later.
		for (int i = 0 ; i < size ; i++) {
			nodes[i] = new MultiPredicateNode(i, false, Functions.biPredicates[0]);
		}

		//arcs
		for (int i = 1 ; i <= order ; i++) {
			int index = indexes.get(new Pair<>(i, i));

			//internal
			this.addEdge(index, index+1);
			this.addEdge(index+1, index);

			//external
			for (int j = 1 ; j <= order ; j++) {
				if (j != i) {
					this.addEdge(index, indexes.get(new Pair<>(j, i)));
				}
			}
		}

		System.out.println(this);
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

	public void setFunctions(int functions) {
		String entry = Integer.toString(functions, Functions.biPredicates.length);
		int i = 0;
		for ( ; i < entry.length() ; i++) {
			this.changeFunctionOf(i, Functions.biPredicates[entry.charAt(i) - '0']);
		}
	}

	public void changeValueOf(int x, int y, boolean newValue) {
		int intKey = indexes.get(new Pair<>(x, y));
		this.changeValueOf(intKey, newValue);
	}

	public void changeFunctionOf(int i, MultiPredicate function) {
		((MultiPredicateNode)this.nodes[i]).setFunction(function);
	}

	public void updateAllValues() {
		Queue<Node> todo = new LinkedList<>();
		for (int i = 0 ; i < order ; i++) {
			todo.add(nodes[i*(order-1)].getAdjacents().get(0));
		}
		while (!todo.isEmpty()) {
			Node node = todo.poll();
			List<Node> parents = node.getParents();

			if (parents.size() == 2) { // indegree = 2, not representing
				node.updateValue(parents.get(0).getValue(), parents.get(1).getValue());
			} else {
				throw new UnsupportedOperationException("Node " + node.getId() + " cannot be updated because it has " + parents.size() + "parents (expected 1 or 2).");
			}
		}
	}

	public boolean isFixedPoint() {
		for (int i = 0 ; i < order ; i++) {
			Node node = nodes[i*(order-1)];
			boolean value = node.getValue();
			node.updateValue(node.getParents().get(0).getValue(), node.getParents().get(1).getValue());
			if (value != node.getValue()) {
				return false;
			}
		}
		return true;
	}
}
