package fr.unice.i3s.mdsc.simuboolnet.util;

/**
 * Pair of two objects.
 *
 * @param <T> type of the first object.
 * @param <U> type of the second object.
 * @author Julien Lemaire
 */
public class Pair<T, U> {
	private T left;
	private U right;

	/**
	 * Main constructor.
	 *
	 * @param left first object.
	 * @param right second object.
	 */
	public Pair(T left, U right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Returns first object.
	 *
	 * @return the first object.
	 */
	public T getLeft() {
		return left;
	}

	/**
	 * Returns the second object.
	 *
	 * @return the second object.
	 */
	public U getRight() {
		return right;
	}

	/**
	 * Test the equality of two pairs of objects.
	 * Two pairs are equals if they both have equals first and second object.
	 *
	 * @param o the object on which the equality is tested
	 * @return true if this and o have the same first and second object.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pair<?, ?> pair = (Pair<?, ?>) o;

		return left.equals(pair.left) && right.equals(pair.right);
	}

	@Override
	public int hashCode() {
		int result = left.hashCode();
		result = 31 * result + right.hashCode();
		return result;
	}
}
