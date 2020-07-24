package orb.____inner_core.util.datatstructure;



import orb.____inner_core.IC_Common;
import orb.____inner_core.geom._AABB_2D;
import orb.____inner_core.util.function._GetX_T;
import orb.____inner_core.util.function._GetY_T;

import java.util.List;

/**
 * Created by doekewartena on 8/30/15.
 */
public interface _Query_2D<T, C extends _Query_2D> extends _GetX_T<T>, _GetY_T<T> { // extends Query?

    // todo separate the next 2 to other interface?
    float getX2(T t);
    float getY2(T t);


    C queryAll(List<T> dest);
    T query(float tx, float ty);
    C query(List<T> containing, List<T> intersecting, float tx, float ty);
    C query(List<T> containing, List<T> intersecting, float tx1, float ty1, float tx2, float ty2);

    default C query(List<T> containing, List<T> intersecting, _AABB_2D bounds) {
        return query(containing, intersecting, bounds.x1(), bounds.y1(), bounds.x2(), bounds.y2());
    }

    default C queryRadius(List<T> containing, List<T> intersecting, float cx, float cy, float radius) {
        queryRadiusSq(containing, intersecting, cx, cy, radius*radius);
        return (C) this;
    }

    C queryRadiusSq(List<T> containing, List<T> intersecting, float cx, float cy, float radiusSQ);

    // todo, move those to shell?
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

    default T queryMaxX() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(-Float.POSITIVE_INFINITY);
        queryMaxX(bestMatch);
        return bestMatch.item;
    }

    default T queryMaxY() {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(-Float.POSITIVE_INFINITY);
        queryMaxY(bestMatch);
        return bestMatch.item;
    }


    C queryMinX(IC_Common.BestMatch<T> bestMatch);
    C queryMinY(IC_Common.BestMatch<T> bestMatch);
    C queryMaxX(IC_Common.BestMatch<T> bestMatch);
    C queryMaxY(IC_Common.BestMatch<T> bestMatch);


    default T queryClosest(float x, float y) {
        IC_Common.BestMatch<T> bestMatch = new IC_Common.BestMatch<>(Float.MAX_VALUE);
        queryClosest(x, y, bestMatch);
        return bestMatch.item;
    }

    C queryClosest(float x, float y, IC_Common.BestMatch<T> bestMatch);



}
