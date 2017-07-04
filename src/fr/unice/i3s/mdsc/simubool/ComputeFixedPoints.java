package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.KStarDiGraph;
import fr.unice.i3s.mdsc.simubool.util.FunctionsIdSet;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ComputeFixedPoints extends RecursiveTask<Integer> {
	private int order;

	public ComputeFixedPoints(int order) {
		this.order = order;
	}

	@Override
	public Integer compute() {
		int objective = order + 1;
		KStarDiGraph diGraph = new KStarDiGraph(order);

		int[] weights = new int[order*order];
		for (int i = 0 ; i < weights.length ; i++) {
			weights[i] = i%(order+1) == 0 ? 2 : 8;
		}

		FunctionsIdSet functionsIdSet = new FunctionsIdSet(weights);

		int phi = 0;
		do {
			ForkJoinTask<Integer> thread = new FunctionTestThread(diGraph, functionsIdSet);
			thread.fork();
			phi = Math.max(thread.join(), phi);
		} while (functionsIdSet.next() != 0);
		return phi;
	}
}
