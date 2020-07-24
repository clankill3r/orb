package orb.____inner_core.geom;


import static orb.____inner_core.IC_Math.*;

//import orb.____inner_core.util.compare.excluded.IC_CompareXY;

/**
 * Created by doekewartena on 8/26/15.
 */

// todo, we should implement a compareXY and compare WH


// we should also be able to compare to the center point
/*

    ---------
    |         |
    |         |
    |    X    |
    |         |
    |         |
    ---------


    // x1y1 | TL

    // x2y1 | TR

    // x1y2 | BL

    // x2y2 | BR

    // cxcy | Center


    y1x1
    y1x2
    y2x1
    y2x2




*/

                                         // what about x2, y2?
                                                            // todo, the compare thing (we have to think it more true)
public interface _AABB_2D<T extends _AABB_2D> extends _AABB {//, _CompareXY_T<T> { // , _CompareXY

//    default int compareTL(T t) {
//        return IC_CompareXY.compareXY(x1(), y1(), t.x1(), t.y1());
//    }

    default boolean contains_point(float x, float y) {
        return x >= x1() && x <= x2() &&
               y >= y1() && y <= y2();
    }

    default boolean contains_point(_Vec2 v) {
        return contains_point(v.x(), v.y());
    }

    default boolean contains_aabb(float bX1, float bY1, float bX2, float bY2) {
        return bX1 >= x1() && bX2 <= x2() &&
                bY1 >= y1() && bY2 <= y2();
    }

    default boolean contains_aabb(_AABB_2D aabb) {
        return contains_aabb(aabb.x1(), aabb.y1(), aabb.x2(), aabb.y2());
    }

    default boolean intersects_aabb(float bX1, float bY1, float bX2, float bY2) {
        return !(x2() < bX1 || bX2 < x1() ||
                y2() < bY1 || bY2 < y1());
    }

    default boolean intersects_aabb(_AABB_2D aabb) {
        return intersects_aabb(aabb.x1(), aabb.y1(), aabb.x2(), aabb.y2());
    }

    // fits is maybe misleading, cause in our implementation we take the
    // position into account
    // maybe isWithing / or isInside
    default boolean fitsWithin_aabb(float bX1, float bY1, float bX2, float bY2) {
        return x1() >= bX1 && x2() <= bX2 &&
               y1() >= bY1 && y2() <= bY2;
    }

    default boolean fitsWithin_aabb(_AABB_2D aabb) {
        return fitsWithin_aabb(aabb.x1(), aabb.y1(), aabb.x2(), aabb.y2());
    }

    default float distToPoint(float px, float py) {
        float cx = max(min(px, x2()), x1());
        float cy = max(min(py, y2()), y1());
        return (float)sqrt((px - cx) * (px - cx) + (py - cy) * (py - cy));
    }

    default float distToPoint(_Vec2 v) {
        return distToPoint(v.x(), v.y());
    }

    default float distToPointSq(float px, float py) {
        float cx = max(min(px, x2()), x1());
        float cy = max(min(py, y2()), y1());
        return (px-cx)*(px-cx) + (py-cy)*(py-cy);
    }

    default float distToPointSq(_Vec2 v) {
        return distToPointSq(v.x(), v.y());
    }

}
