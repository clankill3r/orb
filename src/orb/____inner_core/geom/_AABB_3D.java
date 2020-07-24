package orb.____inner_core.geom;


import static orb.____inner_core.IC_Math.*;

/**
 * Created by doekewartena on 8/26/15.
 */
public interface _AABB_3D extends _AABB {

    float z1();
    float z2();

    default float cz() {
        return z1() + ( (z2()-z1()) / 2);
    }

    default float depth() {
        return z2() - z1();
    }

    default boolean contains_point(float x, float y, float z) {
        return x >= x1() && x <= x2() &&
               y >= y1() && y <= y2() &&
               z >= z1() && z <= z2();
    }

    default boolean contains_point(_Vec3 v) {
        return contains_point(v.x(), v.y(), v.z());
    }

    default boolean contains_aabb(float bX1, float bY1, float bZ1, float bX2, float bY2, float bZ2) {
        return bX1 >= x1() && bX2 <= x2() &&
               bY1 >= y1() && bY2 <= y2() &&
               bZ1 >= z1() && bZ2 <= z2();
    }

    default boolean contains_aabb(_AABB_3D aabb) {
        return contains_aabb(aabb.x1(), aabb.y1(), aabb.z1(), aabb.x2(), aabb.y2(), aabb.z2());
    }

    default boolean intersects_aabb(float bX1, float bY1, float bZ1, float bX2, float bY2, float bZ2) {
        return !(x2() < bX1 || bX2 < x1() ||
                 y2() < bY1 || bY2 < y1() ||
                 z2() < bZ1 || bZ2 < z1());
    }

    default boolean intersects_aabb(_AABB_3D aabb) {
        return intersects_aabb(aabb.x1(), aabb.y1(), aabb.z1(), aabb.x2(), aabb.y2(), aabb.z2());
    }

    default boolean fitsWithin_aabb(float bX1, float bY1, float bZ1, float bX2, float bY2, float bZ2) {
        return x1() >= bX1 && x2() <= bX2 &&
               y1() >= bY1 && y2() <= bY2 &&
               z1() >= bZ1 && z2() <= bZ2;
    }

    default boolean fitsWithin_aabb(_AABB_3D aabb) {
        return fitsWithin_aabb(aabb.x1(), aabb.y1(), aabb.z1(), aabb.x2(), aabb.y2(), aabb.z2());
    }

    default float distToPoint(float x, float y, float z) {
        float cx = max(min(x, x2()), x1());
        float cy = max(min(y, y2()), y1());
        float cz = max(min(z, z2()), z1());
        return (float)sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy) + (z - cz) * (z - cz));
    }

    default float distToPoint(_Vec3 v) {
        return distToPoint(v.x(), v.y(), v.z());
    }

    default float distToPointSq(float x, float y, float z) {
        float cx = max(min(x, x2()), x1());
        float cy = max(min(y, y2()), y1());
        float cz = max(min(z, z2()), z1());
        return (x-cx)*(x-cx) + (y-cy)*(y-cy) + (z-cz)*(z-cz);
    }

    default float distToPointSq(_Vec3 v) {
        return distToPointSq(v.x(), v.y(), v.z());
    }


}
