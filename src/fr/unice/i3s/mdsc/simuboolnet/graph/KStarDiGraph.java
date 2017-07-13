package fr.unice.i3s.mdsc.simuboolnet.graph;

import fr.unice.i3s.mdsc.simuboolnet.graph.node.MultiPredicateNode;
import fr.unice.i3s.mdsc.simuboolnet.graph.node.Node;
import fr.unice.i3s.mdsc.simuboolnet.util.Pair;
import fr.unice.i3s.mdsc.simuboolnet.util.function.Functions;
import fr.unice.i3s.mdsc.simuboolnet.util.function.MultiPredicate;

import java.math.BigInteger;
import java.util.*;

/**
 * K* digraph as defined in README.md.
 *
 * @author Julien Lemaire
 */
public class KStarDiGraph extends DiGraph {
	private int order;
	private Map<Pair<Integer, Integer>, Integer> indexes;

	/**
	 * Main constructor.
	 * Builds a K* digraph at order n by adding the needed <code>Edge</code>s.
	 *
	 * @param n order of the K* digraph.
	 */
	public KStarDiGraph(int n) {
		super(n*(n-1));

		this.indexes = new HashMap<>();
		this.order = n;
		int size = n*(n-1);

		//affiliate couples and ids
		for (int i = 1 ; i <= order ; i++) {
			indexes.put(new Pair<>(i, i), (i-1)*(order-1));
			indexes.put(new Pair<>(i, i+1 <= order ? i+1 : i+1-order), (i-1) * (order-1));
			for (int j = 2 ; j < order ; j++) {
				indexes.put(new Pair<>(i, i + j <= order ? i + j : i + j - order), (i - 1) * (order - 1) + (j - 1));
			}
		}

		//initialize nodes
		//entered predicates here are defaults ones and not necessary the used ones later.
		for (int i = 0 ; i < size ; i++) {
			nodes[i] = new MultiPredicateNode(i, false, Functions.biPredicates[0]);
		}

		//arcs
		for (int i = 1 ; i <= order ; i++) {
			int index = indexes.get(new Pair<>(i, i));

			//internal
			this.addEdge(index, index+1);
			this.addEdge(index+1, index);

			//external
			for (int j = 1 ; j <= order ; j++) {
				if (j != i) {
					this.addEdge(index, indexes.get(new Pair<>(j, i)));
				}
			}
		}
	}

	/**
	 * Copy constructor.
	 *
	 * @param kStarDiGraph the K* digraph to copy.
	 */
	public KStarDiGraph(KStarDiGraph kStarDiGraph) {
		this(kStarDiGraph.order);
	}

	/**
	 * Returns the order of the K* digraph.
	 *
	 * @return the order of the K* digraph.
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Set the value of each node based on the binary representation of an integer.
	 *
	 * @param entry the integer on which the values of the nodes will be based on.
	 */
	public void setValues(int entry) {
		String binaryEntry = Integer.toBinaryString(entry);
		int i = 0;
		for ( ; i < binaryEntry.length() ; i++) {
			this.changeValueOf(i+1, i+1, binaryEntry.charAt(binaryEntry.length() - 1 - i) - '0' == 1);
		}
		for( ; i < order ; i++) {
			this.changeValueOf(i+1, i+1, false);
		}
	}

	/**
	 * Set the function of each node based on the representation of an integer in radix equals to the number of individual functions available.
	 *
	 * @param functions the integer of which the functions of the nodes will be based on.
	 */
	public void setFunctions(BigInteger functions) {
		String entry = functions.toString(Functions.biPredicates.length);
		int i = 0;
		for ( ; i < entry.length() ; i++) {
			this.changeFunctionOf(i, Functions.biPredicates[entry.charAt(i) - '0']);
		}
	}

	/**
	 * Change the value of a node based on its coordinates form.
	 *
	 * @param x x coordinate of the node.
	 * @param y y cooridnate of the node.
	 * @param newValue new value of the node.
	 */
	public void changeValueOf(int x, int y, boolean newValue) {
		int intKey = indexes.get(new Pair<>(x, y));
		this.changeValueOf(intKey, newValue);
	}

	/**
	 * Change the function of a node based on its id.
	 *
	 * @param i the id of the selected node.
	 * @param function the new function to apply to the node.
	 */
	public void changeFunctionOf(int i, MultiPredicate function) {
		((MultiPredicateNode)this.nodes[i]).setFunction(function);
	}

	/**
	 * Update the values of all nodes of the graph.
	 * The updating process of each node is based on its assigned function and the values of its parents.
	 */
	public void updateAllValues() {
		Queue<Node> todo = new LinkedList<>();
		for (int i = 0 ; i < order ; i++) {
			todo.add(nodes[i*(order-1)].getAdjacents().get(0));
		}
		while (!todo.isEmpty()) {
			Node node = todo.poll();
			List<Node> parents = node.getParents();

			if (parents.size() == 2) { // indegree = 2, not representing
				node.updateValue(parents.get(0).getValue(), parents.get(1).getValue());
			} else {
				throw new UnsupportedOperationException("Node " + node.getId() + " cannot be updated because it has " + parents.size() + "parents (expected 1 or 2).");
			}
		}
	}

	/**
	 * Evaluates whether or not the graph was in a fixed point configuration.
	 * Such a configuration happens if none of the representing nodes ((i,i); i in [1, order]) are changing values after update.
	 *
	 * @return true if the graph was in a fixed point configuration.
	 */
	public boolean isFixedPoint() {
		for (int i = 0 ; i < order ; i++) {
			Node node = nodes[i*(order-1)];
			boolean value = node.getValue();
			node.updateValue(node.getParents().get(0).getValue(), node.getParents().get(1).getValue());
			if (value != node.getValue()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Evaluates whether or not the given value is a fixed point on the graph.
	 *
	 * @param value the value to test.
	 * @return true if the value is a fixed point.
	 */
	public boolean isFixedPoint(int value) {
		this.setValues(value);
		this.updateAllValues();
		return this.isFixedPoint();
	}
}
