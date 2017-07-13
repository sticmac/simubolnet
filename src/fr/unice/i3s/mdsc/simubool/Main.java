package fr.unice.i3s.mdsc.simubool;

import java.util.concurrent.ForkJoinPool;

/**
 * Main class.
 *
 * @author Julien Lemaire
 */
public class Main {
	public static void main(String[] args) {
		// Parsing arguments
		int order = 3;
		boolean displayPercents = true;
		if (args.length > 0) {
			order = Integer.parseInt(args[0]);
			if (args.length == 2 && args[1].equals("-n")) {
				displayPercents = false;
			}
		}

		// Pool thread with a number of threads corresponding to the quarter of available processors
		int procs = Runtime.getRuntime().availableProcessors();
		ForkJoinPool pool = new ForkJoinPool(procs/4); //not all processors

		// Running simulator
		ComputeFixedPoints computeFixedPoints = new ComputeFixedPoints(order, procs, displayPercents);
		int phi = pool.invoke(computeFixedPoints); // storing result

		// Display results
		System.out.println("DONE");
		System.out.println("phi(G) = "+phi);
	}
}
