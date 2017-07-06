package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.KStarDiGraph;
import fr.unice.i3s.mdsc.simubool.percentage.PercentageComputation;
import fr.unice.i3s.mdsc.simubool.percentage.PercentageHandler;
import fr.unice.i3s.mdsc.simubool.util.function.Functions;

import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ComputeFixedPoints extends RecursiveTask<Integer> {
	private int order;
	private Queue<ForkJoinTask<Integer>> threadsQueue;
	private final long interval;
	private final int procs;
	private final long nbFunctions;
	private final boolean displayPercentage;

	public ComputeFixedPoints(int order, int procs, boolean displayPercentage) {
		this.order = order;
		this.threadsQueue = new LinkedList<>();
		this.nbFunctions = (long)Math.pow(Functions.biPredicates.length, order*(order-1));
		this.interval = nbFunctions / procs;
		this.procs = procs;
		this.displayPercentage = displayPercentage;
	}

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
			percentageHandler = Optional.of(new PercentageHandler(0, nbFunctions));

			percentageComputation = new PercentageComputation(percentageHandler.get());
			percentageComputation.start();
		}

		for (int functions = 0; functions < this.procs; functions++) {
			ForkJoinTask<Integer> thread = new FunctionTestThread(diGraph, functions * interval, (functions + 1) * interval, percentageHandler);
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
