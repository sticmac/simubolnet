package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;
import fr.unice.i3s.mdsc.simubool.util.function.Functions;

public class Main {
	public static void main(String[] args) {
		int order = 3;
		int objective = order + 1;
		KStarDiGraph diGraph = new KStarDiGraph(order);

		int max = (int)Math.pow(2, order);
		int phi = 0; // value of phi(G)

		int[] weights = new int[order*order];
		for (int i = 0 ; i < weights.length ; i++) {
			weights[i] = i%(order+1) == 0 ? 2 : 8;
		}

		long compt = 0;
		for (int functions = 0 ; functions < Math.pow(Functions.biPredicates.length, order*(order-1)) ; functions++) {
			diGraph.setFunctions(functions);
			int fixedPoints = 0;

			for (int j = 0; j < max; j++) {
				// Values to set in the graph
				diGraph.setValues(j);
				diGraph.updateAllValues();
				if (diGraph.isFixedPoint()) {
					fixedPoints++;
				}
			}

			if (fixedPoints >= objective) {
				System.out.println(functions+": "+fixedPoints);
			}
			phi = Math.max(phi, fixedPoints);
			compt++;
			if (compt == 1000000) {
				System.err.println(functions+": "+phi);
				compt = 0;
			}
		}

		System.out.println("DONE");
		System.out.println("phi(G) = "+phi);
	}
}
