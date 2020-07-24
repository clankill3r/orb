package nl.doekewartena.orb.inner_core;

import nl.doekewartena.orb.inner_core.geom._Vec2;
import nl.doekewartena.orb.inner_core.geom._Vec3;
import nl.doekewartena.orb.inner_core.util.function._GetDouble_T;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by doekewartena on 1/18/15.
 */
public class IC_Math<T> {

    _GetDouble_T<T> getX;
    _GetDouble_T<T> getY;

    public IC_Math(_GetDouble_T<T> getX, _GetDouble_T<T> getY) {
        this.getX = getX;
        this.getY = getY;
    }


    private final static double SQRT_2 = sqrt(2);

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // todo, make certain stuff final
    static public final double dist(double x1, double y1, double x2, double y2) {
        return sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    static public final double dist(_Vec2 v1, _Vec2 v2) {
        return dist(v1.x(), v1.y(), v2.x(), v2.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final double dist(double x1, double y1, double z1, double x2, double y2, double z2) {
        return sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) + (z2-z1)*(z2-z1));
    }

    static public final double dist(_Vec3 v1, _Vec3 v2) {
        return dist(v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.x());
    }
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final double distSq(double x1, double y1, double x2, double y2) {
        return (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1);
    }

    static public final double distSq(_Vec2 v1, _Vec2 v2) {
        return distSq(v1.x(), v1.y(), v2.x(), v2.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final double distSq(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) + (z2-z1)*(z2-z1);
    }

    static public final double distSq(_Vec3 v1, _Vec3 v2) {
        return distSq(v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.x());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final double manhattanDist(double x1, double y1, double x2, double y2) {
        return abs(x1-x2) + abs(y1-y2);
    }

    static public final double manhattanDist(_Vec2 v1, _Vec2 v2) {
        return manhattanDist(v1.x(), v1.y(), v2.x(), v2.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // todo, here we use point in front while in other methods we do it at the end
    public static final double distToLine(double px, double py, double lx1, double ly1, double lx2, double ly2) {
        return sqrt(distToLineSquared(px, py, lx1, ly1, lx2, ly2));
    }

    public static final double distToLine(_Vec2 p, _Vec2 lp1, _Vec2 lp2) {
        return distToLine(p.x(), p.y(), lp1.x(), lp1.y(), lp2.x(), lp2.y());
    }

    // inspired by http://stackoverflow.com/a/1501725/1022707
    public static final double distToLineSquared(double px, double py, double lx1, double ly1, double lx2, double ly2) {

        double lineDist = distSq(lx1, ly1, lx2, ly2);

        if (lineDist == 0) return distSq(px, py, lx1, ly1);

        double t = ((px - lx1) * (lx2 - lx1) + (py - ly1) * (ly2 - ly1)) / lineDist;

        if (t < 0) return distSq(px, py, lx1, ly1);
        if (t > 1) return distSq(px, py, lx2, ly2);

        return distSq(px, py, lx1 + t * (lx2 - lx1), ly1 + t * (ly2 - ly1));
    }

    public static final double distToLineSquared(_Vec2 p, _Vec2 lp1, _Vec2 lp2) {
        return distToLineSquared(p.x(), p.y(), lp1.x(), lp1.y(), lp2.x(), lp2.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // do we need a double version?
    public static final double gridDist(int x1, int y1, int x2, int y2) {
        int dx = abs(x2 - x1);
        int dy = abs(y2 - y1);

        int min = Math.min(dx, dy);
        int max = Math.max(dx, dy);

        int diagonalSteps = min;
        int straightSteps = max - min;

        return SQRT_2 * diagonalSteps + straightSteps;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // todo move all triangle specific to a triangle class

    // or make a static subclass Triangle, Rect, etc.
    // bad!?
    public int ccw(T a, T b, T c) {
        return ccw(getX.val(a), getY.val(a), getX.val(b), getY.val(b), getX.val(c), getY.val(c));
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    final static public int ccw(double x1, double y1, double x2, double y2, double x3, double y3) {
        double area = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (area < 0) return -1; // CCW
        else if (area > 0) return 1; // CW
        else return 0; // COLLINEAR
    }

    static public final double ccw(_Vec2 v1, _Vec2 v2, _Vec2 v3) {
        return ccw(v1.x(), v1.y(), v2.x(), v2.y(), v3.x(), v3.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    final static public double sign (double x1, double y1, double x2, double y2, double x3, double y3) {
        return (x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3);
    }

    static public final double sign(_Vec2 v1, _Vec2 v2, _Vec2 v3) {
        return sign(v1.x(), v1.y(), v2.x(), v2.y(), v3.x(), v3.y());
    }

    // todo from here, rest of _Vec2 / _Vec3 methods

    final static public boolean pointInTriangle (double tx, double ty, double x1, double y1, double x2, double y2, double x3, double y3) {

        boolean b1 = sign(tx, ty, x1, y1, x2, y2) < 0.0f;
        boolean b2 = sign(tx, ty, x2, y2, x3, y3) < 0.0f;
        boolean b3 = sign(tx, ty, x3, y3, x1, y1) < 0.0f;

        return ((b1 == b2) && (b2 == b3));
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final double getTriangleArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        double a = x1 - x3;
        double b = y1 - y3;
        double c = x2 - x3;
        double d = y2 - y3;
        return 0.5f * abs((a*d) - (b*c));
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public static final double distToSegment(double px, double  py, double lx1, double  ly1, double lx2, double ly2) {
        return sqrt(distToSegmentSq(px, py, lx1, ly1, lx2, ly2));
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    // inspired by http://stackoverflow.com/a/1501725/1022707
    public static final double distToSegmentSq(double px, double py, double lx1, double ly1, double lx2, double ly2) {
        double lineDist = distSq(lx1, ly1, lx2, ly2);

        if (lineDist == 0) return distSq(px, py, lx1, ly1);

        double t = ((px - lx1) * (lx2 - lx1) + (py - ly1) * (ly2 - ly1)) / lineDist;

        if (t < 0) return distSq(px, py, lx1, ly1);
        if (t > 1) return distSq(px, py, lx2, ly2);

        return distSq(px, py, lx1 + t * (lx2 - lx1), ly1 + t * (ly2 - ly1));
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final double lerp(double start, double stop, double amt) {
        return start + (stop-start) * amt;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final double map(double value,
                                   double istart, double istop,
                                   double ostart, double ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final double norm(double value, double start, double stop) {
        return (value - start) / (stop - start);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final double constrain(double value, double min, double max) {
        if (value < min) value = min;
        else if (value > max) value = max;
        return value;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final double radians(double degrees) {
        return degrees * 0.017453292519943295;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final double degrees(double radians) {
        return radians * 57.29577951308232;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final double min(double v1, double v2, double... v) {

        double min = Math.min(v1, v2);

        for (double val : v) {
            if (val < min) min = val;
        }
        return min;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final double max(double v1, double v2, double... v) {

        double max = Math.max(v1, v2);

        for (double val : v) {
            if (val > max) max = val;
        }
        return max;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}
