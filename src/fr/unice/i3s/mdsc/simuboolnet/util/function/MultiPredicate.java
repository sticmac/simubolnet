package fr.unice.i3s.mdsc.simuboolnet.util.function;

/**
 * Functional Interface.
 * Test as many boolean values as needed to return another boolean value.
 */
@FunctionalInterface
public interface MultiPredicate {
	boolean test(boolean... booleans);
}
