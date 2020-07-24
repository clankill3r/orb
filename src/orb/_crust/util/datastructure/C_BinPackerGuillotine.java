package orb._crust.util.datastructure;

import orb.___outer_core.util.datastructure.OC_BinPackerGuillotine;

/**
 * Created by doekewartena on 8/30/15.
 */
public class C_BinPackerGuillotine<T> extends OC_BinPackerGuillotine<T, C_BinPackerGuillotine<T>> {

    public C_BinPackerGuillotine(float x, float y, float w, float h) {
        super(x, y, w, h);
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
    public C_BinPackerGuillotine newInstance(float x, float y, float w, float h) {
        return new C_BinPackerGuillotine(x, y, w, h);
    }


}