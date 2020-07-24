package nl.doekewartena.orb.inner_core.util.compare.excluded;


import nl.doekewartena.orb.inner_core.util.function._GetZ_T;

import static nl.doekewartena.orb.inner_core.util.compare.excluded.IC_CompareXY.compare;

/**
 * Created by doekewartena on 9/12/15.
 */
public interface _CompareXYZ_T<T> extends _CompareXY_T<T>, _GetZ_T<T> {// }, _CompareXYZ {


    default int compareZ(T b) {
        return compare(getZ((T) this), getZ(b));
    }

    default int compareZ(double z) {
        return compare(getZ((T) this), z);
    }

    default int compareXZ(T b) {
        // why can't wee static import this?
       return IC_CompareXYZ.compareXZ(getX((T) this), getZ((T) this), getX(b), getZ(b));
    }

    default int compareXZ(double x, double z) {
        // why can't wee static import this?
        return IC_CompareXYZ.compareXZ(getX((T) this), getZ((T) this), x, z);
    }

    default int compareYZ(T b) {
        return IC_CompareXYZ.compareZY(getY((T) this), getZ((T) this), getY(b), getZ(b));
    }

    default int compareYZ(double y, double z) {
        return IC_CompareXYZ.compareZY(getY((T) this), getZ((T) this), y, z);
    }

    default int compareZY(T b) {
        return IC_CompareXYZ.compareZY(getY((T) this), getZ((T) this), getY(b), getZ(b));
    }

    default int compareZY(double y, double z) {
        return IC_CompareXYZ.compareZY(getY((T) this), getZ((T) this), y, z);
    }

    default int compareZX(T b) {
        return IC_CompareXYZ.compareZX(getX((T) this), getZ((T) this), getX(b), getZ(b));
    }

    default int compareZX(double x, double z) {
        return IC_CompareXYZ.compareZX(getX((T) this), getZ((T) this), x, z);
    }

    default int compareXYZ(T b) {
        return IC_CompareXYZ.compareXYZ(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareXYZ(double x, double y, double z) {
        return IC_CompareXYZ.compareXYZ(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    default int compareXZY(T b) {
        return IC_CompareXYZ.compareXZY(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareXZY(double x, double y, double z) {
        return IC_CompareXYZ.compareXZY(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    default int compareYXZ(T b) {
        return IC_CompareXYZ.compareXYZ(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareYXZ(double x, double y, double z) {
        return IC_CompareXYZ.compareXYZ(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    default int compareYZX(T b) {
        return IC_CompareXYZ.compareYZX(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareYZX(double x, double y, double z) {
        return IC_CompareXYZ.compareYZX(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    default int compareZXY(T b) {
        return IC_CompareXYZ.compareZXY(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareZXY(double x, double y, double z) {
        return IC_CompareXYZ.compareZXY(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    default int compareZYX(T b) {
        return IC_CompareXYZ.compareZYX(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b));
    }

    default int compareZYX(double x, double y, double z) {
        return IC_CompareXYZ.compareZYX(getX((T) this), getY((T) this), getZ((T) this), x, y, z);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareDistToPoint(T b, double x, double y, double z) {
        return IC_CompareXYZ.compareDistToPoint(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b), x, y, z);
    }

    default int compareDistToPoint(double x, double y, double z, double px, double py, double pz) {
        return IC_CompareXYZ.compareDistToPoint(getX((T) this), getY((T) this), getZ((T) this), x, y, z, px, py, pz);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareDistSqToPoint(T b, double x, double y, double z) {
        return IC_CompareXYZ.compareDistSqToPoint(getX((T) this), getY((T) this), getZ((T) this), getX(b), getY(b), getZ(b), x, y, z);
    }

    default int compareDistSqToPoint(double x, double y, double z, double px, double py, double pz) {
        return IC_CompareXYZ.compareDistSqToPoint(getX((T) this), getY((T) this), getZ((T) this), x, y, z, px, py, pz);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


}
