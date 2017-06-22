package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		KStarDiGraph diGraph = new KStarDiGraph(3);

		int max = (int)Math.pow(2, Math.pow(2, 3));
		int fixedPoints = 0;
		List<Integer> knownResults = new ArrayList<>();
		knownResults.add(diGraph.value());

		for (int i = 0 ; i < max ; i++) {
			int lastRes = 0;
			// Values to set in the graph
			String binaryString = Integer.toBinaryString(i);
			for (int j = 0 ; j < binaryString.length() ; j++) {
			}
			// Is this a fixed point?
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

		System.out.println(fixedPoints + " points fixes.");
	}
}
