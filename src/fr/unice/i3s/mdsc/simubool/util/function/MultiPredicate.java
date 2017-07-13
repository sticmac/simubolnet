package fr.unice.i3s.mdsc.simubool.util.function;

/**
 * Functional Interface.
 * Test as many boolean values as needed to return another boolean value.
 */
@FunctionalInterface
public interface MultiPredicate {
	boolean test(boolean... booleans);
}
