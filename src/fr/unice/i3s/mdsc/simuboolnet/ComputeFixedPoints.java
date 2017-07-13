package fr.unice.i3s.mdsc.simuboolnet;

import fr.unice.i3s.mdsc.simuboolnet.graph.KStarDiGraph;
import fr.unice.i3s.mdsc.simuboolnet.percentage.PercentageComputation;
import fr.unice.i3s.mdsc.simuboolnet.percentage.PercentageHandler;
import fr.unice.i3s.mdsc.simuboolnet.util.function.Functions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Parent task of all functions computations of the graph.
 *
 * @author Julien Lemaire
 */
public class ComputeFixedPoints extends RecursiveTask<Integer> {
	private int order;
	private Queue<ForkJoinTask<Integer>> threadsQueue;
	private final BigInteger interval;
	private final int procs;
	private final BigInteger nbFunctions;
	private final boolean displayPercentage;

	/**
	 * Main constructor.
	 *
	 * @param order order of the K* graph to build.
	 * @param procs number of threads to use.
	 * @param displayPercentage true if percentage has to be displayed.
	 */
	public ComputeFixedPoints(int order, int procs, boolean displayPercentage) {
		this.order = order;
		this.threadsQueue = new LinkedList<>();
		this.nbFunctions = BigInteger.valueOf(Functions.biPredicates.length).pow(order*(order-1));
		this.interval = nbFunctions.divide(BigInteger.valueOf(procs));
		this.procs = procs;
		this.displayPercentage = displayPercentage;
	}

	/**
	 * Runs the simulation.
	 *
	 * @return phi of the K* graph of order <code>order</code>.
	 */
	@Override
	public Integer compute() {
		KStarDiGraph diGraph = new KStarDiGraph(order);

		int phi = 0;

		System.out.println("Order: "+order);
		System.out.println(nbFunctions+" functions to test.");
		System.out.println("BEGIN");

		Optional<PercentageHandler> percentageHandler = Optional.empty();
		PercentageComputation percentageComputation = null;
		if (displayPercentage) {
			percentageHandler = Optional.of(new PercentageHandler(BigDecimal.ZERO, new BigDecimal(nbFunctions)));

			percentageComputation = new PercentageComputation(percentageHandler.get());
			percentageComputation.start();
		}

		for (int functions = 0; functions < this.procs; functions++) {
			BigInteger bigFunctions = BigInteger.valueOf(functions);
			ForkJoinTask<Integer> thread = new FunctionTestThread(diGraph, interval.multiply(bigFunctions), interval.multiply(bigFunctions.add(BigInteger.ONE)), percentageHandler);
			thread.fork();
			threadsQueue.add(thread);
		}

		while (!threadsQueue.isEmpty()) {
			phi = Math.max(threadsQueue.poll().join(), phi);
		}

		if (displayPercentage) percentageComputation.deactivate();

		return phi;
	}
}
