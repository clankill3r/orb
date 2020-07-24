package nl.doekewartena.orb.inner_core.util.datatstructure;


import nl.doekewartena.orb.inner_core.IC_Common;
import nl.doekewartena.orb.inner_core.geom._AABB_3D;
import nl.doekewartena.orb.inner_core.util.function._GetX_T;
import nl.doekewartena.orb.inner_core.util.function._GetY_T;
import nl.doekewartena.orb.inner_core.util.function._GetZ_T;

import java.util.List;

/**
 * Created by doekewartena on 8/30/15.
 */
public interface _Query_3D<T, C extends _Query_3D> extends _GetX_T<T>, _GetY_T<T>, _GetZ_T<T> {

    // todo, interface?
    double getX2(T t);
    double getY2(T t);
    double getZ2(T t);

    C queryAll(List<T> dest);
    T query(double tx, double ty, double tz);
    C query(List<T> containing, List<T> intersecting, double tx, double ty, double tz);
    C query(List<T> containing, List<T> intersecting, double tx1, double ty1, double tz1, double tx2, double ty2, double tz2);

    default C query(List<T> containing, List<T> intersecting, _AABB_3D bounds) {
        return query(containing, intersecting, bounds.x1(), bounds.y1(), bounds.z1(), bounds.x2(), bounds.y2(), bounds.z2());
    }

    default C queryRadius(List<T> containing, List<T> intersecting, double cx, double cy, double cz, double radius) {
        queryRadiusSq(containing, intersecting, cx, cy, cz, radius * radius);
        return (C) this;
    }

    C queryRadiusSq(List<T> containing, List<T> intersecting, double cx, double cy, double cz, double radiusSQ);

    // we have a lot of overlap here with the 2D variant!

    default T queryMinX() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Double.POSITIVE_INFINITY);
        queryMinX(bestMatch);
        return bestMatch.item;
    }

    default T queryMinY() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Double.POSITIVE_INFINITY);
        queryMinY(bestMatch);
        return bestMatch.item;
    }

    default T queryMinZ() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Double.POSITIVE_INFINITY);
        queryMinZ(bestMatch);
        return bestMatch.item;
    }

    default T queryMaxX() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Double.NEGATIVE_INFINITY);
        queryMaxX(bestMatch);
        return bestMatch.item;
    }

    default T queryMaxY() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Double.NEGATIVE_INFINITY);
        queryMaxY(bestMatch);
        return bestMatch.item;
    }

    default T queryMaxZ() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Double.NEGATIVE_INFINITY);
        queryMaxZ(bestMatch);
        return bestMatch.item;
    }

    C queryMinX(IC_Common.BestMatch<T> bestMatch);
    C queryMinY(IC_Common.BestMatch<T> bestMatch);
    C queryMinZ(IC_Common.BestMatch<T> bestMatch);
    C queryMaxX(IC_Common.BestMatch<T> bestMatch);
    C queryMaxY(IC_Common.BestMatch<T> bestMatch);
    C queryMaxZ(IC_Common.BestMatch<T> bestMatch);



    default T queryClosest(double x, double y, double z) {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Double.MAX_VALUE);
        queryClosest(x, y, z, bestMatch);
        return bestMatch.item;
    }

    C queryClosest(double x, double y, double z, IC_Common.BestMatch<T> bestMatch);

}
