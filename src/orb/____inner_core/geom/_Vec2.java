package orb.____inner_core.geom;


import orb.____inner_core.IC_Math;
//import orb.____inner_core.util.compare.excluded._CompareXY_T;

/**
 * Created by doekewartena on 6/10/15.
 */
/*

todo:

cross
norm
heading?
rotate?
lerp
angleBetween


 clone?


 // aimAt(_Vec2)


 */

public interface _Vec2<C extends _Vec2> { //extends _CompareXY_T<C> {

    // interface!!
    float x();
    float y();


    C set(float x, float y);

    default C foo(_Vec2 v) {
        return (C) this;
    }

    default C set(_Vec2 v) { // or  public C set(OC_Vec2<?> v) {
        set(v.x(), v.y());
        return (C) this;
    }

    default C add(float x, float y) {
        set(x()+x, y()+y);
        return (C) this;
    }

    default C add(_Vec2 o) {
        add(o.x(), o.y());
        return (C) this;
    }

    default C sub(float x, float y) {
        set(x() - x, y() - y);
        return (C) this;
    }

    default C sub(_Vec2 o) {
        sub(o.x(), o.y());
        return (C) this;
    }

    default C mult(float n) {
        set(x() * n, y() * n);
        return (C) this;
    }

    default C div(float n) {
        set(x()/n, y()/n);
        return (C) this;
    }

    default float mag() {
        return (float)Math.sqrt(x() * x() + y() * y());
    }

    default float magSq() {
        return x()*x() + y()*y();
    }

    default float dist(float tx, float ty) {
        // we need full import else it classes with the current class
        return IC_Math.dist(x(), y(), tx, ty);
    }

    default float dist(_Vec2 o) {
        return dist(o.x(), o.y());
    }

    default float distSq(float tx, float ty) {
        // we need full import else it classes with the current class
        return IC_Math.distSq(x(), y(), tx, ty);
    }

    default float distSq(_Vec2 o) {
        return distSq(o.x(), o.y());
    }

    default float dot(float x2, float y2) {
        return x()*x2 + y()*y2;
    }

    default float dot(_Vec2 o) {
        return dot(o.x(), o.y());
    }

    // can only be if we see it from the 0,0 position?
    // this is probably not so useful
    /*
    default C aimAt(_Vec2 v) {

        float mag = magSq();
        set(v);
        norm();
        mult(mag);

        return (C) this;
    }
    */

//    @Override
//    String toString();
//
//    @Override
//    int hashCode();
//
//    @Override
//    boolean equals(Object obj);


}