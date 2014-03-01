import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Abstract test cases for {@link Relation}, to be extended to obtain
 * concrete test cases for an implementation of {@link Relation}.
 * Here we take {@code A = String} and {@code B = Integer}.
 *
 * @author Tom Verhoeff (TU/e)
 */
public abstract class RelationTestCases {

    /** Test fixture. */
    protected Relation<String, Integer> instance;

    /**
     * Sets instance to a newly constructed relation.
     * Cf. Factory Method design pattern.
     */
    protected abstract void setInstance();

    @Before
    public void setUp() {
        setInstance();
    }

    /** Tests the constructor with small values. */
    @Test(timeout = 10)
    public void testConstructor() {
        System.out.println("constructor");
        setInstance();
        assertTrue("isRepOk()", instance.isRepOk());
    }

    /**
     * Invokes areRelated(a, b) and checks the result.
     *
     * @param a  first element in pair
     * @param b  second element in pair
     * @param expResult  expected result
     */
    private void checkAreRelated(String a, int b, boolean expResult) {
        boolean result = instance.areRelated(a, b);
        assertEquals("areRelated(" + a + ", " + b + ")", expResult, result);
        assertTrue("isRepOk()", instance.isRepOk());
    }

    /** Tests the areRelated method on empty relation. */
    @Test(timeout = 10)
    public void testAreRelated() {
        System.out.println("areRelated");
        checkAreRelated("a0", 0, false);
        checkAreRelated("a0", 1, false);
        checkAreRelated("a1", 0, false);
        checkAreRelated("a1", 1, false);
    }

    /** Tests the add method. */
    @Test(timeout = 10)
    public void testAdd() {
        System.out.println("add");
        instance.add("a0", 1); // N.B. not a pair of equals
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated("a0", 1, true);
        checkAreRelated("a0", 0, false);
        checkAreRelated("a1", 0, false);
        checkAreRelated("a1", 1, false);
        instance.add("a0", 1); // already present
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated("a0", 1, true);
        checkAreRelated("a0", 0, false);
        checkAreRelated("a1", 0, false);
        checkAreRelated("a1", 1, false);
    }

    /** Tests the remove method. */
    @Test(timeout = 10)
    public void testRemove() {
        System.out.println("remove");
        instance.remove("a0", 1); // N.B. not a pair of equals
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated("a0", 0, false);
        checkAreRelated("a0", 1, false);
        checkAreRelated("a1", 0, false);
        checkAreRelated("a1", 1, false);
        instance.add("a0", 1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated("a0", 1, true);
        instance.remove("a0", 1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated("a0", 0, false);
        checkAreRelated("a0", 1, false);
        checkAreRelated("a1", 0, false);
        checkAreRelated("a1", 1, false);
    }

    /**
     * Checks the iterable, given its number of related pairs.
     *
     * @param size  the number of related pairs in {@code iterable}
     */
    private void checkIteration(int size) {
        final Relation<String, Integer> visited;
        visited = new RelationMapOfSets<String, Integer>();
        // inv: visited contains all pairs visited by the iterator
        // inv: size == number of pairs still to be visited
        for (Pair<String, Integer> pair : instance) {
            assertTrue("areRelated" + pair,
                    instance.areRelated(pair.a, pair.b));
            assertFalse("Not yet visited (size == " + size + ") " + pair,
                    visited.areRelated(pair.a, pair.b));
            visited.add(pair.a, pair.b);
            -- size;
        }
        assertEquals("Number of unvisited pairs", 0, size);
    }

    /** Tests the iterator on an empty relation. */
    @Test
    public void testEmptyIteration() {
        System.out.println("iterate over [ ]");
        checkIteration(0);
    }

    /** Tests the iterator on a full singleton relation. */
    @Test
    public void testSingletonIteration() {
        System.out.println("iterate over [ (0,0) ]");
        instance.add("a0", 0);
        checkIteration(1);
    }

    /** Tests the iterator on a small relation.  All gaps <= 1. */
    @Test
    public void testSmallIteration() {
        System.out.println("iterate over [ (0,1), (1,0) ]");
        instance.add("a0", 1);
        instance.add("a1", 0);
        checkIteration(2);
    }

    /** Tests the iterator on a small relation.  A gap > 1. */
    @Test
    public void testSmall2Iteration() {
        System.out.println("iterate over [ (0,0), (1,1) ]");
        instance.add("a0", 0);
        instance.add("a1", 1);
        checkIteration(2);
    }

    /**
     * Tests the iterator on a larger relation, in particular,
     * with multiple elements per row.
     */
    @Test
    public void testLargerIteration() {
        System.out.println("iterate over larger relation");
        final int N = 3;
        for (int a = 0; a != N; ++ a) {
            for (int b = 0; b != N; ++ b) {
                if (a != b) {
                    instance.add("a" + a, b);
                }
            }
        }
        checkIteration(N * (N - 1));
    }

    /** Tests next() for exception */
    @Test(expected = NoSuchElementException.class)
    public void testNextException() {
        System.out.println("next exception");
        final Iterator<Pair<String, Integer>> iter = instance.iterator();
        iter.next();
    }

    /** Tests that iterator remove is not implemented. */
    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        System.out.println("iterator remove exception");
        final Iterator<Pair<String, Integer>> iter;
        iter = instance.iterator();
        iter.remove();
    }

    /**
     * Checks instance, given its number of related second integers.
     *
     * @param size  the number of related second integers in {@code r2f}
     */
    private void checkIteration(String a, int size) {
        final Set<Integer> visited;
        visited = new HashSet<Integer>(); // could write HashSet<>
        // inv: visited contains all pairs visited by the iterator
        // inv: size == number of pairs still to be visited
        for (int b : instance.relatedToFirst(a)) {
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
        checkIteration("a0", 0);
        instance.add("a0", 0);
        checkIteration("a0", 1);
    }

    /** Tests the iterator with gaps <= 1. */
    @Test
    public void testGap1Iteration() {
        System.out.println("iterate over [ ], [ 1 ], [ 0 ], [ 0, 1 ]");
        checkIteration("a0", 0);
        instance.add("a0", 1);
        instance.add("a1", 0);
        checkIteration("a0", 1);
        checkIteration("a1", 1);
        instance.add("a0", 0);
        checkIteration("a0", 2);
    }

    /** Tests the iterator with gaps > 1. */
    @Test
    public void testLargerGapIteration() {
        System.out.println("iterate over [ 0 ], [ 1 ], [ 2 ], [ 3 ], [ 0, 3 ]");
        final int extent = 4;
        for (int i = 0; i != extent; ++ i) {
            final String a = "a" + i;
            instance.add(a, i);
            checkIteration(a, 1);
        }
        instance.add("a0", extent - 1);
        checkIteration("a0", 2);
    }

}
