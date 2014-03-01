import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Abstract test cases for IntRelation, to be extended to obtain
 * concrete test cases for an extension of IntRelation.
 *
 * @author Tom Verhoeff (TU/e)
 */

/**
 * <p><font color="red"><b>Ilya Trofimov, 272, 10/23/2013</b></font></p>
 */
//-----8<----- cut line -----8<-----

public abstract class IntRelationIterableTestCases {

    /**
     * Test fixture.
     */
    protected Iterable<Pair> instance;

    /**
     * Sets instance to a newly constructed relation of given extent.
     *
     * @param n extent
     */
    protected abstract void setInstance(final int n);

    /**
     * Tests the constructor with small values.
     */
    @Test
    public void testConstructor() {
        System.out.println("constructor(int)");
        for (int n = 0; n <= 3; ++n) {
            setInstance(n);
            assertTrue("isRepOk()", ((IntRelation) instance).isRepOk());
        }
    }

    /**
     * Tests the extent method with small relations.
     */
    @Test
    public void testExtent() {
        System.out.println("extent");
        for (int n = 0; n <= 3; ++n) {
            setInstance(n);
            assertEquals("size", n, ((IntRelation) instance).extent());
            assertTrue("isRepOk()", ((IntRelation) instance).isRepOk());
        }
    }

    /**
     * Invokes areRelated(a, b) and checks the result.
     *
     * @param a         first element in pair
     * @param b         second element in pair
     * @param expResult expected result
     */
    private void checkAreRelated(int a, int b, boolean expResult) {
        boolean result = ((IntRelation) instance).areRelated(a, b);
        assertEquals("areRelated(" + a + ", " + b + ")", expResult, result);
        assertTrue("isRepOk()", ((IntRelation) instance).isRepOk());
    }

    /**
     * Tests the areRelated method on empty relation.
     */
    @Test
    public void testAreRelated() {
        System.out.println("areRelated");
        setInstance(1);
        checkAreRelated(0, 0, false);
        setInstance(2);
        checkAreRelated(0, 0, false);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
    }

    /**
     * Tests the add method.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        setInstance(2);
        ((IntRelation) instance).add(0, 1); // N.B. not a pair of equals
        assertTrue("isRepOk()", ((IntRelation) instance).isRepOk());
        checkAreRelated(0, 1, true);
        checkAreRelated(0, 0, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
        ((IntRelation) instance).add(0, 1);
        assertTrue("isRepOk()", ((IntRelation) instance).isRepOk());
        checkAreRelated(0, 1, true);
        checkAreRelated(0, 0, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
    }

    /**
     * Tests the remove method.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        setInstance(2);
        ((IntRelation) instance).remove(0, 1); // N.B. not a pair of equals
        assertTrue("isRepOk()", ((IntRelation) instance).isRepOk());
        checkAreRelated(0, 0, false);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
        ((IntRelation) instance).add(0, 1);
        assertTrue("isRepOk()", ((IntRelation) instance).isRepOk());
        checkAreRelated(0, 1, true);
        ((IntRelation) instance).remove(0, 1);
        assertTrue("isRepOk()", ((IntRelation) instance).isRepOk());
        checkAreRelated(0, 0, false);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegative() {
        setInstance(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd1() {
        setInstance(1);
        ((IntRelation) instance).add(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd2() {
        setInstance(1);
        ((IntRelation) instance).add(2, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd3() {
        setInstance(1);
        ((IntRelation) instance).add(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd4() {
        setInstance(1);
        ((IntRelation) instance).add(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd5() {
        setInstance(1);
        ((IntRelation) instance).add(0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd6() {
        setInstance(1);
        ((IntRelation) instance).add(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove1() {
        setInstance(1);
        ((IntRelation) instance).remove(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove2() {
        setInstance(1);
        ((IntRelation) instance).remove(2, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove3() {
        setInstance(1);
        ((IntRelation) instance).remove(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove4() {
        setInstance(1);
        ((IntRelation) instance).remove(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove5() {
        setInstance(1);
        ((IntRelation) instance).remove(0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove6() {
        setInstance(1);
        ((IntRelation) instance).remove(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAreRelated1() {
        setInstance(1);
        ((IntRelation) instance).areRelated(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAreRelated2() {
        setInstance(1);
        ((IntRelation) instance).areRelated(2, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAreRelated3() {
        setInstance(1);
        ((IntRelation) instance).areRelated(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAreRelated4() {
        setInstance(1);
        ((IntRelation) instance).areRelated(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAreRelated5() {
        setInstance(1);
        ((IntRelation) instance).areRelated(0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAreRelated6() {
        setInstance(1);
        ((IntRelation) instance).areRelated(0, 1);
    }
}

