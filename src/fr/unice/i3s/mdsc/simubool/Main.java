package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.*;
import fr.unice.i3s.mdsc.simubool.util.FunctionsIdSet;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		int order = 3;
		int objective = order + 1;
		KStarDiGraph diGraph = new KStarDiGraph(order);

		int phi = 0; // value of phi(G)

		int[] weights = new int[order*order];
		for (int i = 0 ; i < weights.length ; i++) {
			weights[i] = i%(order+1) == 0 ? 2 : 8;
		}

		FunctionsIdSet functionsIdSet = new FunctionsIdSet(weights);
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Stack<Integer> foundResults = new Stack<>();

		do {
			Thread thread = new FunctionTestThread(diGraph, functionsIdSet, foundResults);
			executorService.execute(thread);
			if (!foundResults.isEmpty()) {
				phi = Math.max(foundResults.pop(), phi);
			}
		} while (functionsIdSet.next() != 0);
		executorService.shutdown();
		while (!executorService.isTerminated()) {
        }

		System.out.println("DONE");
		System.out.println("phi(G) = "+phi);
	}
}
