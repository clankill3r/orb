package orb.____inner_core.geom;


/**
 * Created by doekewartena on 6/10/15.
 */

public interface _Vec4<C extends _Vec4> extends _Vec3<C> {

    float w();

    C set(float x, float y, float z, float w);

    default C set(_Vec4 o) {
        set(o.x(), o.y(), o.z(), o.w());
        return (C) this;
    }


    default C add(float x, float y, float z, float w) {
        set(x() + x, y() + y, z() + z, w() + w);
        return (C) this;
    }

    default C add(_Vec4 o) {
        add(o.x(), o.y(), o.z(), o.w());
        return (C) this;
    }

    default C sub(float x, float y, float z, float w) {
        set(x()-x, y()-y, z()-z, w()-w);
        return (C) this;
    }

    default C sub(_Vec4 o) {
        sub(o.x(), o.y(), o.z(), o.w());
        return (C) this;
    }


    default C mult(float n) {
        set(x()*n, y()*n, z()*n, w()*n);
        return (C) this;
    }

    default C div(float n) {
        set(x()/n, y()/n, z()/n, w()/n);
        return (C) this;
    }

    default float mag() {
        return (float)Math.sqrt(x() * x() + y() * y() + z() * z() + w() * w());
    }

    default float magSq() {
        return x()*x() + y()*y() + z()*z() + w()*w();
    }

    default float dist(float x2, float y2, float z2, float w2) {
        float dx = x() - x2;
        float dy = y() - y2;
        float dz = z() - z2;
        float dw = w() - w2;
        return (float)Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
    }

    default float dist(_Vec4 o) {
        return dist(o.x(), o.y(), o.z(), o.w());
    }

    default float distSq(float x2, float y2, float z2, float w2) {
        float dx = x() - x2;
        float dy = y() - y2;
        float dz = z() - z2;
        float dw = w() - w2;
        return dx*dx + dy*dy + dz*dz + dw*dw;
    }

    default float distSq(_Vec4 o) {
        return distSq(o.x(), o.y(), o.z(), o.w());
    }

    default float dot(float x2, float y2, float z2, float w2) {
        return x()*x2 + y()*y2 + z()*z2 + w()*w2;
    }

    default float dot(_Vec4 o) {
        return dot(o.x(), o.y(), o.z(), o.w());
    }

}
