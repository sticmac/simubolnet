package fr.unice.i3s.mdsc.simubool.percentage;

/**
 * Thread displaying the percentage of computed functions over time.
 */
public class PercentageComputation extends Thread {
	private PercentageHandler percentageHandler;
	private boolean run;

	/**
	 * Main constructor.
	 *
	 * @param percentageHandler Handler of percentage.
	 */
	public PercentageComputation(PercentageHandler percentageHandler) {
		this.percentageHandler = percentageHandler;
		this.run = true;
	}

	/**
	 * Deactivate the display of percentage.
	 */
	public void deactivate() {
		this.run = false;
	}

	@Override
	public void run() {
		while (this.run) {
			System.out.println(this.percentageHandler.getPercentage()+"%");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
