package fr.unice.i3s.mdsc.simubool.util;

import java.util.Arrays;

public class FunctionsIdSet {
	private int[] values;
	private int[] maxes;

	public FunctionsIdSet(int[] maxes) {
		this.maxes = maxes;
		this.values = new int[maxes.length];
	}

	public int[] getValues() {
		return values;
	}

	public int next() {
		increaseValue(0);
		return Arrays.stream(values).sum();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i : this.values) {
			stringBuilder.append(i);
		}
		return stringBuilder.toString();
	}

	private void increaseValue(int n) {
		values[n]++;
		if (values[n] == maxes[n]) {
			if (n == values.length - 1) {
				reset();
			} else {
				values[n] = 0;
				increaseValue(n+1);
			}
		}
	}

	private void reset() {
		values = new int[maxes.length];
	}
}
