import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Version of {@link IntRelationArrays}, that allows iteration
 * over all related integers to a given first integer.
 * <p/>
 * <!--//# BEGIN TODO Name, group id, and date-->
 * <p><font color="red"><b>Ilya Trofimov, 272, Nov 6</b></font></p>
 * <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class IntRelationArraysR2F extends IntRelationArrays
        implements IntRelationExtra {

    public IntRelationArraysR2F(final int n) {
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

        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new RelationIterator(a);
            }
        };
//# END TODO
    }

    private class RelationIterator implements Iterator<Integer> {
        //# BEGIN TODO Definition of inner class RelationIterator
        int a;
        int b;

        RelationIterator(int a) {
            this.a = a;
            this.b = 0;
        }

        @Override
        public boolean hasNext() {
            while (isValid(b)) {
                if (areRelated(a, b)) {
                    return true;
                }
                ++b;
            }

            return false;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is not such element.");
            }

            return b++;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot use remove().");
        }
//# END TODO
    }

}
