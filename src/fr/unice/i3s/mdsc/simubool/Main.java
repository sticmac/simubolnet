package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;

public class Main {
	public static void main(String[] args) {
		DiGraph diGraph = new DiGraph(9);

		diGraph.addEdge(0,2);
		diGraph.addEdge(1,0);
		diGraph.addEdge(2,1);

		diGraph.addEdge(4,3);
		diGraph.addEdge(3,5);
		diGraph.addEdge(5,4);

		diGraph.addEdge(8,7);
		diGraph.addEdge(6,8);
		diGraph.addEdge(7,6);

		diGraph.addEdge(0,3);
		diGraph.addEdge(0,6);

		diGraph.addEdge(4,1);
		diGraph.addEdge(4,7);

		diGraph.addEdge(8,2);
		diGraph.addEdge(8,5);

		System.out.println(diGraph);
	}
}
