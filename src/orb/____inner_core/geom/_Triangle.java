package orb.____inner_core.geom;

import nl.doekewartena.orb.inner_core.IC_Math;

/**
 * Created by doekewartena on 5/23/15.
 */

public interface _Triangle {

    double x1();
    double y1();
    double x2();
    double y2();
    double x3();
    double y3();


    default boolean pointInside(double x, double y) {
        return IC_Math.pointInTriangle(x, y, x1(), y1(), x2(), y2(), x3(), y3());
    }

    default double area() {
        return IC_Math.getTriangleArea(x1(), y1(), x2(), y2(), x3(), y3());
    }

    default int compareArea(_Triangle t) { // instead of comparing to other triangle, compare to other shape?

        double a1 = area();
        double a2 = t.area();

        if      (a1 > a2) return 1;
        else if (a1 < a2) return -1;
        else              return 0;
    }


}

