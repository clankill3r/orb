package nl.doekewartena.orb.mantle.pathfinder;

/**
 * Created by doekewartena on 5/14/15.
 */
public interface _FGHNodeProvider<T> {
    M_FGHNode<T> getFGHNode(T t);
    void reset();
}
