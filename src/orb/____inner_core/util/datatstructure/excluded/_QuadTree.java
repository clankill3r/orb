package orb.____inner_core.util.datatstructure.excluded;


import nl.doekewartena.orb.inner_core.util.datatstructure._2D_Query;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree2D;

/**
 * Created by doekewartena on 9/7/15.
 */
/*
There is not much unique at the moment for a QuadTree, most happens in the other interfaces.

Say again!!

 */
public interface _QuadTree<T, C extends _QuadTree> extends _Tree2D<T, C>, _2D_Query<T, C> {

    // nice!

    // maybe remove from core if it has no use anyway
}
