package nl.doekewartena.orb.inner_core.geom;

/**
 * Created by doekewartena on 9/6/15.
 */
public interface _Line {

    double x1();
    double y1();
    double x2();
    double y2();

    default double minX() {
        if (x1() <= x2()) return x1();
        return x2();
    }

    default double minY() {
        if (y1() <= y2()) return y1();
        return y2();
    }

    default double maxX() {
        if (x1() > x2()) return x1();
        return x2();
    }

    default double maxY() {
        if (y1() > y2()) return y1();
        return y2();
    }

}
