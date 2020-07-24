package orb.__mantle.pathfinder;

/**
 * Created by doekewartena on 3/4/15.
 */



// make a interface as well as an exact duplicate?
// extend some default node that supports a parent and a data?
// marker interface for parent?
public class M_FGHNode<T> {

    T linkedObject;

    // methods to get?
    float f, g, h;

    M_FGHNode<T> parent;

    // normally we had a closed list,
    // by using this boolean we don't need to loop over a list anymore
    boolean closed;

    // we still have an open list
    // by checking this boolean we don't have to check
    // a whole list to see if the node is in it
    boolean isInOpenList;



    public M_FGHNode() {
    }



    public void reset() {
        closed = false;
        f = g = h = 0;
        parent = null;

        linkedObject = null;

        isInOpenList = false;

    }


    /**
     * This is added for advanced usage.
     * For example to adjust the pathfinder behaviour
     * in such a way that it looks at how sharp an angle is.
     *
     * @return The parent it's currently linked too
     */

    public M_FGHNode<T> getParent() {
        return parent;
    }

    /**
     * This is added for advanced usage.
     * For example to adjust the pathfinder behaviour
     * in such a way that it looks at how sharp an angle is.
     *
     * @return The linked object.
     */
    public T getLinkedObject() {
        return linkedObject;
    }



}