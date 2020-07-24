package orb.____inner_core.util.datatstructure;


import orb.____inner_core.IC_Common;
import orb.____inner_core.geom._AABB_3D;
import orb.____inner_core.util.function._GetX_T;
import orb.____inner_core.util.function._GetY_T;
import orb.____inner_core.util.function._GetZ_T;

import java.util.List;

/**
 * Created by doekewartena on 8/30/15.
 */
public interface _Query_3D<T, C extends _Query_3D> extends _GetX_T<T>, _GetY_T<T>, _GetZ_T<T> {

    // todo, interface?
    float getX2(T t);
    float getY2(T t);
    float getZ2(T t);

    C queryAll(List<T> dest);
    T query(float tx, float ty, float tz);
    C query(List<T> containing, List<T> intersecting, float tx, float ty, float tz);
    C query(List<T> containing, List<T> intersecting, float tx1, float ty1, float tz1, float tx2, float ty2, float tz2);

    default C query(List<T> containing, List<T> intersecting, _AABB_3D bounds) {
        return query(containing, intersecting, bounds.x1(), bounds.y1(), bounds.z1(), bounds.x2(), bounds.y2(), bounds.z2());
    }

    default C queryRadius(List<T> containing, List<T> intersecting, float cx, float cy, float cz, float radius) {
        queryRadiusSq(containing, intersecting, cx, cy, cz, radius * radius);
        return (C) this;
    }

    C queryRadiusSq(List<T> containing, List<T> intersecting, float cx, float cy, float cz, float radiusSQ);

    // we have a lot of overlap here with the 2D variant!

    default T queryMinX() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Float.POSITIVE_INFINITY);
        queryMinX(bestMatch);
        return bestMatch.item;
    }

    default T queryMinY() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Float.POSITIVE_INFINITY);
        queryMinY(bestMatch);
        return bestMatch.item;
    }

    default T queryMinZ() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Float.POSITIVE_INFINITY);
        queryMinZ(bestMatch);
        return bestMatch.item;
    }

    default T queryMaxX() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Float.NEGATIVE_INFINITY);
        queryMaxX(bestMatch);
        return bestMatch.item;
    }

    default T queryMaxY() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Float.NEGATIVE_INFINITY);
        queryMaxY(bestMatch);
        return bestMatch.item;
    }

    default T queryMaxZ() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Float.NEGATIVE_INFINITY);
        queryMaxZ(bestMatch);
        return bestMatch.item;
    }

    C queryMinX(IC_Common.BestMatch<T> bestMatch);
    C queryMinY(IC_Common.BestMatch<T> bestMatch);
    C queryMinZ(IC_Common.BestMatch<T> bestMatch);
    C queryMaxX(IC_Common.BestMatch<T> bestMatch);
    C queryMaxY(IC_Common.BestMatch<T> bestMatch);
    C queryMaxZ(IC_Common.BestMatch<T> bestMatch);



    default T queryClosest(float x, float y, float z) {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Float.MAX_VALUE);
        queryClosest(x, y, z, bestMatch);
        return bestMatch.item;
    }

    C queryClosest(float x, float y, float z, IC_Common.BestMatch<T> bestMatch);

}
