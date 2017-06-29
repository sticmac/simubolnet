package fr.unice.i3s.mdsc.simubool.util.function;

public class Functions {
	public static final MultiPredicate[] predicates = {
			booleans -> booleans[0],
			booleans -> !booleans[0]
	};

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
