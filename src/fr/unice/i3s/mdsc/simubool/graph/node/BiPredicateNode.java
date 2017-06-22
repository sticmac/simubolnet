package fr.unice.i3s.mdsc.simubool.graph.node;

import java.util.function.BiPredicate;

public class BiPredicateNode extends Node {
	private BiPredicate<Boolean, Boolean> function;

	public BiPredicateNode(int id, boolean value, BiPredicate<Boolean, Boolean> function) {
		super(id, value);
		this.function = function;
	}


	@Override
	public void updateValue() {
		this.value = function.test(this.getParents().get(0).getValue(), this.getParents().get(1).getValue());
	}
}
