package nl.doekewartena.orb.crust.util.datastructure;

import nl.doekewartena.orb.inner_core.geom._AABB_3D;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree_3D;

import java.util.ArrayList;

/**
 * Created by doekewartena on 8/30/15.
 */

public interface _C_Octree<T, C extends _C_Octree> extends _Tree_3D<T, C>

    {
    // move to interface?
    default ArrayList<C> getIntersectingLeafs(_AABB_3D bounds) {

        ArrayList<C> result = new ArrayList<>();

        _C_Octree octree = getDeepestContaining(bounds);

        if (octree != null) {
            octree.getIntersectingLeafs(bounds, result);
        }

        return result;
    }




}
