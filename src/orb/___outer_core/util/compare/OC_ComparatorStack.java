package orb.___outer_core.util.compare;

import java.util.Comparator;

/**
 * Created by doekewartena on 2/9/15.
 */
public class OC_ComparatorStack<T> implements Comparator<T> {

    Comparator<T>[] comparators;

    public OC_ComparatorStack(Comparator<T>... comparators) {
        this.comparators = comparators;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // todo, methods to add and remove comparators etc.?

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public int compare(T o1, T o2) {

        int depth = 0;

        while (depth != comparators.length) {
            int diff = comparators[depth++].compare(o1, o2);
            if (diff != 0) return diff;
        }

        return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


}
