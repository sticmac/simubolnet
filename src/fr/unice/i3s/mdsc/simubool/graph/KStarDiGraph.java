package fr.unice.i3s.mdsc.simubool.graph;

import fr.unice.i3s.mdsc.simubool.graph.node.BiPredicateNode;
import fr.unice.i3s.mdsc.simubool.graph.node.PredicateNode;

public class KStarDiGraph extends DiGraph {

	public KStarDiGraph(int n) {
		super(n*n);

		int size = n*n;

		for (int i = 0 ; i < size ; i++) {
			if (i % 4 == 0) {
				nodes[i] = new PredicateNode(i, false, (b) -> b);
			} else {
				nodes[i] = new BiPredicateNode(i, false, (b1, b2) -> b1 && b2);
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
}
