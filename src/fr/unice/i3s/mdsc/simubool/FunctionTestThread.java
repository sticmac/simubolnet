package fr.unice.i3s.mdsc.simubool;

import fr.unice.i3s.mdsc.simubool.graph.KStarDiGraph;
import fr.unice.i3s.mdsc.simubool.util.FunctionsIdSet;

import java.util.Stack;

public class FunctionTestThread extends Thread {
	private KStarDiGraph diGraph;
	private FunctionsIdSet functionsIdSet;
	private Stack<Integer> results;

	public FunctionTestThread(KStarDiGraph kStarDiGraph, FunctionsIdSet functionsIdSet, Stack<Integer> results) {
		this.diGraph = new KStarDiGraph(kStarDiGraph);
		this.functionsIdSet = new FunctionsIdSet(functionsIdSet);
		this.results = results;
	}

	@Override
	public void run() {
		int order = diGraph.getOrder();
		int objective = order + 1;
		int max = (int)Math.pow(2, order);

		diGraph.setFunctions(functionsIdSet);
		int fixedPoints = 0;

		for (int j = 0; j < max; j++) {
			// Values to set in the graph
			diGraph.setValues(j);
			diGraph.updateAllValues();
			if (diGraph.isFixedPoint()) {
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

		results.push(fixedPoints);
	}
}
