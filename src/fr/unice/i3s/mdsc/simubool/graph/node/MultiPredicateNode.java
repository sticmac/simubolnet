package fr.unice.i3s.mdsc.simubool.graph.node;

import fr.unice.i3s.mdsc.simubool.util.function.MultiPredicate;

public class MultiPredicateNode extends Node {
	private MultiPredicate function;

	public MultiPredicateNode(int id, boolean value, MultiPredicate function) {
		super(id, value);
		this.function = function;
	}

	@Override
	public void updateValue(boolean... values) {
		this.previousValue = this.value;
		this.value = function.test(values);
	}

	public void setFunction(MultiPredicate function) {
		this.function = function;
	}
}
