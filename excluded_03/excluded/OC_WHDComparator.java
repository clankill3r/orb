package orb.___outer_core.util.compare.excluded;


import orb.____inner_core.util.function._GetFloat_T;

import java.util.Comparator;


/**
 * Created by doekewartena on 6/18/14.
 */


// todo compare manhattan dist?
// todo optmise, now we make multiple calls to get the width and height within 1 method

public class OC_WHDComparator<T> extends OC_WHComparator<T> {

    _GetFloat_T<T> getD;

    public CompareD compareD;

    public CompareWD compareWD;
    public CompareHD compareHD;
    public CompareDH compareDH;
    public CompareDW compareDW;

    public CompareWHD compareWHD;
    public CompareWDH compareWDH;
    public CompareHWD compareHWD;
    public CompareHDW compareHDW;
    public CompareDWH compareDWH;
    public CompareDHW compareDHW;

    //public CompareDistToPoint3D compareDistToPoint;


    public OC_WHDComparator(_GetFloat_T<T> getW, _GetFloat_T<T> getH, _GetFloat_T<T> getD) {
        super(getW, getH);

        this.getD = getD;

        compareD = new CompareD();

        compareWD = new CompareWD();
        compareHD = new CompareHD();
        compareDH = new CompareDH();
        compareDW = new CompareDW();

        compareWHD = new CompareWHD();
        compareWDH = new CompareWDH();
        compareHWD = new CompareHWD();
        compareHDW = new CompareHDW();
        compareDWH = new CompareDWH();
        compareDHW = new CompareDHW();

        //compareDistToPoint = new CompareDistToPoint3D();
    }


    // ========================================================================

    public class CompareD implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareD(a,b);
        }
    }

    // ========================================================================

    public class CompareWD implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareWD(a, b);
        }
    }

    // ========================================================================

    public class CompareHD implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareHD(a, b);
        }
    }

    // ========================================================================

    public class CompareDH implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareDH(a, b);
        }
    }

    // ========================================================================

    public class CompareDW implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareDW(a, b);
        }
    }

    // ========================================================================

    public class CompareWHD implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareWHD(a, b);
        }
    }

    // ========================================================================


    public class CompareWDH implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareWDH(a, b);
        }
    }

    // ========================================================================
    public class CompareHWD implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareHWD(a, b);
        }
    }

    // ========================================================================
    public class CompareHDW implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareHDW(a, b);
        }
    }

    // ========================================================================
    public class CompareDWH implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareDWH(a, b);
        }
    }

    // ========================================================================

    public class CompareDHW implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareDHW(a, b);
        }
    }

    // ========================================================================

    /*
    public class CompareDistToPoint3D implements Comparator<T> {

        float x, y, z;

        @Override
        public int compare(T a, T b) {
            return compareDistToPoint(a, b, x, y, z);
        }

        public CompareDistToPoint3D setPoint(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

    }
    */

    // ========================================================================



    public int compareD(T a, T b) {
        if (getD.val(a) < getD.val(b))
            return -1;
        else if (getD.val(a) > getD.val(b))
            return 1;
        else
            return 0;
    }



    // . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareWD(T a, T b) {
        int xDiff = compareW(a, b);
        if (xDiff != 0) return xDiff;
        else return compareD(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareHD(T a, T b) {
        int yDiff = compareH(a, b);
        if (yDiff != 0) return yDiff;
        else return compareD(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareDH(T a, T b) {
        int zDiff = compareD(a, b);
        if (zDiff != 0) return zDiff;
        else return compareH(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public int compareDW(T a, T b) {
        int zDiff = compareD(a, b);
        if (zDiff != 0) return zDiff;
        else return compareW(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public int compareWHD(T a, T b) {
        int xDiff = compareW(a, b);
        if (xDiff != 0) return xDiff;
        else return compareHD(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public int compareWDH(T a, T b) {
        int xDiff = compareW(a, b);
        if (xDiff != 0) return xDiff;
        else return compareDH(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public int compareHWD(T a, T b) {
        int yDiff = compareH(a, b);
        if (yDiff != 0) return yDiff;
        else return compareWD(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .


    public int compareHDW(T a, T b) {
        int yDiff = compareH(a, b);
        if (yDiff != 0) return yDiff;
        else return compareDW(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .




    public int compareDWH(T a, T b) {
        int zDiff = compareD(a, b);
        if (zDiff != 0) return zDiff;
        else return compareWH(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . .



    public int compareDHW(T a, T b) {
        int zDiff = compareD(a, b);
        if (zDiff != 0) return zDiff;
        else return compareHW(a, b);
    }


    // . . . . . . . . . . . . . . . . . . . . . . . .

    /*
    public int compareDistToPoint(T a, T b, float x, float y, float z) {
        float dA = distSq(getWHD.width(a), getWHD.height(a), getWHD.depth(a), x, y, z);
        float dB = distSq(getWHD.width(b), getWHD.height(b), getWHD.depth(b), x, y, z);

        if (dA < dB)
            return -1;
        else if (dA > dB)
            return 1;
        else
            return 0;

    }
    */
    // . . . . . . . . . . . . . . . . . . . . . . . .


}