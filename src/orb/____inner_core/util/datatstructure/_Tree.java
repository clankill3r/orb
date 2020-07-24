package orb.____inner_core.util.datatstructure;


import nl.doekewartena.orb.inner_core.util.function._Insert_T;
import nl.doekewartena.orb.inner_core.util.function._Remove_T;

/**
 * Created by doekewartena on 8/27/15.
 */
public interface _Tree<T, C extends _Tree> extends _Insert_T<T, C>, _Remove_T<T, C>, Iterable<C> {

    boolean hasParent();
    C parent();

    boolean hasChildren();
    C[] children();

}
