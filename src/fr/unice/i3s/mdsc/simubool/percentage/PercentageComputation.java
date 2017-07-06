package fr.unice.i3s.mdsc.simubool.percentage;

public class PercentageComputation extends Thread {
	private PercentageHandler percentageHandler;
	private boolean run;

	public PercentageComputation(PercentageHandler percentageHandler) {
		this.percentageHandler = percentageHandler;
		this.run = true;
	}

	public void desactivate() {
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
