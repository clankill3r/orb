package nl.doekewartena.orb.inner_core.util.compare.excluded;

import nl.doekewartena.orb.inner_core.util.function._GetX_T;
import nl.doekewartena.orb.inner_core.util.function._GetY_T;

/**
 * Created by doekewartena on 9/12/15.
 */
public interface _CompareXY_T<T> extends _GetX_T<T>, _GetY_T<T> {//}, _CompareXY {

    default int compareX(T b) {
        // we need a static class for _CompareXY
        return IC_CompareXY.compareX(getX((T) this), getX(b));
    }

    // todo, methods like this? (makes it great to compare against other objects)
    default int compareX(double x) {
        return IC_CompareXY.compareX(getX((T) this), x);
    }

    default int compareY(T b) {
        return IC_CompareXY.compareY(getY((T) this), getY(b));
    }

    default int compareY(double y) {
        return IC_CompareXY.compareY(getY((T) this), y);
    }

    default int compareXY(T b) {
        return IC_CompareXY.compareXY(getX((T) this), getY((T) this), getX(b), getY(b));
    }

    default int compareXY(double x, double y) {
        return IC_CompareXY.compareXY(getX((T) this), getY((T) this), x, y);
    }

    default int compareYX(T b) {
        return IC_CompareXY.compareYX(getX((T) this), getY((T) this), getX(b), getY(b));
    }

    default int compareYX(double x, double y) {
        return IC_CompareXY.compareYX(getX((T) this), getY((T) this), x, y);
    }

    default int compareDistSqToPoint(T b, double x, double y) {
        return IC_CompareXY.compareDistSqToPoint(getX((T) this), getY((T) this), getX(b), getY(b), x, y);
    }

    default int compareDistSqToPoint(double x, double y, double px, double py) {
        return IC_CompareXY.compareDistSqToPoint(getX((T) this), getY((T) this), x, y, px, py);
    }

    default int compareDistToPoint(T b, double px, double py) {
        return IC_CompareXY.compareDistToPoint(getX((T) this), getY((T) this), getX(b), getY(b), px, py);
    }

    default int compareDistToPoint(double x, double y, double px, double py) {
        return IC_CompareXY.compareDistToPoint(getX((T) this), getY((T) this), x, y, px, py);
    }

    default int compareAngleToPoint(T b, double px, double py) {
        return IC_CompareXY.compareAngleToPoint(getX((T) this), getY((T) this), getX(b), getY(b), px, py);
    }

    default int compareAngleToPoint(double x, double y, double px, double py) {
        return IC_CompareXY.compareAngleToPoint(getX((T) this), getY((T) this), x, y, px, py);
    }

}
