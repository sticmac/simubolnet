package fr.unice.i3s.mdsc.simubool.graph.node;

import java.util.function.Predicate;

public class PredicateNode extends Node {
	private Predicate<Boolean> function;

	public PredicateNode(int id, boolean value, Predicate<Boolean> function) {
		super(id, value);
		this.function = function;
	}

	@Override
	public void updateValue() {
		this.value = function.test(getAdjacents().get(0).getValue());
	}
}
