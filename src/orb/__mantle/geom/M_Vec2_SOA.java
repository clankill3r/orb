package orb.__mantle.geom;

import orb.____inner_core.geom._Vec2;

import java.util.ArrayList;


/**
 * Created by doekewartena on 6/10/15.
 */

// unused atm, needs testing
// can we this way make a custom vec SOA? (sort off)
public class M_Vec2_SOA<C extends M_Vec2_SOA> {//extends OC_Vec2<C> {

    public float x_soa[];
    public float y_soa[];

    int useCount;

    ArrayList<OC_Vec2> free = new ArrayList<>();

    public M_Vec2_SOA(int N) {
        x_soa = new float[N];
        y_soa = new float[N];
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
//        public OC_soa_Vec2(float x, float y) {
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
        public float x() {
            return x_soa[idx];
        }

        @Override
        public float y() {
            return y_soa[idx];
        }

//        @Override
//        public float getX(C2 c2) {
//            return x_soa[idx];
//        }
//
//        @Override
//        public float getY(C2 c2) {
//            return y_soa[idx];
//        }

        @Override
        public C2 set(float x, float y) {
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
    public C add(float x, float y) {
        for (int i = 0; i < this.x.length; i++) {
            this.x[i] += x;
            this.y[i] += y;
        }
        return (C) this;
    }




   // get rid of methods with index? NO

    // makes it sense to return C?
    public C set(int i, float x, float y) {
        this.x[i] = x;
        this.y[i] = y;
        return (C) this;
    }

    public C add(int i, float x, float y) {
        this.x[i] += x;
        this.y[i] += y;
        return (C) this;
    }

    public C sub(int i, float x, float y) {
        this.x[i] -= x;
        this.y[i] -= y;
        return (C) this;
    }

    public C mult(int i, float n) {
        this.x[i] *= n;
        this.y[i] *= n;
        return (C) this;
    }

    public C div(int i, float n) {
        this.x[i] /= n;
        this.y[i] /= n;
        return (C) this;
    }

    public float mag(int i) {
        return sqrt(x[i] * x[i] + y[i] * y[i]);
    }

    public float magSq(int i) {
        return x[i]*x[i] + y[i]*y[i];
    }

    public float dist(int i, float tx, float ty) {
        // we need full import else it classes with the current class
        return OC_Math.dist(x[i], y[i], tx, ty);
    }


    public float distSq(int i, float tx, float ty) {
        // we need full import else it classes with the current class
        return OC_Math.distSq(x[i], y[i], tx, ty);
    }


    public float dot(int i, float x2, float y2) {
        return x[i]*x2 + y[i]*y2;
    }
     */


}