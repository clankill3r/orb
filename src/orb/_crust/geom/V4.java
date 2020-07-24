package orb._crust.geom;

import nl.doekewartena.orb.inner_core.geom._Vec4;

/**
 * Created by doekewartena on 8/19/15.
 */
public class V4 implements _Vec4<V4> {

    public double x, y, z, w;

    public V4() {
    }

    public V4(double x, double y) {
        set(x, y);
    }

    public V4(double x, double y, double z) {
        set(x, y, z);
    }

    public V4(double x, double y, double z, double w) {
        set(x, y, z, w);
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    // todo, we should get rid of either one

//    @Override
//    public double getX(V4 v) {
//        return v.x;
//    }
//
//    @Override
//    public double getY(V4 v) {
//        return v.y;
//    }

    @Override
    public double z() {
        return z;
    }

//    @Override
//    public double getZ(V4 v4) {
//        return v4.z;
//    }

    @Override
    public double w() {
        return w;
    }


    @Override
    public V4 set(double x, double y) {
        set(x, y);
        return this;
    }

    @Override
    public V4 set(double x, double y, double z) {
        set(x, y, z);
        return this;
    }

    @Override
    public V4 set(double x, double y, double z, double w) {
        set(x, y, z, w);
        return this;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + ", " + z + ", " + w +  "}";
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Float.floatToIntBits((float) x);
        result = 31 * result + Float.floatToIntBits((float) y);
        result = 31 * result + Float.floatToIntBits((float) z);
        result = 31 * result + Float.floatToIntBits((float) w);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof _Vec4)) {
            return false;
        }
        _Vec4 v = (_Vec4) obj;
        return x == v.x() &&
               y == v.y() &&
               z == v.z() &&
               w == v.w();
    }


}
