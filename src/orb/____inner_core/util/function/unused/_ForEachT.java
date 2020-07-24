package nl.doekewartena.orb.inner_core.util.function.unused;

import java.util.function.Consumer;

/**
 * Created by doekewartena on 6/5/15.
 */
// todo, is this name good? in other classes T means we have to pass a t as parameter
public interface _ForEachT<T> {
    void forEachT(Consumer<? super T> action);
}
