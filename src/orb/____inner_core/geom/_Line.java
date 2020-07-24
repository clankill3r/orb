package orb.____inner_core.geom;

/**
 * Created by doekewartena on 9/6/15.
 */
public interface _Line {

    float x1();
    float y1();
    float x2();
    float y2();

    default float minX() {
        if (x1() <= x2()) return x1();
        return x2();
    }

    default float minY() {
        if (y1() <= y2()) return y1();
        return y2();
    }

    default float maxX() {
        if (x1() > x2()) return x1();
        return x2();
    }

    default float maxY() {
        if (y1() > y2()) return y1();
        return y2();
    }

}
