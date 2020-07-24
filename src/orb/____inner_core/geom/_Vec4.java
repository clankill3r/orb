package nl.doekewartena.orb.inner_core.geom;


/**
 * Created by doekewartena on 6/10/15.
 */

public interface _Vec4<C extends _Vec4> extends _Vec3<C> {

    double w();

    C set(double x, double y, double z, double w);

    default C set(_Vec4 o) {
        set(o.x(), o.y(), o.z(), o.w());
        return (C) this;
    }


    default C add(double x, double y, double z, double w) {
        set(x() + x, y() + y, z() + z, w() + w);
        return (C) this;
    }

    default C add(_Vec4 o) {
        add(o.x(), o.y(), o.z(), o.w());
        return (C) this;
    }

    default C sub(double x, double y, double z, double w) {
        set(x()-x, y()-y, z()-z, w()-w);
        return (C) this;
    }

    default C sub(_Vec4 o) {
        sub(o.x(), o.y(), o.z(), o.w());
        return (C) this;
    }


    default C mult(double n) {
        set(x()*n, y()*n, z()*n, w()*n);
        return (C) this;
    }

    default C div(double n) {
        set(x()/n, y()/n, z()/n, w()/n);
        return (C) this;
    }

    default double mag() {
        return Math.sqrt(x() * x() + y() * y() + z() * z() + w() * w());
    }

    default double magSq() {
        return x()*x() + y()*y() + z()*z() + w()*w();
    }

    default double dist(double x2, double y2, double z2, double w2) {
        double dx = x() - x2;
        double dy = y() - y2;
        double dz = z() - z2;
        double dw = w() - w2;
        return Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
    }

    default double dist(_Vec4 o) {
        return dist(o.x(), o.y(), o.z(), o.w());
    }

    default double distSq(double x2, double y2, double z2, double w2) {
        double dx = x() - x2;
        double dy = y() - y2;
        double dz = z() - z2;
        double dw = w() - w2;
        return dx*dx + dy*dy + dz*dz + dw*dw;
    }

    default double distSq(_Vec4 o) {
        return distSq(o.x(), o.y(), o.z(), o.w());
    }

    default double dot(double x2, double y2, double z2, double w2) {
        return x()*x2 + y()*y2 + z()*z2 + w()*w2;
    }

    default double dot(_Vec4 o) {
        return dot(o.x(), o.y(), o.z(), o.w());
    }

}
