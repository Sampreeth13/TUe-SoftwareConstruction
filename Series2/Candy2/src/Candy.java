/**
 * k kids together receive c candies.
 * They wonder whether it is possible
 * to divide all candies fairly.
 *
 * If this is possible, they also want to know
 * how many candies each of them gets.
 * This is a number q such that c == q * k .
 *
 * k, c, and q are non-negative integers, less than 10^18.
 *
 <!--//# BEGIN TODO: Name, group, and date-->
 <p><font color="red"><b>Ilya Trofimov, 272, 09/15/13 </b></font></p>
 <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class Candy {

    /**
     * Returns a negative value to indicate
     * that the division is impossible, and otherwise,
     * returns the number of candies each kid gets.
     */
    static long divide(long k, long c) {
        long result; // value to be returned

        if (c == 0 || k == 0 & c == 0) {
            result = 0;
        } else if (k == 0 || c % k != 0) {
            result = -1;
        } else {
            result = c / k;
        }

        return result;
    }
}


