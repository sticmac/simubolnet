package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;
import fr.unice.i3s.mdsc.simubool.util.FunctionsIdSet;

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

		FunctionsIdSet functionsIdSet = new FunctionsIdSet(weights);

		do {
			diGraph.setFunctions(functionsIdSet);
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
				char[] chars = functionsIdSet.toString().toCharArray();
				for (int i = 0 ; i < order ; i++) {
					chars[i * (order + 1)] = chars[i * (order + 1)] == '0' ? 'a' : 'b';
				}
				System.out.println(new String(chars));
			}
			phi = Math.max(phi, fixedPoints);
		} while (functionsIdSet.next() != 0);

		System.out.println("DONE");
		System.out.println("phi(G) = "+phi);
	}
}
