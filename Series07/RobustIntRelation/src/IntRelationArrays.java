// Class to illustrate Test-Driven Development of an Abstract Data Type.

/**
 * An implementation of {@code IntRelation} using nested arrays.
 * For relations with a small extent this may work faster.
 * However, relations with a large extent use more memory,
 * even when they are sparse (have few related pairs).
 *
 * @author Tom Verhoeff (TU/e)
 */

/**
 * <p><font color="red"><b>Ilya Trofimov, 272, 10/23/2013</b></font></p>
 */
//-----8<----- cut line -----8<-----

public class IntRelationArrays extends IntRelation {

    /**
     * Representation of the relation.
     */
    private boolean[][] relation;
    // could be dclared final

    /*
     * Representation invariants
     * 
     * NotNull: relation != null
     * Extent: relation.length == extent()
     * ElementsNotNull: (\forall i; relation.has(i); relation[i] != null)
     * ElementsSameSize: (\forall i; relation.has(i);
     *     relation[i].length == relation.length)
     *
     * Abstraction function: set of (a, b) such that relation[a][b] holds
     */

    public IntRelationArrays(final int n) {
        super(n);
        relation = new boolean[n][n];
    }

    @Override
    public boolean isRepOk() {
        if (relation == null) {
            throw new IllegalStateException("relation == null");
        }
        for (int i = 0; i != relation.length; ++i) {
            if (relation[i] == null) {
                throw new IllegalStateException(
                        "relation[" + i + "] == null");
            }
            if (relation[i].length != relation.length) {
                throw new IllegalStateException(
                        "relation[" + i + "].length != relation.length");
            }
        }
        return true;
    }

    @Override
    public int extent() {
        return relation.length;
    }

    @Override
    public boolean areRelated(int a, int b) throws IllegalArgumentException {
        if (!(this.isValidPair(a, b))) {
            throw new IllegalArgumentException("areRelated(" + a + ", " + b +
                    ") violates precondition");
        }

        return relation[a][b];
    }

    @Override
    public void add(int a, int b) throws IllegalArgumentException {
        if (!(this.isValidPair(a, b))) {
            throw new IllegalArgumentException("add(" + a + ", " + b +
                    ") violates precondition");
        }

        relation[a][b] = true;
    }

    @Override
    public void remove(int a, int b) throws IllegalArgumentException {
        if (!(this.isValidPair(a, b))) {
            throw new IllegalArgumentException("remove(" + a + ", " + b +
                    ") violates precondition");
        }

        relation[a][b] = false;
    }

}
