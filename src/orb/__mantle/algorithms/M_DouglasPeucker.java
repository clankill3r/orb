package orb.__mantle.algorithms;

import static orb.____inner_core.IC_Math.*;

/**
 * Created by doekewartena on 1/19/15.
 */
/*
TODO: -Advanced: if a contour is closed, offset rotate the result and repeat the algorithm? Or something?
TODO: rename epsilon to dist? (check if it's correct)
TODO: option with nOfSides instead of dist

 */
public class M_DouglasPeucker {



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public final static float[][] douglasPeucker(float[][] points, float epsilon) {
        return douglasPeucker(points, 0, points.length-1, epsilon);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    // todo HIGH, make end index exclusive
    public final static float[][] douglasPeucker(float[][] points, int startIndex, int endIndexInc, float epsilon) {
        // find point with max dist
        float dMax = 0;
        int index = -1;

        for (int i = startIndex+1; i <= endIndexInc; i++) {
            float d = distToLineSquared(points[i][0], points[i][1], points[startIndex][0], points[startIndex][1], points[endIndexInc][0], points[endIndexInc][1]);
            if (d > dMax) {
                index = i;
                dMax = d;
            }
        }

        dMax = sqrt(dMax);

        // if it's greater we simplify
        if (dMax > epsilon) {

            float[][] resultFront = douglasPeucker(points, startIndex, index, epsilon);
            float[][] resultBack = douglasPeucker(points, index, endIndexInc, epsilon);

            // combine
            float[][] result = new float[resultFront.length+resultBack.length][2];

            for (int i = 0; i < resultFront.length; i++) {
                System.arraycopy(resultFront[i], 0, result[i], 0, 2);
            }
            for (int i = 0; i < resultBack.length; i++) {
                System.arraycopy(resultBack[i], 0, result[i+resultFront.length], 0, 2);
            }

            return result;
        } else {

            return new float[][] {
                    {
                            points[startIndex][0], points[startIndex][1]
                    }
                    , {
                    points[endIndexInc][0], points[endIndexInc][1]
                }
            };
        }
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .



}
