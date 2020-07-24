package orb.___outer_core.util.datastructure;


import orb.____inner_core.IC_Common;
import orb.____inner_core.util.datatstructure._Data_2D;
import orb.____inner_core.util.function._GetFloat_T;

import java.util.List;

import static orb.____inner_core.IC_Math.distSq;


/**
 * Created by doekewartena on 8/29/15.
 */
public class OC_Data2DPoint_List<T, C extends OC_Data2DPoint_List> implements _Data_2D<T, C> {

    _GetFloat_T<T> getX, getY;

    List<T> items;

    public OC_Data2DPoint_List(_GetFloat_T<T> getX, _GetFloat_T<T> getY, List<T> list) {
        //items = new ArrayList<>();
        this.getX = getX;
        this.getY = getY;
        this.items = list;
    }




    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }


    @Override
    public C queryAll(List<T> dest) {
        for (T t : items) dest.add(t);
        return (C) this;
    }

    @Override
    public void clear() {
        items.clear();
    }

//    @Override
//    public C2 newInstance() {
//        return (C2) new Data2DPoint_List<T, C2>(getX, getY, new ArrayList<>());
//    }

    @Override
    public float getX(T t) {
        return getX.val(t);
    }

    @Override
    public float getY(T t) {
        return getY.val(t);
    }


    // todo don't have this in the interface
    @Override
    public float getX2(T t) {
        System.out.println("[ERROR]: getX2 used with Data2DPoint_List point");
        return -1;
    }

    // todo don't have this in the interface
    @Override
    public float getY2(T t) {
        System.out.println("[ERROR]: getY2 used with Data2DPoint_List point");
        return -1;
    }

    @Override
    public T query(float tx, float ty) {
        for (T t : items) {
            float x = getX.val(t);
            float y = getY.val(t);
            if (x == tx && y == ty) return t;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C query(List<T> containing, List<T> intersecting, float tx, float ty) {

        // like this?
        List<T> target = intersecting != null ? intersecting : containing;

        for (T t : items) {
            float x = getX.val(t);
            float y = getY.val(t);
            if (x == tx && y == ty) target.add(t);
        }
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C query(List<T> containing, List<T> intersecting, float tx1, float ty1, float tx2, float ty2) {

        final boolean checkContaining = containing != null;
        final boolean checkIntersecting = intersecting != null;

        boolean added;

        for (T t : items) {

            added = false;

            float x = getX.val(t);
            float y = getY.val(t);

            if (checkContaining) {
                if (x > tx1 && x < tx2 &&
                        y > ty1 && y < ty2) {
                    containing.add(t);
                    added = true;
                }
            }
            if (!added && checkIntersecting) {
                if (x >= tx1 && x <= tx2 &&
                        y >= ty1 && y <= ty2) {
                    intersecting.add(t);
                }
            }

        }
        return (C) this;
    }




    @Override
    @SuppressWarnings("unchecked")
    public C queryRadiusSq(List<T> containing, List<T> intersecting, float cx, float cy, float radiusSQ) {

        if (items.isEmpty()) return (C) this;

        final boolean checkContaining = containing != null;
        final boolean checkIntersecting = intersecting != null;

        boolean added;

        for (T t : items) {

            added = false;

            float x = getX.val(t);
            float y = getY.val(t);
            float dSQ = distSq(cx, cy, x, y);

            if (checkContaining) {
                if (dSQ < radiusSQ) {
                    containing.add(t);
                    added = true;
                }
            }
            if (!added && checkIntersecting) {
                if (dSQ <= radiusSQ) {
                    intersecting.add(t);
                }
            }

        }
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMinX(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMin(items, getX, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMinY(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMin(items, getY, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMaxX(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMax(items, getX, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMaxY(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMax(items, getY, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryClosest(float x, float y, IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getClosest(items, x, y, getX, getY, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C add(T t) {
        items.add(t);
        return (C) this;
    }

    public C remove(T t) {
        items.remove(t);
        return null;
    }

}
