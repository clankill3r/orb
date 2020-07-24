package nl.doekewartena.orb.inner_core.util.datatstructure;

import nl.doekewartena.orb.inner_core.util.function._Remove_T;

/**
 * Created by doekewartena on 8/29/15.
 */
                                                    // Add_T etc.
public interface _Data <T, C extends _Data> extends _Remove_T<T, C> {

    // which on do we interface?

    // maybe interface like this:
    /*

    interface _Remove<C extends _Remove, T> {

       default boolean supportsRemove() { return false);
       C remove(T t);

    }

    // NOOOO BAD!! CAN BE TUFF TO DEBUG!!

    what about




     */


    boolean isEmpty();

    int size();

    C add(T t);

    //C remove(T t);


    // getAll? (is more java like)
    // disable anyway soon, (octree sadly still relies on it!)
    //C queryAll(List<T> target);

    void clear();


}