package orb.____inner_core.util.compare.excluded;


import nl.doekewartena.orb.inner_core.IC_Math;
import nl.doekewartena.orb.inner_core.geom._Vec2;

/**
 * Created by doekewartena on 9/12/15.
 */
public interface _CompareXY {

    default int compare(double aX, double bX) {
        if      (aX < bX) return -1;
        else if (aX > bX) return 1;
        else              return 0;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareX(double aX, double bX) {
        return compare(aX, bX);
    }

    default int compareY(double aY, double bY) {
        return compare(aY, bY);
    }

    default int compareX(_Vec2 a, _Vec2 b) {
        return compare(a.x(), b.x());
    }

    default int compareY(_Vec2 a, _Vec2 b) {
        return compare(a.y(), b.y());
    }
    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareXY(double aX, double aY, double bX, double bY) {
        int xDiff = compare(aX, bX);
        if (xDiff != 0) return xDiff;
        else return compare(aY, bY);
    }

    default int compareXY(_Vec2 a, _Vec2 b) {
         return compareXY(a.x(), a.y(), b.x(), b.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareYX(double aX, double aY, double bX, double bY) {
        int yDiff = compare(aY, bY);
        if (yDiff != 0) return yDiff;
        else return compare(aX, bX);
    }

    default int compareYX(_Vec2 a, _Vec2 b) {
        return compareYX(a.x(), a.y(), b.x(), b.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    default int compareDistSqToPoint(double aX, double aY, double bX, double bY, double x, double y) {
        final double dA = IC_Math.distSq(aX, aY, x, y);
        final double dB = IC_Math.distSq(bX, bY, x, y);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }

    default int compareDistSqToPoint(_Vec2 a, _Vec2 b, double x, double y) {
        return compareDistSqToPoint(a.x(), a.y(), b.x(), b.y(), x, y);
    }

    default int compareDistSqToPoint(_Vec2 a, _Vec2 b, _Vec2 p) {
        return compareDistSqToPoint(a.x(), a.y(), b.x(), b.y(), p.x(), p.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareDistToPoint(double aX, double aY, double bX, double bY, double x, double y) {
        final double dA = IC_Math.dist(aX, aY, x, y);
        final double dB = IC_Math.dist(bX, bY, x, y);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }

    default int compareDistToPoint(_Vec2 a, _Vec2 b, double x, double y) {
        return compareDistToPoint(a.x(), a.y(), b.x(), b.y(), x, y);
    }

    default int compareDistToPoint(_Vec2 a, _Vec2 b, _Vec2 p) {
        return compareDistToPoint(a.x(), a.y(), b.x(), b.y(), p.x(), p.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareAngleToPoint(double aX, double aY, double bX, double bY, double x, double y) {

        final double aA = Math.atan2(aY-y, aX-x);
        final double aB = Math.atan2(bY-y, bX-x);

        if      (aA < aB) return -1;
        else if (aA > aB) return 1;
        else              return 0;
    }

    default int compareAngleToPoint(_Vec2 a, _Vec2 b, double x, double y) {
        return compareAngleToPoint(a.x(), a.y(), b.x(), b.y(), x, y);
    }

    default int compareAngleToPoint(_Vec2 a, _Vec2 b, _Vec2 p) {
        return compareAngleToPoint(a.x(), a.y(), b.x(), b.y(), p.x(), p.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .
}
