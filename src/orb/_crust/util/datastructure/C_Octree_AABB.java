package nl.doekewartena.orb.crust.util.datastructure;


import nl.doekewartena.orb.inner_core.util.datatstructure._TreeSettings;
import nl.doekewartena.orb.outer_core.util.datastructure.OC_Octree_AABB;

/**
 * Created by doekewartena on 8/19/15.
 */
public class C_Octree_AABB<T> extends OC_Octree_AABB<T, C_Octree_AABB<T>> implements _C_Octree<T, C_Octree_AABB<T>> {




    public boolean auto_scale = true;
    public boolean log = true;

    public C_Octree_AABB(double x1,
                         double y1,
                         double z1,
                         double x2,
                         double y2,
                         double z2,
                         _TreeSettings<T> settings) {

        super(null, x1, y1, z1, x2, y2, z2, settings);
    }




    // used in split
    protected C_Octree_AABB(C_Octree_AABB<T> parent,
                            double x1,
                            double y1,
                            double z1,
                            double x2,
                            double y2,
                            double z2,
                            _TreeSettings<T> settings) {

        super(parent, x1, y1, z1, x2, y2, z2, settings);
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public C_Octree_AABB<T> insert(T t) {
        return insert(t, getX(t), getY(t), getZ(t), getX2(t), getY2(t), getZ2(t));
    }

    @Override
    public C_Octree_AABB<T> insert(T t, double x, double y, double z, double x2, double y2, double z2) {
        return insert(t, x, y, z, x2, y2, z2, 0);
    }

    @Override
    public C_Octree_AABB<T> insert(T t, double _x, double _y, double _z, double _x2, double _y2, double _z2, int level) {

        // shell rocks, we do this only once!
        if (_x > _x2) _x2 = (_x+_x2) - (_x=_x2); // cool swap :)
        if (_y > _y2) _y2 = (_y+_y2) - (_y=_y2);
        if (_z > _z2) _z2 = (_z+_z2) - (_z=_z2);

        if (contains_aabb(_x, _y, _z, _x2, _y2, _z2)) {
            //System.out.println("x: "+x+",y: "+y+", x2: "+x2+", y2: "+y2);
            return super.insert(t, _x, _y, _z, _x2, _y2, _z2, level);
        }

        if (level == 0) { // todo, instead of auto_scale ask a method for permission with the parameters!



            if (parent != null) { // we migh have created a parent in a batch so if there is one we use it
                parent.insert(t, _x, _y, _z, _x2, _y2, _z2, 0); // or do we pass level-1 (negative level might give problems) the parent wouldnt be ble to split how it is now
            }
            else if (auto_scale) {
                //int where = getIndex(x, y, x2, y2);
                //System.out.println("where: " + where);

                boolean expendLeft = _x < this.x1();
                boolean expendTop  = _y < this.y1();
                boolean expendFront  = _z < this.z1();

                System.out.println("expanding parent");

                parent = new C_Octree_AABB<>(expendLeft  ? x1() - width()    : x1(),
                                                expendTop   ? y1() - height()   : y1(),
                                                expendFront ? z1() - depth() : z1(),
                                                !expendLeft ? x2() + width()    : x2,
                                                !expendTop  ? y2() + height()   : y2,
                                                !expendFront ? z2() + depth() : z2,
                                                settings);



                int where = parent.getIndex(cx(), cy(), cz());

                // todo, we must make a method for this in the core
                // it is so prone for error if we leave it for the implementation
                parent.split();
                parent.children[where] = this;

                // return?
                parent.insert(t, _x, _y, _z, _x2, _y2, _z2, 0);

            }
        }
        else { // level > 0
            // todo, check for permission to go back to parent (to much options is not good, makes hard to understand code)
        }
        //System.out.println("doesn't fit!");
        return this;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    @SuppressWarnings("unchecked")
    public void split() {

        children = new C_Octree_AABB[8];

        children[TRF] = new C_Octree_AABB<>(this, cx(), y1,   z1, x2,   cy(), cz(), settings);
        children[TLF] = new C_Octree_AABB<>(this, x1,   y1,   z1, cx(), cy(), cz(), settings);
        children[BLF] = new C_Octree_AABB<>(this, x1,   cy(), z1, cx(), y2,   cz(), settings);
        children[BRF] = new C_Octree_AABB<>(this, cx(), cy(), z1, x2,   y2,   cz(), settings);

        children[TRB] = new C_Octree_AABB<>(this, cx(), y1,   cz(), x2,   cy(), z2, settings);
        children[TLB] = new C_Octree_AABB<>(this, x1,   y1,   cz(), cx(), cy(), z2, settings);
        children[BLB] = new C_Octree_AABB<>(this, x1,   cy(), cz(), cx(), y2,   z2, settings);
        children[BRB] = new C_Octree_AABB<>(this, cx(), cy(), cz(), x2,   y2,   z2, settings);

    }




    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}
