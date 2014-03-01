import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 * <p/>
 * <!--//# BEGIN TODO Name, group id, and date-->
 * <p><font color="red"><b>Ilya Trofimov, 272, 10/09/2013</b></font></p>
 * <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class StatisticsTest {

    /**
     * Subject Under Test.
     */
    final static Statistics SUT = null;

    /**
     * Desired accuracy.
     */
    final static double EPSILON = 1.0e-18;

    //  # BEGIN TODO Test cases to test "good weather" functionality
    @Test
    public void testReset() {
        SUT.reset();
        SUT.update(1);
        SUT.reset();
        assertEquals("result", 0, SUT.getCount());
    }

    @Test
    public void testGetCount0() {
        SUT.reset();
        assertEquals("result", 0, SUT.getCount());
    }

    @Test
    public void testGetCount1() {
        SUT.reset();
        SUT.update(2);
        assertEquals("result", 1, SUT.getCount());
    }

    @Test
    public void testGetCount() {
        SUT.reset();
        SUT.update(2);
        SUT.update(1);
        SUT.update(4);
        SUT.update(3);
        assertEquals("result", 4, SUT.getCount());
    }

    @Test
    public void testGetMinimum() {
        SUT.reset();
        SUT.update(2);
        SUT.update(1);
        SUT.update(3);
        assertEquals("result", 1, SUT.getMinimum());
    }

    @Test
    public void testGetMinimum2() {
        SUT.reset();
        SUT.update(2);
        SUT.update(9);
        SUT.update(3);
        assertEquals("result", 2, SUT.getMinimum());
    }

    @Test
    public void testGetMinimumMINMAX() {
        SUT.reset();
        SUT.update(Integer.MAX_VALUE);
        SUT.update(Integer.MIN_VALUE);
        SUT.update(3);
        assertEquals("result", Integer.MIN_VALUE, SUT.getMinimum());
    }

    @Test
    public void testGetMinimumMAX() {
        SUT.reset();
        SUT.update(Integer.MAX_VALUE);
        SUT.update(Integer.MAX_VALUE);
        assertEquals("result", Integer.MAX_VALUE, SUT.getMinimum());
    }

    @Test
    public void testGetMaximum() {
        SUT.reset();
        SUT.update(1);
        SUT.update(3);
        SUT.update(2);
        assertEquals("result", 3, SUT.getMaximum());
    }

    @Test
    public void testGetMaximumMINMAX() {
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        SUT.update(Integer.MAX_VALUE);
        SUT.update(3);
        assertEquals("result", Integer.MAX_VALUE, SUT.getMaximum());
    }

    @Test
    public void testGetMaximumMIN() {
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        SUT.update(Integer.MIN_VALUE);
        assertEquals("result", Integer.MIN_VALUE, SUT.getMaximum());
    }

    @Test
    public void testGetMean() {
        SUT.reset();
        SUT.update(2);
        SUT.update(10);
        assertEquals("result", 6, SUT.getMean(), EPSILON);
    }

    @Test
    public void testGetMeanMINMAX() {
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        SUT.update(Integer.MAX_VALUE);
        assertEquals("result", -0.5, SUT.getMean(), EPSILON);
    }

    @Test
    public void testGetMeanMIN() {
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        SUT.update(Integer.MIN_VALUE);
        assertEquals("result", Integer.MIN_VALUE, SUT.getMean(), EPSILON);
    }

    @Test
    public void testGetMeanMAX() {
        SUT.reset();
        SUT.update(Integer.MAX_VALUE);
        SUT.update(Integer.MAX_VALUE);
        assertEquals("result", Integer.MAX_VALUE, SUT.getMean(), EPSILON);
    }

    @Test
    public void testGetMeanMAX2() {
        SUT.reset();
        SUT.update(Integer.MAX_VALUE);
        SUT.update(Integer.MAX_VALUE);
        assertEquals("result", Integer.MAX_VALUE, SUT.getMean(), EPSILON);
    }

//# END TODO

    //# BEGIN TODO Test cases to test for robustness ("bad weather")
    @Test(expected = IllegalStateException.class)
    public void testExGetMin() {
        SUT.reset();
        SUT.getMinimum();
    }

    @Test(expected = IllegalStateException.class)
    public void testExGetMinWithReset() {
        SUT.reset();
        SUT.update(2);
        SUT.reset();
        SUT.getMinimum();
    }

    @Test(expected = IllegalStateException.class)
    public void testExGetMax() {
        SUT.reset();
        SUT.getMaximum();
    }

    @Test(expected = IllegalStateException.class)
    public void testExGetMaxWithReset() {
        SUT.reset();
        SUT.update(2);
        SUT.reset();
        SUT.getMaximum();
    }

    @Test(expected = IllegalStateException.class)
    public void testExGetMaxWithReset1() {
        SUT.reset();
        SUT.update(3);
        SUT.reset();
        SUT.getMaximum();
    }

    @Test(expected = IllegalStateException.class)
    public void testExGetMean() {
        SUT.reset();
        SUT.getMean();
    }

    @Test(expected = IllegalStateException.class)
    public void testExGetMean1() {
        SUT.reset();
        SUT.update(3);
        SUT.reset();
        SUT.getMean();
    }

    @Test(expected = IllegalStateException.class)
    public void testExGetMeanWithReset() {
        SUT.reset();
        SUT.update(2);
        SUT.reset();
        SUT.getMean();
    }
//# END TODO
}
