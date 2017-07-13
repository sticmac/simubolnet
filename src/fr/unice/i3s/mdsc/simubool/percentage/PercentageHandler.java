package fr.unice.i3s.mdsc.simubool.percentage;

import java.math.BigDecimal;

/**
 * Handle proportion of work done among work to do.
 * Can then deliver a percentage of work done.
 *
 * @author Julien Lemaire
 */
public class PercentageHandler {
	private BigDecimal debut;
	private BigDecimal end;
	private BigDecimal current;

	/**
	 * Main constructor.
	 *
	 * @param debut initial value
	 * @param end limit value
	 */
	public PercentageHandler(BigDecimal debut, BigDecimal end) {
		this.debut = debut;
		this.current = debut.setScale(5, BigDecimal.ROUND_HALF_DOWN);
		this.end = end;
	}

	/**
	 * Add a value of work done.
	 *
	 * @param i the amount of work done
	 */
	public void add(BigDecimal i) {
		if (current.add(i).compareTo(end) < 0) {
			current = current.add(i);
		}
	}

	/**
	 * Add one piece of work done.
	 */
	public void incr() {
		this.add(BigDecimal.ONE);
	}

	/**
	 * Returns the percentage of work done.
	 *
	 * @return the percentage of work done.
	 */
	public BigDecimal getPercentage() {
		return current.subtract(debut).divide(end.subtract(debut), BigDecimal.ROUND_HALF_DOWN).multiply(BigDecimal.valueOf(100));
	}
}
