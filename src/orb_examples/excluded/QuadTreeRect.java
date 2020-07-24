package util.datastructure.excluded;

import nl.doekewartena.onos.util.datastructure.OC_QuadTree;
import nl.doekewartena.onos.util.datastructure.OS_QuadTreeLine;
import nl.doekewartena.onos.util.datastructure.OS_QuadTreeRect;
import org.problessing.Problessing;
import processing.core.PGraphics;

import java.util.ArrayList;

/**
 * Created by doekewartena on 8/23/15.
 */
public class QuadTreeRect extends Problessing {

    public static void main(String[] args) {
        Problessing.main("util.datastructure.QuadTreeRect", args);
    }

    OS_QuadTreeRect<Rect> quadTree;

    ArrayList<Rect> rects = new ArrayList<>();

    ArrayList<Rect> containing = new ArrayList<>();
    ArrayList<Rect> intersecting = new ArrayList<>();

    double radius = 50;

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void settings() {
        size(512, 512);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void setup() {


        quadTree = new OS_QuadTreeRect<>(
                l -> l.x1, l -> l.y1, l -> l.x2, l -> l.y2,
                0, 0, width, height
        );


        for (int i = 0; i < 1_000; i++) {

            double x = random(width);
            double y = random(height);

            Rect l = new Rect(x, y, x+random(-100,100), y+random(-100, 100));
            rects.add(l);
            quadTree.insert(l);
        }


    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void draw() {
        background(black);

        rectMode(CORNERS);

        stroke(white);
        strokeWeight(0.25f);
        for (Rect l : rects) {
            rect(l.x1, l.y1, l.x2, l.y2);
        }

        noFill();
        stroke(darkRed);
        strokeWeight(1);
        drawQuadTree(quadTree);

        textSize(10);

        for (Rect l : containing) {
            stroke(greenYellow);
            strokeWeight(1.5f);
            rect(l.x1, l.y1, l.x2, l.y2);
        }

        for (Rect l : intersecting) {
            stroke(orange);
            strokeWeight(0.5f);
            rect(l.x1, l.y1, l.x2, l.y2);
        }

        noFill();
        stroke(0, 255, 0);
        ellipse(mouseX, mouseY, radius + radius, radius + radius);



    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

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
        quadTree.queryRadius(containing, intersecting, mouseX, mouseY, radius);
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

    class Rect {
        double x1, y1, x2, y2;

        Rect() {

        }

        Rect(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}
