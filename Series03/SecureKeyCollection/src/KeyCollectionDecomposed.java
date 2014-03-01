/**
 * Concrete class for homework assignment 3 in Series 3,
 * where you provide a functional decomposition of {@code isSecure()}.
 * <p>
 * Write your code in this file between the lines marked by
 * //# BEGIN TODO ... and //# END TODO (do NOT remove these markers).
 * <p>
<!--//# BEGIN TODO: Name, id, and date-->
<p><font color="red"><b>Trofimov Ilya 272 24.09.2013</b></font></p>
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class KeyCollectionDecomposed extends AbstractKeyCollection {

    @Override
    public  boolean isSecure(int[][][] keys)
    {
//# BEGIN TODO: Functional decomposition; the top-level method
        for (int[][] k1 : keys) {
            for (int[][] k2 : keys) {
                if(k1 != k2 && CK(k1, k2)) {
                    return false;
                }
            }
        }
        return true;
//# END TODO
    }
    
//# BEGIN TODO: Functional decomposition; auxiliary method(s)
    /**
     * Indicates whether it is possible to convert the first row
     * into the second row by decreasing its elements
     *
     * @param row1 first row
     * @param row2 second row
     * @return boolean value which shows can be {@code row1} converted into {@code row2} or not
     * @pre {@code row1 != null && row2 != null}
     * @post {@code \result == false && ((\exists i; row1[i] < row2[i] ) || row1.length == row2.length ) ||
     * \result == true && (\forall i; i < row1.length; row1[i] >= row2[i] }
     */
    public boolean CR(int[] row1, int[] row2) {
        {
            if (row1.length != row2.length) {
                return false;
            }

            for (int i = 0; i < row1.length; ++i) {
                if (row1[i] > row2[i]) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * Indicates whether it is possible to convert the first key
     * into the second key
     *
     * @param key1 first key
     * @param key2 second key
     * @return boolean value which shows can be {@code key1} converted into {@code key2} or not
     * @pre {@code key1 != null && key2 != null && isKey(key1) && isKey(key2)}
     * @post {@code\result == true && (\forall i;i < key1.length; CR(key1[i], key2[i])) ||
     * \result == false && (\exists i; !CR(key1[i], key2[i])) }
     */
    public boolean CK(int[][] key1, int[][] key2) {
       for (int r = 0; r < N_ROWS; r++) {
           if (!CR(key1[r], key2[r])) {
               return false;
           }
       }
       return true;
    }
}
//# END TODO
