import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 * Test cases for DiceGame Decomposed,
 * especially for robustness.
 * 
<!--//# BEGIN TODO Name, group id, and date-->
<p><font color="red"><b>Ilya Trofimov, 272, 10/09/2013</b></font></p>
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class DiceGameDecomposedTest {
    
    /** Subject Under Test; involves only static methods */
    final static DiceGameDecomposed SUT = null;
    
    /**
     * Calls SUT.count(0, null) when a NullPointerException is expected,
     * and checks the exception.
     */
    @Ignore("Example")
    @Test
    public void testCountNull() {
        System.out.println("count(0, null)");
        Class expected = NullPointerException.class;
        try {
            SUT.count(0, null);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                    + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.count(0, empty) when NO Exception is expected,
     * and checks the absence of an exception (implicitly).
     */
    @Ignore("Example")
    @Test
    public void testCountEmpty() {
        System.out.println("count(0, empty)");
        SUT.count(0, new int[] { });
    }
    
//# BEGIN TODO Test cases to test for robustness (simulate, roll, max, find)
    @Test (expected = IllegalArgumentException.class)
    public void testSimN0() {
        System.out.println("DiceGameDecomposed.simulate(0, r)");
        SUT.simulate(0, 228);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSimNeg() {
        System.out.println("DiceGameDecomposed.simulate(-1, r)");
        SUT.simulate(-1, 228);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSimRNeg() {
        System.out.println("DiceGameDecomposed.simulate(n, -1)");
        SUT.simulate(1, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSimRNeg2() {
        System.out.println("DiceGameDecomposed.simulate(n, -2)");
        SUT.simulate(1, -2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSimR0Neg() {
        System.out.println("DiceGameDecomposed.simulate(0, -1)");
        SUT.simulate(0, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRollK0() {
        System.out.println("DiceGameDecomposed.roll(0)");
        SUT.roll(0);
    }

    @Test (expected = NullPointerException.class)
    public void testMaxNull() {
        System.out.println("DiceGameDecomposed.max(a); a = null");
        int[] a = null;
        SUT.max(a);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaxLen0() {
        System.out.println("DiceGameDecomposed.max(a); a.length = 0");
        int[] a = new int[0];
        SUT.max(a);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaxNeg() {
        System.out.println("DiceGameDecomposed.max(a); a = { 1, 0, -1 } ");
        int[] a = { 1, 0, -1 };
        SUT.max(a);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaxNeg2() {
        System.out.println("DiceGameDecomposed.max(a); a = { 1, 0, -1 } ");
        int[] a = { 1, 0, -2 };
        SUT.max(a);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaxNeg3() {
        System.out.println("DiceGameDecomposed.max(a); a = { 1, 0, -1 } ");
        int[] a = { 1, 0, -3 };
        SUT.max(a);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaxNeg4() {
        System.out.println("DiceGameDecomposed.max(a); a = { 1, 0, -1 } ");
        int[] a = { 1, 0, -4 };
        SUT.max(a);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaxNeg1El() {
        System.out.println("DiceGameDecomposed.max(a); a = { -1 } ");
        int[] a = { -1 };
        SUT.max(a);
    }

    @Test (expected = NullPointerException.class)
    public void testFindNull() {
        System.out.println("DiceGameDecomposed.find(x, a); a = null");
        int[] a = null;
        SUT.find(1, a);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFindNot() {
        System.out.println("DiceGameDecomposed.find(4, a); a = { 1, 2, 3 } ");
        int[] a = { 1, 2, 3 };
        SUT.find(4, a);
    }
//# END TODO
}
