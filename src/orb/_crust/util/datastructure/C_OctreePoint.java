package orb._crust.util.datastructure;

import orb.____inner_core.util.datatstructure._Data;
import orb.____inner_core.util.datatstructure._TreeSettings;
import orb.____inner_core.util.function._GetFloat_T;
import orb.___outer_core.util.datastructure.OC_Data3DPoint_List;
import orb.___outer_core.util.datastructure.OC_OctreePoint;

import java.util.ArrayList;

/**
 * Created by doekewartena on 8/25/15.
 */
public class C_OctreePoint<T> extends OC_OctreePoint<T, C_OctreePoint<T>> implements _C_Octree<T, C_OctreePoint<T>> {

//    public OS_OctreePoint() {
//        super();
//    }

//    public OS_OctreePoint(_GetFloatT<T> getX, _GetFloatT<T> getY, _GetFloatT<T> getZ) {
//        super(() -> new Data3DPoint_List<>(getX, getY, getZ, new ArrayList<>()));
//    }
//
//    public OS_OctreePoint(_TreeSettings<T> settings) {
//        super(settings);
//    }

    public C_OctreePoint(float x1, float y1, float z1, float x2, float y2, float z2, _TreeSettings<T> settings) {
        super(x1, y1, z1, x2, y2, z2, settings);
    }

    public C_OctreePoint(_GetFloat_T<T> getX, _GetFloat_T<T> getY, _GetFloat_T<T> getZ,
                         float x1, float y1, float z1, float x2, float y2, float z2) {
        //super(null, x1, y1, z1, x2, y2, z2, () -> new OC_Data3DPoint_List<>(getX, getY, getZ, new ArrayList<>()));
        super(null, x1, y1, z1, x2, y2, z2, new _TreeSettings<T>() {

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
                System.out.println("todo createDataInstance!");
                return null;
            }
        });
    }

    protected C_OctreePoint(C_OctreePoint<T> parent, float x1, float y1, float z1, float x2, float y2, float z2, _TreeSettings<T> settings) {
        super(parent, x1, y1, z1, x2, y2, z2, settings);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void split() {

        children = new C_OctreePoint[8];

        children[TRF] = new C_OctreePoint<>(this, cx(), y1,   z1, x2,   cy(), cz(), settings);
        children[TLF] = new C_OctreePoint<>(this, x1,   y1,   z1, cx(), cy(), cz(), settings);
        children[BLF] = new C_OctreePoint<>(this, x1,   cy(), z1, cx(), y2,   cz(), settings);
        children[BRF] = new C_OctreePoint<>(this, cx(), cy(), z1, x2,   y2,   cz(), settings);

        children[TRB] = new C_OctreePoint<>(this, cx(), y1,   cz(), x2,   cy(), z2, settings);
        children[TLB] = new C_OctreePoint<>(this, x1,   y1,   cz(), cx(), cy(), z2, settings);
        children[BLB] = new C_OctreePoint<>(this, x1,   cy(), cz(), cx(), y2,   z2, settings);
        children[BRB] = new C_OctreePoint<>(this, cx(), cy(), cz(), x2,   y2,   z2, settings);
    }


}
