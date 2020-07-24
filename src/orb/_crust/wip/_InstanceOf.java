package nl.doekewartena.orb.crust.wip;

/**
 * Created by doekewartena on 9/12/15.
 */
public interface _InstanceOf {

    default boolean instanceOf(Class<?> c) {
        return (c.isInstance(this));
    }

    default boolean instanceOf(Class<?>... classes) {
        for (Class<?> c : classes) {
            if (!c.isInstance(this)) return false;
        }
        return true;
    }

}
