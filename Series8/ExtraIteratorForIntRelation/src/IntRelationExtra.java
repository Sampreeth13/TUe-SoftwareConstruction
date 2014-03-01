/**
 * Interface for extra operation(s) on {@link IntRelation}.
 *
 * @author Tom Verhoeff (TU/e)
 */
public interface IntRelationExtra {

    /**
     * Returns an iterable that can be used to iterate over
     * all integers that are related to a given first integer.
     *
     * @param a  first integer, to iterate over all related second integers
     * @return iterable over all {@literal int b} such that
     *     {@literal this.areRelated(a, b)}
     * @throws IllegalArgumentException  if precondition violated
     * @pre {@code 0 <= a < this.extent()}
     * @modifies None
     */
    public Iterable<Integer> relatedToFirst(int a);

}
