package fr.unice.i3s.mdsc.simubool.graph;

import fr.unice.i3s.mdsc.simubool.graph.node.Node;

/**
 * A class for directed graph
 *
 * @author Marc Gaetano
 * @author Christophe Papazian
 * @author Julien Lemaire
 */
public class DiGraph extends AbstractGraph {

	/**
	 * builds a directed graph with n vertices
	 */
	public DiGraph(int n) {
		super(n);
	}


	public DiGraph(DiGraph a) {
		super(a);
	}

	@Override
	public void addEdge(Node u, Node v) {
		u.addAdjacent(v);
	}

	@Override
	public void removeEdge(Node u, Node v) {
		u.removeAdjacent(v);
	}

}
