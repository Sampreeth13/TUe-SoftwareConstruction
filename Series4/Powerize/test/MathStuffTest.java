import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for MathStuff.
 *
<!--//# BEGIN TODO Name, group id, and date-->
<p><font color="red"><b>Ilya Trofimov, 272, 09/30/2013</b></font></p>
<!--//# END TODO-->
*/
// -----8<----- cut line -----8<-----
public class MathStuffTest {
    // Test cases for power(int, int).

    /**
     * Invokes power(a, b) and checks for expected result.
     *
     * @param a  the base
     * @param b  the exponent
     * @param expResult  the expected result
     * @pre {@code 0 &lt;= b && expResult = a ^ b}
     */
    private void checkPower(int a, int b, long expResult) {
        System.out.println("power(" + a + ", " + b + ")");
        long result = MathStuff.power(a, b);
        assertEquals("result", expResult, result);
    }

    /** Smallest exponent. */
    @Test
    public void testPower0() {
        checkPower(0, 0, 1);
    }

    /** Exponent 1. */
    @Test
    public void testPower1() {
        checkPower(2, 1, 2);
    }

    /** Exponent 2. */
    @Test
    public void testPower2() {
        checkPower(3, 2, 9);
    }

     /** Largest base and smallest exponent without overflow. */
    @Test(timeout = 100)
    public void testPowerSmallestNoOverflow() {
        int n = Integer.MAX_VALUE;
        checkPower(n, 1, n);
    }

    /** Smallest base &gt; 1 and largest exponent without overflow. */
    @Test(timeout = 100)
    public void testPowerLargestNoOverflow() {
        checkPower(2, 30, Integer.MAX_VALUE / 2 + 1);
    }

   /** Largest base and smallest exponent &gt; 1 with overflow. */
    @Test(timeout = 100)
    public void testPowerSmallestOverflow() {
        checkPower(46341, 2, Long.MAX_VALUE);
    }

    /** Smallest base &gt; 1 and smallest exponent with overflow. */
    @Test(timeout = 100)
    public void testPowerLargestOverflow() {
        checkPower(2, 31, Long.MAX_VALUE);
    }

    // Test cases for power(Power)

    /** Smoke test. */
    @Test
    public void testPowerPower() {
        MathStuff.Power p = new MathStuff.Power(3, 2);
        assertEquals("result", 3 * 3, MathStuff.power(p));
    }

    // Test cases for powerize(int)

    /**
     * Invokes powerize(power(expB, expE)) and checks for expected result.
     *
     * @param expB  expected base
     * @param expE  expected exponent
     * @pre {@code expB} is not a power with exponent greater than one
     */
    private void checkPowerize(int expB, int expE) {
        long n = MathStuff.power(expB, expE);
        System.out.println("powerize(" + n + ")");
        MathStuff.Power result = MathStuff.powerize((int)n);
        assertEquals("power", n, MathStuff.power(result));
        assertEquals("base", expB, result.base);
        assertEquals("exponent", expE, result.exponent);
    }

    //# BEGIN TODO Implementations of test cases for powerize(int)
    @Test
    public void testPowerize122() {
        checkPowerize(12, 2);
    }

    @Test
    public void testPowerize26() {
        checkPowerize(2, 6);
    }

    @Test
    public void testPowerize23() {
        checkPowerize(2, 3);
    }

    @Test
    public void testPowerize24() {
        checkPowerize(2, 4);
    }

    @Test
    public void testPowerize35() {
        checkPowerize(3, 5);
    }

    @Test
    public void testPowerize91() {
        checkPowerize(3, 2);
    }

    @Test
    public void testPowerize62() {
        checkPowerize(6, 2);
    }

    @Test
    public void testPowerize53() {
        checkPowerize(5, 3);
    }
    //# END TODO

//# BEGIN TODO Test cases for auxiliary functions
    /**
     * Test gcd(x, y)
     *
     * @param x first number
     * @param y second number
     * @param expResult greatest common divisor {@code x} {@code y}
     */
    private void checkGCD(int x, int y, int expResult) {
        System.out.println("gcd(" + x + ", " + y + ")");
        assertEquals("result", expResult, MathStuff.gcd(x, y));
    }

    @Test
    public void testGCD228() {
        checkGCD(228, 1448, 4);
    }

    @Test
    public void testGCD36() {
        checkGCD(36, 144 , 36);
    }

    @Test
    public void testGCD12() {
        checkGCD(12, 10, 2);
    }

    @Test
    public void testGCD1() {
        checkGCD(1, 1, 1);
    }

    @Test
    public void testGCD3() {
        checkGCD(3, 3, 3);
    }
//# END TODO

}
