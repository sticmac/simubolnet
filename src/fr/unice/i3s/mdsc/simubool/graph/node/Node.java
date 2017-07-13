package fr.unice.i3s.mdsc.simubool.graph.node;

import java.util.ArrayList;
import java.util.List;

/**
 * A <code>Node</code> of the graph.
 *
 * @author Marc Gaetano
 * @author Christophe Papazian
 * @author Julien Lemaire
 */
public abstract class Node {
	private int id;
	protected boolean value;
	private int inDegree;
	private List<Node> adjacents;
	private List<Node> parents;

	/**
	 * Main constructor.
	 *
	 * @param id the id of the <code>Node</code> (its index in the array of <code>Node</code>s).
	 * @param value the value of the <code>Node</code>.
	 */
	public Node(int id, boolean value) {
		this.id = id;
		this.value = value;
		this.adjacents = new ArrayList<>();
		this.parents = new ArrayList<>();
	}

	/**
	 * Returns the current value of the <code>Node</code>.
	 *
	 * @return the current value of the <code>Node</code>.
	 */
	public boolean getValue() {
		return value;
	}

	/**
	 * Sets a new value for the <code>Node</code>.
	 *
	 * @param value the new value of the <code>Node</code>.
	 */
	public void setValue(boolean value) {
		this.value = value;
	}

	/**
	 * Returns a list of <code>Node</code>s adjacent to this <code>Node</code>.
	 *
	 * @return the list of <code>Node</code>s adjacent to this <code>Node</code>.
	 */
	public List<Node> getAdjacents() {
		return adjacents;
	}

	/**
	 * Returns the list of this <code>Node</code>'s parents <code>Node</code>s.
	 *
	 * @return the list of this <code>Node</code>'s parents <code>Node</code>s.
	 */
	public List<Node> getParents() {
		return parents;
	}

	/**
	 * Returns the id of this <code>Node</code>.
	 *
	 * @return the id of this <code>Node</code>.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Add a <code>Node</code> as an adjacent to this <code>Node</code>.
	 * Also adds this <code>Node</code> as a parent of the other one.
	 *
	 * @param u the adjacent <code>Node</code>.
	 * @return true if the <code>Node</code> had been added to adjacents list (for example, if it wasn't in list already).
	 */
	public boolean addAdjacent(Node u) {
		boolean contains = this.adjacents.contains(u);
		if (!contains) {
			this.adjacents.add(u);
			u.parents.add(this);
			u.inDegree++;
		}
		return !contains;
	}

	/**
	 * Removes an adjacent <code>Node</code> from the list.
	 *
	 * @param u the adjacent <code>Node</code> to remove.
	 * @return true if the <code>Node</code> had been deleted successfully from the adjacent <code>Node</code>s list.
	 */
	public boolean removeAdjacent(Node u) {
		boolean success = this.adjacents.remove(u) && u.parents.remove(this);
		if (success)
			u.inDegree--;
		return success;
	}

	public abstract void updateValue(boolean... values);

	/**
	 * Returns the out-degree of the <code>Node</code>.
	 *
	 * @return the out-degree of the <code>Node</code>.
	 */
	public int outDegree() {
		return this.getAdjacents().size();
	}

	/**
	 * Returns the in-degree of the <code>Node</code>.
	 *
	 * @return the in-degree of the <code>Node</code>.
	 */
	public int inDegree() {
		return inDegree;
	}

	/**
	 * Returns the degree of the <code>Node</code> (its in-degree + its out-degree).
	 *
	 * @return the degree of the <code>Node</code>.
	 */
	public int degree() {
		return outDegree() + inDegree();
	}

	@Override
	public String toString() {
		return "{" + id + " = " + value + "}";
	}
}
