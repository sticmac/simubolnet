package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.KStarDiGraph;
import fr.unice.i3s.mdsc.simubool.percentage.PercentageHandler;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

public class FunctionTestThread extends RecursiveTask<Integer> {
	private KStarDiGraph diGraph;
	private BigInteger functions;
	private Optional<PercentageHandler> percentageHandler;
	private final BigInteger limit;

	public FunctionTestThread(KStarDiGraph kStarDiGraph, BigInteger functions, BigInteger limit, Optional<PercentageHandler> percentageHandler) {
		this.diGraph = new KStarDiGraph(kStarDiGraph);
		this.functions = functions;
		this.limit = limit;
		this.percentageHandler = percentageHandler;
	}

	@Override
	public Integer compute() {
		int phi = 0;

		for (BigInteger i = functions ; i.compareTo(limit) < 0 ; i = i.add(BigInteger.ONE)) {
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
