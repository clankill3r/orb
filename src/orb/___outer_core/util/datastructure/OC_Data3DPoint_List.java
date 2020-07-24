package orb.___outer_core.util.datastructure;


import nl.doekewartena.orb.inner_core.IC_Common;
import nl.doekewartena.orb.inner_core.util.datatstructure._Data_3D;
import nl.doekewartena.orb.inner_core.util.function._GetDouble_T;

import java.util.List;

import static nl.doekewartena.orb.inner_core.IC_Math.distSq;


/**
 * Created by doekewartena on 8/29/15.
 */
public class OC_Data3DPoint_List<T, C extends OC_Data3DPoint_List> implements _Data_3D<T, C> {

    _GetDouble_T<T> getX, getY, getZ;

    List<T> items;

    public OC_Data3DPoint_List(_GetDouble_T<T> getX, _GetDouble_T<T> getY, _GetDouble_T<T> getZ, List<T> list) {
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
    public double getX(T t) {
        return getX.val(t);
    }

    @Override
    public double getY(T t) {
        return getY.val(t);
    }

    @Override
    public double getZ(T t) {
        return getZ.val(t);
    }

    @Override
    public double getX2(T t) {
        System.out.println("[ERROR]: getX2 used with point list");
        return -1;
    }

    @Override
    public double getY2(T t) {
        System.out.println("[ERROR]: getY2 used with point list");
        return -1;
    }

    @Override
    public double getZ2(T t) {
        System.out.println("[ERROR]: getZ2 used with point list");
        return -1;
    }

    @Override
    public T query(double tx, double ty, double tz) {
        for (T t : items) {
            double x = getX.val(t);
            double y = getY.val(t);
            double z = getZ.val(t);
            if (x == tx && y == ty && z == tz) return t;
        }
        return null;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, double tx, double ty, double tz) {

        // like this?
        List<T> target = intersecting != null ? intersecting : containing;

        for (T t : items) {
            double x = getX.val(t);
            double y = getY.val(t);
            double z = getZ.val(t);
            if (x == tx && y == ty && z == tz) target.add(t);
        }
        return (C) this;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, double tx1, double ty1, double tz1, double tx2, double ty2, double tz2) {

        final boolean checkContaining = containing != null;
        final boolean checkIntersecting = intersecting != null;

        boolean added;

        for (T t : items) {

            added = false;

            double x = getX.val(t);
            double y = getY.val(t);
            double z = getZ.val(t);


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
    public C queryRadiusSq(List<T> containing, List<T> intersecting, double cx, double cy, double cz, double radiusSQ) {

        if (items.isEmpty()) return (C) this;

        final boolean checkContaining = containing != null;
        final boolean checkIntersecting = intersecting != null;

        boolean added;

        for (T t : items) {

            added = false;

            double x = getX.val(t);
            double y = getY.val(t);
            double z = getZ.val(t);
            double dSQ = distSq(cx, cy, cz, x, y, z);

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
    public C queryClosest(double x, double y, double z, IC_Common.BestMatch<T> bestMatch) {
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
