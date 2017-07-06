package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.KStarDiGraph;

import java.util.Stack;
import java.util.concurrent.RecursiveTask;

public class FunctionTestThread extends RecursiveTask<Integer> {
	private KStarDiGraph diGraph;
	private int functions;

	public FunctionTestThread(KStarDiGraph kStarDiGraph, int functions) {
		this.diGraph = new KStarDiGraph(kStarDiGraph);
		this.functions = functions;
	}

	@Override
	public Integer compute() {
		int order = diGraph.getOrder();
		int objective = order + 1;
		int max = (int)Math.pow(2, order);

		diGraph.setFunctions(functions);
		int fixedPoints = 0;

		for (int j = 0; j < max; j++) {
			if (diGraph.isFixedPoint(j)) {
				fixedPoints++;
			}
		}

		if (fixedPoints >= objective) {
			System.out.println(functions+": "+fixedPoints);
		}

		return fixedPoints;
	}
}