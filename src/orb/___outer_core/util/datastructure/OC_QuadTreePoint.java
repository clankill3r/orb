package nl.doekewartena.orb.outer_core.util.datastructure;



import nl.doekewartena.orb.inner_core.util.datatstructure._Data_2D;
import nl.doekewartena.orb.inner_core.util.datatstructure._TreeSettings;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by doekewartena on 8/18/15.
 */



public class OC_QuadTreePoint<T, C extends OC_QuadTreePoint> extends OC_QuadTree<T, C> {


    // bad name cause it also provides the data instance
    public _TreeSettings<T> settings;


    public OC_QuadTreePoint(double x1, double y1, double x2, double y2, _TreeSettings<T> settings) {
        this(null, x1, y1, x2, y2, settings); //, 0);
    }

    protected OC_QuadTreePoint(C parent, double x1, double y1, double x2, double y2, _TreeSettings<T> settings) {
        super(parent, x1, y1, x2, y2, (_Data_2D<T, ?>) settings.createDataInstance());
        this.settings = settings;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // defaults?
    @Override
    public C insert(T t) {
        // todo, always return as (C) this so we always return the starting point?
        return insert(t, getX(t), getY(t));
    }

    // move to interface?
    public C insert(T t, double x, double y) {
        return insert(t, x, y, 0);
    }


    @SuppressWarnings("unchecked")
    public C insert(T t, double x, double y, int level) {

        if (hasChildren()) {
            int where = getIndex(x, y);
            return (C) children[where].insert(t, x, y, level+1);
        }
        else {

            data.add(t);

            if (data.size() > settings.getMaxObjects() && level < settings.getMaxLevels()) {  // todo check level < maxLevels

                split();

                List<T> items = new ArrayList<>();
                data.queryAll(items);

                data.clear();

                for (int i = items.size()-1; i >= 0; i--) {
                    T t2 = items.remove(i);
                    double x2 = getX(t2);
                    double y2 = getY(t2);
                    insert(t2, x2, y2, level+1);
                }

                return forwardFind(x, y);

            }

            return (C) this;
        }
    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @SuppressWarnings("unchecked")
    public void split() { // todo

        children = (C[]) new OC_QuadTreePoint[4];

        children[TR] = (C) new OC_QuadTreePoint<>((C) this, cx(), y1, x2, cy(), settings);
        children[TL] = (C) new OC_QuadTreePoint<>((C) this, x1, y1, cx(), cy(), settings);
        children[BL] = (C) new OC_QuadTreePoint<>((C) this, x1, cy(), cx(), y2, settings);
        children[BR] = (C) new OC_QuadTreePoint<>((C) this, cx(), cy(), x2, y2, settings);

    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    // or return the item?
    @Override
    @SuppressWarnings("unchecked")

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
        // todo (throw?)
        System.out.println("[ERROR]: getX2 used with quadTree point");
        return -1;
    }

    @Override
    public double getY2(T t) {
        // todo (throw?)
        System.out.println("[ERROR]: getY2 used with quadTree point");
        return -1;
    }


    // todo, see Quadtree_AABB
    @Override
    public boolean isEmpty() {
        System.out.println("todo isEmpty");
        return false;
    }

    @Override
    public int size() {
        System.out.println("todo size");
        return 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C add(T t) {
        // misleading?
        insert(t);
        return (C) this;
    }

    @Override
    public void clear() {
        System.out.println("todo clear");
    }


    // ======================================================

}


