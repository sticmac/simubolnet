package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		KStarDiGraph diGraph = new KStarDiGraph(3);

		int max = (int)Math.pow(2, Math.pow(3, 2));
		int phi = 0; // value of phi(G)

		for (int i = 0 ; i < max ; i++) {
			diGraph.setFunctions(i);
			int fixedPoints = 0;
			List<Integer> knownResults = new ArrayList<>();
			for (int j = 0; j < max; j++) {
				int lastRes = 0;
				// Values to set in the graph
				diGraph.setValues(j);
				// Is this a fixed point?
				if (!knownResults.contains(diGraph.value())) {
					while (knownResults.size() != max) {
						lastRes = diGraph.value();
						diGraph.updateAllValues();
						if (lastRes == diGraph.value()) {
							fixedPoints++;
							break;
						} else if (knownResults.contains(diGraph.value())) {
							break;
						} else {
							knownResults.add(diGraph.value());
						}
					}
				}
			}
			phi = Math.max(phi, fixedPoints);
			System.out.println(i+" : "+fixedPoints + " points fixes.");
		}

		System.out.println("phi(G) = "+phi);

	}
}
