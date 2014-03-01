import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Class to illustrate Test-Driven Development of an Abstract Data Type.

/**
 * An implementation of {@code IntRelation} using a List of Sets.
 * For sparse relations with a large extent, this reduces memory usage.
 * However, there is a bit of performance overhead.
 *
 * @author Tom Verhoeff (TU/e)
 */

/**
 * <p><font color="red"><b>Ilya Trofimov, 272, Nov 6</b></font></p>
 */
//-----8<----- cut line -----8<-----

public class IntRelationListOfSets extends IntRelation {

    /** Representation of the relation. */
    protected List<Set<Integer>> relation;

    /*
     * Representation invariants
     * 
     * NotNull: relation != null
     * ElementsNotNull: (\forall i; relation.has(i); relation.get(i) != null),
     *     where List.has(i) == 0 <= i < List.size()
     * ElementsInExtent: (\forall i; relation.has(i);
     *     relation.get(i) subset of [0 .. relation.size()))
     *
     * Abstraction function: set of (a, b) such that
     *     relation.get(a).contains(b) holds
     */

    public IntRelationListOfSets(final int n) {
        super(n);
        relation = new ArrayList<Set<Integer>>();

        for (int i = 0; i != n; ++ i) {
            relation.add(new HashSet<Integer>());
        }
    }

    @Override
    public boolean isRepOk() {
        if (relation == null) {
            throw new IllegalStateException("relation == null");
        }
        for (int i = 0; i != relation.size(); ++ i) {
            final Set<Integer> set = relation.get(i);
            if (set == null) {
                throw new IllegalStateException(
                        "relation.get(" + i + ") == null");
            }
            for (int a : set) {
                if (! (0 <= a && a < relation.size())) {
                    throw new IllegalStateException(
                            "relation.get(" + i + ") contains " + a);
                }
            }
        }
        return true;
    }
    
    @Override
    public int extent() {
        return relation.size();
    }

    @Override
    public boolean areRelated(int a, int b) throws IllegalArgumentException {
        if (!(isValidPair(a, b))) {
            throw new IllegalArgumentException("areRelated(" + a + ", " + b +
                    ") violates precondition");
        }

        return relation.get(a).contains(b);
    }

    @Override
    public void add(int a, int b) throws IllegalArgumentException {
        if (!(isValidPair(a, b))) {
            throw new IllegalArgumentException("add(" + a + ", " + b +
                    ") violates precondition");
        }

        relation.get(a).add(b);
    }

    @Override
    public void remove(int a, int b) throws IllegalArgumentException {
        if (!(isValidPair(a, b))) {
            throw new IllegalArgumentException("remove(" + a + ", " + b +
                    ") violates precondition");
        }

        relation.get(a).remove(b);
    }
}
