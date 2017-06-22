package fr.unice.i3s.mdsc.simubool.graph;

import fr.unice.i3s.mdsc.simubool.graph.node.BiPredicateNode;
import fr.unice.i3s.mdsc.simubool.graph.node.Node;
import fr.unice.i3s.mdsc.simubool.graph.node.PredicateNode;

import java.util.List;

public class KStarDiGraph extends DiGraph {
	private int order;

	public KStarDiGraph(int n) {
		super(n*n);

		this.order = n;
		int size = n*n;

		for (int i = 0 ; i < size ; i++) {
			if (i % 4 == 0) {
				nodes[i] = new PredicateNode(i, false, (b) -> b);
			} else {
				nodes[i] = new BiPredicateNode(i, false, (b1, b2) -> b1 || b2);
			}
		}

		this.addEdge(0,2);
		this.addEdge(1,0);
		this.addEdge(2,1);

		this.addEdge(4,3);
		this.addEdge(3,5);
		this.addEdge(5,4);

		this.addEdge(8,7);
		this.addEdge(6,8);
		this.addEdge(7,6);

		this.addEdge(0,3);
		this.addEdge(0,6);

		this.addEdge(4,1);
		this.addEdge(4,7);

		this.addEdge(8,2);
		this.addEdge(8,5);
	}

	public void changeValueOf(int x, int y, boolean newValue) {
		int intKey = (x - 1) * order + (y - 1);
		this.changeValueOf(intKey, newValue);
	}

	public void updateAllValues() {
		this.value = 0;
		for (int i = 0 ; i < nodes.length ; i++) {
			List<Node> parents = nodes[i].getParents();
			if (parents.size() == 2) {
				nodes[i].updateValue(parents.get(0).getPreviousValue(), parents.get(1).getPreviousValue());
			} else if (parents.size() == 1) {
				nodes[i].updateValue(parents.get(0).getPreviousValue());
			} else {
				throw new UnsupportedOperationException("Node " + i + " cannot be updated because it has " + parents.size() + "parents (expected 1 or 2).");
			}
			this.value += (nodes[i].getValue() ? 1 : 0) * Math.pow(2, i);
		}

		for (Node node : nodes) {
			node.syncPreviousValueWithValue();
		}
	}
}
