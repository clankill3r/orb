package orb.____inner_core.util.compare.excluded;


import orb.____inner_core.util.function._GetZ_T;

import static orb.____inner_core.IC_Math.dist;
import static orb.____inner_core.IC_Math.distSq;

/**
 * Created by doekewartena on 6/18/14.
 */


public interface _CompareXYZ_TT<T> extends _CompareXY_TT<T>, _CompareXYZ, _GetZ_T<T> {

    /*
    default int compareZ(T a, T b) {

//        final float aZ = getZ(a);
//        final float bZ = getZ(b);
//
//        if      (aZ < bZ) return -1;
//        else if (aZ > bZ) return 1;
//        else              return 0;

        return compare(getZ(a), getZ(b));
    }
    */

    default int compareZ(T a, T b) {
        return compare(getZ(a), getZ(b));
    }

    default int compareXZ(T a, T b) {
        return compareXZ(getX(a), getZ(a), getX(b), getZ(b));
    }

    default int compareYZ(T a, T b) {
        return compareYZ(getY(a), getZ(a), getY(b), getZ(b));
    }

    default int compareZY(T a, T b) {
        return compareZY(getY(a), getZ(a), getY(b), getZ(b));
    }


    default int compareZX(T a, T b) {
        return compareZX(getX(a), getZ(a), getX(b), getZ(b));
    }

    default int compareXYZ(T a, T b) {
        return compareXYZ(getX(a), getY(a), getZ(a), getX(b), getY(b), getZ(b));
    }

    default int compareXZY(T a, T b) {
        return compareXZY(getX(a), getY(a), getZ(a), getX(b), getY(b), getZ(b));

    }

    default int compareYXZ(T a, T b) {
        return compareYXZ(getX(a), getY(a), getZ(a), getX(b), getY(b), getZ(b));
    }

    default int compareYZX(T a, T b) {
        return compareYZX(getX(a), getY(a), getZ(a), getX(b), getY(b), getZ(b));
    }

    default int compareZXY(T a, T b) {
        return compareZXY(getX(a), getY(a), getZ(a), getX(b), getY(b), getZ(b));
    }

    default int compareZYX(T a, T b) {
        return compareZYX(getX(a), getY(a), getZ(a), getX(b), getY(b), getZ(b));
    }

    default int compareDistToPoint(T a, T b, float x, float y, float z) {
        return compareDistToPoint(getX(a), getY(a), getZ(a), getX(b), getY(b), getZ(b), x, y, z);
    }

    default int compareDistSqToPoint(T a, T b, float x, float y, float z) {
        return compareDistSqToPoint(getX(a), getY(a), getZ(a), getX(b), getY(b), getZ(b), x, y, z);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    // possible in 3d?
    /*
    default int compareAngleToPoint(T a, T b, float x, float y, float z) {
        return compareAngleToPoint(getX(a), getY(a), getX(b), getY(b), x, y, z);
    }
    */

}