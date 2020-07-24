package orb.____inner_core.util.compare.excluded;

import nl.doekewartena.orb.inner_core.geom._Vec3;

import static nl.doekewartena.orb.inner_core.IC_Math.dist;
import static nl.doekewartena.orb.inner_core.IC_Math.distSq;

/**
 * Created by doekewartena on 9/12/15.
 */
public class IC_CompareXYZ extends IC_CompareXY {


    public static int compareZ(double aZ, double bZ) {
        return compare(aZ, bZ);
    }

    public static int compareZ(_Vec3 a, _Vec3 b) {
        return compare(a.x(), b.x());
    }



    // . . . . . . . . . . . . . . . . . . . . . . . .

    public static int compareXZ(double aX, double aZ, double bX, double bZ) {
        int xDiff = compare(aX, bX);
        if (xDiff != 0) return xDiff;
        else return compare(aZ, bZ);
    }

    public static int compareXZ(_Vec3 a, _Vec3 b) {
        return compareXZ(a.x(), a.z(), b.x(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public static int compareYZ(double aY, double aZ, double bY, double bZ) {
        int yDiff = compare(aY, bY);
        if (yDiff != 0) return yDiff;
        else return compare(aZ, bZ);
    }

    public static int compareYZ(_Vec3 a, _Vec3 b) {
        return compareYZ(a.y(), a.z(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    public static int compareZY(double aY, double aZ, double bY, double bZ) {
        int zDiff = compare(aZ, bZ);
        if (zDiff != 0) return zDiff;
        else return compare(aY, bY);
    }

    public static int compareZY(_Vec3 a, _Vec3 b) {
        return compareZY(a.y(), a.z(), b.y(), b.z());
    }


    // . . . . . . . . . . . . . . . . . . . . . . . .



    public static int compareZX(double aX, double aZ, double bX, double bZ) {
        int zDiff = compare(aZ, bZ);
        if (zDiff != 0) return zDiff;
        else return compare(aX, bX);
    }

    public static int compareZX(_Vec3 a, _Vec3 b) {
        return compareZX(a.x(), a.z(), b.x(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public static int compareXYZ(double aX, double aY, double aZ, double bX, double bY, double bZ) {
        int xDiff = compare(aX, bX);
        if (xDiff != 0) return xDiff;
        else return compareYZ(aY, aZ, bY, bZ);
    }

    public static int compareXYZ(_Vec3 a, _Vec3 b) {
        return compareXYZ(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public static int compareXZY(double aX, double aY, double aZ, double bX, double bY, double bZ) {
        int xDiff = compare(aX, bX);
        if (xDiff != 0) return xDiff;
        else return compareZY(aY, aZ, bY, bZ);
    }

    public static int compareXZY(_Vec3 a, _Vec3 b) {
        return compareXZY(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public static int compareYXZ(double aX, double aY, double aZ, double bX, double bY, double bZ) {
        int yDiff = compare(aY, bY);
        if (yDiff != 0) return yDiff;
        else return compareXZ(aX, aZ, bX, bZ);
    }

    public static int compareYXZ(_Vec3 a, _Vec3 b) {
        return compareYXZ(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public static int compareYZX(double aX, double aY, double aZ, double bX, double bY, double bZ) {
        int yDiff = compare(aY, bY);
        if (yDiff != 0) return yDiff;
        else return compareZX(aX, aZ, bX, bZ);
    }

    public static int compareYZX(_Vec3 a, _Vec3 b) {
        return compareYZX(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public static int compareZXY(double aX, double aY, double aZ, double bX, double bY, double bZ) {
        int zDiff = compare(aZ, bZ);
        if (zDiff != 0) return zDiff;
        else return compareXY(aX, aY, bX, bY);
    }

    public static int compareZXY(_Vec3 a, _Vec3 b) {
        return compareZXY(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }
    // . . . . . . . . . . . . . . . . . . . . . . . .



    public static int compareZYX(double aX, double aY, double aZ, double bX, double bY, double bZ) {
        int zDiff = compare(aZ, bZ);
        if (zDiff != 0) return zDiff;
        else return compareYX(aX, aY, bX, bY);
    }

    public static int compareZYX(_Vec3 a, _Vec3 b) {
        return compareZYX(a.x(), a.y(), a.z(), b.x(), b.y(), b.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    public static int compareDistToPoint(double aX, double aY, double aZ, double bX, double bY, double bZ, double x, double y, double z) {
        double dA = dist(aX, aY, aZ, x, y, z);
        double dB = dist(bX, bY, bZ, x, y, z);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }

    public static int compareDistToPoint(_Vec3 a, _Vec3 b, double x, double y, double z) {
        return compareDistToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), x, y, z);
    }

    public static int compareDistToPoint(_Vec3 a, _Vec3 b, _Vec3 p) {
        return compareDistToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), p.x(), p.y(), p.z());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    public static int compareDistSqToPoint(double aX, double aY, double aZ, double bX, double bY, double bZ, double x, double y, double z) {
        double dA = distSq(aX, aY, aZ, x, y, z);
        double dB = distSq(bX, bY, bZ, x, y, z);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }

    public static int compareDistSqToPoint(_Vec3 a, _Vec3 b, double x, double y, double z) {
        return compareDistSqToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), x, y, z);
    }

    public static int compareDistSqToPoint(_Vec3 a, _Vec3 b, _Vec3 p) {
        return compareDistSqToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), p.x(), p.y(), p.z());
    }
    // . . . . . . . . . . . . . . . . . . . . . . . .

    // is this possible in 3d?
    /*
    default int compareAngleToPoint(double aX, double aY, double aZ, double bX, double bY, double bZ, double x, double y, double z) {
//        final double aA = Math.atan2(aY-y, aX-x);
//        final double aB = Math.atan2(bY-y, bX-x);
//
//        if      (aA < aB) return -1;
//        else if (aA > aB) return 1;
//        else              return 0;

    }

    default int compareAngleToPoint(_Vec3 a, _Vec3 b, double x, double y, double z) {
        return compareAngleToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), x, y, z);
    }

    default int compareAngleToPoint(_Vec3 a, _Vec3 b, _Vec3 p) {
        return compareAngleToPoint(a.x(), a.y(), a.z(), b.x(), b.y(), b.z(), p.x(), p.y(), p.z());
    }
    */

}
