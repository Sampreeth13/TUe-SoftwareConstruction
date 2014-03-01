import java.util.List;
import java.util.ArrayList;

/**
 * Library with static mathematical functions.
 * <p/>
 * <!--//# BEGIN TODO Name, group id, and date-->
 * <p><font color="red"><b>Ilya Trofimov, 272, 09/30/2013</b></font></p>
 * <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public abstract class MathStuff {

    /**
     * Returns exponentiation, taking care of overflow.
     *
     * @param a the base
     * @param b the exponent
     * @return {@code a ^ b} if {@code a ^ b &lt;= Integer.MAX_VALUE}
     * else Long.MAX_VALUE
     * @throws IllegalArgumentException if {@code b &lt; 0}
     * @pre {@code 0 &lt;= b}
     */
    public static long power(int a, int b) {
        if (b < 0) {
            throw new IllegalArgumentException("power: negative exponent");
        }
        // 0 <= b
        long x = a; // see invariant
        int k = b; // see invariant
        long result = 1L; // see invariant

        // invariant: 0 <= k <= b && result * x^k == a^b
        while (k != 0) {
            if (k % 2 == 0) { // even exponent
                if (x <= Integer.MAX_VALUE) {
                    x *= x;
                } else {
                    x = Long.MAX_VALUE;
                }
                k /= 2;
            } else { // odd exponent
                if (result <= Integer.MAX_VALUE && x <= Integer.MAX_VALUE) {
                    result *= x;
                } else {
                    result = Long.MAX_VALUE;
                }
                k -= 1;
            }
            // invariant holds again, k has decreased
        }
        // k == 0, hence result == a^b

        if (result > Integer.MAX_VALUE) {
            return Long.MAX_VALUE;
        }

        return result;
    }

    /**
     * Record containing a base and an exponent.
     *
     * @inv {@code 0 &lt;= base && 0 &lt;= exponent}
     */
    public static class Power {

        /**
         * The base.
         */
        public int base;

        /**
         * The exponent.
         */
        public int exponent;

        /**
         * Constructs a Power with given base and exponent.
         *
         * @param base     the base
         * @param exponent the exponent
         * @pre {@code 0 &lt;= base && 0 &lt;= exponent}
         * @post {@code \result.base == base && \result.exponent == exponent}
         */
        public Power(int base, int exponent) {
            this.base = base;
            this.exponent = exponent;
        }

    }

    /**
     * Returns exponentiation.
     *
     * @param p the base and exponent
     * @return {@code power(p.base, p.exponent)}
     */
    public static long power(Power p) {
        return power(p.base, p.exponent);
    }

    /**
     * Write a number as a power with maximal exponent.
     *
     * @param n the number to 'powerize'
     * @pre {@code 2 &lt;= n}
     * @post {@code n == power(\result) &&
     * (\forall int b, int e;
     * 2 &lt;= b && 1 &lt;= e && n == b ^ e;
     * e &lt;= \result.exponent)}
     */
    public static Power powerize(int n) {
//# BEGIN TODO Implementation of powerize
        List<Power> multipliers = fact(n);

        int e = calculateGcd(multipliers);
        multipliers = divideMultipliers(multipliers, e);
        int b = defact(multipliers);

        return new Power(b, e);
//# END TODO
    }

//# BEGIN TODO Contracts and implementation of auxiliary functions.

    /**
     * Factorize a number to list of Powers
     *
     * @param n number
     * @return List of powers
     * @pre {@code n >= 2}
     * @post {@code \result.size() >= 1 }
     */
    public static List<Power> fact(int n) {
        List<Power> listWithSimpleBases = new ArrayList<Power>();
        int multiplier = 3;
        int exp = 0;

        while ((n & 1) == 0) {
            n >>= 1;
            exp++;
        }

        if (exp > 0) {
            listWithSimpleBases.add(new Power(2, exp));
        }

        exp = 0;

        while (multiplier * multiplier <= n) {
            while (n % multiplier == 0) {
                n /= multiplier;
                exp++;
            }
            if (exp > 0) {
                listWithSimpleBases.add(new Power(multiplier, exp));
            }
            exp = 0;
            multiplier += 2;
        }

        if (n > 1) {
            listWithSimpleBases.add(new Power(n, 1));
        }

        return listWithSimpleBases;
    }

    /**
     * Defactorize a number from list of powers
     *
     * @param list multipliers
     * @return multiplication of all elements
     * @pre {@code list.size() > 0}
     * @post {@code (\forall }
     */
    public static int defact(List<Power> list) {
        int result = 1;
        for (Power aList : list) {
            result *= power(aList.base, aList.exponent);
        }

        return result;
    }

    /**
     * Divids each element's exponent on gcd
     *
     * @param list multipliers
     * @param cDiv greatest common divisor
     * @return list of divided multipliers
     * @pre {@code \forall i; list[i] > 0}
     * @post {@code \forall i; list[i] > 0}
     */
    public static List<Power> divideMultipliers(List<Power> list, int cDiv) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).exponent /= cDiv;
        }

        return list;
    }


    /**
     * Calculates greatest common divisor for list
     *
     * @param list multipliers
     * @return greatest common divisor for input list
     * @pre {@code \forall i; list[i] > 0}
     * @post {@code \forall i; list[i] > 0}
     */
    public static int calculateGcd(List<Power> list) {
        int result = list.get(0).exponent;
        for (int i = 1; i < list.size(); i++) {
            result = gcd(result, list.get(i).exponent);
        }

        return result;
    }

    /**
     * Calculate greatest common divisor for two numbers
     *
     * @param x first number
     * @param y second number
     * @return greatest common divisor for two numbers
     * @pre {@code x >= 1 && y >= 1}
     * @post {@code \result >= 1}
     */
    public static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }

        return gcd(y, x % y);
    }
//# END TODO
}