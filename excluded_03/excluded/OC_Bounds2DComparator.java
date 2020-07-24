package orb.___outer_core.util.compare.excluded;

import orb.____inner_core.util.function._GetFloat_T;

import java.util.Comparator;


/**
 * Created by doekewartena on 6/18/14.
 */

// WIP
// area etc?

public class OC_Bounds2DComparator<T> {


    _GetFloat_T<T> getX1;
    _GetFloat_T<T> getX2;
    _GetFloat_T<T> getY1;
    _GetFloat_T<T> getY2;


    public CompareTop compareTop;
    public CompareLeft compareLeft;
    public CompareBottom compareBottom;
    public CompareRight compareRight;



    public OC_Bounds2DComparator(_GetFloat_T<T> getX1, _GetFloat_T<T> getY1, _GetFloat_T<T> getX2, _GetFloat_T<T> getY2) {

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

        final float aY = getY1.val(a);
        final float bY = getY1.val(b);

        if      (aY < bY) return -1;
        else if (aY > bY) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareLeft(T a, T b) {

        final float aX = getX1.val(a);
        final float bX = getX1.val(b);

        if      (aX < bX) return -1;
        else if (aX > bX) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareBottom(T a, T b) {

        final float aY = getY2.val(a);
        final float bY = getY2.val(b);

        if      (aY < bY) return -1;
        else if (aY > bY) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public int compareRight(T a, T b) {

        final float aX = getX2.val(a);
        final float bX = getX2.val(b);

        if      (aX < bX) return -1;
        else if (aX > bX) return 1;
        else              return 0;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .





}
