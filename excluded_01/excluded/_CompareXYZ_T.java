package orb.____inner_core.util.compare.excluded;


import orb.____inner_core.util.function._GetZ_T;

import static orb.____inner_core.util.compare.excluded.IC_CompareXY.compare;

/**
 * Created by doekewartena on 9/12/15.
 */
public interface _CompareXYZ_T<T> extends _CompareXY_T<T>, _GetZ_T<T> {// }, _CompareXYZ {


    default int compareZ(T b) {
        return compare(getZ((T) this), getZ(b));
    }

    default int compareZ(float z) {
        return compare(getZ((T) this), z);
    }

    default int compareXZ(T b) {
        // why can't wee static import this?
       return IC_CompareXYZ.compareXZ(getX((T) this), getZ((T) this), getX(b), getZ(b));
    }

    default int compareXZ(float x, float z) {
        // why can't wee static import this?
        return IC_CompareXYZ.compareXZ(getX((T) this), getZ((T) this), x, z);
    }

    default int compareYZ(T b) {
        return IC_CompareXYZ.compareZY(getY((T) this), getZ((T) this), getY(b), getZ(b));
    }

    default int compareYZ(float y, float z) {
        return IC_CompareXYZ.compareZY(getY((T) this), getZ((T) this), y, z);
    }

    default int compareZY(T b) {
        return IC_CompareXYZ.compareZY(getY((T) this), getZ((T) this), getY(b), getZ(b));
    }

    default int compareZY(float y, float z) {
        return IC_CompareXYZ.compareZY(getY((T) this), getZ((T) this), y, z);
    }

    default int compareZX(T b) {
        return IC_CompareXYZ.compareZX(getX((T) this), getZ((T) this), getX(b), getZ(b));
    }

    default int compareZX(float x, float z) {
        return IC_CompareXYZ.compareZX(getX((T) this), getZ((T) this), x, z);
    }

    default int compareXYZ(T b) {
        return IC_CompareXYZ.compareXYZ(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareXYZ(float x, float y, float z) {
        return IC_CompareXYZ.compareXYZ(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    default int compareXZY(T b) {
        return IC_CompareXYZ.compareXZY(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareXZY(float x, float y, float z) {
        return IC_CompareXYZ.compareXZY(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    default int compareYXZ(T b) {
        return IC_CompareXYZ.compareXYZ(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareYXZ(float x, float y, float z) {
        return IC_CompareXYZ.compareXYZ(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    default int compareYZX(T b) {
        return IC_CompareXYZ.compareYZX(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareYZX(float x, float y, float z) {
        return IC_CompareXYZ.compareYZX(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    default int compareZXY(T b) {
        return IC_CompareXYZ.compareZXY(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareZXY(float x, float y, float z) {
        return IC_CompareXYZ.compareZXY(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    default int compareZYX(T b) {
        return IC_CompareXYZ.compareZYX(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareZYX(float x, float y, float z) {
        return IC_CompareXYZ.compareZYX(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareDistToPoint(T b, float x, float y, float z) {
        return IC_CompareXYZ.compareDistToPoint(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b), x, y, z);
    }

    default int compareDistToPoint(float x, float y, float z, float px, float py, float pz) {
        return IC_CompareXYZ.compareDistToPoint(getX((T) this), getY((T) this), getZ((T) this), x, y, z, px, py, pz);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareDistSqToPoint(T b, float x, float y, float z) {
        return IC_CompareXYZ.compareDistSqToPoint(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b), x, y, z);
    }

    default int compareDistSqToPoint(float x, float y, float z, float px, float py, float pz) {
        return IC_CompareXYZ.compareDistSqToPoint(getX((T) this), getY((T) this), getZ((T) this), x, y, z, px, py, pz);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


}
