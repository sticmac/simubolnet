package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.KStarDiGraph;
import fr.unice.i3s.mdsc.simubool.util.FunctionsIdSet;

import java.util.Stack;
import java.util.concurrent.RecursiveTask;

public class FunctionTestThread extends RecursiveTask<Integer> {
	private KStarDiGraph diGraph;
	private FunctionsIdSet functionsIdSet;

	public FunctionTestThread(KStarDiGraph kStarDiGraph, FunctionsIdSet functionsIdSet) {
		this.diGraph = new KStarDiGraph(kStarDiGraph);
		this.functionsIdSet = new FunctionsIdSet(functionsIdSet);
	}

	@Override
	public Integer compute() {
		int order = diGraph.getOrder();
		int objective = order + 1;
		int max = (int)Math.pow(2, order);

		diGraph.setFunctions(functionsIdSet);
		int fixedPoints = 0;

		for (int j = 0; j < max; j++) {
			if (diGraph.isFixedPoint(j)) {
				fixedPoints++;
			}
		}

		if (fixedPoints >= objective) {
			char[] chars = functionsIdSet.toString().toCharArray();
			for (int i = 0 ; i < order ; i++) {
				chars[i * (order + 1)] = chars[i * (order + 1)] == '0' ? 'a' : 'b';
			}
			System.out.println(new String(chars));
		}

		return fixedPoints;
	}
}
