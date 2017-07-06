package fr.unice.i3s.mdsc.simubool;

import java.util.concurrent.ForkJoinPool;

public class Main {
	public static void main(String[] args) {
		int order = 3;

		int procs = Runtime.getRuntime().availableProcessors();

		//ExecutorService pool = Executors.newFixedThreadPool(procs);
		ForkJoinPool pool = new ForkJoinPool(procs);

		ComputeFixedPoints computeFixedPoints = new ComputeFixedPoints(order, procs);
		int phi = pool.invoke(computeFixedPoints);

		System.out.println("DONE");
		System.out.println("phi(G) = "+phi);
	}
}
