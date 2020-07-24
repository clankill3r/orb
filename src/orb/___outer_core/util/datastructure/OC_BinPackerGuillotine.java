package nl.doekewartena.orb.outer_core.util.datastructure;

import nl.doekewartena.orb.inner_core.IC_Common;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree_2D;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by doekewartena on 6/11/15.
 */

// implement marker interface children / split?
// this is a binary tree since it can only have 2 children



public class OC_BinPackerGuillotine<T, C extends OC_BinPackerGuillotine> // extends?

        implements

        _Tree_2D<T, C>

        //Iterable<C>  << happens in _Tree
        {


    public double x, y, w, h;

    public boolean used;

    public C parent;

    // _Data?
    public T item;

    // store in an array?
    public C right;
    public C down;



    public OC_BinPackerGuillotine(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    // todo: what about: (we can use that in the split method, which makes it much more save cause OS has not to override
    // split for example.

    public C newInstance(double x, double y, double w, double h) {
        return (C) new OC_BinPackerGuillotine(x, y, w, h);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public void split(double w, double h) {
        used = true;
//        right = (C) new OC_GuillotinePacker(x + w, y, this.w - w, h);
//        down  = (C) new OC_GuillotinePacker(x, y + h, this.w, this.h - h);
        right = newInstance(x + w, y, this.w - w, h);
        down  = newInstance(x, y + h, this.w, this.h - h);

        right.parent = this;
        down.parent = this;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public C find(double w, double h) {

        if (this.w < w || this.h < h) return null;

        if (!used) {
            if (w <= this.w && h <= this.h) return (C) this;
        }
        else {
            C result = (C) right.find(w, h);
            if (result != null) return (C) result;

            result = (C) down.find(w, h);
            if (result != null) return (C) result;
        }

       return null;

    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public C pack(double w, double h) {

        C target = find(w, h);

        if (target != null) {
            target.split(w, h);
        }

        return target;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public C pack(T t, double w, double h) {

        C where = pack(w, h);
        if (where != null) where.item = t;
        return where;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    @Override
    public Iterator<C> iterator() {

        ArrayList<C> open = new ArrayList<>();

        open.add((C) this);

        return new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return open.size() > 0;
            }

            @Override
            public C next() {

                C toReturn = open.remove(open.size()-1);

                if (toReturn.right != null) open.add((C) toReturn.right);
                if (toReturn.down  != null) open.add((C) toReturn.down);

                return toReturn;
            }
        };

    }

    @Override
    public double x1() {
        return x;
    }

    @Override
    public double y1() {
        return y;
    }

    @Override
    public double x2() {
        return x+w;
    }

    @Override
    public double y2() {
        return y+h;
    }


    // maybe a find method as well? (in interface) (it might could even be a default based on forward find and back find)
    @Override
    public C backFind(double x, double y) {

        if (x2() > x || y2() > y) return null;

        if (contains_point(x, y)) return (C) this;

        if (parent != null) return (C) parent.backFind(x, y);

        return null;
    }

    @Override
    public C backFind(double x, double y, double x2, double y2) {
        // todo
        // what will the purpose be of this method?
        // find the first bin that fits this condition (it doesn't have to be free)
        System.out.println("todo, what about backFind aabb in OC_GuillotinePacker? ");
        return null;
    }

    @Override
    public C forwardFind(double x, double y) {

        if (hasChildren()) {
            if (right.contains_point(x, y)) return (C) right.forwardFind(x, y);
            if (down.contains_point(x, y)) return (C) down.forwardFind(x, y);
        }
        if (this.contains_point(x, y)) return (C) this;

        return null;
    }

    @Override
    public C forwardFind(double x, double y, double x2, double y2) {
        // todo
        System.out.println("todo, what about forwardFind aabb in OC_GuillotinePacker? ");
        return null;
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public C parent() {
        return parent;
    }

    @Override
    public boolean hasChildren() {
        return right != null || down != null;
    }

    @Override
    public C[] children() {
        C[] result = (C[]) Array.newInstance(this.getClass(), 2);
        result[0] = right;
        result[1] = down;
        return result;
    }

    @Override
    public C insert(T t) {
        // todo
        // we have getX etc. so in theory we should be able to insert it!
        System.out.println("todo, what about insert in OC_GuillotinePacker? ");
        return null;
    }

    @Override
    public C remove(T t) {
        // todo
        // we should find where it is and remove it
        System.out.println("todo, what about remove in OC_GuillotinePacker? ");
        return null;
    }

            // todo

            @Override
            public double getX2(T t) {
                return 0;
            }

            @Override
            public double getY2(T t) {
                return 0;
            }

            @Override
            public C queryAll(List<T> dest) {
                return null;
            }

            @Override
            public T query(double tx, double ty) {
                return null;
            }

            @Override
            public C query(List<T> containing, List<T> intersecting, double tx, double ty) {
                return null;
            }

            @Override
            public C query(List<T> containing, List<T> intersecting, double tx1, double ty1, double tx2, double ty2) {
                return null;
            }

            @Override
            public C queryRadiusSq(List<T> containing, List<T> intersecting, double cx, double cy, double radiusSQ) {
                return null;
            }

            @Override
            public C queryMinX(IC_Common.BestMatch<T> bestMatch) {
                return null;
            }

            @Override
            public C queryMinY(IC_Common.BestMatch<T> bestMatch) {
                return null;
            }

            @Override
            public C queryMaxX(IC_Common.BestMatch<T> bestMatch) {
                return null;
            }

            @Override
            public C queryMaxY(IC_Common.BestMatch<T> bestMatch) {
                return null;
            }

            @Override
            public C queryClosest(double x, double y, IC_Common.BestMatch<T> bestMatch) {
                return null;
            }

            @Override
            public double getX(T t) {
                return 0;
            }

            @Override
            public double getY(T t) {
                return 0;
            }


            // . . . . . . . . . . . . . . . . . . . . . . . . . . . .




}
