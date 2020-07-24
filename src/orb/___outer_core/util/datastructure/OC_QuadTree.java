package orb.___outer_core.util.datastructure;


import nl.doekewartena.orb.inner_core.IC_Common.BestMatch;
import nl.doekewartena.orb.inner_core.util.datatstructure._Data_2D;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree_2D;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;


/**
 * Created by doekewartena on 6/4/15.
 */


public abstract class OC_QuadTree<T, C extends OC_QuadTree>

        implements

        _Tree_2D<T, C>,
        _Data_2D<T, C>

    {

    public final static int TL = 0; // top left
    public final static int TR = 1; // top right
    public final static int BL = 2; // bottom left
    public final static int BR = 3; // bottom right

    // todo: remove this and the init method?
    double x1, x2, y1, y2;

    protected C[] children;

    protected C parent;

    // tmp public
    public _Data_2D<T, ?> data;


    // data in constructor? better not I guess since it's not required
    // it will just be good so people don't forget
    public OC_QuadTree(C parent, double x1, double y1, double x2, double y2, _Data_2D<T, ?> data) { //}, int level) {
        init(parent, x1, y1, x2, y2, data); //, level);
    }

    // remove and leave to implementation?
    @Override
    public double x1() {
        return x1;
    }

    @Override
    public double y1() {
        return y1;
    }

    @Override
    public double x2() {
        return x2;
    }

    @Override
    public double y2() {
        return y2;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Float.floatToIntBits((float) x1);
        result = 31 * result + Float.floatToIntBits((float) y1);
        result = 31 * result + Float.floatToIntBits((float) x2);
        result = 31 * result + Float.floatToIntBits((float) y2);
        return result;
    }

    // get rid of this init method?
    protected void init(C parent, double x1, double y1, double x2, double y2, _Data_2D<T, ?> data) { //}, int level) {

        this.parent = parent;

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.data = data;
        // how often is this used?, maybe not store for memory efficiency
        //cx = x1 + (x2-x1)/2.0;
        //cy = y1 + (y2-y1)/2.0;

        //this.level = level;
    }

    // =================================================================================================


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
        return children != null && children.length > 0;
    }

    @Override
    public C[] children() {
        return children;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    @Override
    public Iterator<C> iterator() {

        Stack<C> open = new Stack<>();

        // todo, do we want to loop over the root?
        // (probably yes, cause it's easy to skip in an implementation, while it's harder to add)
        open.add((C) this);

        return new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return open.size() > 0;
            }

            @Override
            public C next() {
                C toReturn = open.pop();

                if (toReturn.hasChildren()) {
                    for (int i = 0; i < toReturn.children.length; i++) {
                        C c = (C) toReturn.children[i];
                        open.push(c);
                    }

                }
                return toReturn;
            }
        };
    }



    // =================================================================================================
    // OC_Tree2D

    @Override
    public C backFind(double x, double y) {

        if (contains_point(x, y)) {
            return (C) this;
        }
        if (parent == null) return null;
        return (C) parent.backFind(x, y);


    }

    @Override
    public C backFind(double x, double y, double x2, double y2) {

        if (contains_aabb(x, y, x2, y2)) {
            return (C) this;
        }
        if (parent == null) return null;
        return (C) parent.backFind(x, y, x2, y2);


    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    // or upward and downward
    @Override
    public C forwardFind(double x, double y) {
        if (hasChildren()) {
            int where = getIndex(x, y);
            return (C) children[where].forwardFind(x, y);
        }
        return (C) this;
    }

    @Override
    public C forwardFind(double x, double y, double x2, double y2) {
        if (hasChildren()) {
            int where = getIndex(x, y, x2, y2);
            if (where != -1) return (C) children[where].forwardFind(x, y, x2, y2);
        }
        return (C) this;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public C queryAll(List<T> dest) {
        if (!data.isEmpty()) {
            data.queryAll(dest);
        }

        if (hasChildren()) {
            for (int i = 0; i < children.length; i++) {
                children[i].queryAll(dest);
            }
        }
        return (C) this;
    }




        // todo, do want to search up as well? (counts for more methods)
    @Override
    public T query(double tx, double ty) {

        if (!contains_point(tx, ty)) return null;

        if (!data.isEmpty()) {
            T t = data.query(tx, ty);
            if (t != null) return t;
        }

        if (hasChildren()) {
            int where = getIndex(tx, ty);
            return (T) children[where].query(tx, ty);
        }

        return null;
    }


    @Override
    public C query(List<T> containing, List<T> intersecting, double tx, double ty) {

        if (!contains_point(tx, ty)) return (C) this;

        if (!data.isEmpty()) {
            data.query(containing, intersecting, tx, ty);
        }

        if (hasChildren()) {
            int where = getIndex(tx, ty);
            children[where].query(containing, intersecting, tx, ty);
        }
        return (C) this;
    }



    @Override
    public C query(List<T> containing, List<T> intersecting, double tx1, double ty1, double tx2, double ty2) {

        if (this.fitsWithin_aabb(tx1, ty1, tx2, ty2)) {

            queryAll(containing);

        }
        else if (this.contains_aabb(tx1, ty1, tx2, ty2) || this.intersects_aabb(tx1, ty1, tx2, ty2)) {

            if (!data.isEmpty()) {
                data.query(containing, intersecting, tx1, ty1, tx2, ty2);
            }
            if (hasChildren()) {
//                children[TR].query(containing, intersecting, tx1, ty1, tx2, ty2);
//                children[TL].query(containing, intersecting, tx1, ty1, tx2, ty2);
//                children[BL].query(containing, intersecting, tx1, ty1, tx2, ty2);
//                children[BR].query(containing, intersecting, tx1, ty1, tx2, ty2);

                for (int i = 0; i < children.length; i++) {
                    children[i].query(containing, intersecting, tx1, ty1, tx2, ty2);
                }

            }

        }
        return (C) this;
    }


    @Override
    public C queryRadiusSq(List<T> containing, List<T> intersecting, double cx, double cy, double radiusSQ) {

        // todo, make the parameters so that we don't have to square! (no cx, cy maybe)
        double radius = Math.sqrt(radiusSQ);

        double tx1, ty1, tx2, ty2;
        tx1 = cx - radius;
        ty1 = cy - radius;
        tx2 = cx + radius;
        ty2 = cy + radius;

        queryRadiusSq(containing, intersecting, tx1, ty1, tx2, ty2, cx, cy, radiusSQ);

        return (C) this;
    }

    // todo, we should optimes for a really big radius
    // divide a circle into squares with some really good code
    // we need a aabb in circle test method
    //
    protected void queryRadiusSq(List<T> containing, List<T> intersecting, double tx1, double ty1, double tx2, double ty2, double cx, double cy, double radiusSQ) {

        if (!this.contains_aabb(tx1, ty1, tx2, ty2) && !this.intersects_aabb(tx1, ty1, tx2, ty2)) {
            return;
        }

        if (!data.isEmpty()) {
            //System.out.println("query radius sq");
            data.queryRadiusSq(containing, intersecting, cx, cy, radiusSQ);
        }
        if (hasChildren()) {
//            children[TR].queryRadiusSq(containing, intersecting, tx1, ty1, tx2, ty2, cx, cy, radiusSQ);
//            children[TL].queryRadiusSq(containing, intersecting, tx1, ty1, tx2, ty2, cx, cy, radiusSQ);
//            children[BL].queryRadiusSq(containing, intersecting, tx1, ty1, tx2, ty2, cx, cy, radiusSQ);
//            children[BR].queryRadiusSq(containing, intersecting, tx1, ty1, tx2, ty2, cx, cy, radiusSQ);
            for (int i = 0; i < children.length; i++) {
                children[i].queryRadiusSq(containing, intersecting, tx1, ty1, tx2, ty2, cx, cy, radiusSQ);
            }
        }


    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .



    // todo, we could pass an array with an order? getMinX(bestMatch, {TL, BL, TR, BR});

    @Override
    public C queryMinX(BestMatch<T> bestMatch) {

        if (x1() > bestMatch.val) return (C) this;

        if (!data.isEmpty()) {
            data.queryMinX(bestMatch);
        }

        if (hasChildren()) {
            // we don't loop over children cause order is important for speed!
            children[TL].queryMinX(bestMatch);
            children[BL].queryMinX(bestMatch);
            children[TR].queryMinX(bestMatch);
            children[BR].queryMinX(bestMatch);
        }
        return (C) this;
    }


    @Override
    public C queryMinY(BestMatch<T> bestMatch) {

        if (y1() > bestMatch.val) return (C) this;

        if (!data.isEmpty()) {
            data.queryMinY(bestMatch);
        }

        if (hasChildren()) {
            // we don't loop over children cause order is important for speed!
            children[TL].queryMinY(bestMatch);
            children[TR].queryMinY(bestMatch);
            children[BL].queryMinY(bestMatch);
            children[BR].queryMinY(bestMatch);
        }
        return (C) this;
    }


    @Override
    public C queryMaxX(BestMatch<T> bestMatch) {

        if (x2() < bestMatch.val) return (C) this;

        if (!data.isEmpty()) {
            data.queryMaxX(bestMatch);
        }

        if (hasChildren()) {
            // we don't loop over children cause order is important for speed!
            children[TR].queryMaxX(bestMatch);
            children[BR].queryMaxX(bestMatch);
            children[TL].queryMaxX(bestMatch);
            children[BL].queryMaxX(bestMatch);
        }
        return (C) this;
    }


    @Override
    public C queryMaxY(BestMatch<T> bestMatch) {

        if (y2() < bestMatch.val) return (C) this;

        if (!data.isEmpty()) {
            data.queryMaxY(bestMatch);
        }

        if (hasChildren()) {
            // we don't loop over children cause order is important for speed!
            children[BL].queryMaxY(bestMatch);
            children[BR].queryMaxY(bestMatch);
            children[TL].queryMaxY(bestMatch);
            children[TR].queryMaxY(bestMatch);
        }
        return (C) this;
    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .




    @Override
    public C queryClosest(double x, double y, BestMatch<T> bestMatch) {

        if (distToPointSq(x, y) > bestMatch.val) {
            return (C) this;
        }

        if (!data.isEmpty()) {
            data.queryClosest(x, y, bestMatch);
        }

        if (hasChildren()) {

            int rl = cx() < x ? 1 : 0;
            int bt = cy() < y ? 1 : 0;

            children[bt*2+rl].queryClosest(x, y, bestMatch);
            if (bestMatch.val == 0) return (C) this;
            children[bt*2+(1-rl)].queryClosest(x, y, bestMatch);
            if (bestMatch.val == 0) return (C) this;
            children[(1-bt)*2+rl].queryClosest(x, y, bestMatch);
            if (bestMatch.val == 0) return (C) this;
            children[(1-bt)*2+(1-rl)].queryClosest(x, y, bestMatch);
            //if (bestMatch.val == 0) return;
        }
        return (C) this;

    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .



    /*
    protected void split() {

        if (children == null) children = (C[]) Array.newInstance(this.getClass(), 4);

        children[TR] = OC_Common.newInstance((C) this);
        children[TL] = OC_Common.newInstance((C) this);
        children[BL] = OC_Common.newInstance((C) this);
        children[BR] = OC_Common.newInstance((C) this);

        children[TR].init(this, cx, y1, x2, cy, level+1);
        children[TL].init(this, x1, y1, cx, cy, level+1);
        children[BL].init(this, x1, cy, cx, y2, level+1);
        children[BR].init(this, cx, cy, x2, y2, level+1);

    }
    */

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


//    // todo, remove?
//    public int getLevel() {
//        return level;
//    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


//    // untested
//    public void merge() {
//
//        queryAll(items);
//
//        for (int i = 0; i < children.length; i++) {
//            children[i] = null;
//        }
//
//        children = null;
//    }



    // todo, make this part of OC_Tree2D? (maybe better to have a forwardFind with a limit)
    // the thing with an index is that we could have different indexes for top left for example
    // this would depend on the implementation
    // other name?
    public int getIndex(double x, double y) {
        //if (!hasQuads()) return -1;
        if (y < cy()) return x < cx() ? TL : TR;
        else          return x < cx() ? BL : BR;

    }

    // other name?
    public int getIndex(double x, double y, double x2, double y2) {

        int index1 = getIndex(x, y);
        int index2 = getIndex(x2, y2);

        if (index1 == index2) return index1;

        return -1;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // happens in interface now
    /*
    public C getDeepestContaining(_AABB_2D bounds) {

        // make level 0 check? getDeepestContaining(bounds, maxLevel, level)

        // do we want this to be an intersecting check?
        // we could also do a contains check,
        // BUT then we get null of our bounds only intersects a little
        // while in practice it is probably more convenient
        // to return the root on a intersection
        if (!intersects_aabb(bounds)) return null;

        if (hasChildren() && contains_aabb(bounds)) {
            for (C child : children()) {
                if (child.contains_aabb(bounds)) {
                    return (C) child.getDeepestContaining(bounds);
                }
            }
        }

        return (C) this;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // todo, do we want a capture for the list?
    public void getIntersectingLeafs(_AABB_2D bounds, List l) {

        if (hasChildren()) {
            for (C child : children()) {
                child.getIntersectingLeafs(bounds, l);
            }
        }
        else {
            if (intersects_aabb(bounds)) l.add(this);
        }
    }
    */
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .




    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}



