package nl.doekewartena.orb.crust.util.datastructure;

import nl.doekewartena.orb.inner_core.geom._AABB_2D;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree_2D;

import java.util.ArrayList;

/**
 * Created by doekewartena on 8/30/15.
 */

public interface _C_QuadTree<T, C extends _C_QuadTree> extends _Tree_2D<T, C>

    {
    // move to interface?
    default ArrayList<C> getIntersectingLeafs(_AABB_2D bounds) {

        ArrayList<C> result = new ArrayList<>();

        _C_QuadTree quadTree = getDeepestContaining(bounds);

        if (quadTree != null) {
            quadTree.getIntersectingLeafs(bounds, result);
        }

        return result;
    }




}
