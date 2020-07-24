package orb.____inner_core.util.compare.excluded;

import orb.____inner_core.geom._Vec3;

import static orb.____inner_core.IC_Math.dist;
import static orb.____inner_core.IC_Math.distSq;

/**
 * Created by doekewartena on 9/12/15.
 */
public interface _CompareXYZ extends _CompareXY {


    default int compareZ(float aZ, float bZ) {
        return compare(aZ, bZ);
    }

    default int compareZ(_Vec3 a, _Vec3 b) {
        return compare(a.x(), b.x());
    }



    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareXZ(float aX, float aZ, float bX, float bZ) {
        int xDiff = compare(aX, bX);
        if (xDiff != 0) return xDiff;
        else return compare(aZ, bZ);
    }

    default int compareXZ(_Vec3 a, _Vec3 b) {
        return compareXZ(a.x(), a.z(), b.x(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    default int compareYZ(float aY, float aZ, float bY, float bZ) {
        int yDiff = compare(aY, bY);
        if (yDiff != 0) return yDiff;
        else return compare(aZ, bZ);
    }

    default int compareYZ(_Vec3 a, _Vec3 b) {
        return compareYZ(a.y(), a.z(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareZY(float aY, float aZ, float bY, float bZ) {
        int zDiff = compare(aZ, bZ);
        if (zDiff != 0) return zDiff;
        else return compare(aY, bY);
    }

    default int compareZY(_Vec3 a, _Vec3 b) {
        return compareZY(a.y(), a.z(), b.y(), b.z());
    }


    // . . . . . . . . . . . . . . . . . . . . . . . .



    default int compareZX(float aX, float aZ, float bX, float bZ) {
        int zDiff = compare(aZ, bZ);
        if (zDiff != 0) return zDiff;
        else return compare(aX, bX);
    }

    default int compareZX(_Vec3 a, _Vec3 b) {
        return compareZX(a.x(), a.z(), b.x(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    default int compareXYZ(float aX, float aY, float aZ, float bX, float bY, float bZ) {
        int xDiff = compare(aX, bX);
        if (xDiff != 0) return xDiff;
        else return compareYZ(aY, aZ, bY, bZ);
    }

    default int compareXYZ(_Vec3 a, _Vec3 b) {
        return compareXYZ(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    default int compareXZY(float aX, float aY, float aZ, float bX, float bY, float bZ) {
        int xDiff = compare(aX, bX);
        if (xDiff != 0) return xDiff;
        else return compareZY(aY, aZ, bY, bZ);
    }

    default int compareXZY(_Vec3 a, _Vec3 b) {
        return compareXZY(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    default int compareYXZ(float aX, float aY, float aZ, float bX, float bY, float bZ) {
        int yDiff = compare(aY, bY);
        if (yDiff != 0) return yDiff;
        else return compareXZ(aX, aZ, bX, bZ);
    }

    default int compareYXZ(_Vec3 a, _Vec3 b) {
        return compareYXZ(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    default int compareYZX(float aX, float aY, float aZ, float bX, float bY, float bZ) {
        int yDiff = compare(aY, bY);
        if (yDiff != 0) return yDiff;
        else return compareZX(aX, aZ, bX, bZ);
    }

    default int compareYZX(_Vec3 a, _Vec3 b) {
        return compareYZX(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    default int compareZXY(float aX, float aY, float aZ, float bX, float bY, float bZ) {
        int zDiff = compare(aZ, bZ);
        if (zDiff != 0) return zDiff;
        else return compareXY(aX, aY, bX, bY);
    }

    default int compareZXY(_Vec3 a, _Vec3 b) {
        return compareZXY(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }
    // . . . . . . . . . . . . . . . . . . . . . . . .



    default int compareZYX(float aX, float aY, float aZ, float bX, float bY, float bZ) {
        int zDiff = compare(aZ, bZ);
        if (zDiff != 0) return zDiff;
        else return compareYX(aX, aY, bX, bY);
    }

    default int compareZYX(_Vec3 a, _Vec3 b) {
        return compareZYX(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareDistToPoint(float aX, float aY, float aZ, float bX, float bY, float bZ, float x, float y, float z) {
        float dA = dist(aX, aY, aZ, x, y, z);
        float dB = dist(bX, bY, bZ, x, y, z);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }

    default int compareDistToPoint(_Vec3 a, _Vec3 b, float x, float y, float z) {
        return compareDistToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), x, y, z);
    }

    default int compareDistToPoint(_Vec3 a, _Vec3 b, _Vec3 p) {
        return compareDistToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), p.x(), p.y(), p.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    default int compareDistSqToPoint(float aX, float aY, float aZ, float bX, float bY, float bZ, float x, float y, float z) {
        float dA = distSq(aX, aY, aZ, x, y, z);
        float dB = distSq(bX, bY, bZ, x, y, z);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }

    default int compareDistSqToPoint(_Vec3 a, _Vec3 b, float x, float y, float z) {
        return compareDistSqToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), x, y, z);
    }

    default int compareDistSqToPoint(_Vec3 a, _Vec3 b, _Vec3 p) {
        return compareDistSqToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), p.x(), p.y(), p.z());
    }
    // . . . . . . . . . . . . . . . . . . . . . . . .

    // is this possible in 3d?
    /*
    default int compareAngleToPoint(float aX, float aY, float aZ, float bX, float bY, float bZ, float x, float y, float z) {
//        final float aA = atan2(aY-y, aX-x);
//        final float aB = atan2(bY-y, bX-x);
//
//        if      (aA < aB) return -1;
//        else if (aA > aB) return 1;
//        else              return 0;

    }

    default int compareAngleToPoint(_Vec3 a, _Vec3 b, float x, float y, float z) {
        return compareAngleToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), x, y, z);
    }

    default int compareAngleToPoint(_Vec3 a, _Vec3 b, _Vec3 p) {
        return compareAngleToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), p.x(), p.y(), p.z());
    }
    */

}
