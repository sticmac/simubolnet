package fr.unice.i3s.mdsc.simubool.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int id;
	private int value;
	private int inDegree;
	private List<Node> adjacents;

	public Node(int id, int value) {
		this.id = id;
		this.value = value;
		this.adjacents = new ArrayList<>();
	}

	public int getValue() {
		return value;
	}

	public List<Node> getAdjacents() {
		return adjacents;
	}

	public boolean addAdjacent(Node u) {
		boolean contains = this.adjacents.contains(u);
		if (!contains) {
			this.adjacents.add(u);
			this.inDegree++;
		}
		return !contains;
	}

	public boolean removeAdjacent(Node u) {
		boolean success = this.adjacents.remove(u);
		if (success)
			this.inDegree--;
		return success;
	}

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
		return "{"+id+" = "+value+"}";
	}
}
