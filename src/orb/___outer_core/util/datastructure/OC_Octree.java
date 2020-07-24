package orb.___outer_core.util.datastructure;


import orb.____inner_core.IC_Common;
import orb.____inner_core.util.datatstructure._Data_3D;
import orb.____inner_core.util.datatstructure._Tree_3D;

import static orb.____inner_core.IC_Math.*;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by doekewartena on 8/27/15.
 */
public abstract class OC_Octree<T, C extends OC_Octree>

        implements

        //_Tree3D<T, C>,
        //_3D_Query<T, C>
        //_Octree<T, C>
        _Tree_3D<T, C>

    {

    

    public final static int TLF = 0; // top left front
    public final static int TRF = 1; // top right front
    public final static int BLF = 2; // bottom left front
    public final static int BRF = 3; // bottom right front

    public final static int TLB = 4; // top left back
    public final static int TRB = 5; // top right back
    public final static int BLB = 6; // bottom left back
    public final static int BRB = 7; // bottom right back

    // todo: remove and remove init method?
    protected float x1, y1, x2, y2;
    protected float z1, z2;

    protected C[] children;

    protected C parent;

    _Data_3D<T, ?> data;


    public OC_Octree(C parent, float x1, float y1, float z1, float x2, float y2, float z2, _Data_3D<T, ?> data) {
        init(parent, x1, y1, z1, x2, y2, z2, data);
    }


    // to interface OC_Tree?
    //abstract boolean hasItems();

    // once more,
    @Override
    public float x1() {
        return x1;
    }

    @Override
    public float y1() {
        return y1;
    }

    @Override
    public float x2() {
        return x2;
    }

    @Override
    public float y2() {
        return y2;
    }

    @Override
    public float z1() {
        return z1;
    }

    @Override
    public float z2() {
        return z2;
    }

    @Override
    public int hashCode() {
        int result = 1;
        // todo, is 31 to high for this case?
        result = 31 * result + Float.floatToIntBits((float) x1);
        result = 31 * result + Float.floatToIntBits((float) y1);
        result = 31 * result + Float.floatToIntBits((float) z1);
        result = 31 * result + Float.floatToIntBits((float) x2);
        result = 31 * result + Float.floatToIntBits((float) y2);
        result = 31 * result + Float.floatToIntBits((float) z2);
        return result;
    }

    protected void init(C parent, float x1, float y1, float z1, float x2, float y2, float z2, _Data_3D<T, ?> data) {

        this.parent = parent;

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;

        this.data = data;
//        cx = x1 + (x2-x1)/2.0;
//        cy = y1 + (y2-y1)/2.0;
//        cz = z1 + (z2-z1)/2.0;
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
    @SuppressWarnings("unchecked")
    public Iterator<C> iterator() {

        Stack<C> open = new Stack<>();
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
    // OC_Tree3D

    @Override
    @SuppressWarnings("unchecked")
    public C backFind(float x, float y, float z) {

        if (contains_point(x, y, z)) {
            return (C) this;
        }
        if (parent == null) return null;
        return (C) parent.backFind(x, y, z);


    }

    @Override
    @SuppressWarnings("unchecked")
    public C backFind(float x, float y, float z, float x2, float y2, float z2) {

        if (contains_aabb(x, y, z, x2, y2, z2)) {
            return (C) this;
        }
        if (parent == null) return null;
        return (C) parent.backFind(x, y, z, x2, y2, z2);


    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    @SuppressWarnings("unchecked")
    public C forwardFind(float x, float y, float z) {
        if (hasChildren()) {
            int where = getIndex(x, y, z);
            return (C) children[where].forwardFind(x, y, z);
        }
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C forwardFind(float x, float y, float z, float x2, float y2, float z2) {
        if (hasChildren()) {
            int where = getIndex(x, y, z, x2, y2, z2);
            if (where != -1) return (C) children[where].forwardFind(x, y, z, x2, y2, z2);
        }
        return (C) this;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    @SuppressWarnings("unchecked")
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

    @Override
    @SuppressWarnings("unchecked")
    public T query (float tx, float ty, float tz) {
        if (!contains_point(tx, ty, tz)) return null;

        if (!data.isEmpty()) {
            T t = data.query(tx, ty, tz);
            if (t != null) return t;
        }

        if (hasChildren()) {
            int where = getIndex(tx, ty, tz);
            return (T) children[where].query(tx, ty, tz);
        }

        return null;
    }


    @Override
    @SuppressWarnings("unchecked")
    public C query (List<T> containing, List<T> intersecting, float tx, float ty, float tz) {
        if (!contains_point(tx, ty, tz)) return (C) this;

        if (!data.isEmpty()) {
            data.query(containing, intersecting, tx, ty, tz);
        }

        if (hasChildren()) {
            int where = getIndex(tx, ty, tz);
            children[where].query(containing, intersecting, tx, ty, tz);
        }
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C query (List<T> containing, List<T> intersecting, float tx1, float ty1, float tz1, float tx2, float ty2, float tz2) {
        if (this.fitsWithin_aabb(tx1, ty1, tz1, tx2, ty2, tz2)) {

            queryAll(containing);

        }
        else if (this.contains_aabb(tx1, ty1, tz1, tx2, ty2, tz2) || this.intersects_aabb(tx1, ty1, tz1, tx2, ty2, tz2)) {

            if (!data.isEmpty()) {

                data.query(containing, intersecting, tx1, ty1, tz1, tx2, ty2, tz2);

            }
            if (hasChildren()) {
                for (int i = 0; i < children.length; i++) {
                    children[i].query(containing, intersecting, tx1, ty1, tz1, tx2, ty2, tz2);
                }
            }

        }
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryRadius (List<T> containing, List<T> intersecting, float cx, float cy, float cz, float radius) {
        queryRadiusSq(containing, intersecting, cx, cy, cz, radius * radius);
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryRadiusSq (List<T> containing, List<T> intersecting, float cx, float cy, float cz, float radiusSQ) {

        float radius = sqrt(radiusSQ);

        float tx1, ty1, tz1, tx2, ty2, tz2;
        tx1 = cx - radius;
        ty1 = cy - radius;
        tz1 = cz - radius;
        tx2 = cx + radius;
        ty2 = cy + radius;
        tz2 = cz + radius;

        queryRadiusSq(containing, intersecting, tx1, ty1, tz1, tx2, ty2, tz2, cx, cy, cz, radiusSQ);

        return (C) this;
    }

    protected void queryRadiusSq(List<T> containing, List<T> intersecting,
                                 float tx1, float ty1, float tz1, float tx2, float ty2, float tz2,
                                 float cx, float cy, float cz, float radiusSQ) {

        if (!this.contains_aabb(tx1, ty1, tz1, tx2, ty2, tz2) && !this.intersects_aabb(tx1, ty1, tz1, tx2, ty2, tz2)) {
            return;
        }

        if (!data.isEmpty()) {
            data.queryRadiusSq(containing, intersecting, cx, cy, cz, radiusSQ);
        }

        if (hasChildren()) {
            for (int i = 0; i < children.length; i++) {
                children[i].queryRadiusSq(containing, intersecting, tx1, ty1, tz1, tx2, ty2, tz2, cx, cy, cz, radiusSQ);
            }
        }

    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    @SuppressWarnings("unchecked")
    public C queryMinX(IC_Common.BestMatch<T> bestMatch) {

        if (x1() > bestMatch.val) return (C) this;

        if (!data.isEmpty()) {
            data.queryMinX(bestMatch);
        }

        if (hasChildren()) {
            // we don't loop over children cause order is important for speed!
            children[TLF].queryMinX(bestMatch);
            children[BLF].queryMinX(bestMatch);
            children[TLB].queryMinX(bestMatch);
            children[BLB].queryMinX(bestMatch);

            children[TRF].queryMinX(bestMatch);
            children[BRF].queryMinX(bestMatch);
            children[TRB].queryMinX(bestMatch);
            children[BRB].queryMinX(bestMatch);
        }
        return (C) this;
    }


    @Override
    @SuppressWarnings("unchecked")
    public C queryMinY(IC_Common.BestMatch<T> bestMatch) {

        if (y1() > bestMatch.val) return (C) this;

        if (!data.isEmpty()) {
            data.queryMinY(bestMatch);
        }

        if (hasChildren()) {
            // we don't loop over children cause order is important for speed!
            children[TLF].queryMinY(bestMatch);
            children[TRF].queryMinY(bestMatch);
            children[TLB].queryMinY(bestMatch);
            children[TRB].queryMinY(bestMatch);

            children[BLF].queryMinY(bestMatch);
            children[BRF].queryMinY(bestMatch);
            children[BLB].queryMinY(bestMatch);
            children[BRB].queryMinY(bestMatch);
        }
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMinZ(IC_Common.BestMatch<T> bestMatch) {

        if (z1() > bestMatch.val) return (C) this;

        if (!data.isEmpty()) {
            data.queryMinZ(bestMatch);
        }

        if (hasChildren()) {
            // we don't loop over children cause order is important for speed!
            children[TLF].queryMinZ(bestMatch);
            children[TRF].queryMinZ(bestMatch);
            children[BLF].queryMinZ(bestMatch);
            children[BRF].queryMinZ(bestMatch);

            children[TLB].queryMinZ(bestMatch);
            children[TRB].queryMinZ(bestMatch);
            children[BLB].queryMinZ(bestMatch);
            children[BRB].queryMinZ(bestMatch);
        }
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMaxX(IC_Common.BestMatch<T> bestMatch) {


        if (x2() < bestMatch.val) return (C) this;

        if (!data.isEmpty()) {
            data.queryMaxX(bestMatch);
        }

        if (hasChildren()) {
            // we don't loop over children cause order is important for speed!
            children[TRF].queryMinX(bestMatch);
            children[BRF].queryMinX(bestMatch);
            children[TRB].queryMinX(bestMatch);
            children[BRB].queryMinX(bestMatch);

            children[TLF].queryMinX(bestMatch);
            children[BLF].queryMinX(bestMatch);
            children[TLB].queryMinX(bestMatch);
            children[BLB].queryMinX(bestMatch);
        }
        return (C) this;
    }


    @Override
    @SuppressWarnings("unchecked")
    public C queryMaxY(IC_Common.BestMatch<T> bestMatch) {

        if (y2() < bestMatch.val) return (C) this;

        if (!data.isEmpty()) {
            data.queryMaxY(bestMatch);
        }

        if (hasChildren()) {
            // we don't loop over children cause order is important for speed!
            children[BLF].queryMinY(bestMatch);
            children[BRF].queryMinY(bestMatch);
            children[BLB].queryMinY(bestMatch);
            children[BRB].queryMinY(bestMatch);

            children[TLF].queryMinY(bestMatch);
            children[TRF].queryMinY(bestMatch);
            children[TLB].queryMinY(bestMatch);
            children[TRB].queryMinY(bestMatch);
        }
        return (C) this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public C queryMaxZ(IC_Common.BestMatch<T> bestMatch) {

        if (z2() < bestMatch.val) return (C) this;

        if (!data.isEmpty()) {
            data.queryMaxZ(bestMatch);
        }

        if (hasChildren()) {
            // we don't loop over children cause order is important for speed!
            children[TLB].queryMinZ(bestMatch);
            children[TRB].queryMinZ(bestMatch);
            children[BLB].queryMinZ(bestMatch);
            children[BRB].queryMinZ(bestMatch);

            children[TLF].queryMinZ(bestMatch);
            children[TRF].queryMinZ(bestMatch);
            children[BLF].queryMinZ(bestMatch);
            children[BRF].queryMinZ(bestMatch);

        }
        return (C) this;
    }





    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    // todo todo todo
    @Override
    @SuppressWarnings("unchecked")
    public C queryClosest(float x, float y, float z, IC_Common.BestMatch<T> bestMatch) {

        if (distToPointSq(x, y, z) > bestMatch.val) {
            return (C) this;
        }

        if (!data.isEmpty()) {
            data.queryClosest(x, y, z, bestMatch);
        }

        if (hasChildren()) {



            // todo!
            /*
            int rl = cx < x ? 1 : 0;
            int bt = cy < y ? 1 : 0;
            int bf = cz < z ? 1 : 0;

            children[bt*2+rl].queryClosest(x, y, bestMatch);
            if (bestMatch.val == 0) return;
            children[bt*2+(1-rl)].queryClosest(x, y, bestMatch);
            if (bestMatch.val == 0) return;
            children[(1-bt)*2+rl].queryClosest(x, y, bestMatch);
            if (bestMatch.val == 0) return;
            children[(1-bt)*2+(1-rl)].queryClosest(x, y, bestMatch);
            //if (bestMatch.val == 0) return;
            */

            // for now brute force :(
            children[TLF].queryClosest(x, y, z, bestMatch);
            if (bestMatch.val == 0) return (C) this;
            children[TRF].queryClosest(x, y, z, bestMatch);
            if (bestMatch.val == 0) return (C) this;
            children[BLF].queryClosest(x, y, z, bestMatch);
            if (bestMatch.val == 0) return (C) this;
            children[BRF].queryClosest(x, y, z, bestMatch);
            if (bestMatch.val == 0) return (C) this;
            children[TLB].queryClosest(x, y, z, bestMatch);
            if (bestMatch.val == 0) return (C) this;
            children[TRB].queryClosest(x, y, z, bestMatch);
            if (bestMatch.val == 0) return (C) this;
            children[BLB].queryClosest(x, y, z, bestMatch);
            if (bestMatch.val == 0) return (C) this;
            children[BRB].queryClosest(x, y, z, bestMatch);

        }
        return (C) this;

    }





    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .




    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public int getIndex(float x, float y, float z) {
        if (z < cz()) { // front
            if (y < cy()) return x < cx() ? TLF : TRF;
            else          return x < cx() ? BLF : BRF;
        }
        else { // back
            if (y < cy()) return x < cx() ? TLB : TRB;
            else          return x < cx() ? BLB : BRB;
        }
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // protected?
    // other name?
    // correct for octree? (some explanation...)
    public int getIndex(float x, float y, float z, float x2, float y2, float z2) {

        int index1 = getIndex(x, y, z);
        int index2 = getIndex(x2, y2, z2);

        if (index1 == index2) return index1;

        return -1;
    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    /*
    interface QueryData3D<T, C2 extends QueryData3D> extends _Data<T, C2> {

        T query(float tx, float ty, float tz);

        // C2 as return type?
        void query(List<T> containing, List<T> intersecting, float tx, float ty, float tz);

        void query(List<T> containing, List<T> intersecting, float tx1, float ty1, float tz1, float tx2, float ty2, float tz2);


        void queryRadiusSq(List<T> containing, List<T> intersecting, float cx, float cy, float cz, float radiusSQ);

        // _Data -> _Data2D / _Data3D -> _QueryData2D / _QueryData3D
        void queryMinX(IC_Common.BestMatch<T> bestMatch);
        void queryMinY(IC_Common.BestMatch<T> bestMatch);
        void queryMinZ(IC_Common.BestMatch<T> bestMatch);
        void queryMaxX(IC_Common.BestMatch<T> bestMatch);
        void queryMaxY(IC_Common.BestMatch<T> bestMatch);
        void queryMaxZ(IC_Common.BestMatch<T> bestMatch);



        void queryClosest(float x, float y, float z, IC_Common.BestMatch<T> bestMatch);
    }
    */


}
