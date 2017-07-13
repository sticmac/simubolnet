package fr.unice.i3s.mdsc.simuboolnet;

import fr.unice.i3s.mdsc.simuboolnet.graph.KStarDiGraph;
import fr.unice.i3s.mdsc.simuboolnet.percentage.PercentageHandler;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

/**
 * Thread calculating the number of fixed points for several functions applied to the graph.
 *
 * @author Julien Lemaire
 */
public class FunctionTestThread extends RecursiveTask<Integer> {
	private KStarDiGraph diGraph;
	private BigInteger functions;
	private Optional<PercentageHandler> percentageHandler;
	private final BigInteger limit;

	/**
	 * Main constructor.
	 *
	 * @param kStarDiGraph graph on which to apply the function.
	 * @param functions id of the first function to apply to the graph.
	 * @param limit exclusive limit of to apply functions.
	 * @param percentageHandler work done percentage.
	 */
	public FunctionTestThread(KStarDiGraph kStarDiGraph, BigInteger functions, BigInteger limit, Optional<PercentageHandler> percentageHandler) {
		this.diGraph = new KStarDiGraph(kStarDiGraph);
		this.functions = functions;
		this.limit = limit;
		this.percentageHandler = percentageHandler;
	}

	/**
	 * Instructions applied by the threads.
	 * Calculates the number of fixed points for the given functions.
	 *
	 * @return the number of fixed points for the given function.
	 */
	@Override
	public Integer compute() {
		int phi = 0;

		for (BigInteger i = functions ; i.compareTo(limit) < 0 ; i = i.add(BigInteger.ONE)) {
			int order = diGraph.getOrder();
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
