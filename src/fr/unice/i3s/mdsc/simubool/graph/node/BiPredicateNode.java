package fr.unice.i3s.mdsc.simubool.graph.node;

import java.util.function.BiPredicate;

public class BiPredicateNode extends Node {
	private BiPredicate<Boolean, Boolean> function;

	public BiPredicateNode(int id, boolean value, BiPredicate<Boolean, Boolean> function) {
		super(id, value);
		this.function = function;
	}


	@Override
	public void updateValue(boolean... values) {
		if (values.length >= 2) {
			this.previousValue = this.value;
			this.value = function.test(values[0], values[1]);
		}
	}
}
