package orb.____inner_core.util.compare.excluded;


import orb.____inner_core.IC_Math;
import orb.____inner_core.geom._Vec2;

/**
 * Created by doekewartena on 9/12/15.
 */
public class IC_CompareXY { //implements _CompareXY {


    public static int compare(float aX, float bX) {
        if      (aX < bX) return -1;
        else if (aX > bX) return 1;
        else              return 0;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . .

    public static int compareX(float aX, float bX) {
        return compare(aX, bX);
    }

    public static int compareY(float aY, float bY) {
        return compare(aY, bY);
    }

    public static int compareX(_Vec2 a, _Vec2 b) {
        return compare(a.x(), b.x());
    }

    public static int compareY(_Vec2 a, _Vec2 b) {
        return compare(a.y(), b.y());
    }
    // . . . . . . . . . . . . . . . . . . . . . . . .

    public static int compareXY(float aX, float aY, float bX, float bY) {
        int xDiff = compare(aX, bX);
        if (xDiff != 0) return xDiff;
        else return compare(aY, bY);
    }

    public static int compareXY(_Vec2 a, _Vec2 b) {
         return compareXY(a.x(), a.y(), b.x(), b.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    public static int compareYX(float aX, float aY, float bX, float bY) {
        int yDiff = compare(aY, bY);
        if (yDiff != 0) return yDiff;
        else return compare(aX, bX);
    }

    public static int compareYX(_Vec2 a, _Vec2 b) {
        return compareYX(a.x(), a.y(), b.x(), b.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public static int compareDistSqToPoint(float aX, float aY, float bX, float bY, float x, float y) {
        final float dA = IC_Math.distSq(aX, aY, x, y);
        final float dB = IC_Math.distSq(bX, bY, x, y);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }

    public static int compareDistSqToPoint(_Vec2 a, _Vec2 b, float x, float y) {
        return compareDistSqToPoint(a.x(), a.y(), b.x(), b.y(), x, y);
    }

    public static int compareDistSqToPoint(_Vec2 a, _Vec2 b, _Vec2 p) {
        return compareDistSqToPoint(a.x(), a.y(), b.x(), b.y(), p.x(), p.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    public static int compareDistToPoint(float aX, float aY, float bX, float bY, float x, float y) {
        final float dA = IC_Math.dist(aX, aY, x, y);
        final float dB = IC_Math.dist(bX, bY, x, y);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }

    public static int compareDistToPoint(_Vec2 a, _Vec2 b, float x, float y) {
        return compareDistToPoint(a.x(), a.y(), b.x(), b.y(), x, y);
    }

    public static int compareDistToPoint(_Vec2 a, _Vec2 b, _Vec2 p) {
        return compareDistToPoint(a.x(), a.y(), b.x(), b.y(), p.x(), p.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    public static int compareAngleToPoint(float aX, float aY, float bX, float bY, float x, float y) {

        final float aA = atan2(aY-y, aX-x);
        final float aB = atan2(bY-y, bX-x);

        if      (aA < aB) return -1;
        else if (aA > aB) return 1;
        else              return 0;
    }

    public static int compareAngleToPoint(_Vec2 a, _Vec2 b, float x, float y) {
        return compareAngleToPoint(a.x(), a.y(), b.x(), b.y(), x, y);
    }

    public static int compareAngleToPoint(_Vec2 a, _Vec2 b, _Vec2 p) {
        return compareAngleToPoint(a.x(), a.y(), b.x(), b.y(), p.x(), p.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .
}
