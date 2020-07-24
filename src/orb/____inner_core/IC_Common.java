package nl.doekewartena.orb.inner_core;


import nl.doekewartena.orb.inner_core.util.function._GetDouble_T;

import java.util.ArrayList;
import java.util.List;

import static nl.doekewartena.orb.inner_core.IC_Math.distSq;


/**
 * Created by doekewartena on 1/19/15.
 */
public class IC_Common {


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // swapping with last before remove prevents shifting of the items
    public static void swapWithLastRemove(ArrayList l, int indexToRemove) {
        l.set(indexToRemove, l.get(l.size()-1));
        l.remove(l.size()-1);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final boolean allTheSame(double... numbers) {

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] != numbers[i - 1]) return false;
        }
        return true;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // move back to quadTree? usage can be quite tricky
    public static <C> C newInstance(C obj) {
        try {
            //System.out.println(obj.getClass().getSimpleName());
            C c = (C) obj.getClass().newInstance();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    /*
    public static <T> T getMax(List<T> items, GetDoubleT<T> getV) {
        double m = -Double.MAX_VALUE;
        T result = null;
        for (int i = 0; i < items.size(); i++) {
            T t = items.get(i);
            if (t != null) {
                double v = getV.val(t);
                if (v > m) {
                    m = v;
                    result = t;
                }
            }
        }
        return result;
    }
    */

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @SuppressWarnings("unchecked")
    public static <T> void getMax(List<T> items, _GetDouble_T<T> getV, BestMatch bestMatch) {

        for (T t : items) {
            if (t == null) continue;
            double m = getV.val(t);
            if (m > bestMatch.val) {
                bestMatch.val = m;
                bestMatch.item = t;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void getMax(List<T> items, _GetDouble_T<T> getV, _GetDouble_T<T> getV2, BestMatch bestMatch) {

        for (T t : items) {
            if (t == null) continue;
            double m = getV.val(t);
            if (m > bestMatch.val) {
                bestMatch.val = m;
                bestMatch.item = t;
            }
            m = getV2.val(t);
            if (m > bestMatch.val) {
                bestMatch.val = m;
                bestMatch.item = t;
            }
        }
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static <T> void getMin(List<T> items, _GetDouble_T<T> getV, BestMatch bestMatch) {

        for (T t : items) {
            if (t == null) continue;
            double m = getV.val(t);
            if (m < bestMatch.val) {
                bestMatch.val = m;
                bestMatch.item = t;
            }
        }
    }


    public static <T> void getMin(List<T> items, _GetDouble_T<T> getV, _GetDouble_T<T> getV2, BestMatch bestMatch) {

        for (T t : items) {
            if (t == null) continue;
            double m = getV.val(t);
            if (m < bestMatch.val) {
                bestMatch.val = m;
                bestMatch.item = t;
            }
            m = getV2.val(t);
            if (m < bestMatch.val) {
                bestMatch.val = m;
                bestMatch.item = t;
            }
        }
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static <T> T getMin(T[] arr, _GetDouble_T<T> getV) {

        double min = Double.POSITIVE_INFINITY;
        T res = null;

        for (T t : arr) {
            double v = getV.val(t);
            if (v < min) {
                min = v;
                res = t;
            }
        }

        return res;
    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public static <T> T getMax(T[] arr, _GetDouble_T<T> getV) {

        double max = Double.NEGATIVE_INFINITY;
        T res = null;

        for (T t : arr) {
            double v = getV.val(t);
            if (v > max) {
                max = v;
                res = t;
            }
        }

        return res;
    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static <T> void getClosest(List<T> items, double x, double y, _GetDouble_T<T> getX, _GetDouble_T<T> getY, BestMatch bestMatch) {

        for (T t : items) {

            double dist = distSq(x, y, getX.val(t), getY.val(t));

            if (dist < bestMatch.val) {
                bestMatch.val = dist;
                bestMatch.item = t;
                if (dist == 0) return;
            }
        }
    }

    public static <T> void getClosest(List<T> items, double x, double y, _GetDouble_T<T> getX, _GetDouble_T<T> getY, _GetDouble_T<T> getX2, _GetDouble_T<T> getY2, BestMatch bestMatch) {

        for (T t : items) {

            double dist = distSq(x, y, getX.val(t), getY.val(t));

            if (dist < bestMatch.val) {
                bestMatch.val = dist;
                bestMatch.item = t;
                if (dist == 0) return;
            }

            dist = distSq(x, y, getX2.val(t), getY2.val(t));

            if (dist < bestMatch.val) {
                bestMatch.val = dist;
                bestMatch.item = t;
                if (dist == 0) return;
            }

        }
    }


    public static <T> void getClosest(List<T> items, double x, double y, double z, _GetDouble_T<T> getX, _GetDouble_T<T> getY, _GetDouble_T<T> getZ, BestMatch bestMatch) {

        for (T t : items) {

            double dist = distSq(x, y, z, getX.val(t), getY.val(t), getZ.val(t));

            if (dist < bestMatch.val) {
                bestMatch.val = dist;
                bestMatch.item = t;
                if (dist == 0) return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void getClosest(List<T> items, double x, double y, double z, _GetDouble_T<T> getX, _GetDouble_T<T> getY, _GetDouble_T<T> getZ, _GetDouble_T<T> getX2, _GetDouble_T<T> getY2, _GetDouble_T<T> getZ2, BestMatch bestMatch) {

        for (T t : items) {

            double dist = distSq(x, y, z, getX.val(t), getY.val(t), getZ.val(t));

            if (dist < bestMatch.val) {
                bestMatch.val = dist;
                bestMatch.item = t;
                if (dist == 0) return;
            }

            dist = distSq(x, y, z, getX2.val(t), getY2.val(t), getZ2.val(t));

            if (dist < bestMatch.val) {
                bestMatch.val = dist;
                bestMatch.item = t;
                if (dist == 0) return;
            }

        }
    }
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public static class BestMatch<T> {

        public T item;
        public double val;

        public BestMatch(double val) {
            this.val = val;
        }



    }





    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}
