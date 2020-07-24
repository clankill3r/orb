package orb.____inner_core.geom;


import orb.____inner_core.IC_Math;
//import orb.____inner_core.util.compare.excluded._CompareXYZ_T;

/**
 * Created by doekewartena on 6/10/15.
 */


public interface _Vec3<C extends _Vec3> extends _Vec2<C> { //}, _CompareXYZ_T<C> {


    float z();

    C set(float x, float y, float z);

    default C set(_Vec3 o) {
        set(o.x(), o.y(), o.z());
        return (C) this;
    }

    default C add(float x, float y, float z) {
        set(x()+x, y()+y, z()+z);
        return (C) this;
    }

    default C add(_Vec3 o) {
        add(o.x(), o.y(), o.z());
        return (C) this;
    }


    default C sub(float x, float y, float z) {
        set(x()-x, y()-y, z()-z);
        return (C) this;
    }

    default C sub(_Vec3 o) {
        set(x()-o.x(), y()-o.y(), z()-o.z());
        return (C) this;
    }

    @Override
    default C mult(float n) {
        set(x()*n, y()*n, z()*n);
        return (C) this;
    }

    @Override
    default C div(float n) {
        set(x()/n, y()/n, z()/n);
        return (C) this;
    }

    @Override
    default float mag() {
        return (float)Math.sqrt(x()*x() + y()*y() + z()*z());
    }

    @Override
    default float magSq() {
        return x()*x() + y()*y() + z()*z();
    }

    default float dist(float x2, float y2, float z2) {
        return IC_Math.dist(x(), y(), z(), x2, y2, z2);
    }

    default float dist(_Vec3 o) {
        return dist(o.x(), o.y(), o.z());
    }

    default float distSq(float x2, float y2, float z2) {
        return IC_Math.distSq(x(), y(), z(), x2, y2, z2);
    }

    default float distSq(_Vec3 o) {
        return distSq(o.x(), o.y(), o.z());
    }

    default float dot(float x2, float y2, float z2) {
        return x()*x2 + y()*y2 + z()*z2;
    }

    default float dot(_Vec3 o) {
        return dot(o.x(), o.y(), o.z());
    }

//    @Override
//    String toString();
//
//    @Override
//    int hashCode();
//
//    @Override
//    boolean equals(Object obj);



}

