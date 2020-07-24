package orb.____inner_core.geom;

import orb.____inner_core.IC_Math;

/**
 * Created by doekewartena on 5/23/15.
 */

public interface _Triangle {

    float x1();
    float y1();
    float x2();
    float y2();
    float x3();
    float y3();


    default boolean pointInside(float x, float y) {
        return IC_Math.pointInTriangle(x, y, x1(), y1(), x2(), y2(), x3(), y3());
    }

    default float area() {
        return IC_Math.getTriangleArea(x1(), y1(), x2(), y2(), x3(), y3());
    }

    default int compareArea(_Triangle t) { // instead of comparing to other triangle, compare to other shape?

        float a1 = area();
        float a2 = t.area();

        if      (a1 > a2) return 1;
        else if (a1 < a2) return -1;
        else              return 0;
    }


}

