package fr.unice.i3s.mdsc.simubool.graph.node;

import fr.unice.i3s.mdsc.simubool.util.function.MultiPredicate;

/**
 * A <code>Node</code> with an assigned <code>MultiPredicate</code>.
 */
public class MultiPredicateNode extends Node {
	private MultiPredicate function;

	/**
	 * Main constructor.
	 *
	 * @param id the id of the <code>Node</code> (its index in the array of nodes).
	 * @param value the value of the <code>Node</code>.
	 * @param function the <code>MultiPredicate</code> assigned to this <code>Node</code>.
	 */
	public MultiPredicateNode(int id, boolean value, MultiPredicate function) {
		super(id, value);
		this.function = function;
	}

	/**
	 * Updates the value of the node with the <code>MultiPredicate</code> applied with given boolean values.
	 *
	 * @param values the parameters given to the <code>MultiPredicate</code>.
	 */
	@Override
	public void updateValue(boolean... values) {
		this.value = function.test(values);
	}

	/**
	 * Change the <code>MultiPredicate</code> function assigned to this <code>Node</code>.
	 *
	 * @param function the new function.
	 */
	public void setFunction(MultiPredicate function) {
		this.function = function;
	}
}
