package orb.___outer_core.util.compare.excluded;


import nl.doekewartena.orb.inner_core.util.function._GetDouble_T;

import java.util.Comparator;


/**
 * Created by doekewartena on 6/18/14.
 */

// todo optmise, now we make multiple calls to get the width and height within 1 method

public class OC_WHComparator<T> {


    _GetDouble_T<T> getW;
    _GetDouble_T<T> getH;


    public CompareW compareW;
    public CompareH compareH;

    public CompareWH compareWH;
    public CompareHW compareHW;

    // ?
    //public CompareDistToPoint2D compareDistToPoint;
    // ?
    //public CompareAngleToPoint2D compareAngleToPoint;

    public OC_WHComparator(_GetDouble_T<T> getW, _GetDouble_T<T> getH) {

        this.getW = getW;
        this.getH = getH;

        compareW = new CompareW();
        compareH = new CompareH();

        compareWH = new CompareWH();
        compareHW = new CompareHW();

        //compareDistToPoint = new CompareDistToPoint2D();

        //compareAngleToPoint = new CompareAngleToPoint2D();


    }


    // ========================================================================

    public class CompareW implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareW(a,b);
        }
    }

    // ========================================================================

    public class CompareH implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareH(a, b);
        }
    }


    // ========================================================================

    public class CompareWH implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareWH(a,b);
        }
    }

    // ========================================================================

    public class CompareHW implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareHW(a, b);
        }
    }


    // ========================================================================

    /*
    public class CompareDistToPoint2D implements Comparator<T> {

        float x, y;

        @Override
        public int compare(T a, T b) {
            return compareDistToPoint(a, b, x, y);
        }

        public CompareDistToPoint2D setPoint(float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }

    }
    */

    // ========================================================================

    /*
    public class CompareAngleToPoint2D implements Comparator<T> {

        float x, y;

        @Override
        public int compare(T a, T b) {
            return compareAngleToPoint(a, b, x, y);
        }

        public CompareAngleToPoint2D setPoint(float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }
    }
    */

    // ========================================================================





    // . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareW(T a, T b) {

        double aW = getW.val(a);
        double bW = getW.val(b);

        if      (aW < bW) return -1;
        else if (aW > bW) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareH(T a, T b) {

        double aH = getH.val(a);
        double bH = getH.val(b);

        if      (aH < bH) return -1;
        else if (aH > bH) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareWH(T a, T b) {
        int wDiff = compareW(a, b);
        if (wDiff != 0) return wDiff;
        else return compareH(a, b);
    }


    // . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareHW(T a, T b) {
        int hDiff = compareH(a, b);
        if (hDiff != 0) return hDiff;
        else return compareW(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .

    /*
    public int compareDistToPoint(T a, T b, float x, float y) {
        float dA = distSq(getXY.x(a), getXY.y(a), x, y);
        float dB = distSq(getXY.x(b), getXY.y(b), x, y);

        if (dA < dB)
            return -1;
        else if (dA > dB)
            return 1;
        else
            return 0;

    }
    */

    // . . . . . . . . . . . . . . . . . . . . . . . .

    /*
    private int compareAngleToPoint(T a, T b, float x, float y) {

        float aA = (float)Math.atan2(getXY.y(a)-y, getXY.x(a)-x);
        float aB = (float)Math.atan2(getXY.y(b)-y, getXY.x(b)-x);

        if (aA < aB)
            return -1;
        else if (aA > aB)
            return 1;
        else
            return 0;
    }
    */


    // ===========================================================================================================



}
