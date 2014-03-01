/**
 * Illustrates Test-Driven Development of a single method.
 *
<!--//# BEGIN TODO Name, group id, and date-->
<p><font color="red"><b>Ilya Trofimov, 272, 09/30/2013</b></font></p>
<!--//# END TODO -->
*/
// -----8<----- cut line -----8<-----
public class TDDForCountDigitsMethod {

    /**
     * Counts the digits of a number.
     * This concerns a non-negative integer, assumed to be
     * written in positional notation without leading zeroes.
     *
<!--//# BEGIN TODO Contract-->
<p><font color="red"><b>
     * @param n  the number whose digits are counted
     * @param r  the radix of the number {@code n}
     * @return  the number of digits in {@code n}
     * @pre {@code 0 <= n && r >= 2}
     * @post {@code \result = (\min int k; 1 <= k; n < 10 ˆ k)}
</b></font></p>
<!--//# END TODO-->
     */
    public static int countDigits(long n, long r) {
//# BEGIN TODO Implementation
        if (n < 0) {
            throw new IllegalArgumentException("Argument n must be >= 0");
        }
        if (r < 2) {
            throw new IllegalArgumentException("Argument r must be >= 2");
        }

        int result = 1;
        while (r <= n) {
            n /= r;
            ++ result;
        }

        return result;
//# END TODO
    }
}
