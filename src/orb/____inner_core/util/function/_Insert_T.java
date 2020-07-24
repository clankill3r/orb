package orb.____inner_core.util.function;

import java.util.Collection;

/**
 * Created by doekewartena on 8/19/15.
 */
public interface _Insert_T<T, C extends _Insert_T> {

    C insert(T t);

    default C insertAll(Collection<? extends T> c) {
        c.forEach(this::insert);
        return (C) this;
    }
}
