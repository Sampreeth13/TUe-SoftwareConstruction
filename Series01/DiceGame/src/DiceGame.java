/**
 * Consider the following dice game.
 * Five players each roll once per round.
 * Player 1 rolls a (fair) dodecahedron,
 * having 12 faces with the numbers 1 through 12.
 * The other players (2 through 5) roll two fair dice,
 * each having 6 faces with the numbers 1 through 6.
 * The player with the unique highest roll
 * wins the round.  If the highest roll is
 * not unique, then there is no round winner.
 *
 <!--//# BEGIN TODO: Name, id, and date-->
 <p><font color="red"><b>Ilya Trofimov, 272, 09/07/13 </b></font></p>
 <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class DiceGame {

    /** Number of players, >= 1 */
    final static int NUM_PLAYERS = 5;

    /** Index for frequency of rounds without winner */
    final static int NO_WINNER = 0;

    /**
     * Simulates r >= 0 rounds of the dice game and
     * returns how often each player won.
     * The return value is an array, where
     * index 0 counts the number rounds without winner, and
     * index i > 0 counts the number of rounds won by player i.
     */
    static public int[] simulate(int r) {
        int[] result; // winning frequencies
        result = new int[1 + NUM_PLAYERS]; // initialize to 0

        //# BEGIN TODO: Provide your solution

        int maxScore; // Max score in each round
        int winner; // Player, who has max score in round

        for (int i = 0; i < r; i++) {

            int firstPlayerScore = (int)(Math.random() * 12 + 1);

            maxScore = firstPlayerScore;
            winner = 1;

            for (int p = 2; p <= NUM_PLAYERS; p++) {
                int currentPlayerScore = (int)(Math.random() * 6 + 1) + (int)(Math.random() * 6 + 1);
                if (currentPlayerScore > maxScore) {
                    maxScore = currentPlayerScore;
                    winner = p;
                } else if (currentPlayerScore == maxScore) {
                    winner = NO_WINNER;
                    if (maxScore == 12) {
                        break; // There is no any sense to continue round, because 12 score is not unique
                    }
                }
            }

            result[winner]++;
        }

        //# END TODO

        return result;
    }
}