package fr.unice.i3s.mdsc.simubool.graph;

import java.util.*;

/**
 * A class for abstract graph. An abstract graph is the common part
 * between directed graph (DiGraph) and undirected graph (UnDiGraph)
 */
public abstract class AbstractGraph {
	
	// the total number of edges
	protected int nbEdges;
	
	// the adjacency list
	//protected List<List<Integer>> adjacencyList;
	protected Node[] nodes;

	/**
	 * builds an abstract graph with n vertices
	 */
	public AbstractGraph(int n) {
		nbEdges = 0;
		nodes = new Node[n];
		for (int i = 0 ; i < nodes.length ; i++) {
			nodes[i] = new Node(i, 0);
		}
	}
	
	/**
	 * returns the number of vertices
	 */
	public int nbVertices() {
		return nodes.length;
	}
	
	/**
	 * returns the number of edges
	 */
	public int nbEdges() {
		return nbEdges;
	}
	
	/**
	 * returns an iterable object over
	 * the vertices adjacent to u
	 */
	public Iterable<Node> adjacents(Node u) {
		return u.getAdjacents();
	}
	
	/**
	 * returns an iterable object over
	 * the edges incident to u
	 */	
	public Iterable<Edge> incidents(Node u) {
		return new EdgeIterator(u);
	}
	
	/**
	 * adds the edge e to the graph
	 */
	public void addEdge(Edge e) {
		addEdge(e.origin(), e.destination());
	}
	
	/**
	 * removes the edge e from the graph
	 */
	public void removeEdge(Edge e) {
		removeEdge(e.origin(), e.destination());
	}

	public void addEdge(int u, int v) {
		addEdge(nodes[u], nodes[v]);
	}

	public void removeEdge(int u, int v) {
		removeEdge(nodes[u], nodes[v]);
	}
	
	/**
	 * adds the edge (u,v) to the graph
	 */	
	public abstract void addEdge(Node u, Node v);
	
	/**
	 * removes the edge (u,v) from the graph
	 */	
	public abstract void removeEdge(Node u, Node v);

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0 ; i < nodes.length ; i++) {
			stringBuilder.append(nodes[i]).append(" -> ").append(nodes[i].getAdjacents()).append("\n");
		}

		return stringBuilder.toString();
	}
	
	// an inner class to iterate over the incident edges
	private class EdgeIterator implements Iterable<Edge>, Iterator<Edge> {
		
		Node origin;
		Iterator<Node> adjacents;
		
		EdgeIterator(Node u) {
			origin = u;
			adjacents = u.getAdjacents().iterator();
		}
		
		public Iterator<Edge> iterator() {
			return this;
		}
		
		public boolean hasNext() {
			return adjacents.hasNext();
		}
		
		public Edge next() {
			return new Edge(origin,adjacents.next());
		}
		
		public void remove() {
			throw new UnsupportedOperationException(); 
		}		
	}
}
