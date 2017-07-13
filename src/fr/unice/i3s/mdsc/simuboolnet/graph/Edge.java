package fr.unice.i3s.mdsc.simuboolnet.graph;

import fr.unice.i3s.mdsc.simuboolnet.graph.node.Node;
import fr.unice.i3s.mdsc.simuboolnet.util.Pair;

/**
 * A class for the edges of a graph.
 *
 * @author Marc Gaetano
 * @author Christophe Papazian
 * @author Julien Lemaire
 */
public class Edge extends Pair<Node, Node> {

	public Edge(Node left, Node right) {
		super(left, right);
	}

	/**
	 * returns the origin of the edge
	 */
	public Node origin() {
		return getLeft();
	}
	
	/**
	 * returns the destination of the edge
	 */
	public Node destination() {
		return getRight();
	}
	
	@Override
	public String toString() {
		return "(" + getLeft() + ", " + getRight() + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		Edge e = (Edge) o;
		return getLeft() == e.getLeft() && getRight() == e.getRight();
	}
	
	@Override
	public int hashCode() {
		return getLeft().hashCode() + 37*getRight().hashCode();
	}
}
