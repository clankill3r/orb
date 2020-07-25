package orb_examples;

import processing.core.*;
import java.util.ArrayList;
import orb._crust.util.datastructure.C_QuadTree_AABB;
import static orb._crust._CSSColors.*;

/**
 * Created by doekewartena on 8/23/15.
 */
public class EX_QuadTreeRect extends PApplet {

    public static void main(String[] args) {
        PApplet.main(EX_QuadTreeRect.class, args);
    }

    C_QuadTree_AABB<Rect> quadTree;

    ArrayList<Rect> rects = new ArrayList<>();

    ArrayList<Rect> containing = new ArrayList<>();
    ArrayList<Rect> intersecting = new ArrayList<>();

    float radius = 50;

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void settings() {
        size(512, 512);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void setup() {


        quadTree = new C_QuadTree_AABB<>(
                l -> l.x1, l -> l.y1, l -> l.x2, l -> l.y2,
                0, 0, width, height
        );


        for (int i = 0; i < 1_000; i++) {

            float x = random(width);
            float y = random(height);

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
    int mousePressX, mousePressY;

    @Override
    public void mousePressed() {
        mousePressX = mouseX;
        mousePressY = mouseY;
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
        quadTree.queryRadius(containing, intersecting, mouseX, mouseY, radius);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public void drawQuadTree(C_QuadTree_AABB<?> tree) {
        drawQuadTree(tree, g);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public void drawQuadTree(C_QuadTree_AABB<?> tree, PGraphics pg) {

        pg.pushStyle();
        pg.rectMode(CORNERS);

        for (C_QuadTree_AABB q : tree) {
            if (!q.hasChildren()) {
                pg.rect((float)q.x1(), (float)q.y1(), (float)q.x2(), (float)q.y2());
            }
        }
        pg.popStyle();
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    class Rect {
        float x1, y1, x2, y2;

        Rect() {

        }

        Rect(float x1, float y1, float x2, float y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}
