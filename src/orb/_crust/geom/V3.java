package orb._crust.geom;

import nl.doekewartena.orb.inner_core.geom._Vec3;

/**
 * Created by doekewartena on 8/19/15.
 */
public class V3 implements _Vec3<V3> {

    public double x, y, z;

    public V3() {

    }

    public V3(double x, double y) {
        set(x, y);
    }

    public V3(double x, double y, double z) {
        set(x, y, z);
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
//    public double getX(V3 v) {
//        return v.x;
//    }
//
//    @Override
//    public double getY(V3 v) {
//        return v.y;
//    }


    @Override
    public double z() {
        return z;
    }

//    @Override
//    public double getZ(V3 v3) {
//        return v3.z;
//    }

    @Override
    public V3 set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public V3 set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + ", " + z + "}";
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Float.floatToIntBits((float) x);
        result = 31 * result + Float.floatToIntBits((float) y);
        result = 31 * result + Float.floatToIntBits((float) z);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof _Vec3)) {
            return false;
        }
        _Vec3 v = (_Vec3) obj;
        return x == v.x() &&
               y == v.y() &&
               z == v.z();
    }


}
