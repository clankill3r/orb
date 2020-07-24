package orb.____inner_core.geom;

/**
 * Created by doekewartena on 8/27/15.
 */
public interface _AABB {

    // this is more like a "logic marker" interface.
    // We put the methods in cause
    // so we can group _AABB_2D together with _AABB_3D
    // but since             ..
    // bla bla bla

    // maybe remove cx and cy and implement it on the first level needed?
    // then we can check against type?
    // how does type checking take place?
    //

    // reason atm is that the minimum dimenstion to create an AABB is 2D
    // but we don't want to expose the other methods that come with it to
    // the 3D variant of an AABB

    // todo, name minY etc?, it's kind important that x1 and y1 are min etc.
    // also we leave x1 etc open for something else.
    // for example a triangle that is an AABB at the same time
    // really think this true!
    double x1();
    double y1();
    double x2();
    double y2();

    default double cx() {
        return x1() + ( (x2()-x1()) / 2);
    }

    default double cy() {
        return y1() + ( (y2()-y1()) / 2);
    }

    default double width() {
        return x2() - x1();
    }

    default double height() {
        return y2() - y1();
    }



}
