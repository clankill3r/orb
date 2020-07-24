package orb.____inner_core.geom;

/**
 * Created by doekewartena on 8/26/15.
 */
public interface _AABB_3D extends _AABB {

    double z1();
    double z2();

    default double cz() {
        return z1() + ( (z2()-z1()) / 2);
    }

    default double depth() {
        return z2() - z1();
    }

    default boolean contains_point(double x, double y, double z) {
        return x >= x1() && x <= x2() &&
               y >= y1() && y <= y2() &&
               z >= z1() && z <= z2();
    }

    default boolean contains_point(_Vec3 v) {
        return contains_point(v.x(), v.y(), v.z());
    }

    default boolean contains_aabb(double bX1, double bY1, double bZ1, double bX2, double bY2, double bZ2) {
        return bX1 >= x1() && bX2 <= x2() &&
               bY1 >= y1() && bY2 <= y2() &&
               bZ1 >= z1() && bZ2 <= z2();
    }

    default boolean contains_aabb(_AABB_3D aabb) {
        return contains_aabb(aabb.x1(), aabb.y1(), aabb.z1(), aabb.x2(), aabb.y2(), aabb.z2());
    }

    default boolean intersects_aabb(double bX1, double bY1, double bZ1, double bX2, double bY2, double bZ2) {
        return !(x2() < bX1 || bX2 < x1() ||
                 y2() < bY1 || bY2 < y1() ||
                 z2() < bZ1 || bZ2 < z1());
    }

    default boolean intersects_aabb(_AABB_3D aabb) {
        return intersects_aabb(aabb.x1(), aabb.y1(), aabb.z1(), aabb.x2(), aabb.y2(), aabb.z2());
    }

    default boolean fitsWithin_aabb(double bX1, double bY1, double bZ1, double bX2, double bY2, double bZ2) {
        return x1() >= bX1 && x2() <= bX2 &&
               y1() >= bY1 && y2() <= bY2 &&
               z1() >= bZ1 && z2() <= bZ2;
    }

    default boolean fitsWithin_aabb(_AABB_3D aabb) {
        return fitsWithin_aabb(aabb.x1(), aabb.y1(), aabb.z1(), aabb.x2(), aabb.y2(), aabb.z2());
    }

    default double distToPoint(double x, double y, double z) {
        double cx = Math.max(Math.min(x, x2()), x1());
        double cy = Math.max(Math.min(y, y2()), y1());
        double cz = Math.max(Math.min(z, z2()), z1());
        return Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy) + (z - cz) * (z - cz));
    }

    default double distToPoint(_Vec3 v) {
        return distToPoint(v.x(), v.y(), v.z());
    }

    default double distToPointSq(double x, double y, double z) {
        double cx = Math.max(Math.min(x, x2()), x1());
        double cy = Math.max(Math.min(y, y2()), y1());
        double cz = Math.max(Math.min(z, z2()), z1());
        return (x-cx)*(x-cx) + (y-cy)*(y-cy) + (z-cz)*(z-cz);
    }

    default double distToPointSq(_Vec3 v) {
        return distToPointSq(v.x(), v.y(), v.z());
    }


}
