package orb.___outer_core.util.datastructure;


import nl.doekewartena.orb.inner_core.IC_Common;
import nl.doekewartena.orb.inner_core.util.datatstructure._Data_2D;
import nl.doekewartena.orb.inner_core.util.function._GetDouble_T;

import java.util.List;

/**
 * Created by doekewartena on 8/29/15.
 */
public class OC_Data2DAABB_List<T, C extends OC_Data2DAABB_List> implements _Data_2D<T, C> {

    _GetDouble_T<T> getX, getY, getX2, getY2;

    List<T> items;

    public OC_Data2DAABB_List(_GetDouble_T<T> getX, _GetDouble_T<T> getY, _GetDouble_T<T> getX2, _GetDouble_T<T> getY2, List<T> list) {
        //items = new ArrayList<>();
        this.getX = getX;
        this.getY = getY;
        this.getX2 = getX2;
        this.getY2 = getY2;
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
    public double getX(T t) {
        return getX.val(t);
    }

    @Override
    public double getY(T t) {
        return getY.val(t);
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
    public T query(double tx, double ty) {
        for (T t : items) {
            double x = getX.val(t);
            double y = getY.val(t);
            double x2 = getX2.val(t);
            double y2 = getY2.val(t);

            if (!(  tx > x2 ||
                    tx < x ||
                    ty > y2 ||
                    ty < y)) {
                return t;
            }

        }
        return null;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, double tx, double ty) {

        // like this?
        List<T> target = intersecting != null ? intersecting : containing;

        for (T t : items) {
            double x = getX.val(t);
            double y = getY.val(t);
            double x2 = getX2.val(t);
            double y2 = getY2.val(t);

            if (!(  tx > x2 ||
                    tx < x ||
                    ty > y2 ||
                    ty < y)) {
                intersecting.add(t);
            }
        }
        return (C) this;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, double tx1, double ty1, double tx2, double ty2) {

        final boolean checkContaining = containing != null;
        final boolean checkIntersecting = intersecting != null;

        boolean added;

        for (T t : items) {

            added = false;

            double x1 = getX.val(t);
            double y1 = getY.val(t);
            double x2 = getX2.val(t);
            double y2 = getY2.val(t);

            if (checkContaining) {
                if (x1 > tx1 && x1 < tx2 &&
                    x2 > tx1 && x2 < tx2 &&
                    y1 > ty1 && y1 < ty2 &&
                    y2 > ty1 && y2 < ty2) {

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
                        ty2 < y1)) {
                    intersecting.add(t);
                }
            }

        }
        return (C) this;
    }




    @Override
    public C queryRadiusSq(List<T> containing, List<T> intersecting, double cx, double cy, double radiusSQ) {

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
            double x2 = getX2.val(t);
            double y2 = getY2.val(t);

            double furthestX = Math.abs(cx - x1) > Math.abs(cx-x2) ? x1 : x2;
            double furthestY = Math.abs(cy - y1) > Math.abs(cy-y2) ? y1 : y2;

            double dSqFurthest = (cx-furthestX)*(cx-furthestX) + (cy-furthestY)*(cy-furthestY);

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

                double dSqClosest = (cx-closestX)*(cx-closestX) + (cy-closestY)*(cy-closestY);

                if (dSqClosest <= radiusSQ) {
                    intersecting.add(t);
                }
            }

        }
        return (C) this;
    }

    @Override
    public C queryMinX(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMin(items, getX, getX2, bestMatch);
        return (C) this;
    }

    @Override
    public C queryMinY(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMin(items, getY, getY2, bestMatch);
        return (C) this;
    }

    @Override
    public C queryMaxX(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMax(items, getX, getX2, bestMatch);
        return (C) this;
    }

    @Override
    public C queryMaxY(IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getMax(items, getY, getY2, bestMatch);
        return (C) this;
    }

    @Override
    public C queryClosest(double x, double y, IC_Common.BestMatch<T> bestMatch) {
        IC_Common.getClosest(items, x, y, getX, getY, getX2, getY2, bestMatch);
        return (C) this;
    }

    @Override
    public C add(T t) {
        items.add(t);
        return (C) this;
    }

    public C remove(T t) {
        items.remove(t);
        return null;
    }

}
