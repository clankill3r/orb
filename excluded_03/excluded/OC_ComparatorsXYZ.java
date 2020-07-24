package orb.___outer_core.util.compare.excluded;


import orb.____inner_core.util.compare.excluded._CompareXYZ_TT;
import orb.____inner_core.util.function._GetFloatT;

import java.util.Comparator;


/**
 * Created by doekewartena on 6/18/14.
 */

// see XY version for notes
public class OC_ComparatorsXYZ<T> extends OC_ComparatorsXY<T> implements _CompareXYZ_TT<T> {


    _GetFloatT<T> getZ;


    public Comparator<T> compareX = this::compareX;
    public Comparator<T> compareY = this::compareY;

    public Comparator<T> compareXY = this::compareXY;
    public Comparator<T> compareYX = this::compareYX;

    // those 2 require a point to compare to...
    //public Comparator<T> compareDistToPoint = this::compareDistToPoint;
    //public Comparator<T> CompareAngleToPoint = this::CompareAngleToPoint;

    public CompareDistToPoint compareDistToPoint3D;
    public CompareAngleToPoint compareAngleToPoint3D;

    public OC_ComparatorsXYZ(_GetFloatT<T> getX, _GetFloatT<T> getY, _GetFloatT<T> getZ) {

        super(getX, getY);
        this.getZ = getZ;

        compareDistToPoint3D = new CompareDistToPoint();
        compareAngleToPoint3D = new CompareAngleToPoint();
    }

    @Override
    public float getX(T t) {
        return getX.val(t);
    }

    @Override
    public float getY(T t) {
        return getY.val(t);
    }

    @Override
    public float getZ(T t) {
        return getZ.val(t);
    }


    // ========================================================================
    /*
    public class CompareX implements Comparator<T> {
        @Override
        public int compare(T a, T b) {
            return compare(a,b);
        }
    }

    // ========================================================================

    public class CompareY implements Comparator<T> {
        @Override
        public int compare(T a, T b) {
            return compareY(a, b);
        }
    }

    // ========================================================================

    public class CompareXY implements Comparator<T> {
        @Override
        public int compare(T a, T b) {
            return compareXY(a, b);
        }
    }

    // ========================================================================

    public class CompareYX implements Comparator<T> {
        @Override
        public int compare(T a, T b) {
            return compareYX(a, b);
        }
    }
    */

    // ========================================================================


    public class CompareDistToPoint implements Comparator<T> {

        float x, y, z;

        @Override
        public int compare(T a, T b) {
            return compareDistToPoint(a, b, x, y, z);
        }

        public CompareDistToPoint setPoint(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

    }


    // ========================================================================


    public class CompareAngleToPoint implements Comparator<T> {

        float x, y, z;

        @Override
        public int compare(T a, T b) {
            return compareAngleToPoint(a, b, x, y);
        }

        public CompareAngleToPoint setPoint(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }
    }

    // ========================================================================



    /*

    public int compare(T a, T b) {

        final float aX = getX.val(a);
        final float bX = getX.val(b);

        if      (aX < bX) return -1;
        else if (aX > bX) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareY(T a, T b) {

        final float aY = getY.val(a);
        final float bY = getY.val(b);

        if      (aY < bY) return -1;
        else if (aY > bY) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareXY(T a, T b) {
        int xDiff = compare(a, b);
        if (xDiff != 0) return xDiff;
        else return compareY(a, b);
    }


    // . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareYX(T a, T b) {
        int yDiff = compareY(a, b);
        if (yDiff != 0) return yDiff;
        else return compare(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareDistToPoint(T a, T b, float x, float y) {
        final float dA = OC_Math.distSq(getX.val(a), getY.val(a), x, y);
        final float dB = OC_Math.distSq(getX.val(b), getY.val(b), x, y);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }


    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareAngleToPoint(T a, T b, float x, float y) {

        final float aA = atan2(getY.val(a)-y, getX.val(a)-x);
        final float aB = atan2(getY.val(b)-y, getX.val(b)-x);

        if      (aA < aB) return -1;
        else if (aA > aB) return 1;
        else              return 0;
    }
    */



}
