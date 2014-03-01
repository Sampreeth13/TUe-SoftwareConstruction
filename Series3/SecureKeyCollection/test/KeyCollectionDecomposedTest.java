import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for functional decomposition in {@code KeyCollectionDecomposed}.
 *
<!--//# BEGIN TODO: Name, id, and date-->
 <p><font color="red"><b>Trofimov Ilya 272 24.09.2013</b></font></p>
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class KeyCollectionDecomposedTest extends AbstractKeyCollectionTestCases
{

    public KeyCollectionDecomposedTest() {

    }

    @Before
    public void setUp() {
        instance = new KeyCollectionDecomposed();
    }

//# BEGIN TODO: Test cases for auxiliary methods

    int[][] key1;
    int[][] key2;
    int[] row1;
    int[] row2;

    private void checkCK(String message, boolean expectedResult) {
        boolean realResult = ((KeyCollectionDecomposed)instance).CK(key1, key2);
        assertEquals(message, expectedResult, realResult);
    }

    private void checkCR(String message, boolean expectedResult) {
        boolean realResult = ((KeyCollectionDecomposed)instance).CR(row1, row2);
        assertEquals(message, expectedResult, realResult);
    }

    @Test
    public void TestCK1() {
        key1 = new int[][]{new int[]{1}, new int[]{2}};
        key2 = new int[][]{new int[]{3}, new int[]{4, 5}};
        checkCK("Different length", false);
    }

    @Test
    public void TestCK2() {

        key1 = new int[][]{new int[]{2}, new int[]{2}};
        key2 = new int[][]{new int[]{2}, new int[]{4}};
        checkCK("The same length", true);
    }

    @Test
    public void TestCR1() {

        row1 = new int[]{1, 2, 3};
        row2 = new int[]{3, 4, 2};
        checkCR("Cant be converted because of different values", false);
    }
    @Test
    public void CheckCR1() {

        row1 = new int[]{1, 2};
        row2 = new int[]{1, 2, 3};
        checkCR("Cant be converted because of different length of rows", false);
    }
//# END TODO
}
