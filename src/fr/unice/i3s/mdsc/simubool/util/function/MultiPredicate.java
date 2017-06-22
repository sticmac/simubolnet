package fr.unice.i3s.mdsc.simubool.util.function;

@FunctionalInterface
public interface MultiPredicate {
	boolean test(boolean... booleans);
}
