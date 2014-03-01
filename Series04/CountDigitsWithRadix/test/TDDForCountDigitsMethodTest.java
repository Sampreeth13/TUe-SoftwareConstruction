import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test cases for countDigits method, of class TDDForCountDigitsMethod.
 *
 <!--//# BEGIN TODO Name, group id, and date-->
 <p><font color="red"><b>Ilya Trofimov, 272, 09/30/2013</b></font></p>
 <!--//# END TODO -->
 */
// -----8<----- cut line -----8<-----
public class TDDForCountDigitsMethodTest {

    /** Subject Under Test.  Only static members are used. */
    private static final TDDForCountDigitsMethod SUT = null;

    /**
     * Invokes countDigits(n, r) and checks result against expectation.
     *
     * @param n  the number whose digits are counted
     * @param r  the radix
     * @param expResult  the expected result
     */
    private void checkCountDigits(long n, long r, int expResult) {
        System.out.println("countDigits(" + n + ", " + r + ")");
        int result = SUT.countDigits(n, r);
        assertEquals("result", expResult, result);
    }

//# BEGIN TODO Test cases for "good weather" (no exceptions)
    // Test countDigits method with n = 0L and radix 10
    @Test
    public void testCountDigitsDec0() {
        checkCountDigits(0L, 10, 1);
    }

    // Test countDigits method with n = 10L and radix 10
    @Test
    public void testCountDigitsDec10() {
        checkCountDigits(10L, 10, 2);
    }

    // Test countDigits method with n = 4L (100 in binary) and radix 2
    @Test
    public void testCountDigitsBin5() {
        checkCountDigits(4L, 2, 3);
    }

    // Test countDigits method with n = 8L (1000 in binary) and radix 2
    @Test
    public void testCountDigitsBin() {
        checkCountDigits(8L, 2, 4);
    }

    // Test countDigits method with Long.MAX_VALUE and radix 10
    @Test (timeout = 1000)
    public void testCountDigitsLongMaxValue10() {
        checkCountDigits(Long.MAX_VALUE, 10, 19);
    }

    // Test countDigits method with Long.MAX_VALUE and radix 2
    @Test (timeout = 1000)
    public void testCountDigitsLongMaxValue2() {
        checkCountDigits(Long.MAX_VALUE, 2, 63);
    }

    // Test countDigits method with Long.MAX_VALUE and radix 256
    @Test (timeout = 1000)
    public void testCountDigitsLongMaxValue256() {
        checkCountDigits(Long.MAX_VALUE, 256, 8);
    }

    @Test
    public void testCountDigitsLongMaxValue1234() {
        checkCountDigits(1234, 10, 4);
    }

    @Test
    public void testCountDigitsLongMaxValue12345() {
        checkCountDigits(12345, 10, 5);
    }

    @Test
    public void testCountDigitsLongMaxValue123() {
        checkCountDigits(123, 10, 3);
    }
//# END TODO
}
