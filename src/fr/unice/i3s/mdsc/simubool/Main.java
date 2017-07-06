package fr.unice.i3s.mdsc.simubool;

import java.util.concurrent.ForkJoinPool;

public class Main {
	public static void main(String[] args) {
		int order = 3;
		if (args.length > 0) {
			order = Integer.parseInt(args[0]);
		}

		int procs = Runtime.getRuntime().availableProcessors();

		ForkJoinPool pool = new ForkJoinPool(procs/4); //not all processors

		ComputeFixedPoints computeFixedPoints = new ComputeFixedPoints(order, procs);
		int phi = pool.invoke(computeFixedPoints);

		System.out.println("DONE");
		System.out.println("phi(G) = "+phi);
	}
}
