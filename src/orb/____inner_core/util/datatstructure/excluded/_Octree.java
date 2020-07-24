package nl.doekewartena.orb.inner_core.util.datatstructure.excluded;

import nl.doekewartena.orb.inner_core.util.datatstructure._3D_Query;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree3D;

/**
 * Created by doekewartena on 9/7/15.
 */
/*
For more details look in _QuadTree

*/
public interface _Octree<T, C extends _Octree> extends _Tree3D<T, C>, _3D_Query<T, C> {

    // nice +1

    // maybe remove from core if it has no use anyway

}
