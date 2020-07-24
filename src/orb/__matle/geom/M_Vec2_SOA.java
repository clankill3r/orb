package nl.doekewartena.orb.mantle.geom;

import nl.doekewartena.orb.inner_core.geom._Vec2;

import java.util.ArrayList;


/**
 * Created by doekewartena on 6/10/15.
 */

// unused atm, needs testing
// can we this way make a custom vec SOA? (sort off)
public class M_Vec2_SOA<C extends M_Vec2_SOA> {//extends OC_Vec2<C> {

    public double x_soa[];
    public double y_soa[];

    int useCount;

    ArrayList<OC_Vec2> free = new ArrayList<>();

    public M_Vec2_SOA(int N) {
        x_soa = new double[N];
        y_soa = new double[N];
    }


    // C2? (will be hard? ArrayList<C2>?)
    public OC_Vec2 getVec2(int idx) {

        if (!free.isEmpty()) {
            return free.remove(free.size() - 1).setIndex(idx);
        }
        return new OC_Vec2(idx);
    }


    // name free, return, release?
    public void returnVec2(OC_Vec2 toReturn) {
        toReturn.setIndex(-1);
        free.add(toReturn);
    }



    // how well can the compiler optimise if we use classes?

//    // somewhere else?
//    interface _Vec2 {
//
//    }

    // maybe name _V2 OC_V2 and __OC_V2?

    protected class OC_Vec2<C2 extends OC_Vec2> implements _Vec2<C2> { //implements _Vec2<C2> {

        int idx; // we should be able to change the idx for re use of the __OC_v2 obj

        //        public OC_soa_Vec2() {
//
//        }
//
//        public OC_soa_Vec2(double x, double y) {
//
//        }
        protected C2 setIndex(int idx) {
            this.idx = idx;
            return (C2) this;
        }


        protected OC_Vec2(int idx) {
            this.idx = idx;
        }


        @Override
        public double x() {
            return x_soa[idx];
        }

        @Override
        public double y() {
            return y_soa[idx];
        }

//        @Override
//        public double getX(C2 c2) {
//            return x_soa[idx];
//        }
//
//        @Override
//        public double getY(C2 c2) {
//            return y_soa[idx];
//        }

        @Override
        public C2 set(double x, double y) {
            x_soa[idx] = x;
            y_soa[idx] = y;
            return (C2) this;
        }


        @Override
        public String toString() {
            return "{" + x() + ", " + y() + "}";
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + Float.floatToIntBits((float) x());
            result = 31 * result + Float.floatToIntBits((float) y());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof _Vec2)) {
                return false;
            }
            _Vec2 v = (_Vec2) obj;
            return x() == v.x() && y() == v.y();
        }



    }



    /*
    // methods to add to all vectors for example
    // maybe therefor extend one vector for polymorphism!

    // imagine how easy it will be to change all vectors according to a angle!

    @Override
    public C add(double x, double y) {
        for (int i = 0; i < this.x.length; i++) {
            this.x[i] += x;
            this.y[i] += y;
        }
        return (C) this;
    }




   // get rid of methods with index? NO

    // makes it sense to return C?
    public C set(int i, double x, double y) {
        this.x[i] = x;
        this.y[i] = y;
        return (C) this;
    }

    public C add(int i, double x, double y) {
        this.x[i] += x;
        this.y[i] += y;
        return (C) this;
    }

    public C sub(int i, double x, double y) {
        this.x[i] -= x;
        this.y[i] -= y;
        return (C) this;
    }

    public C mult(int i, double n) {
        this.x[i] *= n;
        this.y[i] *= n;
        return (C) this;
    }

    public C div(int i, double n) {
        this.x[i] /= n;
        this.y[i] /= n;
        return (C) this;
    }

    public double mag(int i) {
        return java.lang.Math.sqrt(x[i] * x[i] + y[i] * y[i]);
    }

    public double magSq(int i) {
        return x[i]*x[i] + y[i]*y[i];
    }

    public double dist(int i, double tx, double ty) {
        // we need full import else it classes with the current class
        return OC_Math.dist(x[i], y[i], tx, ty);
    }


    public double distSq(int i, double tx, double ty) {
        // we need full import else it classes with the current class
        return OC_Math.distSq(x[i], y[i], tx, ty);
    }


    public double dot(int i, double x2, double y2) {
        return x[i]*x2 + y[i]*y2;
    }
     */


}