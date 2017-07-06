package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.KStarDiGraph;
import fr.unice.i3s.mdsc.simubool.util.function.Functions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ComputeFixedPoints extends RecursiveTask<Integer> {
	private int order;
	private Queue<ForkJoinTask<Integer>> threadsQueue;
	private final int interval;
	private final int procs;
	private final int nbFunctions;

	public ComputeFixedPoints(int order, int procs) {
		this.order = order;
		this.threadsQueue = new LinkedList<>();
		this.nbFunctions = (int)Math.pow(Functions.biPredicates.length, order*(order-1));
		this.interval = nbFunctions / procs;
		this.procs = procs;
	}

	@Override
	public Integer compute() {
		KStarDiGraph diGraph = new KStarDiGraph(order);

		int phi = 0;

		System.out.println("Order: "+order);
		System.out.println(nbFunctions+" functions to test.");
		System.out.println("BEGIN");

		for (int functions = 0 ; functions < this.procs ; functions++) {
			ForkJoinTask<Integer> thread = new FunctionTestThread(diGraph,functions * interval, (functions + 1) * interval);
			thread.fork();
			threadsQueue.add(thread);
		}

		while (!threadsQueue.isEmpty()) {
			phi = Math.max(threadsQueue.poll().join(), phi);
		}

		return phi;
	}
}
