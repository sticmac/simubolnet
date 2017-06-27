package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;

public class Main {
	public static void main(String[] args) {
		int order = 3;
		KStarDiGraph diGraph = new KStarDiGraph(order);

		int max = (int)Math.pow(2, Math.pow(order, 2));
		int phi = 0; // value of phi(G)

		for (int i = 0 ; i < max ; i++) {
			diGraph.setFunctions(i);
			int fixedPoints = 0;

			for (int j = 0; j < max; j++) {
				// Values to set in the graph
				diGraph.setValues(j);
				diGraph.updateAllValues();
				if (diGraph.value() == j) {
					fixedPoints++;
				}
			}

			phi = Math.max(phi, fixedPoints);
			System.out.println(i+" : "+fixedPoints + " points fixes.");
		}

		System.out.println("phi(G) = "+phi);

	}
}
