package orb.___outer_core.util.datastructure;

import orb.____inner_core.util.datatstructure._Data_3D;
import orb.____inner_core.util.datatstructure._TreeSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doekewartena on 8/24/15.
 */

public class OC_OctreePoint<T, C extends OC_OctreePoint> extends OC_Octree<T, C> { //OC_Octree_Base<T, C> {  // Serializable


    public _TreeSettings<T> settings;


    public OC_OctreePoint(float x1, float y1, float z1, float x2, float y2, float z2, _TreeSettings<T> settings) {
        this(null, x1, y1, z1, x2, y2, z2, settings);
    }

    protected OC_OctreePoint(C parent, float x1, float y1, float z1, float x2, float y2, float z2, _TreeSettings<T> settings) {
        super(parent, x1, y1, z1, x2, y2, z2, (_Data_3D<T, ?>) settings.createDataInstance());
        this.settings = settings;
    }


    // splitting up is shit cause it leads in using getX etc. more times then required...
    /*
    public boolean contains(T t, float tx1, float ty1, float tz1, float tx2, float ty2, float tz2) {

        float x = settings.getX.val(t);
        float y = settings.getY.val(t);
        float z = settings.getZ.val(t);

        return (x > tx1 && x < tx2 &&
                y > ty1 && y < ty2 &&
                z > tz1 && z < tz2);
    }


    public boolean intersects(T t, float tx1, float ty1, float tz1, float tx2, float ty2, float tz2) {

        float x = settings.getX.val(t);
        float y = settings.getY.val(t);
        float z = settings.getZ.val(t);

        return (x >= tx1 && x <= tx2 &&
                y >= ty1 && y <= ty2 &&
                z >= tz1 && z <= tz2);
    }
    */

    // something like this is nice in syntax but it would require creating an instance = sloooow
    // if rect(10,10,10,10).intersectsLine(10, 10, 20, 20)

//    public boolean hasItems() {
//        return items.size() > 0;
//    }


//    @Override
//    public void queryAll(List<T> dest) {
//        if (hasItems()) {
//            for (T t : items) dest.add(t);
//        }
//
//        if (hasChildren()) {
//            for (int i = 0; i < children.length; i++) {
//                children[i].queryAll(dest);
//            }
//        }
//    }



    @Override
    public C insert(T t) {
        return insert(t, getX(t), getY(t), getZ(t));
    }

    public C insert(T t, float x, float y, float z) {
        return insert(t, x, y, z, 0);
    }

    //todo  no bound checking (which is/was correct!)
    public C insert(T t, float x, float y, float z, int level) {

        if (hasChildren()) {
            //nOfItems++;
            int where = getIndex(x, y, z);
            return (C) children[where].insert(t, x, y, z, level+1);
        }
        else {

            data.add(t);
            //nOfItems++;

            if (data.size() > settings.getMaxObjects() && level < settings.getMaxLevels()) {  // todo check level < maxLevels

                split();

                List<T> items = new ArrayList<>();
                data.queryAll(items);
                data.clear();

                for (int i = items.size()-1; i >= 0; i--) {
                    T t2 = items.remove(i);
                    float x2 = getX(t2);
                    float y2 = getY(t2);
                    float z2 = getZ(t2);
                    insert(t2, x2, y2, z2, level+1);
                }

                return forwardFind(x, y, z);
            }

            return (C) this;
        }
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @SuppressWarnings("unchecked")
    public void split() { // todo

        children = (C[]) new OC_OctreePoint[8];

        children[TRF] = (C) new OC_OctreePoint<T, C>((C) this, cx(), y1,   z1, x2,   cy(), cz(), settings);
        children[TLF] = (C) new OC_OctreePoint<T, C>((C) this, x1,   y1,   z1, cx(), cy(), cz(), settings);
        children[BLF] = (C) new OC_OctreePoint<T, C>((C) this, x1,   cy(), z1, cx(), y2,   cz(), settings);
        children[BRF] = (C) new OC_OctreePoint<T, C>((C) this, cx(), cy(), z1, x2,   y2,   cz(), settings);

        children[TRB] = (C) new OC_OctreePoint<T, C>((C) this, cx(), y1,   cz(), x2,   cy(), z2, settings);
        children[TLB] = (C) new OC_OctreePoint<T, C>((C) this, x1,   y1,   cz(), cx(), cy(), z2, settings);
        children[BLB] = (C) new OC_OctreePoint<T, C>((C) this, x1,   cy(), cz(), cx(), y2,   z2, settings);
        children[BRB] = (C) new OC_OctreePoint<T, C>((C) this, cx(), cy(), cz(), x2,   y2,   z2, settings);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // why not in OC_OCtree / OC_QuadTree?
    @Override
    public C remove(T t) {
        // todo, merge with parent if required
        data.remove(t);
        return (C) this;
    }

    @Override
    public float getX(T t) {
        return data.getX(t);
    }

    @Override
    public float getY(T t) {
        return data.getY(t);
    }

    @Override
    public float getZ(T t) {
        return data.getZ(t);
    }

    @Override
    public float getX2(T t) {
        System.out.println("[ERROR]: getX2 used with OctreePoint");
        return -1;
    }

    @Override
    public float getY2(T t) {
        System.out.println("[ERROR]: getY2 used with OctreePoint");
        return -1;
    }

    @Override
    public float getZ2(T t) {
        System.out.println("[ERROR]: getZ2 used with OctreePoint");
        return -1;
    }

    // ======================================================
    /*
    public class Data3DList<C2 extends Data3DList> implements QueryData3D<T, C2> {

        List<T> items;

        Data3DList() {
            items = new ArrayList<>();
        }


        @Override
        public T query(float tx, float ty, float tz) {
            for (T t : items) {
                float x = settings.getX.val(t);
                float y = settings.getY.val(t);
                float z = settings.getZ.val(t);
                if (x == tx && y == ty && z == tz) return t;
            }
            return null;
        }

        @Override
        public void query(List<T> containing, List<T> intersecting, float tx, float ty, float tz) {
            for (T t : items) {
                float x = settings.getX.val(t);
                float y = settings.getY.val(t);
                float z = settings.getZ.val(t);
                // todo, check for intersecting null?
                if (x == tx && y == ty && z == tz) intersecting.add(t);
            }
        }

        @Override
        public void query(List<T> containing, List<T> intersecting, float tx1, float ty1, float tz1, float tx2, float ty2, float tz2) {
            final boolean checkContaining = containing != null;
            final boolean checkIntersecting = intersecting != null;

            boolean added;

            for (T t : items) {

                added = false;

                float x = settings.getX.val(t);
                float y = settings.getY.val(t);
                float z = settings.getZ.val(t);

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
                            z >= tz1 && z <= tz2) intersecting.add(t);
                }

            }
        }

        @Override
        public void queryRadiusSq(List<T> containing, List<T> intersecting, float cx, float cy, float cz, float radiusSQ) {
            final boolean checkContaining = containing != null;
            final boolean checkIntersecting = intersecting != null;

            boolean added;

            for (T t : items) {

                added = false;

                float x = settings.getX.val(t);
                float y = settings.getY.val(t);
                float z = settings.getZ.val(t);
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
        }

        @Override
        public void queryMinX(BestMatch<T> bestMatch) {
            OC_Common.getMin(items, settings.getX, bestMatch);
        }

        @Override
        public void queryMinY(BestMatch<T> bestMatch) {
            OC_Common.getMin(items, settings.getY, bestMatch);
        }

        @Override
        public void queryMinZ(BestMatch<T> bestMatch) {
            OC_Common.getMax(items, settings.getX, bestMatch);
        }

        @Override
        public void queryMaxX(BestMatch<T> bestMatch) {
            OC_Common.getMax(items, settings.getY, bestMatch);
        }

        @Override
        public void queryMaxY(BestMatch<T> bestMatch) {
            OC_Common.getMin(items, settings.getZ, bestMatch);
        }

        @Override
        public void queryMaxZ(BestMatch<T> bestMatch) {
            OC_Common.getMax(items, settings.getZ, bestMatch);
        }

        @Override
        public void queryClosest(float x, float y, float z, BestMatch<T> bestMatch) {
            OC_Common.getClosest(items, x, y, z, settings.getX, settings.getY, settings.getZ, bestMatch);
        }

        @Override
        public boolean isEmpty() {
            return items.size() > 0;
        }

        @Override
        public int size() {
            return items.size();
        }

        @Override
        public C2 add(T t) {
            items.add(t);
            return (C2) this;
        }

        @Override
        public C2 remove(T t) {
            items.remove(t);
            return (C2) this;
        }

        @Override
        public C2 queryAll(List<T> target) {
            target.addAll(items);
            return (C2) this;
        }

        @Override
        public void clear() {
            items.clear();
        }

//        @Override
//        public C2 newInstance() {
//            // todo
//            return null;
//        }
    }
    */

    // ======================================================
     /*
    public class Settings { // todo, serializable

        public int maxObjecs = 64;
        public int maxLevels = 64;

        public boolean debug = false;

        // todo, test if final makes it faster
        // todo, this is the only good reason so for to not use lambda's for a flexiable library so far? (Or can GetFloatT implement Serializable)
        transient _GetFloatT<T> getX;
        transient _GetFloatT<T> getY;
        transient _GetFloatT<T> getZ;

        public Settings(_GetFloatT<T> getX, _GetFloatT<T> getY, _GetFloatT<T> getZ) {
            this.getX = getX;
            this.getY = getY;
            this.getZ = getZ;
        }

    }
    */
    // ======================================================


}
