/**
 * Test cases for IntRelationArraysR2F.
 *
 * @author Tom Verhoeff (TU/e)
 */
public class IntRelationListOfSetsR2FTest extends IntRelationR2FTestCases {

    @Override
    protected void setInstance(final int n) {
        instance = new IntRelationListOfSetsR2F(n);
    }

    @Override
    protected void setR2F(final int n) {
        r2f = new IntRelationListOfSetsR2F(n);
        instance = ((IntRelation) r2f);
    }

}
