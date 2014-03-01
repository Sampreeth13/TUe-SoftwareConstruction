import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Abstract test cases for an {@link IntRelation} that implements
 * {@link IntRelationExtra}.  This class is to be extended to obtain
 * concrete test cases for an extension of {@link IntRelation}
 * that implements {@link IntRelationExtra}.
 *
 * @author Tom Verhoeff (TU/e)
 */
public abstract class IntRelationR2FTestCases extends IntRelationTestCases {

    /** Additional test fixture: instance viewed as {@link IntRelationExtra}. */
    protected IntRelationExtra r2f;

    /**
     * Sets {@literal r2f} to a newly constructed relation, of given extent,
     * that implements {@link IntRelationExtra}.
     *
     * @param n   extent
     */
    protected abstract void setR2F(final int n);

    /* In general, to test an iterator, you need to check that
     * each item in the iterable collection is visited exactly once.
     * We do so by maintaining another relation, namely of
     * the already visited pairs.  Initially, it is empty.
     * Every newly visited pair should be unvisited, and is
     * then added to this relation.
     *
     * In rigorous black-box testing of iterators over mutable collections,
     * it would also be necessary to check that the iterator has not modified
     * the collection.  Here, inspection of the implementation code
     * should easily reveal whether this is the case, and we omit
     * those tests.
     */

    /**
     * Checks r2f, given its number of related second integers.
     *
     * @param size  the number of related second integers in {@literal r2f}
     */
    private void checkIteration(int a, int size) {
        final Set<Integer> visited;
        visited = new HashSet<Integer>(); // could write HashSet<>
        // inv: visited contains all pairs visited by the iterator
        // inv: size == number of pairs still to be visited
        for (int b : r2f.relatedToFirst(a)) {
            assertTrue("areRelated(" + a + ", " + b + ")",
                    instance.areRelated(a, b));
            assertFalse("Not yet visited (size == " + size + ") " + b,
                    visited.contains(b));
            visited.add(b);
            -- size;
        }
        assertEquals("Number of unvisited pairs", 0, size);
    }

    /** Tests the iterator on relation of extent 1. */
    @Test
    public void testExtent1Iteration() {
        System.out.println("iterate over [ ] and [ 0 ]");
        setR2F(1);
        checkIteration(0, 0);
        instance.add(0, 0);
        checkIteration(0, 1);
    }

    /** Tests the iterator with gaps <= 1. */
    @Test
    public void testGap1Iteration() {
        System.out.println("iterate over [ ], [ 1 ], [ 0 ], [ 0, 1 ]");
        setR2F(2);
        checkIteration(0, 0);
        instance.add(0, 1);
        instance.add(1, 0);
        checkIteration(0, 1);
        checkIteration(1, 1);
        instance.add(0, 0);
        checkIteration(0, 2);
    }

    /** Tests the iterator with gaps > 1. */
    @Test
    public void testLargerGapIteration() {
        System.out.println("iterate over [ 0 ], [ 1 ], [ 2 ], [ 3 ], [ 0, 3 ]");
        final int extent = 4;
        setR2F(extent);
        for (int i = 0; i != extent; ++ i) {
            instance.add(i, i);
            checkIteration(i, 1);
        }
        instance.add(0, extent - 1);
        checkIteration(0, 2);
    }

    /** Tests the iterator on an empty relation for proper exception. */
    @Test
    public void testEmptyIterationException() {
        System.out.println("relatedToFirst(0) exception");
        setR2F(0);
        Class expected = IllegalArgumentException.class;
        try {
            r2f.relatedToFirst(0);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            System.out.println("  " + e.toString());
            assertTrue("type: " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    // No test for remove; it could be implemented or not

}
