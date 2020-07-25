package orb.____inner_core;

import orb.____inner_core.geom._Vec2;
import orb.____inner_core.geom._Vec3;
import orb.____inner_core.util.function._GetFloat_T;

/**
 * Created by doekewartena on 1/18/15.
 */
public class IC_Math<T> {

    _GetFloat_T<T> getX;
    _GetFloat_T<T> getY;

    public IC_Math(_GetFloat_T<T> getX, _GetFloat_T<T> getY) {
        this.getX = getX;
        this.getY = getY;
    }


    private final static float SQRT_2 = sqrt(2);


    static public final float atan2(float y, float x) {
        return (float) Math.atan2(y, x);
    }

    static public final float sq(float x) {
        return x * x;
    }

    static public final float sqrt(float x) {
        return (float) Math.sqrt(x);
    }

    static public float abs(float x) {
        return (float) Math.abs(x);
    }

    static public int abs(int x) {
        return Math.abs(x);
    }

    static public int min(int a, int b) {
        return Math.min(a, b);
    }

    static public int max(int a, int b) {
        return Math.max(a, b);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // todo, make certain stuff final
    static public final float dist(float x1, float y1, float x2, float y2) {
        return (float) sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    static public final float dist(_Vec2 v1, _Vec2 v2) {
        return dist(v1.x(), v1.y(), v2.x(), v2.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final float dist(float x1, float y1, float z1, float x2, float y2, float z2) {
        return (float) sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) + (z2-z1)*(z2-z1));
    }

    static public final float dist(_Vec3 v1, _Vec3 v2) {
        return dist(v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.x());
    }
    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final float distSq(float x1, float y1, float x2, float y2) {
        return (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1);
    }

    static public final float distSq(_Vec2 v1, _Vec2 v2) {
        return distSq(v1.x(), v1.y(), v2.x(), v2.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final float distSq(float x1, float y1, float z1, float x2, float y2, float z2) {
        return (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) + (z2-z1)*(z2-z1);
    }

    static public final float distSq(_Vec3 v1, _Vec3 v2) {
        return distSq(v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.x());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final float manhattanDist(float x1, float y1, float x2, float y2) {
        return abs(x1-x2) + abs(y1-y2);
    }

    static public final float manhattanDist(_Vec2 v1, _Vec2 v2) {
        return manhattanDist(v1.x(), v1.y(), v2.x(), v2.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // todo, here we use point in front while in other methods we do it at the end
    public static final float distToLine(float px, float py, float lx1, float ly1, float lx2, float ly2) {
        return (float) sqrt(distToLineSquared(px, py, lx1, ly1, lx2, ly2));
    }

    public static final float distToLine(_Vec2 p, _Vec2 lp1, _Vec2 lp2) {
        return distToLine(p.x(), p.y(), lp1.x(), lp1.y(), lp2.x(), lp2.y());
    }

    // inspired by http://stackoverflow.com/a/1501725/1022707
    public static final float distToLineSquared(float px, float py, float lx1, float ly1, float lx2, float ly2) {

        float lineDist = distSq(lx1, ly1, lx2, ly2);

        if (lineDist == 0) return distSq(px, py, lx1, ly1);

        float t = ((px - lx1) * (lx2 - lx1) + (py - ly1) * (ly2 - ly1)) / lineDist;

        if (t < 0) return distSq(px, py, lx1, ly1);
        if (t > 1) return distSq(px, py, lx2, ly2);

        return distSq(px, py, lx1 + t * (lx2 - lx1), ly1 + t * (ly2 - ly1));
    }

    public static final float distToLineSquared(_Vec2 p, _Vec2 lp1, _Vec2 lp2) {
        return distToLineSquared(p.x(), p.y(), lp1.x(), lp1.y(), lp2.x(), lp2.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // do we need a float version?
    public static final float gridDist(int x1, int y1, int x2, int y2) {
        int dx = abs(x2 - x1);
        int dy = abs(y2 - y1);

        int min = min(dx, dy);
        int max = max(dx, dy);

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

    final static public int ccw(float x1, float y1, float x2, float y2, float x3, float y3) {
        float area = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (area < 0) return -1; // CCW
        else if (area > 0) return 1; // CW
        else return 0; // COLLINEAR
    }

    static public final float ccw(_Vec2 v1, _Vec2 v2, _Vec2 v3) {
        return ccw(v1.x(), v1.y(), v2.x(), v2.y(), v3.x(), v3.y());
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    final static public float sign (float x1, float y1, float x2, float y2, float x3, float y3) {
        return (x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3);
    }

    static public final float sign(_Vec2 v1, _Vec2 v2, _Vec2 v3) {
        return sign(v1.x(), v1.y(), v2.x(), v2.y(), v3.x(), v3.y());
    }

    // todo from here, rest of _Vec2 / _Vec3 methods

    final static public boolean pointInTriangle (float tx, float ty, float x1, float y1, float x2, float y2, float x3, float y3) {

        boolean b1 = sign(tx, ty, x1, y1, x2, y2) < 0.0f;
        boolean b2 = sign(tx, ty, x2, y2, x3, y3) < 0.0f;
        boolean b3 = sign(tx, ty, x3, y3, x1, y1) < 0.0f;

        return ((b1 == b2) && (b2 == b3));
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final float getTriangleArea(float x1, float y1, float x2, float y2, float x3, float y3) {
        float a = x1 - x3;
        float b = y1 - y3;
        float c = x2 - x3;
        float d = y2 - y3;
        return 0.5f * abs((a*d) - (b*c));
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public static final float distToSegment(float px, float  py, float lx1, float  ly1, float lx2, float ly2) {
        return sqrt(distToSegmentSq(px, py, lx1, ly1, lx2, ly2));
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    // inspired by http://stackoverflow.com/a/1501725/1022707
    public static final float distToSegmentSq(float px, float py, float lx1, float ly1, float lx2, float ly2) {
        float lineDist = distSq(lx1, ly1, lx2, ly2);

        if (lineDist == 0) return distSq(px, py, lx1, ly1);

        float t = ((px - lx1) * (lx2 - lx1) + (py - ly1) * (ly2 - ly1)) / lineDist;

        if (t < 0) return distSq(px, py, lx1, ly1);
        if (t > 1) return distSq(px, py, lx2, ly2);

        return distSq(px, py, lx1 + t * (lx2 - lx1), ly1 + t * (ly2 - ly1));
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final float lerp(float start, float stop, float amt) {
        return start + (stop-start) * amt;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final float map(float value,
                                   float istart, float istop,
                                   float ostart, float ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static public final float norm(float value, float start, float stop) {
        return (value - start) / (stop - start);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final float constrain(float value, float min, float max) {
        if (value < min) value = min;
        else if (value > max) value = max;
        return value;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final float radians(float degrees) {
        return (float) (degrees * 0.017453292519943295);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final float degrees(float radians) {
        return (float) (radians * 57.29577951308232);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final float min(float v1, float v2, float... v) {

        float min = Math.min(v1, v2);

        for (float val : v) {
            if (val < min) min = val;
        }
        return min;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static final float max(float v1, float v2, float... v) {

        float max = Math.max(v1, v2);

        for (float val : v) {
            if (val > max) max = val;
        }
        return max;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}
