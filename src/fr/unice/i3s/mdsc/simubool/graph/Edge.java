package fr.unice.i3s.mdsc.simubool.graph;

import fr.unice.i3s.mdsc.simubool.graph.node.Node;

/**
 * A class for the edges of a graph.
 */
public class Edge {

	private Node x;
	private Node y;
	
	/**
	 * builds the edge (x,y)
	 */
	public Edge(Node x, Node y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * returns the origin of the edge
	 */
	public Node origin() {
		return x;
	}
	
	/**
	 * returns the destination of the edge
	 */
	public Node destination() {
		return y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		Edge e = (Edge) o;
		return x == e.x && y == e.y;
	}
	
	@Override
	public int hashCode() {
		return x.hashCode() + 37*y.hashCode();
	}
}
