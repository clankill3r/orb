package orb.____inner_core.util.datatstructure;

import orb.____inner_core.geom._AABB_3D;

import java.util.List;

/**
 * Created by doekewartena on 8/26/15.
 */

// do we want to extend OC_Tree2D instead of OC_Tree3D?
// this can open all gates to hell
public interface _Tree_3D<T, C extends _Tree_3D> extends _Tree<T, C>, _AABB_3D, _Query_3D<T, C> {

    C backFind(float x, float y, float z);
    C backFind(float x, float y, float z, float x2, float y2, float z2);

    C forwardFind(float x, float y, float z);
    C forwardFind(float x, float y, float z, float x2, float y2, float z2);

    /*
    C queryAll(List<T> dest);
    T query (float tx, float ty, float tz);
    C query (List<T> containing, List<T> intersecting, float tx, float ty, float tz);
    C query (List<T> containing, List<T> intersecting, float tx1, float ty1, float tz1, float tx2, float ty2, float tz2);
    C queryRadius (List<T> containing, List<T> intersecting, float cx, float cy, float cz, float radius);
    C queryRadiusSq (List<T> containing, List<T> intersecting, float cx, float cy, float cz, float radiusSQ);

    // yes or no? (throw Exception)
    T queryMinX();
    T queryMinY();
    T queryMinZ();
    T queryMaxX();
    T queryMaxY();
    T queryMaxZ();
    // what about the bestMatch? That might become to specific

    T queryClosest(float x, float y, float z);
    */


    default C getDeepestContaining(_AABB_3D bounds) {

        // make level 0 check? getDeepestContaining(bounds, maxLevel, level)

        // do we want this to be an intersecting check?
        // we could also do a contains check,
        // BUT then we get null of our bounds only intersects a little
        // while in practice it is probably more convenient
        // to return the root on a intersection
        if (!intersects_aabb(bounds)) return null;

        if (hasChildren() && contains_aabb(bounds)) {
            for (C child : children()) {
                if (child.contains_aabb(bounds)) {
                    return (C) child.getDeepestContaining(bounds);
                }
            }
        }

        return (C) this;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // todo, do we want a capture for the list?
    default void getIntersectingLeafs(_AABB_3D bounds, List l) {

        if (hasChildren()) {
            for (C child : children()) {
                child.getIntersectingLeafs(bounds, l);
            }
        }
        else {
            if (intersects_aabb(bounds)) l.add(this);
        }
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


}
