package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.KStarDiGraph;
import fr.unice.i3s.mdsc.simubool.percentage.PercentageHandler;

import java.util.Optional;
import java.util.concurrent.RecursiveTask;

public class FunctionTestThread extends RecursiveTask<Integer> {
	private KStarDiGraph diGraph;
	private long functions;
	private Optional<PercentageHandler> percentageHandler;
	private final long limit;

	public FunctionTestThread(KStarDiGraph kStarDiGraph, long functions, long limit, Optional<PercentageHandler> percentageHandler) {
		this.diGraph = new KStarDiGraph(kStarDiGraph);
		this.functions = functions;
		this.limit = limit;
		this.percentageHandler = percentageHandler;
	}

	@Override
	public Integer compute() {
		int phi = 0;

		for (long i = functions ; i < limit ; i++) {
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
			percentageHandler.ifPresent(PercentageHandler::incr);
		}
		return phi;
	}
}
