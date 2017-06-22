package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;

public class Main {
	public static void main(String[] args) {
		KStarDiGraph diGraph = new KStarDiGraph(3);

		diGraph.changeValueOf(1, 1, true);

		System.out.println(diGraph);
		String lastRes = "";

		for (int i = 0 ; i < 100000 && !diGraph.toString().equals(lastRes) ; i++) {
			lastRes = diGraph.toString();
			diGraph.updateAllValues();
			System.out.println(diGraph);
		}
	}
}
