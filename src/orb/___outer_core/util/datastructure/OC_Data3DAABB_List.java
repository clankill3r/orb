package nl.doekewartena.orb.outer_core.util.datastructure;


import nl.doekewartena.orb.inner_core.IC_Common;
import nl.doekewartena.orb.inner_core.util.datatstructure._Data_3D;
import nl.doekewartena.orb.inner_core.util.function._GetDouble_T;

import java.util.List;

/**
 * Created by doekewartena on 8/29/15.
 */
public class OC_Data3DAABB_List<T, C extends OC_Data3DAABB_List> implements _Data_3D<T, C> {



    _GetDouble_T<T> getX, getY, getZ, getX2, getY2, getZ2;

    List<T> items;

    public OC_Data3DAABB_List(_GetDouble_T<T> getX, _GetDouble_T<T> getY, _GetDouble_T<T> getZ, _GetDouble_T<T> getX2, _GetDouble_T<T> getY2, _GetDouble_T<T> getZ2, List<T> list) {
        //items = new ArrayList<>();
        this.getX = getX;
        this.getY = getY;
        this.getZ = getZ;
        this.getX2 = getX2;
        this.getY2 = getY2;
        this.getZ2 = getZ2;
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
        return getX2.val(t);
    }

    @Override
    public double getY2(T t) {
        return getY2.val(t);
    }

    @Override
    public double getZ2(T t) {
        return getZ2.val(t);
    }

    @Override
    public T query(double tx, double ty, double tz) {
        for (T t : items) {
            double x = getX.val(t);
            double y = getY.val(t);
            double z = getY.val(t);
            double x2 = getX2.val(t);
            double y2 = getY2.val(t);
            double z2 = getZ2.val(t);

            if (!(  tx > x2 ||
                    tx < x ||
                    ty > y2 ||
                    ty < y ||
                    tz > z2 ||
                    tz < z)) {
                return t;
            }

        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C query(List<T> containing, List<T> intersecting, double tx, double ty, double tz) {

        // like this?
        List<T> target = intersecting != null ? intersecting : containing;

        for (T t : items) {
            double x = getX.val(t);
            double y = getY.val(t);
            double z = getZ.val(t);
            double x2 = getX2.val(t);
            double y2 = getY2.val(t);
            double z2 = getZ2.val(t);

            if (!(  tx > x2 ||
                    tx < x ||
                    ty > y2 ||
                    ty < y ||
                    tz > z2 ||
                    tz < z)) {
                intersecting.add(t);
            }
        }
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C query(List<T> containing, List<T> intersecting, double tx1, double ty1, double tz1, double tx2, double ty2, double tz2) {

        final boolean checkContaining = containing != null;
        final boolean checkIntersecting = intersecting != null;

        boolean added;

        for (T t : items) {

            added = false;

            double x1 = getX.val(t);
            double y1 = getY.val(t);
            double z1 = getZ.val(t);
            double x2 = getX2.val(t);
            double y2 = getY2.val(t);
            double z2 = getZ2.val(t);

            if (checkContaining) {
                if (x1 > tx1 && x1 < tx2 &&
                    x2 > tx1 && x2 < tx2 &&
                    y1 > ty1 && y1 < ty2 &&
                    y2 > ty1 && y2 < ty2 &&
                    z1 > tz1 && z1 < tz2 &&
                    z2 > tz1 && z2 < tz2) {

                    containing.add(t);
                    added = true;

                }
            }
            if (!added && checkIntersecting) {
//                if (x1 >= tx1 && x1 <= tx2 &&
//                    x2 >= tx1 && x2 <= tx2 &&
//                    y1 >= ty1 && y1 <= ty2 &&
//                    y2 >= ty1 && y2 <= ty2) {
//
//                    intersecting.add(t);
//                }
                // less values
                if (!(  tx1 > x2 ||
                        tx2 < x1 ||
                        ty1 > y2 ||
                        ty2 < y1 ||
                        tz1 > z2 ||
                        tz2 < z1)) {
                    intersecting.add(t);
                }
            }

        }
        return (C) this;
    }




    @Override
    @SuppressWarnings("unchecked")
    public C queryRadiusSq(List<T> containing, List<T> intersecting, double cx, double cy, double cz, double radiusSQ) {

        if (items.isEmpty()) return (C) this;

        // todo, check for containing and intersecting being the same list
        // if it is skip checkContaining

        final boolean checkContaining = containing != null;
        final boolean checkIntersecting = intersecting != null;

        boolean added;


        for (T t : items) {

            added = false;

            double x1 = getX.val(t);
            double y1 = getY.val(t);
            double z1 = getZ.val(t);
            double x2 = getX2.val(t);
            double y2 = getY2.val(t);
            double z2 = getZ2.val(t);

            double furthestX = Math.abs(cx - x1) > Math.abs(cx-x2) ? x1 : x2;
            double furthestY = Math.abs(cy - y1) > Math.abs(cy-y2) ? y1 : y2;
            double furthestZ = Math.abs(cz - z1) > Math.abs(cz-z2) ? z1 : z2;

            double dSqFurthest = (cx-furthestX)*(cx-furthestX) + (cy-furthestY)*(cy-furthestY) + (cz-furthestZ)*(cz-furthestZ);

            if (checkContaining) {

                // todo <= ?
                if (dSqFurthest < radiusSQ) {

                    containing.add(t);
                    added = true;

                }
            }
            if (!added && checkIntersecting) {

                double closestX = Math.max(Math.min(cx, x2), x1);
                double closestY = Math.max(Math.min(cy, y2), y1);
                double closestZ = Math.max(Math.min(cz, z2), z1);

                double dSqClosest = (cx-closestX)*(cx-closestX) + (cy-closestY)*(cy-closestY) + (cz-closestZ)*(cz-closestZ);

                if (dSqClosest <= radiusSQ) {
                    intersecting.add(t);
                }
            }

        }
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMinX(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMin(items, getX, getX2, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMinY(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMin(items, getY, getY2, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMinZ(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMin(items, getZ, getZ2, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMaxX(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMax(items, getX, getX2, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMaxY(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMax(items, getY, getY2, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMaxZ(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMax(items, getZ, getZ2, bestMatch);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryClosest(double x, double y, double z, IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getClosest(items, x, y, z, getX, getY, getZ, getX2, getY2, getZ2, bestMatch);
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
        // do we have null for a reason? Or do we still had to do return (C) this?
        return null;
    }

}
