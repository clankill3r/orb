package orb.___outer_core.util.datastructure;


import nl.doekewartena.orb.inner_core.IC_Common;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree_3D;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by doekewartena on 6/11/15.
 */

// implement marker interface children / split?
// this is a binary tree since it can only have 2 children

// AABB?
// x1, y1, x2, y2?


public class OC_BinPackerGuillotine_3D<T, C extends OC_BinPackerGuillotine_3D> // extends?

        implements

        _Tree_3D<T, C>

        //Iterable<C>
    {

    public double x, y, z, w, h, d;

    public boolean used;

    public C parent;

    public T item;

    public C right;
    public C down;
    public C behind; // name back?



    public OC_BinPackerGuillotine_3D(double x, double y, double z, double w, double h, double d) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.d = d;
    }


    public C newInstance(double x, double y, double z, double w, double h, double d) {
        return (C) new OC_BinPackerGuillotine_3D(x, y, z, w, h, d);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public void split(double w, double h, double d) {
        used = true;
//        right  = (C) new OC_GuillotinePacker_3D(x + w, y    , z  , this.w - w, h         , d);
//        down   = (C) new OC_GuillotinePacker_3D(x    , y + h, z  , this.w    , this.h - h, d);
//        behind = (C) new OC_GuillotinePacker_3D(x    , y    , z+d, this.w    , this.h    , this.d - d);
        right  = newInstance(x + w, y    , z  , this.w - w, h         , d);
        down   = newInstance(x    , y + h, z  , this.w    , this.h - h, d);
        behind = newInstance(x    , y    , z+d, this.w    , this.h    , this.d - d);

        right.parent = this;
        down.parent = this;
        behind.parent = this;

    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public C find(double w, double h, double d) {

        if (this.w < w || this.h < h || this.d < d) return null;

        if (!used) {
            if (w <= this.w && h <= this.h && d <= this.d) return (C) this;
        }
        else {
            C result = (C) right.find(w, h, d);
            if (result != null) return (C) result;

            result = (C) down.find(w, h, d);
            if (result != null) return (C) result;

            result = (C) behind.find(w, h, d);
            if (result != null) return (C) result;
        }

       return null;

    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public C pack(double w, double h, double d) {

        C target = find(w, h, d);

        if (target != null) {
            target.split(w, h, d);
        }

        return target;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public C pack(T t, double w, double h, double d) {

        C where = pack(w, h, d);
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
                if (toReturn.behind  != null) open.add((C) toReturn.behind);

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
    public double z1() {
        return z;
    }

    @Override
    public double x2() {
        return x+w;
    }

    @Override
    public double y2() {
        return y+h;
    }

    @Override
    public double z2() {
        return z+d;
    }


    // todo

    @Override
    public C backFind(double x, double y, double z) {
        if (x2() > x || y2() > y || z2() > z) return null;

        if (contains_point(x, y, z)) return (C) this;

        if (parent != null) return (C) parent.backFind(x, y, z);

        return null;
    }

    @Override
    public C backFind(double x, double y, double z, double x2, double y2, double z2) {
        // todo
        // what will the purpose be of this method?
        System.out.println("todo, what about backFind aabb in OC_GuillotinePacker? ");
        return null;
    }

    @Override
    public C forwardFind(double x, double y, double z) {
        if (hasChildren()) {
            if (right.contains_point(x, y, z)) return (C) right.forwardFind(x, y, z);
            if (down.contains_point(x, y, z)) return (C) down.forwardFind(x, y, z);
        }
        if (this.contains_point(x, y, z)) return (C) this;

        return null;
    }

    @Override
    public C forwardFind(double x, double y, double z, double x2, double y2, double z2) {
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
        C[] result = (C[]) Array.newInstance(this.getClass(), 3);
        result[0] = right;
        result[1] = down;
        result[2] = behind;
        return result;
    }

    @Override
    public C insert(T t) {
        // todo
        System.out.println("todo, what about insert in OC_GuillotinePacker? ");
        return null;
    }

    @Override
    public C remove(T t) {
        // todo
        System.out.println("todo, what about remove in OC_GuillotinePacker? ");
        return null;
    }


        // todo:

        @Override
        public double getX2(T t) {
            return 0;
        }

        @Override
        public double getY2(T t) {
            return 0;
        }

        @Override
        public double getZ2(T t) {
            return 0;
        }

        @Override
        public C queryAll(List<T> dest) {
            return null;
        }

        @Override
        public T query(double tx, double ty, double tz) {
            return null;
        }

        @Override
        public C query(List<T> containing, List<T> intersecting, double tx, double ty, double tz) {
            return null;
        }

        @Override
        public C query(List<T> containing, List<T> intersecting, double tx1, double ty1, double tz1, double tx2, double ty2, double tz2) {
            return null;
        }

        @Override
        public C queryRadiusSq(List<T> containing, List<T> intersecting, double cx, double cy, double cz, double radiusSQ) {
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
        public C queryMinZ(IC_Common.BestMatch<T> bestMatch) {
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
        public C queryMaxZ(IC_Common.BestMatch<T> bestMatch) {
            return null;
        }

        @Override
        public C queryClosest(double x, double y, double z, IC_Common.BestMatch<T> bestMatch) {
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

        @Override
        public double getZ(T t) {
            return 0;
        }


        // . . . . . . . . . . . . . . . . . . . . . . . . . . . .




}
