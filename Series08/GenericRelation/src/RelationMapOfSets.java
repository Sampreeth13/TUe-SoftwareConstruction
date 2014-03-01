import java.util.*;

// Class to illustrate Test-Driven Development of a generic Abstract Data Type.

/**
 * An implementation of {@link Relation} using a {@link Map} of {@link Set}s.
 * <p/>
 * <!--//# BEGIN TODO Name, group id, and date-->
 * <p><font color="red"><b>Ilya Trofimov, 272, Nov 6</b></font></p>
 * <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class RelationMapOfSets<A, B> implements Relation<A, B> {
    //# BEGIN TODO Definition of class RelationMapOfSets
    private Map<A, Set<B>> relation;

    public RelationMapOfSets() {
        relation = new HashMap<A, Set<B>>();
    }

    @Override
    public boolean isRepOk() throws IllegalStateException {
        if (relation == null) {
            throw new IllegalStateException("relation == null");
        }
        for (A el : relation.keySet()) {
            if (relation.get(el) == null) {
                throw new IllegalStateException(
                        "relation.get(" + el.toString() + ") == null");
            }
        }

        return true;
    }

    @Override
    public boolean areRelated(A a, B b) {
        if (relation.containsKey(a)) {
            return relation.get(a).contains(b);
        }

        return false;
    }

    @Override
    public void add(A a, B b) {
        if (!relation.containsKey(a)) {
            relation.put(a, new HashSet<B>());
        }

        relation.get(a).add(b);
    }

    @Override
    public void remove(A a, B b) {
        if (relation.containsKey(a)) {
            relation.get(a).remove(b);
        }
    }

    @Override
    public Iterable<B> relatedToFirst(A a) {
        if (!relation.containsKey(a)) {
            relation.put(a, new HashSet<B>());
        }

        return relation.get(a);
    }

    @Override
    public Iterator<Pair<A, B>> iterator() {
        return new PairIterator();
    }

    public class PairIterator implements Iterator<Pair<A, B>> {
        Iterator<B> iterateB;
        int indexKey;
        ArrayList<A> keys;

        PairIterator() {
            indexKey = 0;
            keys = new ArrayList<A>();

            for (A key : relation.keySet()) {
                keys.add(key);
            }
        }

        @Override
        public boolean hasNext() {
            while (indexKey < keys.size()) {
                if (iterateB == null) {
                    iterateB = relation.get(keys.get(indexKey)).iterator();
                }
                if (iterateB.hasNext()) {
                    return true;
                } else {
                    indexKey++;
                    iterateB = null;
                }
            }

            return false;
        }

        @Override
        public Pair<A, B> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is not such element.");
            }

            return new Pair<A, B>(keys.get(indexKey), iterateB.next());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot use remove().");
        }
    }
//# END TODO
}
