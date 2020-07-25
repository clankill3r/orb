package orb_examples;


import orb._crust.C_Common;
import orb.____inner_core.IC_Common;
import orb.____inner_core.geom._Triangle;
import orb.__mantle.algorithms.M_ConvexHull;
import orb.__mantle.algorithms.M_DouglasPeucker;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by doekewartena on 7/21/14.
 */

/* todo

-draw exclude triangles
-use exclude triangle points for next hull, simplify with douglass peucker to control
-maybe make an easy method for the above



 */
public class ConvexHullTest extends PApplet {

    public static void main(String[] args) {
        PApplet.main(ConvexHullTest.class, args);
    }

    ArrayList<PVector> vecs = new ArrayList<PVector>();

    //OC_XYZComparator<PVector> pvectorComparator;

    M_ConvexHull<PVector> convexHull;

    ArrayList<PVector> toDraw = new ArrayList<PVector>();


    PGraphics pointsPG, hullPG;



    public void settings() {
        size(600, 600);
    }

    public void setup() {


      //  addRandomVecs();

        convexHull = new M_ConvexHull<PVector>(
                (v) -> {return v.x;},
                (v) -> {return v.y;});

        //convexHull.p = this;

       // convexHull.setMagnets(new float[][] {{0,0}, {width,0}, {width,height},{0,height}});

        pointsPG = createGraphics(width, height); // P3D
        pointsPG.beginDraw();
        pointsPG.background(255, 0);
        pointsPG.endDraw();

        hullPG = createGraphics(width, height); // P3D


        // test
        for (int i = 0; i < 6; i++) {
            PVector v = new PVector(width-100, 100+(i*20));
            vecs.add(v);
            toDraw.add(v);
           // ellipse(v.x, v.y, 20, 20);
        }
        PVector v = new PVector(50, 100);
        vecs.add(v);
        toDraw.add(v);

        v = new PVector(50, 300);
        vecs.add(v);
        toDraw.add(v);

        v = new PVector(50, 350);
        vecs.add(v);
        toDraw.add(v);

        //noLoop();

    }

    public void draw() {

        background(255);

        final boolean drawPoints = true;
        final boolean hullNormal = true;
        final boolean hullOptimised = true;


        String frameTitle = "ConvexHullTest\tfps: "+(int)frameRate+" ";
        frameTitle += "vecs.size(): "+vecs.size()+" ";


        if (drawPoints) {

            pointsPG.beginDraw();
            pointsPG.beginShape(POINTS);
            pointsPG.stroke(0);
            pointsPG.fill(0);
            int count = 0;
            pointsPG.strokeCap(SQUARE);
            pointsPG.strokeWeight(2);
            for (PVector v : toDraw) {
                pointsPG.vertex(v.x, v.y);
            }
            pointsPG.noFill();
            pointsPG.endShape();
            pointsPG.endDraw();

            image(pointsPG, 0, 0);

        }

        hullPG.beginDraw();
        hullPG.background(255,0);
        hullPG.endDraw();

        if (hullNormal) {

            final boolean hullNormal_draw = true;

            int start;
            int time = 0;

            Collections.shuffle(vecs);

            //convexHull.p = this;

            start = millis();
            Stack<PVector> hull = convexHull.hull(vecs);
            time = millis() - start;
            // 20000 31:35ms

            if (hullNormal_draw) {
                hullPG.beginDraw();
                hullPG.beginShape();
                hullPG.stroke(0, 255, 0);
                hullPG.noFill();
                for (PVector v : hull) {
                    hullPG.vertex(v.x, v.y);
                }
                hullPG.endShape(CLOSE);


                for (PVector v : hull) {
                    hullPG.ellipse(v.x, v.y, 5, 5);
                }

                hullPG.endDraw();
            }

            ArrayList<PVector> onEdgeHull = convexHull.getOnEdgeHull();
            for (PVector v : onEdgeHull) {
                noFill();
                stroke(255,0,0);
                ellipse(v.x, v.y, 10, 10);
            }


            frameTitle += "hull() time ms: "+time+" ";

        }

        if (hullOptimised) {
            Collections.shuffle(vecs);

            int start = millis();
            Stack<PVector> hull2 = convexHull.hull2(vecs);
            int time = millis() - start;

            //frame.setTitle("fps: " + frameRate + " vecs: " + vecs.size() + " time: " + time + " time2: " + time2);


            hullPG.beginDraw();

            hullPG.beginShape();
            //hullPG.translate(10, 10);
            hullPG.stroke(0, 0, 255);
            hullPG.noFill();
            for (PVector v : hull2) {
                hullPG.vertex(v.x, v.y);
              //  hullPG.ellipse(v.x, v.y, 5, 5);
            }
            hullPG.endShape(CLOSE);


            ArrayList<PVector> onEdgeHull = convexHull.getOnEdgeHull();
            for (PVector v : onEdgeHull) {
                hullPG.noFill();
                hullPG.stroke(255,0,0);
                hullPG.ellipse(v.x, v.y, 10, 10);
            }

            ArrayList<_Triangle> triangles = convexHull.getTriangles();

            randomSeed(1);
            for (_Triangle t : triangles) {

                hullPG.fill(random(255), random(255), random(255), 200);
                //stroke(0,255,0);
                //fill(0, 255,0);
                hullPG.triangle((float)t.x1(), (float)t.y1(), (float)t.x2(), (float)t.y2(), (float)t.x3(), (float)t.y3());
            }



            float epsilon = map(mouseX, 0, width, 0, 500);
            // todo, move from inner core common
            float[][] xyList = C_Common.xyListTo2DArray(
                    (v) -> {
                        return v.x;
                    },
                    (v) -> {
                        return v.y;
                    },
                    hull2);

            hullPG.ellipse((float)xyList[0][0], (float)xyList[0][1], 15, 15);
            hullPG.ellipse((float)xyList[xyList.length-1][0], (float)xyList[xyList.length-1][1], 15, 15);


            float[][] points = M_DouglasPeucker.douglasPeucker(xyList, epsilon);

            //convexHull.setPreHullInterestPoints(hull2, 6);
            convexHull.setMagnets(points);


            hullPG.endDraw();

            frameTitle += "hull2() time ms: "+time+" ";
        }

        randomSeed(frameCount);

        image(hullPG, 0, 0);

        frame.setTitle(frameTitle);

        // todo, use post methods as a structure

        toDraw.clear();





    }


    public void keyPressed() {
        if (key == 'r') {
            addRandomVecs();
        }
        else if (key == 'x') {
            vecs.clear();
        }
    }

    public void addRandomVecs() {

        for (int i = 0; i < 10; i++) {
            PVector v = new PVector(random(width*0.25f, width*0.75f), random(height * 0.25f, height * 0.75f));
            vecs.add(v);
            toDraw.add(v);
        }
    }


    public void mouseDragged() {
        // vecs.add(new PVector(mouseX, mouseY));
        for (int i = 0; i < 50; i++) {
            PVector v = new PVector(mouseX+random(-50, 50), mouseY+random(-50, 50));
            vecs.add(v);
            toDraw.add(v);
        }

    }


    public void mousePressed() {
        PVector v = new PVector(mouseX, mouseY);
        vecs.add(v);
        toDraw.add(v);
    }

}
