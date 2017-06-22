package fr.unice.i3s.mdsc.simubool.graph.node;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
	private int id;
	protected boolean value;
	protected boolean previousValue;
	private int inDegree;
	private List<Node> adjacents;
	private List<Node> parents;

	public Node(int id, boolean value) {
		this.id = id;
		this.value = value;
		this.previousValue = value;
		this.adjacents = new ArrayList<>();
		this.parents = new ArrayList<>();
	}

	public boolean getValue() {
		return value;
	}

	public boolean getPreviousValue() {
		return previousValue;
	}

	public void syncPreviousValueWithValue() {
		this.previousValue = this.value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public List<Node> getAdjacents() {
		return adjacents;
	}

	public List<Node> getParents() {
		return parents;
	}

	public int getId() {
		return id;
	}

	public boolean addAdjacent(Node u) {
		boolean contains = this.adjacents.contains(u);
		if (!contains) {
			this.adjacents.add(u);
			u.parents.add(this);
			this.inDegree++;
		}
		return !contains;
	}

	public boolean removeAdjacent(Node u) {
		boolean success = this.adjacents.remove(u) && u.parents.remove(this);
		if (success)
			this.inDegree--;
		return success;
	}

	public abstract void updateValue(boolean... values);

	/**
	 * returns the out-degree of u
	 */
	public int outDegree(Node u) {
		return u.getAdjacents().size();
	}

	/**
	 * returns the in-degree of u
	 */
	public int inDegree(Node u) {
		return inDegree;
	}

	public int degree(Node u) {
		return outDegree(u) + inDegree(u);
	}

	@Override
	public String toString() {
		return "{" + id + " = " + value + "}";
	}
}
