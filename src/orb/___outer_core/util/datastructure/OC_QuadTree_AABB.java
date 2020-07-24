package orb.___outer_core.util.datastructure;


import nl.doekewartena.orb.inner_core.util.datatstructure._Data_2D;
import nl.doekewartena.orb.inner_core.util.datatstructure._TreeSettings;

/**
 * Created by doekewartena on 8/18/15.
 */


// todo, should we force a AABB 2d?
// let's say we have a list of 3d stuuf, won't it be easy to allow it to be stored in a quadtree as well
// without the need of a wrapper to make it an AABB_2D!
public class OC_QuadTree_AABB<T, C extends OC_QuadTree_AABB> extends OC_QuadTree<T, C> {


    // bad name cause it also provides the data instance
    public _TreeSettings<T> settings;



    // @not nullable?
    // todo, methods like those to ONOS SHELL?
//    public OC_QuadTree_AABB(_TreeSettings settings) {
//        this(0, 0, 1, 1, settings);
//    }

    public OC_QuadTree_AABB(double x1, double y1, double x2, double y2, _TreeSettings<T> settings) {
        this(null, x1, y1, x2, y2, settings); //, 0);
    }

    protected OC_QuadTree_AABB(C parent, double x1, double y1, double x2, double y2, _TreeSettings<T> settings) {
        super(parent, x1, y1, x2, y2, (_Data_2D<T, ?>) settings.createDataInstance());
        this.settings = settings;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // defaults?
    @Override
    public C insert(T t) {
        return insert(t, getX(t), getY(t), getX2(t), getY2(t));
    }

    // move to interface?
    public C insert(T t, double x, double y, double x2, double y2) {
        return insert(t, x, y, x2, y2, 0);
    }


    public C insert(T t, double x, double y, double x2, double y2, int level) {

        int where = level == settings.getMaxLevels() ? -1 : getIndex(x, y, x2, y2);

        if (where != -1) {

            if (!hasChildren()) {
                split();
            }
            return (C) children[where].insert(t, x, y, x2, y2, level+1);
        }
        else {
            // todo check if quad is big enough? (it could be bigger then the root)
            data.add(t);
            return (C) this;
        }
    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @SuppressWarnings("unchecked")
    public void split() { // todo

        children = (C[]) new OC_QuadTree_AABB[4];

        children[TR] = (C) new OC_QuadTree_AABB<>((C) this, cx(), y1, x2, cy(), settings);
        children[TL] = (C) new OC_QuadTree_AABB<>((C) this, x1, y1, cx(), cy(), settings);
        children[BL] = (C) new OC_QuadTree_AABB<>((C) this, x1, cy(), cx(), y2, settings);
        children[BR] = (C) new OC_QuadTree_AABB<>((C) this, cx(), cy(), x2, y2, settings);

    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    // or return the item?
    @Override
    public C remove(T t) {
        // todo, merge with parent if required
        data.remove(t);
        return (C) this;
    }


    // yes or no? user can always Override
    @Override
    public double getX(T t) {
        return data.getX(t);
    }

    @Override
    public double getY(T t) {
        return data.getY(t);
    }

    @Override
    public double getX2(T t) {
        return data.getX2(t);
    }

    @Override
    public double getY2(T t) {
        return data.getY2(t);
    }

    // make default?
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        // todo, how do we do this?
        // i think it should be total of this one and all children, recursive
        return -1;
    }

    @Override
    public C add(T t) {
        insert(t);
        return (C) this;
    }

    @Override
    public void clear() {
        // todo
        System.out.println("todo, clear in quadTree_AABB");
        // also return type C?
    }


    // ======================================================

}


