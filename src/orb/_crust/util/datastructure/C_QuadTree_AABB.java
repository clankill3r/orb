package orb._crust.util.datastructure;


import orb.____inner_core.util.datatstructure._Data;
import orb.____inner_core.util.datatstructure._TreeSettings;
import orb.____inner_core.util.function._GetFloat_T;
import orb.___outer_core.util.datastructure.OC_Data2DAABB_List;
import orb.___outer_core.util.datastructure.OC_Data2DPoint_List;
import orb.___outer_core.util.datastructure.OC_QuadTree_AABB;

import java.util.ArrayList;

/**
 * Created by doekewartena on 8/19/15.
 */
public class C_QuadTree_AABB<T> extends OC_QuadTree_AABB<T, C_QuadTree_AABB<T>> implements _C_QuadTree<T, C_QuadTree_AABB<T>> {



    // todo, order is not consequent

    // OS_TreeSettings?

    public boolean auto_scale = true;
    public boolean log = true;


//    public OS_QuadTree_AABB(_TreeSettings<T> settings) {
//        super(settings);
//        auto_scale = true;
//    }

    public C_QuadTree_AABB(float x1,
                           float y1,
                           float x2,
                           float y2,
                           _TreeSettings<T> settings) {

        super(null, x1, y1, x2, y2, settings);
    }


    public C_QuadTree_AABB(_GetFloat_T<T> getX1, _GetFloat_T<T> getY1,
                           _GetFloat_T<T> getX2, _GetFloat_T<T> getY2,
                           float x1, float y1, float x2, float y2) {

        //super(null, x1, y1, x2, y2, () -> new OC_Data2DPoint_List<>());
        super(null, x1, y1, x2, y2, new _TreeSettings<T>() {

            // todo: acquire from global settings?
            int maxObjects = 64;
            int maxLevels = 64;

            @Override
            public int getMaxObjects() {
                return maxObjects;
            }

            @Override
            public int getMaxLevels() {
                return maxLevels;
            }

            @Override
            public void setMaxObjects(int max) {
                maxObjects = max;
            }

            @Override
            public void setMaxLevels(int max) {
                maxLevels = max;
            }

            @Override
            public _Data<T, ? extends _Data> createDataInstance() {
                return new OC_Data2DAABB_List<>(getX1, getY1, getX2, getY2, new ArrayList<>());
            }
        });
    }

    // used in split
    protected C_QuadTree_AABB(C_QuadTree_AABB<T> parent,
                              float x1,
                              float y1,
                              float x2,
                              float y2,
                              _TreeSettings<T> settings) {

        super(parent, x1, y1, x2, y2, settings);
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public C_QuadTree_AABB<T> insert(T t) {
        return insert(t, getX(t), getY(t), getX2(t), getY2(t));
    }

    @Override
    public C_QuadTree_AABB<T> insert(T t, float x, float y, float x2, float y2) {
        return insert(t, x, y, x2, y2, 0);
    }

    @Override
    public C_QuadTree_AABB<T> insert(T t, float _x, float _y, float _x2, float _y2, int level) {

        // shell rocks, we do this only once!
        if (_x > _x2) _x2 = (_x+_x2) - (_x=_x2); // cool swap :)
        if (_y > _y2) _y2 = (_y+_y2) - (_y=_y2);

        if (contains_aabb(_x, _y, _x2, _y2)) {
            //System.out.println("x: "+x+",y: "+y+", x2: "+x2+", y2: "+y2);
            return super.insert(t, _x, _y, _x2, _y2, level);
        }

        if (level == 0) { // todo, instead of auto_scale ask a method for permission with the parameters!



            if (parent != null) { // we migh have created a parent in a batch so if there is one we use it
                parent.insert(t, _x, _y, _x2, _y2, 0); // or do we pass level-1 (negative level might give problems) the parent wouldnt be ble to split how it is now
            }
            else if (auto_scale) {
                //int where = getIndex(x, y, x2, y2);
                //System.out.println("where: " + where);

                boolean expendLeft = _x < this.x1();
                boolean expendTop = _y < this.y1();
                //boolean expendRight = _x2 > this.x2();
                //boolean expendBottom = _y2 > this.y2();


                System.out.println("expanding parent");

                parent = new C_QuadTree_AABB<>(expendLeft  ? x1() - width()    : x1(),
                                                expendTop   ? y1() - height()   : y1(),
                                                !expendLeft ? x2() + width()    : x2(),
                                                !expendTop  ? y2() + height()   : y2(),
                                                settings);



                int where = parent.getIndex(cx(), cy());

                // todo, we must make a method for this in the core
                // it is so prone for error if we leave it for the implementation
                parent.split();
                parent.children[where] = this;

                // return?
                parent.insert(t, _x, _y, _x2, _y2, 0);

            }
        }
        else { // level > 0
            // todo, check for permission to go back to parent (to much options is not good, makes hard to understand code)
        }
        //System.out.println("doesn't fit!");
        return this;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @SuppressWarnings("unchecked")
    public void split() {

        children = new C_QuadTree_AABB[4];

        children[TR] = new C_QuadTree_AABB<>(this, cx(), y1(), x2(), cy(), settings);
        children[TL] = new C_QuadTree_AABB<>(this, x1(), y1(), cx(), cy(), settings);
        children[BL] = new C_QuadTree_AABB<>(this, x1(), cy(), cx(), y2(), settings);
        children[BR] = new C_QuadTree_AABB<>(this, cx(), cy(), x2(), y2(), settings);

    }




    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}
