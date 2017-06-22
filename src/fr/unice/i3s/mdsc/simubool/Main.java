package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		KStarDiGraph diGraph = new KStarDiGraph(3);

		int lastRes = 0;
		int max = (int)Math.pow(2, Math.pow(2, 3));
		boolean fixedPoint = false;
		List<Integer> knownResults = new ArrayList<>();
		knownResults.add(diGraph.value());

		while (knownResults.size() != max) {
			lastRes = diGraph.value();
			diGraph.updateAllValues();
			if (lastRes == diGraph.value()) {
				fixedPoint = true;
				break;
			} else if (knownResults.contains(diGraph.value())) {
				break;
			} else {
				knownResults.add(diGraph.value());
			}
		}

		System.out.println(fixedPoint ? "Point fixe" : "Pas de point fixe");
	}
}
