package orb.___outer_core.util.datastructure;


import orb.____inner_core.IC_Common;
import orb.____inner_core.util.datatstructure._Data_3D;
import orb.____inner_core.util.function._GetFloat_T;

import java.util.List;

import static orb.____inner_core.IC_Math.distSq;


/**
 * Created by doekewartena on 8/29/15.
 */
public class OC_Data3DPoint_List<T, C extends OC_Data3DPoint_List> implements _Data_3D<T, C> {

    _GetFloat_T<T> getX, getY, getZ;

    List<T> items;

    public OC_Data3DPoint_List(_GetFloat_T<T> getX, _GetFloat_T<T> getY, _GetFloat_T<T> getZ, List<T> list) {
        //items = new ArrayList<>();
        this.getX = getX;
        this.getY = getY;
        this.getZ = getZ;
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
    @SuppressWarnings("unchecked")
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

    @Override
    public float getZ(T t) {
        return getZ.val(t);
    }

    @Override
    public float getX2(T t) {
        System.out.println("[ERROR]: getX2 used with point list");
        return -1;
    }

    @Override
    public float getY2(T t) {
        System.out.println("[ERROR]: getY2 used with point list");
        return -1;
    }

    @Override
    public float getZ2(T t) {
        System.out.println("[ERROR]: getZ2 used with point list");
        return -1;
    }

    @Override
    public T query(float tx, float ty, float tz) {
        for (T t : items) {
            float x = getX.val(t);
            float y = getY.val(t);
            float z = getZ.val(t);
            if (x == tx && y == ty && z == tz) return t;
        }
        return null;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, float tx, float ty, float tz) {

        // like this?
        List<T> target = intersecting != null ? intersecting : containing;

        for (T t : items) {
            float x = getX.val(t);
            float y = getY.val(t);
            float z = getZ.val(t);
            if (x == tx && y == ty && z == tz) target.add(t);
        }
        return (C) this;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, float tx1, float ty1, float tz1, float tx2, float ty2, float tz2) {

        final boolean checkContaining = containing != null;
        final boolean checkIntersecting = intersecting != null;

        boolean added;

        for (T t : items) {

            added = false;

            float x = getX.val(t);
            float y = getY.val(t);
            float z = getZ.val(t);


            if (checkContaining) {
                if (x > tx1 && x < tx2 &&
                    y > ty1 && y < ty2 &&
                    z > tz1 && z < tz2) {
                    containing.add(t);
                    added = true;
                }
            }
            if (!added && checkIntersecting) {
                if (x >= tx1 && x <= tx2 &&
                    y >= ty1 && y <= ty2 &&
                    z >= tz1 && z <= tz2) {
                    intersecting.add(t);
                }
            }

        }
        return (C) this;
    }




    @Override
    public C queryRadiusSq(List<T> containing, List<T> intersecting, float cx, float cy, float cz, float radiusSQ) {

        if (items.isEmpty()) return (C) this;

        final boolean checkContaining = containing != null;
        final boolean checkIntersecting = intersecting != null;

        boolean added;

        for (T t : items) {

            added = false;

            float x = getX.val(t);
            float y = getY.val(t);
            float z = getZ.val(t);
            float dSQ = distSq(cx, cy, cz, x, y, z);

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
    public C queryMinX(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMin(items, getX, bestMatch);
        return (C) this;
    }

    @Override
    public C queryMinY(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMin(items, getY, bestMatch);
        return (C) this;
    }

    @Override
    public C queryMinZ(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMin(items, getZ, bestMatch);
        return (C) this;
    }

    @Override
    public C queryMaxX(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMax(items, getX, bestMatch);
        return (C) this;
    }

    @Override
    public C queryMaxY(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMax(items, getY, bestMatch);
        return (C) this;
    }

    @Override
    public C queryMaxZ(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMax(items, getZ, bestMatch);
        return (C) this;
    }

    @Override
    public C queryClosest(float x, float y, float z, IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getClosest(items, x, y, z, getX, getY, getZ, bestMatch);
        return (C) this;
    }

    @Override
    public C add(T t) {
        items.add(t);
        return (C) this;
    }

    public C remove(T t) {
        items.remove(t);
        return (C) this;
    }

}
