package orb._crust.geom;


import orb.____inner_core.geom._Vec2;

/**
 * Created by doekewartena on 8/19/15.
 */
public class V2 implements _Vec2<V2> {

    public float x, y;

    public V2() {

    }

    public V2(float x, float y) {
        set(x, y);
    }

    @Override
    public float x() {
        return x;
    }

    @Override
    public float y() {
        return y;
    }

    // todo, we should get rid of either one

//    @Override
//    public float getX(V2 v) {
//        return v.x;
//    }
//
//    @Override
//    public float getY(V2 v) {
//        return v.y;
//    }

    @Override
    public V2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Float.floatToIntBits((float) x);
        result = 31 * result + Float.floatToIntBits((float) y);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof _Vec2)) {
            return false;
        }
        _Vec2 v = (_Vec2) obj;
        return x == v.x() && y == v.y();
    }



}
