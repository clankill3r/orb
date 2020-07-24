package nl.doekewartena.orb.inner_core.util.datatstructure;



import nl.doekewartena.orb.inner_core.IC_Common;
import nl.doekewartena.orb.inner_core.geom._AABB_2D;
import nl.doekewartena.orb.inner_core.util.function._GetX_T;
import nl.doekewartena.orb.inner_core.util.function._GetY_T;

import java.util.List;

/**
 * Created by doekewartena on 8/30/15.
 */
public interface _Query_2D<T, C extends _Query_2D> extends _GetX_T<T>, _GetY_T<T> { // extends Query?

    // todo separate the next 2 to other interface?
    double getX2(T t);
    double getY2(T t);


    C queryAll(List<T> dest);
    T query(double tx, double ty);
    C query(List<T> containing, List<T> intersecting, double tx, double ty);
    C query(List<T> containing, List<T> intersecting, double tx1, double ty1, double tx2, double ty2);

    default C query(List<T> containing, List<T> intersecting, _AABB_2D bounds) {
        return query(containing, intersecting, bounds.x1(), bounds.y1(), bounds.x2(), bounds.y2());
    }

    default C queryRadius(List<T> containing, List<T> intersecting, double cx, double cy, double radius) {
        queryRadiusSq(containing, intersecting, cx, cy, radius*radius);
        return (C) this;
    }

    C queryRadiusSq(List<T> containing, List<T> intersecting, double cx, double cy, double radiusSQ);

    // todo, move those to shell?
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

    default T queryMaxX() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(-Double.POSITIVE_INFINITY);
        queryMaxX(bestMatch);
        return bestMatch.item;
    }

    default T queryMaxY() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(-Double.POSITIVE_INFINITY);
        queryMaxY(bestMatch);
        return bestMatch.item;
    }


    C queryMinX(IC_Common.BestMatch<T> bestMatch);
    C queryMinY(IC_Common.BestMatch<T> bestMatch);
    C queryMaxX(IC_Common.BestMatch<T> bestMatch);
    C queryMaxY(IC_Common.BestMatch<T> bestMatch);


    default T queryClosest(double x, double y) {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Double.MAX_VALUE);
        queryClosest(x, y, bestMatch);
        return bestMatch.item;
    }

    C queryClosest(double x, double y, IC_Common.BestMatch<T> bestMatch);



}
