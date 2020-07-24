package orb.___outer_core.util.compare.excluded;



import orb.____inner_core.util.function._GetFloatT;

import java.util.Comparator;

import static orb.____inner_core.IC_Math.distSq;


/**
 * Created by doekewartena on 6/18/14.
 */


// todo compare manhattan dist?
// todo optmise, now we make multiple calls to get the width and height within 1 method

public class OC_XYZComparator<T> extends OC_ComparatorsXY<T> {

    _GetFloatT<T> getZ;


    public CompareZ compareZ;

    public CompareXZ compareXZ;
    public CompareYZ compareYZ;
    public CompareZY compareZY;
    public CompareZX compareZX;

    public CompareXYZ compareXYZ;
    public CompareXZY compareXZY;
    public CompareYXZ compareYXZ;
    public CompareYZX compareYZX;
    public CompareZXY compareZXY;
    public CompareZYX compareZYX;

    public CompareDistToPoint3D compareDistToPoint3D;


    public OC_XYZComparator(_GetFloatT<T> getX, _GetFloatT<T> getY, _GetFloatT<T> getZ) {
        super(getX, getY);

        this.getZ = getZ;

        compareZ = new CompareZ();

        compareXZ = new CompareXZ();
        compareYZ = new CompareYZ();
        compareZY = new CompareZY();
        compareZX = new CompareZX();

        compareXYZ = new CompareXYZ();
        compareXZY = new CompareXZY();
        compareYXZ = new CompareYXZ();
        compareYZX = new CompareYZX();
        compareZXY = new CompareZXY();
        compareZYX = new CompareZYX();

        compareDistToPoint3D = new CompareDistToPoint3D();
    }


    // ========================================================================

    public class CompareZ implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareZ(a,b);
        }
    }

    // ========================================================================

    public class CompareXZ implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareXZ(a, b);
        }
    }

    // ========================================================================

    public class CompareYZ implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareYZ(a, b);
        }
    }

    // ========================================================================

    public class CompareZY implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareZY(a, b);
        }
    }

    // ========================================================================

    public class CompareZX implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareZX(a, b);
        }
    }

    // ========================================================================

    public class CompareXYZ implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareXYZ(a, b);
        }
    }

    // ========================================================================


    public class CompareXZY implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareXZY(a, b);
        }
    }

    // ========================================================================
    public class CompareYXZ implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareYXZ(a, b);
        }
    }

    // ========================================================================
    public class CompareYZX implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareYZX(a, b);
        }
    }

    // ========================================================================
    public class CompareZXY implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareZXY(a, b);
        }
    }

    // ========================================================================

    public class CompareZYX implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareZYX(a, b);
        }
    }

    // ========================================================================


    public class CompareDistToPoint3D implements Comparator<T> {

        float x, y, z;

        @Override
        public int compare(T a, T b) {
            return compareDistToPoint3D(a, b, x, y, z);
        }

        public CompareDistToPoint3D setPoint(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

    }

    // ========================================================================



    public int compareZ(T a, T b) {

        final float aZ = getZ.val(a);
        final float bZ = getZ.val(b);

        if      (aZ < bZ) return -1;
        else if (aZ > bZ) return 1;
        else              return 0;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareXZ(T a, T b) {
        int xDiff = compareX(a, b);
        if (xDiff != 0) return xDiff;
        else return compareZ(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareYZ(T a, T b) {
        int yDiff = compareY(a, b);
        if (yDiff != 0) return yDiff;
        else return compareZ(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareZY(T a, T b) {
        int zDiff = compareZ(a, b);
        if (zDiff != 0) return zDiff;
        else return compareY(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public int compareZX(T a, T b) {
        int zDiff = compareZ(a, b);
        if (zDiff != 0) return zDiff;
        else return compareX(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public int compareXYZ(T a, T b) {
        int xDiff = compareX(a, b);
        if (xDiff != 0) return xDiff;
        else return compareYZ(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public int compareXZY(T a, T b) {
        int xDiff = compareX(a, b);
        if (xDiff != 0) return xDiff;
        else return compareZY(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public int compareYXZ(T a, T b) {
        int yDiff = compareY(a, b);
        if (yDiff != 0) return yDiff;
        else return compareXZ(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareYZX(T a, T b) {
        int yDiff = compareY(a, b);
        if (yDiff != 0) return yDiff;
        else return compareZX(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareZXY(T a, T b) {
        int zDiff = compareZ(a, b);
        if (zDiff != 0) return zDiff;
        else return compareXY(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public int compareZYX(T a, T b) {
        int zDiff = compareZ(a, b);
        if (zDiff != 0) return zDiff;
        else return compareYX(a, b);
    }


    // . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareDistToPoint3D(T a, T b, float x, float y, float z) {
        float dA = distSq(getX.val(a), getY.val(a), getZ.val(a), x, y, z);
        float dB = distSq(getX.val(b), getY.val(b), getZ.val(b), x, y, z);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


}