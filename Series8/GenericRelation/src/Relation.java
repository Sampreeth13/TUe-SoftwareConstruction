/**
 * Interface for a generic relation (in the mathematical sense of a set of
 * pairs) between a collection of objects of type {@code A}
 * and a collection of objects of type {@code B}.
 * The collections themselves (domain and range) are implicit,
 * as opposed to the explicit extent of {@see IntRelation}.
 * <p>
 * The relation is mutable.
 * It starts out empty, and can be added to and removed from.
 *
 * @param <A>  type of first elements in relation
 * @param <B>  type of second elements in relation
 * 
 * @author Tom Verhoeff (TU/e)
 */
public interface Relation<A, B> extends Iterable<Pair<A, B>>{

    /**
     * Checks whether the representation invariants hold.
     * For testing purposes only.
     *
     * @return whether the representation invariants hold
     * @throws IllegalStateException  if precondition violated
     * @pre representation invariants hold
     * @modifies None
     * @post {@code \result}
     */
    public abstract boolean isRepOk() throws IllegalStateException;

    /**
     * Returns whether the elements in a pair are related.
     *
     * @param a  first element of the pair
     * @param b  second element of the pair
     * @return  whether {@code (a, b)} are related
     * @modifies None
     * @post {@code \result == (a, b) in this}
     */
    boolean areRelated(A a, B b);

    /**
     * Adds a pair to the relation.
     *
     * @param a  first element of the pair to add
     * @param b  second element of the pair to add
     * @modifies {@code this}, but not {@code this.extent()}
     * @post {@code this == \old(this) union [ (a, b) ]}
     */
    void add(A a, B b);
    /**
     * Removes a pair from the relation.
     *
     * @param a  first element of the pair to remove
     * @param b  second element of the pair to remove
     * @modifies {@code this}
     * @post {@code this == \old(this) minus [ (a, b) ]}
     */
    void remove(A a, B b);

    /**
     * Returns an iterable that can be used to iterate over
     * all integers that are related to a given first integer.
     *
     * @param a  first integer, to iterate over all related second integers
     * @return iterable over all {@code B b} such that
     *     {@code this.areRelated(a, b)}
     * @modifies None
     */
    public Iterable<B> relatedToFirst(A a);

}
