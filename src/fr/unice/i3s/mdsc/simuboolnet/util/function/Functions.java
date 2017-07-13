package fr.unice.i3s.mdsc.simuboolnet.util.function;

/**
 * Sets of needed <code>MultiPredicate</code> stored in static array.
 */
public class Functions {
	public static final MultiPredicate[] biPredicates = {
			booleans -> booleans[0] && booleans[1],
			booleans -> booleans[0] || booleans[1],
			booleans -> !booleans[0] && booleans[1],
			booleans -> booleans[0] && !booleans[1],
			booleans -> !booleans[0] && !booleans[1],
			booleans -> !booleans[0] || booleans[1],
			booleans -> booleans[0] || !booleans[1],
			booleans -> !booleans[0] || !booleans[1]
	};
}
