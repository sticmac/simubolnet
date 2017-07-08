package fr.unice.i3s.mdsc.simubool.percentage;

import java.math.BigDecimal;

public class PercentageHandler {
	private BigDecimal debut;
	private BigDecimal end;
	private BigDecimal current;

	public PercentageHandler(BigDecimal debut, BigDecimal end) {
		this.debut = debut;
		this.current = debut.setScale(5, BigDecimal.ROUND_HALF_DOWN);
		this.end = end;
	}

	public void add(BigDecimal i) {
		if (current.add(i).compareTo(end) < 0) {
			current = current.add(i);
		}
	}

	public void incr() {
		this.add(BigDecimal.ONE);
	}

	public BigDecimal getPercentage() {
		return current.subtract(debut).divide(end.subtract(debut), BigDecimal.ROUND_HALF_DOWN).multiply(BigDecimal.valueOf(100));
	}

	public double getDoublePercentage() {
		return this.getPercentage().doubleValue();
	}
}
