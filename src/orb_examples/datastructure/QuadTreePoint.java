package  orb_examples.datastructure;


import nl.doekewartena.orb.crust.geom.V2;
import nl.doekewartena.orb.crust.util.datastructure.C_QuadTreePoint;
import nl.doekewartena.orb.outer_core.util.datastructure.OC_QuadTree;
import org.problessing.Problessing;
import processing.core.PGraphics;

import java.util.ArrayList;

/**
 * Created by doekewartena on 8/23/15.
 */
public class QuadTreePoint extends Problessing {

    public static void main(String[] args) {
        Problessing.main("util.datastructure.QuadTreePoint", args);
    }


    ArrayList<V2> points = new ArrayList<>();


    // todo, allow OS_QuadTree for example, can we construct it so that we mirror the whole OC diagram to OS? (THAT WOULD BE BOMB!)
    C_QuadTreePoint<V2> quadTree;

    // idea for jai:
    // xywh_to_xy1xy2(0, 0, width, height)


    // jai
    // quick access method
    // also someMethod(aabb[0,1,2,3]); <<-- pass indexes, get interpreted like this (for example): someMethod(10, 10, 300, 200);

    // for selection
    ArrayList<V2> containing = new ArrayList<>();
    ArrayList<V2> intersecting = new ArrayList<>();

    double radius = 50;

    PGraphics pgAllVecs;

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void settings() {
        size(512, 512);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void setup() {




        quadTree = new C_QuadTreePoint<>(
                p -> p.x, p -> p.y,
                0, 0, width, height
        );
//


        // why does this fail?
//        _TreeSettings<V2> settings = () -> new Data2DPoint_List<>(p -> p.x, p -> p.y, new ArrayList<>());
//
//
//        quadTree = new OS_QuadTreePoint<>(
//                0, 0, width, height, settings
//        );


        for (int i = 0; i < 30_000; i++) {

            double x = noise(i*0.05f)*width;
            double y = noise(i*i*0.05f)*height;

            V2 p = new V2(x, y);
            points.add(p);
            quadTree.insert(p);
        }




        println("quadTree.data.size(): " + quadTree.data.size());


    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void draw() {

        if (pgAllVecs == null) {
            pgAllVecs = createGraphics(width, height);
            PGraphics pg = pgAllVecs;

            pg.beginDraw();

            pg.background(0);
            pg.fill(0);
            pg.rect(0, 0, pg.width, pg.height);

            pg.stroke(white);
            pg.strokeWeight(1.25f);
            for (V2 p : points) {
                pg.point((int) p.x, (int) p.y);
            }
            pg.endDraw();
        }

        image(pgAllVecs, 0, 0);


        noFill();
        stroke(red);
        strokeWeight(1);
        drawQuadTree(quadTree);

        textSize(10);

        for (V2 p : containing) {
            stroke(greenYellow);
            strokeWeight(1.5f);
            point((int) p.x, (int) p.y);
        }

        for (V2 p : intersecting) {
            stroke(orange);
            strokeWeight(0.5f);
            point((int) p.x, (int) p.y);
        }

        noFill();
        stroke(0, 255, 0);
        ellipse(mouseX, mouseY, radius + radius, radius + radius);



    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public void mouseDragged() {
        containing.clear();
        intersecting.clear();
        quadTree.queryRadius(containing, intersecting, mouseX, mouseY, radius);
    }

    @Override
    public void mouseReleased() {

        pushStyle();
        fill(red);
        rectMode(CORNERS);
        rect(mousePressX, mousePressY, mouseX, mouseY);
        popStyle();

        containing.clear();
        intersecting.clear();

        println(mouseX + " " + mouseY + " " + radius);

        //println();
        //System.out.println("size before query: " + quadTree.data.size() + "") ;
        // todo, easey way to measure ms in floating point format: for example 0.1ms
        long start = millis();
        quadTree.queryRadius(containing, intersecting, mouseX, mouseY, radius);
        println("time ms: "+(millis()-start));
        //((Data2DPoint_List)(quadTree.data)).queryRadiusSq(containing, intersecting, mouseX, mouseY, radius*radius);
        //System.out.println("size after query: "+quadTree.data.size()+"");
        //println();

        System.out.println("containing size: " + containing.size());
        System.out.println("intersecting size: "+intersecting.size());
        println();
        println();
        println();
        println();

    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public void drawQuadTree(OC_QuadTree<?, ?> tree) {
        drawQuadTree(tree, g);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public void drawQuadTree(OC_QuadTree<?, ?> tree, PGraphics pg) {

        pg.pushStyle();
        pg.rectMode(CORNERS);

        for (OC_QuadTree q : tree) {
            if (!q.hasChildren()) {
                pg.rect((float)q.x1(), (float)q.y1(), (float)q.x2(), (float)q.y2());
            }
        }
        pg.popStyle();
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


}
