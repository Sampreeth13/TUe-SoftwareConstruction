/**
 * <b>Problem</b>: Consider the following dice game.
 * {@code n >= 1} players each roll once per round.
 * Player 1 rolls a (fair) dodecahedron,
 * having 12 faces with the numbers 1 through 12.
 * The other players (2 through {@code N}) roll two fair dice,
 * each having 6 faces with the numbers 1 through 6.
 * The player with the unique highest roll
 * wins the round.  If the highest roll is
 * not unique, then there is no round winner.
 *
 <!--//# BEGIN TODO: Name, group, and date-->
 <p><font color="red"><b>Ilya Trofimov, 272, 09/24/13 </b></font></p>
 <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class DiceGameDecomposed {

    /** Index for frequency of rounds without winner */
    final static int NO_WINNER = 0;

    /**
     * Simulates {@code r >= 0} rounds of the dice game and
     * returns how often each of {@code n >= 1} players has won.
     * The return value is an array, where
     * {@code index 0} counts the number of rounds without winner, and
     * {@code index i > 0} counts the number of rounds won by player {@code i}.
     *
     * @param n number of players
     * @param r number of rounds
     * @return an array where {@code index 0} counts the number of rounds without
     * winner, and {@code index i > 0} counts the number of rounds won by
     * player {@code i}.
     * @pre {@code 1 <= n <= 2^31 - 1 && 0 <= r <= 2^31 - 1}
     * @post {@code int[] \result && \result.length == n + 1}
     */
    static public int[] simulate(int n, int r) {
        int[] result; // winning frequencies
        result = new int[1 + n]; // initialize to 0

        int index;

        for (int i = 0; i < r; i++) {
            index = simulate1round(n);
            result[index]++;
        }

        return result;
    }

    /**
     * Simulates one round of the game for {@code n} players.
     * Returns the winner of the round, or {@code NO_WINNER}
     * if there is no winner.
     *
     * @param n number of players
     * @return an value which shows the winner of the round, or {@code NO_WINNER}
     * if there is no winner.
     * @pre {@code 1 <= n < 2^31 - 1}
     * @post {@code 0 <= \result <= n}
     */
    static int simulate1round(int n) {
        int[] players = getRolls(n);
        return getWinner(players);
    }

    /**
     * Returns array with {@code n} rolls:
     * the first player rolls a dodecahedron;
     * the other players roll two dice.
     * N.B. The roll of player 1 is returned at {@code index 0}.
     *
     * @param n number of players
     * @return array with {@code n} rolls
     * @pre  {@code 1 <= n <= 2^31 - 1}
     * @post {@code int[] \result && \result.length == n}
     */
    static int[] getRolls(int n) {
        int[] result = new int[n];

        result[0] = roll(12);

        for (int i = 1; i < n; i++) {
            result[i] = roll(6) + roll(6);
        }

        return result;
    }

    /**
     * The random generator, used only by {@code }roll()}
     */
    final static java.util.Random random = new java.util.Random();

    /**
     * Rolls {@code k}-sided fair die with values 1 through {@code k}.
     * @param k number of sides and max value of dice
     * @return a value which shows a score of dice
     * @pre {@code 1 <= k <= 2^31 - 1}
     * @post {@code 1 <= \result <= k}
     */
    static int roll(int k) {
        return random.nextInt(k) + 1;
    }

    /**
     * Determine round winner, given the round rolls.
     * @param rolls an array which contains rolls of a round
     * @return a value which contains number of player or NO_WINNER if there is no winner
     * @pre {@code int[] rolls, where \forall rolls[i]: -2^31 <= rolls[i] <= 2^31 - 1}
     * @post {@code -1 <= \result <= rolls.length - 1}
     */
    static int getWinner(int[] rolls) {
        int maxValue = max(rolls);
        if (count(maxValue, rolls) > 1) {
            return NO_WINNER;
        } else {
            return find(maxValue, rolls) + 1;
        }
    }

    /**
     * Returns maximum of a given integer array of nonnegative values.
     * N.B. The array is not empty.
     * @param a an array with any numbers
     * @return maximum of a given integer array of nonnegative values.
     * @pre {@code int[] a, where \forall a[i]: 0 <= a[i] <= 2^31 - 1}
     * @post {@code 0 <= \result <= 2^31 - 1}
     */
    static int max(int[] a) {
        int result = a[0];

        for (int i = 0; i < a.length; i++) {
            if (a[i] >= result) {
                result = a[i];
            }
        }
        return result;
    }

    /**
     * Returns how many times value {@code x} occurs in array {@code a}.
     * @param x value which should be counted
     * @param a an array which contain {@code x}
     * @return how many times value {@code x} occurs in array {@code a}
     * @pre {@code -2^31 <= x <= 2^31 - 1 && \exists x && a.has(x)
     * && int[] a, where \forall a[i]: 0 <= a[i] <= 2^31 - 1}
     * @post {@code 1 <= \result <= a.length}
     */
    static int count(int x, int[] a) {
        int result = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                result++;
            }
        }

        return result;
    }

    /**
     * Finds a value x in array a, given that it occurs.
     * @param x a value which should be found
     * @param a an array which contains {@code x}
     * @return the first index of {@code x} in array {@code a}
     * @pre {@code -2^31 <= x <= 2^31 - 1 && \exists x && a.has(x)
     * && int[] a, where \forall a[i]: 0 <= a[i] <= 2^31 - 1}
     * @post {@code a[\result] == x && 0 <= \result <= a.length - 1}
     */
    static int find(int x, int[] a) {
        int index = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                index = i;
                break;
            }
        }

        return index;
    }
}