package fr.unice.i3s.mdsc.simubool.graph.node;

import fr.unice.i3s.mdsc.simubool.graph.Edge;

import java.util.function.Predicate;

public class PredicateNode extends Node {
	private Predicate<Boolean> function;

	public PredicateNode(int id, boolean value, Predicate<Boolean> function) {
		super(id, value);
		this.function = function;
	}

	@Override
	public void updateValue(boolean... values) {
		if (values.length >= 1) {
			this.previousValue = this.value;
			this.value = function.test(values[0]);
		}
	}
}
