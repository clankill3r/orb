package orb._crust;

import orb.____inner_core.IC_Common;
import orb.____inner_core.util.function._GetFloat_T;

import java.util.List;

/**
 * Created by doekewartena on 8/25/15.
 */
public class C_Common extends IC_Common {  // if we get more commons then we have to extend in order

    // this one is in the shell cause it creates the bestMatch every time we use it
    // however, it's a lot more convenient!
    public static <T> T getMax(List<T> items, _GetFloat_T<T> getV) {
        BestMatch<T> bestMatch = new BestMatch<>(-Float.MAX_VALUE);
        getMax(items, getV, bestMatch);
        return bestMatch.item;
    }

    // same
    public static <T> T getMin(List<T> items, _GetFloat_T<T> getV) {
        BestMatch<T> bestMatch = new BestMatch<>(Float.MAX_VALUE);
        getMin(items, getV, bestMatch);
        return bestMatch.item;
    }


    // todo, in path class?
    public static <T> float[][] xyListTo2DArray(_GetFloat_T<T> getX, _GetFloat_T<T> getY, List<T> items) {

        float[][] result = new float[items.size()][2];

        T item;
        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            result[i][0] = getX.val(item);
            result[i][1] = getY.val(item);
        }

        return result;

    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // todo, in path class?
    public static <T> float[][] xyzListTo2DArray(_GetFloat_T<T> getX, _GetFloat_T<T> getY, _GetFloat_T<T> getZ, List<T> items) {

        float[][] result = new float[items.size()][3];

        T item;
        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            result[i][0] = getX.val(item);
            result[i][1] = getY.val(item);
            result[i][2] = getZ.val(item);
        }

        return result;

    }

}
