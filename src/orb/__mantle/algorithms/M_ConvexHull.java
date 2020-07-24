package orb.__mantle.algorithms;



import orb.____inner_core.IC_Math;
import orb.____inner_core.geom._Triangle;
import orb.____inner_core.util.function._GetFloat_T;
import orb.__mantle.geom.M_Triangle;
import orb.___outer_core.util.compare.OC_ComparatorStack;
//import orb.outer_core.util.compare.excluded.OC_ComparatorsXY;

import java.util.*;

import static orb.____inner_core.IC_Math.*;
import static orb.____inner_core.IC_Math.manhattanDist;
import static orb.____inner_core.util.compare.IC_Compare.*;


/**
 * Created by doekewartena on 1/13/15.
 */
/*

-rename ConvexHull to ConvexHullCreator?

+

return ArrayList instead of Stack?


+





 */

public class M_ConvexHull<T> {

    IC_Math<T> math;

    _GetFloat_T<T> getX;
    _GetFloat_T<T> getY;
    //OC_ComparatorsXY<T> comparatorYX;
    Comparator<T> comparatorYX;

    Comparator<T> comparatorAngleToPoint;
    float px, py;

    ArrayList<T> workList;

    ArrayList<T> onEdgeHull;

    // used for optimization

    ArrayList<_Triangle> triangles = new ArrayList<>();

    public M_ConvexHull(_GetFloat_T<T> getX, _GetFloat_T<T> getY) {
        this.getX = getX;
        this.getY = getY;
        //comparatorYX = new OC_ComparatorsXY<>(getX, getY);

        comparatorYX = new OC_ComparatorStack<>(    (o1, o2) -> compare(getY.val(o1), getY.val(o2)),
                                                    (o1, o2) -> compare(getX.val(o1), getX.val(o2)));


        // this syntax is a bit longer now but we avoid so many classes / interfaces
        // maybe we should make some helpers for special cases like this
        comparatorAngleToPoint = (a, b) -> {
            final float aA = atan2(getY.val(a)-py, getX.val(a)-px);
            final float aB = atan2(getY.val(b)-py, getX.val(b)-px);
            return compare(aA, aB);
        };

        workList = new ArrayList<>();
        onEdgeHull = new ArrayList<>();

        math = new IC_Math<>(getX, getY);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public Stack<T> hull(List<T> list) {


        Stack<T> hull = new Stack<>();

        if (list.size() < 3) return hull;

        //xyzComparator.setCompareType(PBConstants.YX);
        Collections.sort(list, comparatorYX);

        //xyzComparator.setCompareType(PBConstants.ANGLE_GIVEN_POINT_2D);
        T first = list.get(0);
        //xyzComparator.setComparePoint(getXYZ.x(first), getXYZ.y(first));
        // is the angle inverted in the comparator?
        //comparatorYX.compareAngleToPoint2D.setPoint(getX.val(first), getY.val(first));
        //Collections.sort(list.subList(1, list.size()), Collections.reverseOrder(comparatorYX.compareAngleToPoint2D));
        px = getX.val(first);
        py = getY.val(first);
        Collections.sort(list.subList(1, list.size()), Collections.reverseOrder(comparatorAngleToPoint));

        onEdgeHull.clear();

        hull.push(list.get(0));
        hull.push(list.get(1));

        for (int i = 2; i < list.size(); i++) {
            T top = hull.pop();
//            while (ccw(hull.peek(), top, list.get(i)) >= 0) {
//                top = hull.pop();
//            }

            while (true) {

                float ccw = math.ccw(hull.peek(), top, list.get(i));
                if (ccw < 0) {
                    break;
                }
                if (ccw == 0) {
                    onEdgeHull.add(top);
                }
                else {
                    //
                }

                top = hull.pop();
                // this prevents empty stack exception
                // which can happen with points on the edge
                if (hull.size() < 1) break;

            }

            hull.push(top);
            hull.push(list.get(i));

        }


        return hull;

    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    /**
     * Call this after getting the hull.
     *
     * @return the item's that are on edge of the hull but not part of the hull
     */
    public ArrayList<T> getOnEdgeHull() {
        return onEdgeHull;
    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // triangle test

    // todo, better names and at top
    // preHullInterests?
    float[][] magnetPoints;
    float[] magnetDistances;
    Object[] objectsClosestToMagnet;

    public void setMagnets(float[][] points) {

        if (points.length < 3) {
            return;
        }

        // copy the values?
        magnetPoints = points;

        magnetDistances = new float[points.length];


        objectsClosestToMagnet = new Object[points.length];
       // sort them according to the angle



    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public void setPreHullInterestPoints(List<T> list, int maxTriangles) {

        // first create a unique list of the points?
        HashSet<T> unique = new HashSet<T>(list);

        magnetPoints = new float[unique.size()][2];

        Iterator<T> itr = unique.iterator();

        int i = 0;
        while (itr.hasNext()) {
            T item = itr.next();
            float x = getX.val(item);
            float y = getY.val(item);

            magnetPoints[i][0] = x;
            magnetPoints[i][1] = y;

           i++;
        }

        setMagnets(magnetPoints);

    }




    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public Stack<T> hull2(List<T> list) {


        Stack<T> hull = new Stack<T>();

        if (list.size() < 3) return hull;


        T item; // = list.get(0);
        float x; // = getX.val(item);
        float y; // = getY.val(item);



        if (magnetPoints == null) {
            // todo
            setMagnets(new float[][] {{0,0}, {1000,0},{1000,1000},{0,1000},
                    {300,0}, {300, 600}
            });
        }

        // reset magnetPoint distances
        for (int i = 0; i < magnetDistances.length; i++) {
            magnetDistances[i] = Float.MAX_VALUE;
        }


        for (int i = 0; i < list.size(); i++) {

            item = list.get(i);

            x = getX.val(item);
            y = getY.val(item);


            for (int p = 0; p < magnetPoints.length; p++) {

                float d = manhattanDist(x, y, magnetPoints[p][0], magnetPoints[p][1]);
                if (d < magnetDistances[p]) {
                    magnetDistances[p] = d;
                    objectsClosestToMagnet[p] = item;
                }
            }

        }




        // here we find out which points we want for the pre hullPG:
        HashMap<String, T> uniquePointsMap = new HashMap<String, T>();

        for (int i = 0; i < objectsClosestToMagnet.length; i++) {
            item = (T)objectsClosestToMagnet[i];
            x = getX.val(item);
            y = getY.val(item);

            uniquePointsMap.put(x + "" + y, item);
        }

        T[] preHullPoints = (T[]) uniquePointsMap.values().toArray();


        hull = hull(Arrays.asList(preHullPoints));

        preHullPoints = (T[]) hull.toArray();

        // todo, douglas peucker
        // ...




        if (preHullPoints.length < 3) {
            hull = hull(list);
        }
        else {

            // =====================================================================================
            // create the triangles

            triangles.clear();

            T preHullItem = preHullPoints[0];
            float baseX = getX.val(preHullItem);
            float baseY = getY.val(preHullItem);

            preHullItem = preHullPoints[1];
            float px = getX.val(preHullItem);
            float py = getY.val(preHullItem);

            for (int j = 2; j < preHullPoints.length; j++) {

                preHullItem = preHullPoints[j];

                float tx = getX.val(preHullItem);
                float ty = getY.val(preHullItem);

                M_Triangle t = new M_Triangle(baseX, baseY, px, py, tx, ty);
                triangles.add(t);

                px = tx;
                py = ty;

            }

            // bigger triangles first
            // this was ok when natural sorting of triangle was area but that's removed now..
            //Collections.sort(triangles, Collections.reverseOrder());

            // we compare o2 to o1 instead of the other way around so we don't have to reverse the list
            Collections.sort(triangles, (o1, o2) -> o2.compareArea(o1));

            // end of creating the triangles
            // ===========================================================================================


            // ========== check which points are inside the triangle ======================

            for (int i = 0; i < list.size(); i++) {

                item = list.get(i);

                x = getX.val(item);
                y = getY.val(item);

                boolean inTriangle = false;

                for (int j = 0; j < triangles.size(); j++) {

                    _Triangle t = triangles.get(j);

                    if (t.pointInside(x, y)) {
                        inTriangle = true;
                        break;
                    }

                }

                if (inTriangle == false) {
                    workList.add(item);
                }

            }

            // at last we need to add the ones that
            // formed the pre hull
            for (int j = 0; j < preHullPoints.length; j++) {
                preHullItem = preHullPoints[j];

                if (!workList.contains(preHullItem)) {
                    workList.add(preHullItem);
                }
            }


            hull = hull(workList);
            workList.clear();
        }



        return hull;

    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public ArrayList<_Triangle> getTriangles() {
        return triangles;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .





    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}
