package  orb_examples.datastructure;

import controlP5.ControlP5;

import orb.crust.geom.V3;
import orb.crust.util.datastructure.C_OctreePoint;
import orb.outer_core.util.datastructure.OC_Octree;
import org.problessing.Problessing;
import peasy.PeasyCam;
import processing.core.PGraphics;
import processing.core.PShape;

import java.util.ArrayList;

import static orb.crust.C_Common.getMax;
import static orb.crust.C_Common.getMin;


/**
 * Created by doekewartena on 8/23/15.
 */
/*

todo, disable cam on cp5

 */




public class OctreeTreePoint extends Problessing {

    public static void main(String[] args) {
        Problessing.main("util.datastructure.OctreeTreePoint", args);
    }

    PeasyCam cam;

    C_OctreePoint<V3> octree;

    ArrayList<V3> points = new ArrayList<>();

    ArrayList<V3> containing = new ArrayList<>();
    ArrayList<V3> intersecting = new ArrayList<>();


    float minX;
    float minY;
    float minZ;
    float maxX;
    float maxY;
    float maxZ;

    // what if PShape would extends PGraphics?

    PShape sOctree, sTRex;

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void settings() {
        size(512*2, 512*2, P3D);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void setup() {

        cam = new PeasyCam(this, 100);
        cam.setMinimumDistance(50);
        cam.setMaximumDistance(5000);

        String dataPath = "/Users/doekewartena/IdeaProjects/onos/onos_p5/projects/onos/data/";
        String file1 = "trex_vertexes.tsv";
        String file2 = "trex2_vertexes.tsv";

        String[] lines = loadStrings(dataPath+file2);

        for (int i = 0; i < lines.length; i++) {
            String[] tokens = split(lines[i], "\t");
            float x = Float.parseFloat(tokens[0]);
            float y = Float.parseFloat(tokens[1]);
            float z = Float.parseFloat(tokens[2]);
            V3 p = new V3(x, y, z);
            points.add(p);
        }

        minX = getMin(points, v -> v.x).x;
        minY = getMin(points, v -> v.y).y;
        minZ = getMin(points, v -> v.z).z;

        maxX = getMax(points, v -> v.x).x;
        maxY = getMax(points, v -> v.y).y;
        maxZ = getMax(points, v -> v.z).z;

        octree = new C_OctreePoint<>(
                p -> p.x,
                p -> p.y,
                p -> p.z,
                minX, minY, minZ,
                maxX, maxY, maxZ
        );

        /*
        for (int i = 0; i < 2_500; i++) {

            float x = random(width);
            float y = random(height);
            float z = random(depth);

            V3 p = new V3(x, y, z);
            points.add(p);

            octree.insert(p);

        }
        */

        for (V3 v : points) {
            octree.insert(v);
        }


        {
            sTRex = createShape();

            sTRex.beginShape(POINTS);
            sTRex.strokeCap(ROUND);
            sTRex.strokeWeight(5);
            sTRex.stroke(white);

            for (V3 p : points) {
                sTRex.vertex((int) p.x, (int) p.y, (int) p.z);
            }

            sTRex.endShape();
        }


        sOctree = createOctreeShape(octree);


        // for easier debug
        gui();

    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static class GUI {

        public static float tx;
        public static float ty;
        public static float tz;

    }


    ControlP5 cp5;

    public void gui() {

        cp5 = new ControlP5(this);
        cp5.setAutoDraw(false);

        GUI g_ = new GUI();

        float x = 20;
        float y = 20;

        cp5.addSlider(g_, "tx").setPosition(x, y += 20).setRange((float)minX, (float)maxX);
        cp5.addSlider(g_, "ty").setPosition(x, y += 20).setRange((float)minY, (float)maxY);
        cp5.addSlider(g_, "tz").setPosition(x, y += 20).setRange((float)minZ, (float)maxZ);



    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void draw() {

        cam.setActive(true);
        if (cp5.isMouseOver()) {
            cam.setActive(false);
        }

        background(black);

//        stroke(white);
//        strokeWeight(1.25f);
//        for (V3 p : points) {
//            point((int) p.x, (int) p.y, (int) p.z);
//        }

        shape(sTRex);



//        noFill();
//        stroke(darkRed);
//        strokeWeight(1);
//        drawOctree(octree);

        shape(sOctree);

        textSize(10);

        for (V3 p : containing) {
            stroke(greenYellow);
            strokeWeight(1.5f);
            point((int) p.x, (int) p.y, (int) p.z);
        }

        for (V3 p : intersecting) {
            stroke(orange);
            strokeWeight(0.5f);
            point((int) p.x, (int) p.y, (int) p.z);
        }

        // debug
        {
            pushMatrix();
            translate(GUI.tx, GUI.ty, GUI.tz);
            sphereDetail(6);
            fill(0, 255, 0);
            sphere(20);
            popMatrix();


            C_OctreePoint<V3> founded = octree.forwardFind(GUI.tx, GUI.ty, GUI.tz);
            noFill();
            stroke(0, 255, 0);
            drawOctree(founded);

            fill(white);
            cam.beginHUD();
            // how many items in founded?
            //text("size" + founded.size(), 20, 20);


            // does it match with the index?
            int idx = octree.getIndex(GUI.tx, GUI.ty, GUI.tz);
            text("index" + idx, 20, 40);
            cam.endHUD();

        }


        noFill();
        stroke(0, 255, 0);
        //ellipse(mouseX, mouseY, radius + radius, radius + radius);

        cam.beginHUD();
        cp5.draw();
        cam.endHUD();

        surface.setTitle(""+frameRate);


    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void mouseReleased() {

        // todo
        println("todo mouseReleased");
        /*
        pushStyle();
        fill(red);
        rectMode(CORNERS);
        rect(mousePressX, mousePressY, mouseX, mouseY);
        popStyle();

        containing.clear();
        intersecting.clear();

        println(mouseX + " " + mouseY + " " + radius);
        octree.queryRadius(containing, intersecting, mouseX, mouseY, radius);
        */
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // todo, we should not rely on point!
    public void drawOctree(OC_Octree<?, ?> tree) {
        drawOctree(tree, g);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public void drawOctree(OC_Octree<?, ?> tree, PGraphics pg) {

        pg.pushStyle();
        pg.rectMode(CORNERS);

        for (OC_Octree q : tree) {
            if (!q.hasChildren()) {
                box(pg, (float)q.x1(), (float)q.y1(), (float)q.z1(), (float)q.x2(), (float)q.y2(), (float)q.z2());

//                g.sphereDetail(6);
//                g.pushMatrix();
//                g.translate((float)q.cx(), (float)q.cy(), (float)q.cz());
//                g.sphere(5);
//                g.popMatrix();
            }
        }
        pg.popStyle();
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public PShape createOctreeShape(OC_Octree<?, ?> tree) {

        PShape s = createShape();

        s.beginShape(LINES);
        s.stroke(red);
        s.strokeWeight(0.5f);

        for (OC_Octree q : tree) {
            if (!q.hasChildren()) {
                s.vertex((float)q.x1(), (float)q.y1(), (float)q.z1());
                s.vertex((float)q.x2(), (float)q.y1(), (float)q.z1());
                s.vertex((float)q.x1(), (float)q.y2(), (float)q.z1());
                s.vertex((float)q.x2(), (float)q.y2(), (float)q.z1());
                s.vertex((float)q.x1(), (float)q.y1(), (float)q.z2());
                s.vertex((float)q.x2(), (float)q.y1(), (float)q.z2());
                s.vertex((float)q.x1(), (float)q.y2(), (float)q.z2());
                s.vertex((float)q.x2(), (float)q.y2(), (float)q.z2());

                s.vertex((float)q.x1(), (float)q.y1(), (float)q.z1());
                s.vertex((float)q.x1(), (float)q.y2(), (float)q.z1());
                s.vertex((float)q.x2(), (float)q.y1(), (float)q.z1());
                s.vertex((float)q.x2(), (float)q.y2(), (float)q.z1());
                s.vertex((float)q.x1(), (float)q.y1(), (float)q.z2());
                s.vertex((float)q.x1(), (float)q.y2(), (float)q.z2());
                s.vertex((float)q.x2(), (float)q.y1(), (float)q.z2());
                s.vertex((float)q.x2(), (float)q.y2(), (float)q.z2());

                s.vertex((float)q.x1(), (float)q.y1(), (float)q.z1());
                s.vertex((float)q.x1(), (float)q.y1(), (float)q.z2());
                s.vertex((float)q.x2(), (float)q.y1(), (float)q.z1());
                s.vertex((float)q.x2(), (float)q.y1(), (float)q.z2());
                s.vertex((float)q.x1(), (float)q.y2(), (float)q.z1());
                s.vertex((float)q.x1(), (float)q.y2(), (float)q.z2());
                s.vertex((float)q.x2(), (float)q.y2(), (float)q.z1());
                s.vertex((float)q.x2(), (float)q.y2(), (float)q.z2());
            }
        }

        s.endShape();
        return s;
    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public static void box(PGraphics g, float x1, float y1, float z1, float x2, float y2, float z2) {
        // todo, rectangle surfaces...
        g.line(x1, y1, z1, x2, y1, z1);
        g.line(x1, y2, z1, x2, y2, z1);
        g.line(x1, y1, z2, x2, y1, z2);
        g.line(x1, y2, z2, x2, y2, z2);

        g.line(x1, y1, z1, x1, y2, z1);
        g.line(x2, y1, z1, x2, y2, z1);
        g.line(x1, y1, z2, x1, y2, z2);
        g.line(x2, y1, z2, x2, y2, z2);

        g.line(x1, y1, z1, x1, y1, z2);
        g.line(x2, y1, z1, x2, y1, z2);
        g.line(x1, y2, z1, x1, y2, z2);
        g.line(x2, y2, z1, x2, y2, z2);

    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


}
