package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.KStarDiGraph;
import fr.unice.i3s.mdsc.simubool.util.function.Functions;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ComputeFixedPoints extends RecursiveTask<Integer> {
	private int order;

	public ComputeFixedPoints(int order) {
		this.order = order;
	}

	@Override
	public Integer compute() {
		KStarDiGraph diGraph = new KStarDiGraph(order);

		int phi = 0;

		for (int functions = 0 ; functions < Math.pow(Functions.biPredicates.length, order*(order-1)) ; functions++) {
			ForkJoinTask<Integer> thread = new FunctionTestThread(diGraph, functions);
			thread.fork();
			phi = Math.max(thread.join(), phi);
		}

		return phi;
	}
}
