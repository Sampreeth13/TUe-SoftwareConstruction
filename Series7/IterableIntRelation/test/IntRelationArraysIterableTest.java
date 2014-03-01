/**
 * <p><font color="red"><b>Ilya Trofimov, 272, 10/23/2013</b></font></p>
 */
//-----8<----- cut line -----8<-----

public class IntRelationArraysIterableTest
        extends IntRelationIterableTestCases {

    @Override
    protected void setInstance(int n) {
        instance = new IntRelationArraysIterable(n);
    }
}
