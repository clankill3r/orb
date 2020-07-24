package  orb_examples.datastructure;


import nl.doekewartena.orb.crust.util.datastructure.C_QuadTree_AABB;
import nl.doekewartena.orb.inner_core.geom._AABB_2D;
import nl.doekewartena.orb.inner_core.geom._Line;
import nl.doekewartena.orb.inner_core.util.datatstructure._Data;
import nl.doekewartena.orb.inner_core.util.datatstructure._TreeSettings;
import nl.doekewartena.orb.outer_core.util.datastructure.OC_Data2DAABB_List;
import nl.doekewartena.orb.outer_core.util.datastructure.OC_QuadTree;
import org.problessing.Problessing;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.HashMap;

import static nl.doekewartena.orb.inner_core.IC_Math.distSq;


/**
 * Created by doekewartena on 8/23/15.
 */

// this one is with lines for now but we should make it a more modular text example
public class QuadTree_AABB extends Problessing {

    public static void main(String[] args) {
        Problessing.main("util.datastructure.QuadTree_AABB", args);
    }

    C_QuadTree_AABB<Line> quadTree;
    C_QuadTree_AABB<Line> origQuadTree;

    ArrayList<Line> lines = new ArrayList<>();

    ArrayList<Line> containing = new ArrayList<>();
    ArrayList<Line> intersecting = new ArrayList<>();

    double radius = 50;

    HashMap<C_QuadTree_AABB<Line>, PGraphics> imageHashMap = new HashMap<>();

    C_QuadTree_AABB<Line> selected;

    _AABB_2D view;

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void settings() {
        size(1024, 1024);
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public void setup() {

        // todo, the name _TreeSettings starts to hurt!  -> _TreeController ?
        // we should not allow a default of 0, 0, 1, 1!
        // it so prone for errors
        // we either makes one that scales up
        quadTree = new C_QuadTree_AABB<>(448, 448, width-448, height-448, new _TreeSettings<Line>() {
            @Override
            public _Data<Line, ? extends _Data> createDataInstance() {
                return new OC_Data2DAABB_List<>(l -> l.x1, l -> l.y1, l -> l.x2, l -> l.y2, new ArrayList<>());
            }
        });


        int target = 5_000;
        int n = 0;

        while (n < target) {

            double x = width/2 + random(-100, 100);
            double y = height/2 + random(-100, 100);
            double x2 = x+random(-100,100);
            double y2 = y+random(-100, 100);

            x = constrain((float)x, 384, width-384);
            x2 = constrain((float)x2, 384, width-384);
            y = constrain((float)y, 384, height-384);
            y2 = constrain((float)y2, 384, height-384);

            if (distSq(x, y, x2, y2) < 15*15) continue;

            Line l = new Line(x, y, x2, y2);
            lines.add(l);
            quadTree.insert(l);
            n++;
        }



        origQuadTree = quadTree;
        while (quadTree.parent() != null) {
            quadTree = quadTree.parent();
        }


        createImageMaps(quadTree, imageHashMap, 256, 256);


        view = new View(128, 128, 256, 256);

        /*
        for AABB?

        int closestEdge(x, y) {
            // top, right, bottom, left
        }


         */




    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public void createImageMaps(C_QuadTree_AABB<Line> quadTree,
                                HashMap<C_QuadTree_AABB<Line>, PGraphics> imageHashMap,
                                int maxWidth, int maxHeight) {


        if (quadTree.width() > maxWidth || quadTree.height() > maxHeight) {

            if (quadTree.hasChildren()) { // do we need this?
                for (C_QuadTree_AABB<Line> child : quadTree.children()) {
                    createImageMaps(child, imageHashMap, maxWidth, maxHeight);
                }
            }
        }
        else {


            C_QuadTree_AABB<Line> root = quadTree; // or pass root?
            while (root.hasParent()) root = root.parent();

            // first check for any empty children, if there are, split up
            boolean shouldSplit = false;

            if (quadTree.hasChildren()) { // do we need this?
                for (C_QuadTree_AABB<Line> child : quadTree.children()) {

                    ArrayList test = new ArrayList();
                    root.query(test, test, child.x1(), child.y1(), child.x2(), child.y2()); // todo, null for 2nd?

                    if (test.size() == 0) shouldSplit = true;

                }
            }

            if (shouldSplit) {
                for (C_QuadTree_AABB<Line> child : quadTree.children()) {
                    createImageMaps(child, imageHashMap, maxWidth, maxHeight);
                }
            }
            else { // make a graphic if we have content

                ArrayList al = new ArrayList();
                root.query(al, al, quadTree.x1(), quadTree.y1(), quadTree.x2(), quadTree.y2()); // todo, null for 2nd?

                if (al.size() > 0) {
                    int width = (int) (quadTree.x2() - quadTree.x1()); // todo, floor etc
                    int height = (int) (quadTree.y2() - quadTree.y1()); // todo, floor etc
                    PGraphics pg = createGraphics(width, height);

                    pg.beginDraw();
                    pg.background(random(100, 255), random(100, 255), random(100, 255));
                    //pg.clear();

                    pg.stroke(white);
                    pg.strokeWeight(0.5f);

                    pg.pushMatrix();
                    pg.translate((float) -quadTree.x1(), (float) -quadTree.y1());

                    for (Line l : lines) {
                        pg.line((float) l.x1, (float) l.y1, (float) l.x2, (float) l.y2);
                    }

                    pg.popMatrix();

                    pg.endDraw();

                    imageHashMap.put(quadTree, pg);

                }
            }

        }





    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    void drawQuadTreeAdvanced(C_QuadTree_AABB<Line> quadTree) {

        ArrayList<Line> isEmptyCheck = new ArrayList<>();

        PGraphics pg = imageHashMap.get(quadTree);
        if (pg == null) {
            // try children
            if (quadTree.hasChildren()) {
                for (C_QuadTree_AABB<Line> child : quadTree.children()) {
                    // check for items
                    child.queryAll(isEmptyCheck);
                    int n = isEmptyCheck.size();
                    isEmptyCheck.clear();

                    if (n > 0) {
                        drawQuadTreeAdvanced(child);
                    }
                }
            }
        }
        else {
            image(pg, (float)quadTree.x1(), (float)quadTree.y1());
        }



    }



    @Override
    public void draw() {

        ((View)view).setPos(mouseX, mouseY);


        background(black);


        drawQuadTreeAdvanced(quadTree);

        noFill();
        stroke(darkRed);
        strokeWeight(1);
        drawQuadTree(quadTree);

        rectMode(CORNERS);
        noFill();
        stroke(0,255,0);
        rect(origQuadTree.x1(), origQuadTree.y1(), origQuadTree.x2(), origQuadTree.y2());

        textSize(10);

        for (Line l : intersecting) {
            stroke(orange);
            strokeWeight(0.5f);
            line(l.x1, l.y1, l.x2, l.y2);
        }

        for (Line l : containing) {
            stroke(greenYellow);
            strokeWeight(1.5f);
            line(l.x1, l.y1, l.x2, l.y2);
        }

        if (selected != null) {
            noFill();
            stroke(orange);
            strokeWeight(1);
            drawQuadTree(selected);

            fill(white);
            int n = nOfItems(selected);
            text(""+n, 20, 20);
        }

        noFill();
        stroke(blueViolet);
        rectMode(CORNERS);
        rect(view.x1(), view.y1(), view.x2(), view.y2());

        /*
        noFill();
        stroke(0, 255, 0);
        ellipse(mouseX, mouseY, radius + radius, radius + radius);
        */


        // get the first Quad that is still bigger then the view

        //OC_QuadTree_AABB collisionWithView = getDeepestContaining(quadTree, view);
        C_QuadTree_AABB collisionWithView = quadTree.getDeepestContaining(view);

        noFill();
        stroke(greenYellow);
        rectMode(CORNERS);
        if (collisionWithView != null) {

            rect(collisionWithView.x1(), collisionWithView.y1(), collisionWithView.x2(), collisionWithView.y2());
            line(collisionWithView.x1(), collisionWithView.y1(), collisionWithView.x2(), collisionWithView.y2());
            line(collisionWithView.x1(), collisionWithView.y2(), collisionWithView.x2(), collisionWithView.y1());

            /*
            // we should also get a list where we further split up
            ArrayList<OC_QuadTree_AABB> drawContent = new ArrayList<>();

            getIntersectingLeafs(collisionWithView, view, drawContent);

            stroke(orangeRed);
            for (OC_QuadTree_AABB qt : drawContent) {
                rect(qt.x1(), qt.y1(), qt.x2(), qt.y2());
            }
            */

        }

        noFill();
        rectMode(CORNERS);
        stroke(orangeRed);


        /*
        // we don't always want the leafes.
        // Let's say we draw the netherlands, then we wan't to get
        // the first level that is suitable for drawning.
        // which could even be the whole quadTree, then doing every leaf would be a pain
        for (OC_QuadTree_AABB qt : getIntersectingLeafs(quadTree, view)) {
            rect(qt.x1(), qt.y1(), qt.x2(), qt.y2());
        }
        */

        // we need a simple check to indicate when to stop?
        // not really, Lets say we intersect the origin, getDeepestContaining
        // will return the root.
        // now our draw method can draw this.
        // the draw method might not have anything at the root level to draw so it goes a level deeper
        // and also checks what it has to draw of that level?




        quadTree.getIntersectingLeafs(view);



    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    /*
    // onos shell?
    public ArrayList<OC_QuadTree_AABB<?, ?>> getIntersectingLeafs(OC_QuadTree_AABB<?, ?> quadTree, _AABB_2D bounds) {

        ArrayList<OC_QuadTree_AABB<?, ?>> result = new ArrayList<>();

        quadTree = quadTree.getDeepestContaining(bounds);

        if (quadTree != null) {
            quadTree.getIntersectingLeafs(bounds, result);
        }

        return result;
    }
     */

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .





    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    @Override
    public void mouseDragged() {

//        pushStyle();
//        fill(red);
//        rectMode(CORNERS);
//        rect(mousePressX, mousePressY, mouseX, mouseY);
//        popStyle();

        /*
        containing.clear();
        intersecting.clear();

        println(mouseX + " " + mouseY + " " + radius);
        quadTree.queryRadius(containing, intersecting, mouseX, mouseY, radius);
        println("containing.size(): " + containing.size());
        println("intersecting.size(): " + intersecting.size());
        */

        quadTree.insert(new Line(mouseX, mouseY, mouseX+30, mouseY+25));

        while (quadTree.parent() != null) {
            quadTree = quadTree.parent();
        }

    }

    public void mousePressed() {

        if (selected == null) {
            selected = quadTree;
        }
        else {
            if (keyPressed) {
                while (selected.hasParent() && !selected.contains_point(mouseX, mouseY)) selected = selected.parent();
            }
            else {
                while (selected.hasParent() && !selected.contains_point(mouseX, mouseY)) selected = selected.parent();
                while (selected.hasChildren()) selected = selected.children()[selected.getIndex(mouseX, mouseY)];
            }
        }
    }



    int nOfItems(OC_QuadTree<?, ?> quadTree) {
        ArrayList items = new ArrayList();
//        quadTree.queryAll(items);
//        return items.size();
        OC_QuadTree root = quadTree;
        while (root.hasParent()) root = root.parent();

        root.query(items, items, quadTree.x1(), quadTree.y1(), quadTree.x2(), quadTree.y2());

        //println(quadTree.x1(), quadTree.y1(), quadTree.x2(), quadTree.y2());
        return items.size();

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


    class View implements _AABB_2D {

        double x1, y1, x2, y2;

        View(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public double x1() {
            return x1;
        }

        @Override
        public double y1() {
            return y1;
        }

        @Override
        public double x2() {
            return x2;
        }

        @Override
        public double y2() {
            return y2;
        }

        public void setPos(double x, double y) {
            x2 = x+width();
            y2 = y+height();
            x1 = x;
            y1 = y;
        }

    }


    class Line implements _Line {

        double x1, y1, x2, y2;

        Line() {

        }

        Line(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public double x1() {
            return x1;
        }

        @Override
        public double y1() {
            return y1;
        }

        @Override
        public double x2() {
            return x2;
        }

        @Override
        public double y2() {
            return y2;
        }




    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

}
