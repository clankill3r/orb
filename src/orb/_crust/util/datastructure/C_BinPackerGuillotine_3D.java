package orb._crust.util.datastructure;

import orb.___outer_core.util.datastructure.OC_BinPackerGuillotine_3D;

/**
 * Created by doekewartena on 8/30/15.
 */
public class C_BinPackerGuillotine_3D<T> extends OC_BinPackerGuillotine_3D<T, C_BinPackerGuillotine_3D<T>> {

    public C_BinPackerGuillotine_3D(float x, float y, float z, float w, float h, float d) {
        super(x, y, z, w, h, d);
    }

    // this is quite risky if the method changes in core...
//    @Override
//    public void split(float w, float h) {
//        used = true;
//        right = new OS_GuillotinePacker(x + w, y, this.w - w, h);
//        down  = new OS_GuillotinePacker(x, y + h, this.w, this.h - h);
//
//        right.parent = this;
//        down.parent = this;
//    }

    @Override
    @SuppressWarnings("unchecked")
    public C_BinPackerGuillotine_3D newInstance(float x, float y, float z, float w, float h, float d) {
        return new C_BinPackerGuillotine_3D(x, y, z, w, h, d);
    }


}