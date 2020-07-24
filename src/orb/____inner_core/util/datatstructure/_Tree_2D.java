package orb.____inner_core.util.datatstructure;

import nl.doekewartena.orb.inner_core.geom._AABB_2D;

import java.util.List;

/**
 * Created by doekewartena on 8/26/15.
 */

public interface _Tree_2D<T, C extends _Tree_2D> extends _Tree<T, C>, _AABB_2D, _Query_2D<T, C> {

    // todo, keep in mind we can always throw not supported exceptions
    // split
    // merge
    // hasItems? how does this relate to children
    // getLevel? (storing this can be a huge waste!)



    // do we want this? (it was usefull in our debug (for forwardFind))
    // this is not something we want for a celltree for example
    C backFind(double x, double y);
    C backFind(double x, double y, double x2, double y2);

    C forwardFind(double x, double y);
    C forwardFind(double x, double y, double x2, double y2);

     /*

    // todo, move!
    // queryPoint interface?, no we can query other things with the same methods
    //
    void queryAll(List<T> dest);
    T query (double tx, double ty);
    void query (List<T> containing, List<T> intersecting, double tx, double ty);
    void query (List<T> containing, List<T> intersecting, double tx1, double ty1, double tx2, double ty2);
    void queryRadius (List<T> containing, List<T> intersecting, double cx, double cy, double radius);
    void queryRadiusSq (List<T> containing, List<T> intersecting, double cx, double cy, double radiusSQ);

    // yes or no? (throw Exception)
    T queryMinX();
    T queryMinY();
    T queryMaxX();
    T queryMaxY();
    // what about the bestMatch? That might become to specific

    T queryClosest(double x, double y);

    */

    // be aware of OC_CellGrid, it's not a tree and we prefer not to get those methods
    // by moving this!

    default C getDeepestContaining(_AABB_2D bounds) {

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
    default void getIntersectingLeafs(_AABB_2D bounds, List l) {

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
