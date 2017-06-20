package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;

public class Main {
	public static void main(String[] args) {
		DiGraph diGraph = new DiGraph(2);
		diGraph.addEdge(0, 1);
		diGraph.addEdge(1, 0);

		System.out.println(diGraph);
	}
}
