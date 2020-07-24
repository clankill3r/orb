package nl.doekewartena.orb.crust.util.datastructure;

import nl.doekewartena.orb.inner_core.util.datatstructure._Data;
import nl.doekewartena.orb.inner_core.util.datatstructure._TreeSettings;
import nl.doekewartena.orb.inner_core.util.function._GetDouble_T;
import nl.doekewartena.orb.outer_core.util.datastructure.OC_Data2DPoint_List;
import nl.doekewartena.orb.outer_core.util.datastructure.OC_QuadTreePoint;

import java.util.ArrayList;

/**
 * Created by doekewartena on 8/19/15.
 */
public class C_QuadTreePoint<T> extends OC_QuadTreePoint<T, C_QuadTreePoint<T>> implements _C_QuadTree<T, C_QuadTreePoint<T>> {

    // todo (StackOverFlow), is it bad to do this?
    // the user can still see it has a field children but when accessing it now
    // it will complain that it has protected access.
    //protected OS_QuadTreePoint<T>[] children;

//    public OS_QuadTreePoint() {
//        super();
//    }

    // todo, order is not consequent

//    public OS_QuadTreePoint(_GetDoubleT<T> getX, _GetDoubleT<T> getY) {
//        super(() -> new Data2DPoint_List<>(getX, getY, new ArrayList<>()));
//    }
//
//    public OS_QuadTreePoint(_TreeSettings<T> settings) {
//        super(settings);
//    }

    public C_QuadTreePoint(double x1, double y1, double x2, double y2, _TreeSettings<T> settings) {
        super(null, x1, y1, x2, y2, settings);
    }

    public C_QuadTreePoint(_GetDouble_T<T> getX, _GetDouble_T<T> getY,
                           double x1, double y1, double x2, double y2) {

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
                return new OC_Data2DPoint_List<>(getX, getY, new ArrayList<>());
            }
        });
    }

    protected C_QuadTreePoint(C_QuadTreePoint<T> parent, double x1, double y1, double x2, double y2, _TreeSettings<T> settings) {
        super(parent, x1, y1, x2, y2, settings);
    }



    @Override
    @SuppressWarnings("unchecked")
    public void split() {

        children = new C_QuadTreePoint[4];

        children[TR] = new C_QuadTreePoint<>(this, cx(), y1(), x2(), cy(), settings);
        children[TL] = new C_QuadTreePoint<>(this, x1(), y1(), cx(), cy(), settings);
        children[BL] = new C_QuadTreePoint<>(this, x1(), cy(), cx(), y2(), settings);
        children[BR] = new C_QuadTreePoint<>(this, cx(), cy(), x2(), y2(), settings);

    }


}
