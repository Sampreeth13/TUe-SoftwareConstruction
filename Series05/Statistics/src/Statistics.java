/**
 * Class to collect some statistics for
 * a sequence of measurements
 * (<b>Robust version</b>).
 * The measured values can be dice rolls, or scores in a game, etc.
 * The values are offered one by one.
 * At any time, some statistics can be queried.
 * For efficiency reasons, the values are not stored individually.
 * <p/>
 * <p>
 * To write formal contracts, we use
 * <ul>
 * <li> {@code X(i)} for the {@code i}-th measurement
 * with {@code 0 <= i};
 * <li> {@code N} for the number of measurements so far.
 * </ul>
 * <p/>
 * <!--//# BEGIN TODO Name, group id, and date-->
 * <p><font color="red"><b>Ilya Trofimov, 272, 10/09/2013</b></font></p>
 * <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public abstract class Statistics {

    /**
     * The number of values measured
     */
    private static int count = 0;

    /**
     * The minimum of values measured
     */
    private static int minimum = Integer.MAX_VALUE;

    /**
     * The maximum of values measured
     */
    private static int maximum = Integer.MIN_VALUE;

    /**
     * The mean of the values measured
     */
    private static double mean = 0.0;

    /**
     * Resets the statistics gathering process.
     *
     * @pre {@code true}
     * @post {@code N == 0}
     */
    public static void reset() {
        count = 0;
        minimum = Integer.MAX_VALUE;
        maximum = Integer.MIN_VALUE;
        mean = 0.0;
    }

    /**
     * Gets the number of values measured.
     *
     * @return number of values measured
     * @pre {@code true}
     * @post {@code \result == N}
     */
    public static int getCount() {
        return count;
    }

    /**
     * Gets the minimum of values measured.
     *
     * @return minimum value measured
     *         <!--//# BEGIN TODO Contract for exceptions-->
     *         <p><font color="red"><b>@throws IllegalStateException  if {@code N <= 0}
     *         </b></font></p>
     *         <!--//# END TODO-->
     * @pre {@code N > 0}
     * @post {@code \result == (\min i; 0 <= i < N; X(i))}
     */
    public static int getMinimum()
//# BEGIN TODO Declare thrown exceptions
            throws IllegalStateException
//# END TODO
    {
//# BEGIN TODO Implement precondition checking
        if (getCount() <= 0) {
            throw new IllegalStateException(
                    "Statistics.getMinimum()" +
                            " violates precondition: N < 0");
        }
//# END TODO
        return minimum;
    }

    /**
     * Gets the maximum of values measured.
     *
     * @return maximum value measured
     *         <!--//# BEGIN TODO Contract for exceptions-->
     *         <p><font color="red"><b>@throws IllegalStateException  if {@code N <= 0}
     *         </b></font></p>
     *         <!--//# END TODO-->
     * @pre {@code N > 0}
     * @post {@code \result == (\max i; 0 <= i < N; X(i))}
     */
    public static int getMaximum()
//# BEGIN TODO Declare thrown exceptions
            throws IllegalStateException
//# END TODO
    {
//# BEGIN TODO Implement precondition checking
        if (getCount() <= 0) {
            throw new IllegalStateException(
                    "Statistics.getMaximum()" +
                            " violates precondition: N < 0");
        }
//# END TODO
        return maximum;
    }

    /**
     * Gets the mean of values measured.
     *
     * @return mean value measured
     *         <!--//# BEGIN TODO Contract for exceptions-->
     *         <p><font color="red"><b>@throws IllegalStateException  if {@code N <= 0}
     *         </b></font></p>
     *         <!--//# END TODO-->
     * @pre {@code N > 0}
     * @post {@code \result == (\sum i; 0 <= i < N; X(i)) / N}
     */
    public static double getMean()
//# BEGIN TODO Declare thrown exceptions
            throws IllegalStateException
//# END TODO
    {
//# BEGIN TODO Implement precondition checking
        if (getCount() <= 0) {
            throw new IllegalStateException(
                    "Statistics.getMean()" +
                            " violates precondition: N < 0");
        }
//# END TODO
        return mean;
    }

    /**
     * Updates the state with a measured value.
     *
     * @param value the measured value to incorporate
     * @pre {@code true}
     * @modifies {@code count, minimum, maximum, mean}
     * @post {@code count, minimum, maximum, mean} have been updated
     * to incorporate {@code value}
     */
    public static void update(int value) {
//# BEGIN TODO Implementation
        ++count;

        if (value < getMinimum()) {
            minimum = value;
        }

        if (value > getMaximum()) {
            maximum = value;
        }

        mean = getMean() * (getCount() - 1) / getCount() +
                (double) value / getCount();
//# END TODO
    }

}
