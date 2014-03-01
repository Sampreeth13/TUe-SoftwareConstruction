/**
 * A generic record type to hold a related pair of {@code A} and {@code B}
 * objects.
 * This is used by iterators over a {@link Relation}.
 *
 * @param <A>  type of first element in a pair
 * @param <B>  type of second element in a pair
 *
 * @author Tom Verhoeff (TU/e)
 */
public class Pair<A, B> {

    /** First element of pair. */
    public A a;

    /** Second element of pair. */
    public B b;

    /**
     * Constructs a pair for two given integers.
     *
     * @param a  first element of pair
     * @param b  second element of pair
     */
    public Pair(final A a, final B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ")";
    }
}
