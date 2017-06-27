package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;
import fr.unice.i3s.mdsc.simubool.util.FunctionsIdSet;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int order = 3;
		KStarDiGraph diGraph = new KStarDiGraph(order);

		int max = (int)Math.pow(2, Math.pow(order, 2));
		int phi = 0; // value of phi(G)

		int[] weights = new int[order*order];
		for (int i = 0 ; i < weights.length ; i++) {
			weights[i] = i%(order+1) == 0 ? 2 : 10;
		}
		FunctionsIdSet functionsIdSet = new FunctionsIdSet(weights);

		do {
			diGraph.setFunctions(functionsIdSet);
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
			System.out.println(functionsIdSet + " : " + fixedPoints + " points fixes.");
			functionsIdSet.next();
		} while (functionsIdSet.next() != 0);

		System.out.println("DONE");
		System.out.println("phi(G) = "+phi);

	}
}
