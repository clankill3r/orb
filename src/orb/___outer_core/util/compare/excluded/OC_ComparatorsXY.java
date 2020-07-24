package orb.___outer_core.util.compare.excluded;


import nl.doekewartena.orb.inner_core.util.compare.excluded._CompareXY_TT;
import nl.doekewartena.orb.inner_core.util.function._GetDoubleT;

import java.util.Comparator;


/**
 * Created by doekewartena on 6/18/14.
 */

// todo compare manhattan dist?
// todo optmise, now we make multiple calls to get the width and height within 1 method
public class OC_ComparatorsXY<T> implements _CompareXY_TT<T> {


    _GetDoubleT<T> getX;
    _GetDoubleT<T> getY;

    /*
    public CompareX compare;
    public CompareY compareY;

    public CompareXY compareXY;
    public CompareYX compareYX;


    */

    // or do we prefer a class so it's not so anonymous?
    public Comparator<T> compareX = this::compareX;
    public Comparator<T> compareY = this::compareY;

    public Comparator<T> compareXY = this::compareXY;
    public Comparator<T> compareYX = this::compareYX;

    // those 2 require a point to compare to...
    //public Comparator<T> compareDistToPoint = this::compareDistToPoint;
    //public Comparator<T> CompareAngleToPoint = this::CompareAngleToPoint;

    public CompareDistToPoint compareDistToPoint2D;
    public CompareAngleToPoint compareAngleToPoint2D;

    public OC_ComparatorsXY(_GetDoubleT<T> getX, _GetDoubleT<T> getY) {

        this.getX = getX;
        this.getY = getY;
        /*
        compare = new CompareX();
        compareY = new CompareY();

        compareXY = new CompareXY();
        compareYX = new CompareYX();
        */
        compareDistToPoint2D = new CompareDistToPoint();

        compareAngleToPoint2D = new CompareAngleToPoint();
    }

    @Override
    public double getX(T t) {
        return getX.val(t);
    }

    @Override
    public double getY(T t) {
        return getY.val(t);
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

        double x, y;

        @Override
        public int compare(T a, T b) {
            return compareDistToPoint(a, b, x, y);
        }

        public CompareDistToPoint setPoint(double x, double y) {
            this.x = x;
            this.y = y;
            return this;
        }

    }


    // ========================================================================


    public class CompareAngleToPoint implements Comparator<T> {

        double x, y;

        @Override
        public int compare(T a, T b) {
            return compareAngleToPoint(a, b, x, y);
        }

        public CompareAngleToPoint setPoint(double x, double y) {
            this.x = x;
            this.y = y;
            return this;
        }
    }

    // ========================================================================



    /*

    public int compare(T a, T b) {

        final double aX = getX.val(a);
        final double bX = getX.val(b);

        if      (aX < bX) return -1;
        else if (aX > bX) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareY(T a, T b) {

        final double aY = getY.val(a);
        final double bY = getY.val(b);

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


    public int compareDistToPoint(T a, T b, double x, double y) {
        final double dA = OC_Math.distSq(getX.val(a), getY.val(a), x, y);
        final double dB = OC_Math.distSq(getX.val(b), getY.val(b), x, y);

        if      (dA < dB) return -1;
        else if (dA > dB) return 1;
        else              return 0;

    }


    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareAngleToPoint(T a, T b, double x, double y) {

        final double aA = java.lang.Math.atan2(getY.val(a)-y, getX.val(a)-x);
        final double aB = java.lang.Math.atan2(getY.val(b)-y, getX.val(b)-x);

        if      (aA < aB) return -1;
        else if (aA > aB) return 1;
        else              return 0;
    }
    */



}
