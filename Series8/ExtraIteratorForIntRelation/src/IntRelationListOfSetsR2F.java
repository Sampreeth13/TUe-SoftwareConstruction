/**
 * Version of {@link IntRelationListOfSets}, that allows iteration
 * over all related integers to a given first integer.
 * <p/>
 * <!--//# BEGIN TODO Name, group id, and date-->
 * <p><font color="red"><b>Ilya Trofimov, 272, Nov 6</b></font></p>
 * <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class IntRelationListOfSetsR2F extends IntRelationListOfSets
        implements IntRelationExtra {

    public IntRelationListOfSetsR2F(final int n) {
        super(n);
    }

    private boolean isValid(int v) {
        return extent() >= 0 && 0 <= v && v < extent();
    }

    @Override
    public Iterable<Integer> relatedToFirst(final int a) {
//# BEGIN TODO Robust implementation of relatedToFirst
        if (!isValid(a)) {
            throw new IllegalArgumentException("relatedToFirst(" + a + ")" +
                    " violates precondition.");
        }

        return relation.get(a);
//# END TODO
    }

}
