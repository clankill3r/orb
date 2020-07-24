package orb.__mantle.geom;

import nl.doekewartena.orb.inner_core.geom._Triangle;

/**
 * Created by doekewartena on 5/23/15.
 */

// do we want this in the mantle?
// this class is only used in convex hull atm
// todo, make an interface with default methods?
public class M_Triangle implements _Triangle {//, Comparable<OC_Triangle> {

    public double x1, y1, x2, y2, x3, y3;

    // no this will take up space!
    /*
    _Vec2 p1 = new _Vec2() {
        @Override
        public double x() {return x1;}
        @Override
        public double y() {return y1;}
        @Override
        public _Vec2 set(double x, double y) {
            OC_Triangle.this.x1 = x;
            OC_Triangle.this.y1 = y;
            return this;
        }
    };

    _Vec2 p2 = new _Vec2() {
        @Override
        public double x() {return x2;}
        @Override
        public double y() {return y2;}
        @Override
        public _Vec2 set(double x, double y) {
            OC_Triangle.this.x2 = x;
            OC_Triangle.this.y2 = y;
            return this;
        }
    };

    _Vec2 p3 = new _Vec2() {
        @Override
        public double x() {return x3;}
        @Override
        public double y() {return y3;}
        @Override
        public _Vec2 set(double x, double y) {
            OC_Triangle.this.x3 = x;
            OC_Triangle.this.y3 = y;
            return this;
        }
    };
    */


    public M_Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double x1() {
        return x1;
    }

    @Override
    public double y1() {
        return y1;
    }

    @Override
    public double x2() {
        return x2;
    }

    @Override
    public double y2() {
        return y2;
    }

    @Override
    public double x3() {
        return x3;
    }

    @Override
    public double y3() {
        return y3;
    }



    /*
    // todo, more compare methods? Or remove those?
    @Override
    public int compareTo(OC_Triangle t) {

        double a1 = area();
        double a2 = t.area();

        if      (a1 > a2) return 1;
        else if (a1 < a2) return -1;
        else              return 0;

    }
    */

}

