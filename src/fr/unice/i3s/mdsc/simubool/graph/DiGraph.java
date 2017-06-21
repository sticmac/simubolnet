package fr.unice.i3s.mdsc.simubool.graph;

/**
 * A class for directed graph
 */
public class DiGraph extends AbstractGraph {

	/**
	 * builds a directed graph with n vertices
	 */
	public DiGraph(int n) {
		super(n);
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
