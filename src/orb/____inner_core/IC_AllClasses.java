package orb.inner_core;

import nl.doekewartena.orb.inner_core.geom._AABB;
import nl.doekewartena.orb.inner_core.geom._AABB_2D;
import nl.doekewartena.orb.inner_core.geom._AABB_3D;
import nl.doekewartena.orb.inner_core.geom._Line;
import nl.doekewartena.orb.inner_core.geom._Rect;
import nl.doekewartena.orb.inner_core.geom._Triangle;
import nl.doekewartena.orb.inner_core.geom._Vec2;
import nl.doekewartena.orb.inner_core.geom._Vec3;
import nl.doekewartena.orb.inner_core.geom._Vec4;

/*
import nl.doekewartena.orb.inner_core.util.compare.excluded._CompareXY;
import nl.doekewartena.orb.inner_core.util.compare.excluded._CompareXY_T;
import nl.doekewartena.orb.inner_core.util.compare.excluded._CompareXY_TT;
import nl.doekewartena.orb.inner_core.util.compare.excluded._CompareXYZ;
import nl.doekewartena.orb.inner_core.util.compare.excluded._CompareXYZ_T;
import nl.doekewartena.orb.inner_core.util.compare.excluded._CompareXYZ_TT;
*/

import nl.doekewartena.orb.inner_core.util.datatstructure._Data         ;
import nl.doekewartena.orb.inner_core.util.datatstructure._Data_2D      ;
import nl.doekewartena.orb.inner_core.util.datatstructure._Data_3D      ;
import nl.doekewartena.orb.inner_core.util.datatstructure._Query_2D     ;
import nl.doekewartena.orb.inner_core.util.datatstructure._Query_3D     ;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree         ;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree_2D      ;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree_3D      ;
import nl.doekewartena.orb.inner_core.util.datatstructure._TreeSettings ;



import nl.doekewartena.orb.inner_core.util.function._GetDouble_T;
import nl.doekewartena.orb.inner_core.util.function._GetDouble_TT;
import nl.doekewartena.orb.inner_core.util.function._GetInt_T;
import nl.doekewartena.orb.inner_core.util.function._GetIterator_T;
import nl.doekewartena.orb.inner_core.util.function._GetX_T;
import nl.doekewartena.orb.inner_core.util.function._GetY_T;
import nl.doekewartena.orb.inner_core.util.function._GetZ_T;
import nl.doekewartena.orb.inner_core.util.function._Insert_T;
import nl.doekewartena.orb.inner_core.util.function._Remove_T;


/**
 * Created by doekewartena on 9/12/15.
 */
public class IC_AllClasses {

    // geom
    public final static Class _AABB     = _AABB.class;
    public final static Class _AABB_2D  = _AABB_2D.class;
    public final static Class _AABB_3D  = _AABB_3D.class;
    public final static Class _Line     = _Line.class;
    public final static Class _Rect     = _Rect.class;
    public final static Class _Triangle = _Triangle.class;
    public final static Class _Vec2     = _Vec2.class;
    public final static Class _Vec3     = _Vec3.class;
    public final static Class _Vec4     = _Vec4.class;

    // util

        // compare
    /*
    public final static Class _CompareXY     = _CompareXY.class;
    public final static Class _CompareXY_T   = _CompareXY_T.class;
    public final static Class _CompareXY_TT  = _CompareXY_TT.class;
    public final static Class _CompareXYZ    = _CompareXYZ.class;
    public final static Class _CompareXYZ_T  = _CompareXYZ_T.class;
    public final static Class _CompareXYZ_TT = _CompareXYZ_TT.class;
    */
        // datastructure


    public final static Class _Data         = _Data.class;
    public final static Class _Data_2D      = _Data_2D.class;
    public final static Class _Data_3D      = _Data_3D.class;
    public final static Class _Query_2D     = _Query_2D.class;
    public final static Class _Query_3D     = _Query_3D.class;
    public final static Class _Tree         = _Tree.class;
    public final static Class _Tree_2D      = _Tree_2D.class;
    public final static Class _Tree_3D      = _Tree_3D.class;
    public final static Class _TreeSettings = _TreeSettings.class;

    // function
        // there is also unused atm!

    public final static Class _GetDoubleT   = _GetDouble_T.class;
    public final static Class _GetDoubleTT  = _GetDouble_TT.class;
    public final static Class _GetIntT      = _GetInt_T.class;
    public final static Class _GetIteratorT = _GetIterator_T.class;
    public final static Class _GetX_T       = _GetX_T.class;
    public final static Class _GetY_T       = _GetY_T.class;
    public final static Class _GetZ_T       = _GetZ_T.class;
    public final static Class _Insert       = _Insert_T.class;
    public final static Class _Remove       = _Remove_T.class;

   // root

    public final static Class IC_AllClasses = IC_AllClasses.class;
    public final static Class IC_Common     = IC_Common.class;
    public final static Class IC_Math       = IC_Math.class;




}
