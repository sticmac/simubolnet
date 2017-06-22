package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;

public class Main {
	public static void main(String[] args) {
		KStarDiGraph diGraph = new KStarDiGraph(3);

		diGraph.changeValueOf(1, 1, true);

		System.out.println(diGraph);

		diGraph.updateAllValues();

		System.out.println(diGraph);
	}
}
