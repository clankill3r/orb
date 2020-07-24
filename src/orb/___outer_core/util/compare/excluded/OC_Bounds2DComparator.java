package nl.doekewartena.orb.outer_core.util.compare.excluded;

import nl.doekewartena.orb.inner_core.util.function._GetDouble_T;

import java.util.Comparator;


/**
 * Created by doekewartena on 6/18/14.
 */

// WIP
// area etc?

public class OC_Bounds2DComparator<T> {


    _GetDouble_T<T> getX1;
    _GetDouble_T<T> getX2;
    _GetDouble_T<T> getY1;
    _GetDouble_T<T> getY2;


    public CompareTop compareTop;
    public CompareLeft compareLeft;
    public CompareBottom compareBottom;
    public CompareRight compareRight;



    public OC_Bounds2DComparator(_GetDouble_T<T> getX1, _GetDouble_T<T> getY1, _GetDouble_T<T> getX2, _GetDouble_T<T> getY2) {

        this.getX1 = getX1;
        this.getY1 = getY1;
        this.getX2 = getX2;
        this.getY2 = getY2;

       //init();

        compareTop = new CompareTop();
        compareLeft = new CompareLeft();
        compareBottom = new CompareBottom();
        compareRight = new CompareRight();

    }
    /*
    public Bounds2DComparator(GetBounds2D<T> getBounds) {
        this.getBounds = getBounds;
        init();
    }
    */

    /*
    protected void init() {
        compareTop = new CompareTop();
        compareLeft = new CompareLeft();
        compareBottom = new CompareBottom();
        compareRight = new CompareRight();
    }
     */

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public class CompareTop implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareTop(a, b);
        }


    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public class CompareLeft implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareLeft(a, b);
        }
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public class CompareBottom implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareBottom(a, b);
        }
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public class CompareRight implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            return compareRight(a, b);
        }
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareTop(T a, T b) {

        final double aY = getY1.val(a);
        final double bY = getY1.val(b);

        if      (aY < bY) return -1;
        else if (aY > bY) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareLeft(T a, T b) {

        final double aX = getX1.val(a);
        final double bX = getX1.val(b);

        if      (aX < bX) return -1;
        else if (aX > bX) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareBottom(T a, T b) {

        final double aY = getY2.val(a);
        final double bY = getY2.val(b);

        if      (aY < bY) return -1;
        else if (aY > bY) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareRight(T a, T b) {

        final double aX = getX2.val(a);
        final double bX = getX2.val(b);

        if      (aX < bX) return -1;
        else if (aX > bX) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .





}
