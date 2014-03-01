import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for Kakuro combination generator.
 *
 * @author Tom Verhoeff (TU/e)
 *         <!--//# BEGIN TODO Name, group id, and date-->
 *         <p><font color="red"><b>Ilya Trofimov, 272, Nov 14</b></font></p>
 *         <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class KakuroCombinationGeneratorTest {

    /**
     * The subject under test.
     */
    private KakuroCombinationGenerator instance;

    /**
     * The listener.
     */
    private Checker checker;

    @Before
    public void setUp() {
        instance = new KakuroCombinationGenerator();
        checker = new Checker();
        instance.addListener(checker);
    }

    // Tests of generate method, of class KakuroCombinationGenerator.

    /**
     * Calls {@code KakuroCombinationGenerator.generate(s, n)},
     * and checks the result, expecting {@code t} combinations.
     */
    public void checkGenerator(int s, int n, int t) {
        System.out.println("generate(" + s + ", " + n
                + ") should generate " + t + " combinations");
        checker.set(s, n);
        instance.generate(s, n);
        assertEquals("Number of combinations", t, checker.count);
    }

    /**
     * Boundary case: minimal s that still works.
     */
    @Test
    public void testGeneratorMinimal0() {
        checkGenerator(0, 0, 1);
    }

    /**
     * Boundary case: minimal s that just does not work.
     */
    @Test
    public void testGeneratorMinimal1() {
        checkGenerator(0, 1, 0);
    }

    //# BEGIN TODO Further test cases
    @Test
    public void testGenerator1() {
        checkGenerator(3, 2, 1);
    }

    @Test
    public void testGenerator2() {
        checkGenerator(45, 9, 1);
    }

    @Test
    public void testGenerator3() {
        checkGenerator(21, 4, 11);
    }

    @Test
    public void testGenerator4() {
        checkGenerator(3, 3, 0);
    }

    @Test
    public void testGenerator5() {
        checkGenerator(1, 1, 1);
    }

    @Test
    public void testGenerator6() {
        checkGenerator(9, 2, 4);
    }

    @Test
    public void testGenerator7() {
        checkGenerator(15, 4, 6);
    }

    @Test
    public void testGenerator8() {
        checkGenerator(17, 2, 1);
    }

    @Test
    public void testGenerator9() {
        checkGenerator(17, 3, 7);
    }

    @Test
    public void testGenerator10() {
        checkGenerator(10, 2, 4);
    }
    //# END TODO

    // Auxiliary definitions

    /**
     * Listener that checks that each generated combination
     * has indeed the expected sum and length,
     * and that they appear in lexicographic order.
     */
    private class Checker implements GeneratorListener {

        /**
         * Number of reported combinations.
         */
        public int count;

        /**
         * Expected sum.
         */
        private int s;

        /**
         * Expected number.
         */
        private int n;

        /**
         * Preceding generated combination.
         */
        private Set<Integer> preceding;

        /**
         * Creates a default initialized checker.
         */
        public Checker() {
            this.count = 0;
            this.preceding = null;
        }

        /**
         * Sets the expected sum and length.
         *
         * @param s expected sum
         * @param n expected length
         */
        public void set(int s, int n) {
            this.s = s;
            this.n = n;
        }

        @Override
        public void combinationGenerated(Set<Integer> combination) {
            ++this.count;
            System.out.println(combination);
            assertTrue("Lexicographic", precedes(preceding, combination));
            assertEquals("Sum", s, sum(combination));
            assertEquals("Number", n, combination.size());
            this.preceding = new HashSet<Integer>(combination); // NEEDS COPY!
        }
    }

    /**
     * Returns sum of given set of integers.
     *
     * @param c set of integers, not null
     * @return sum of integers in {@code c}
     */
    private int sum(Set<Integer> c) {
        int result = 0;
        for (int i : c) {
            result += i;
        }
        return result;
    }

    /**
     * Determines whether one integer set lexicographically precedes another.
     * Null precedes every non-null set.
     *
     * @param c first set of integers
     * @param d second set of integers
     * @return whether {@code c} strictly precedes {@code d}
     */
    private boolean precedes(Set<Integer> c, Set<Integer> d) {
        if (c == null | d == null) {
            return c == null && d != null;
        }
        // c != null && d != null
        for (int i = 1; i < instance.getMaxNumber(); ++i) {
            if (c.contains(i) != d.contains(i)) {
                return c.contains(i);
            }
        }
        return false;
    }

}
