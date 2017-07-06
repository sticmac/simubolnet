package fr.unice.i3s.mdsc.simubool.percentage;

public class PercentageHandler {
	private double debut;
	private double end;
	private double current;

	public PercentageHandler(long debut, long end) {
		this.debut = debut;
		this.current = debut;
		this.end = end;
	}

	public void add(int i) {
		if (current + i <= end) {
			current += i;
		}
	}

	public void incr() {
		this.add(1);
	}

	public double getPercentage() {
		return ((current - debut) / (end - debut)) * 100;
	}
}
