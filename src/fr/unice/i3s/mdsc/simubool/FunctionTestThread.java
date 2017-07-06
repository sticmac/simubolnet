package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.KStarDiGraph;

import java.util.Stack;
import java.util.concurrent.RecursiveTask;

public class FunctionTestThread extends RecursiveTask<Integer> {
	private KStarDiGraph diGraph;
	private int functions;
	private final int limit;

	public FunctionTestThread(KStarDiGraph kStarDiGraph, int functions, int limit) {
		this.diGraph = new KStarDiGraph(kStarDiGraph);
		this.functions = functions;
		this.limit = limit;
	}

	@Override
	public Integer compute() {
		int phi = 0;

		for (int i = functions ; i < limit ; i++) {
			int order = diGraph.getOrder();
			int objective = order + 1;
			int max = (int) Math.pow(2, order);

			diGraph.setFunctions(i);
			int fixedPoints = 0;

			for (int j = 0; j < max; j++) {
				if (diGraph.isFixedPoint(j)) {
					fixedPoints++;
				}
			}

			phi = Math.max(phi, fixedPoints);
		}
		return phi;
	}
}
