package orb.____inner_core.geom;


import nl.doekewartena.orb.inner_core.IC_Math;
//import nl.doekewartena.orb.inner_core.util.compare.excluded._CompareXYZ_T;

/**
 * Created by doekewartena on 6/10/15.
 */


public interface _Vec3<C extends _Vec3> extends _Vec2<C> { //}, _CompareXYZ_T<C> {


    double z();

    C set(double x, double y, double z);

    default C set(_Vec3 o) {
        set(o.x(), o.y(), o.z());
        return (C) this;
    }

    default C add(double x, double y, double z) {
        set(x()+x, y()+y, z()+z);
        return (C) this;
    }

    default C add(_Vec3 o) {
        add(o.x(), o.y(), o.z());
        return (C) this;
    }


    default C sub(double x, double y, double z) {
        set(x()-x, y()-y, z()-z);
        return (C) this;
    }

    default C sub(_Vec3 o) {
        set(x()-o.x(), y()-o.y(), z()-o.z());
        return (C) this;
    }

    @Override
    default C mult(double n) {
        set(x()*n, y()*n, z()*n);
        return (C) this;
    }

    @Override
    default C div(double n) {
        set(x()/n, y()/n, z()/n);
        return (C) this;
    }

    @Override
    default double mag() {
        return Math.sqrt(x()*x() + y()*y() + z()*z());
    }

    @Override
    default double magSq() {
        return x()*x() + y()*y() + z()*z();
    }

    default double dist(double x2, double y2, double z2) {
        return IC_Math.dist(x(), y(), z(), x2, y2, z2);
    }

    default double dist(_Vec3 o) {
        return dist(o.x(), o.y(), o.z());
    }

    default double distSq(double x2, double y2, double z2) {
        return IC_Math.distSq(x(), y(), z(), x2, y2, z2);
    }

    default double distSq(_Vec3 o) {
        return distSq(o.x(), o.y(), o.z());
    }

    default double dot(double x2, double y2, double z2) {
        return x()*x2 + y()*y2 + z()*z2;
    }

    default double dot(_Vec3 o) {
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

