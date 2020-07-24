package nl.doekewartena.orb.inner_core.util.compare.excluded;


import nl.doekewartena.orb.inner_core.util.function._GetX_T;
import nl.doekewartena.orb.inner_core.util.function._GetY_T;

/**
 * Created by doekewartena on 6/18/14.
 */

public interface _CompareXY_TT<T> extends _GetX_T<T>, _GetY_T<T>, _CompareXY {

    default int compareX(T a, T b) {
        return compare(getX(a), getX(b));
    }

    default int compareY(T a, T b) {
     return compare(getY(a), getY(b));
    }

    default int compareXY(T a, T b) {
        return compareXY(getX(a), getY(a), getX(b), getY(b));
    }

    default int compareYX(T a, T b) {
     return compareYX(getX(a), getY(a), getX(b), getY(b));
    }

    default int compareDistSqToPoint(T a, T b, double x, double y) {
     return compareDistSqToPoint(getX(a), getY(a), getX(b), getY(b), x, y);
    }

    default int compareDistToPoint(T a, T b, double x, double y) {
     return compareDistToPoint(getX(a), getY(a), getX(b), getY(b), x, y);
    }

    default int compareAngleToPoint(T a, T b, double x, double y) {
     return compareAngleToPoint(getX(a), getY(a), getX(b), getY(b), x, y);
    }


}
