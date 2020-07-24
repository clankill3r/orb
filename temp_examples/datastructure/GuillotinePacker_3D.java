package  orb_examples.datastructure;


import orb.crust.util.datastructure.C_BinPackerGuillotine_3D;
import orb.____inner_core.geom._AABB_3D;
import orb.outer_core.util.compare.OC_ComparatorStack;
import org.problessing.Problessing;
import peasy.PeasyCam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by doekewartena on 6/11/15.
 */
public class GuillotinePacker_3D extends Problessing {

    public static void main(String[] args) {
        Problessing.main("util.datastructure.GuillotinePacker_3D", args);
    }

    public void settings() {
        size(1024, 1024, P3D);
    }

    C_BinPackerGuillotine_3D<int[]> packer;

    ArrayList<int[]> widthHeightDepths = new ArrayList<>();

    PeasyCam cam;

    boolean pause;

    public void setup() {

        //frameRate(5);

        packer = new C_BinPackerGuillotine_3D<>(0, 0, 0, width, height, width);

        cam = new PeasyCam(this, 100);
        cam.setMinimumDistance(50);
        cam.setMaximumDistance(5000);


    }

    public void draw() {

        background(255);

        // draw bounding:
        pushMatrix();
        stroke(darkRed);
        noFill();
        translate((float) packer.cx(), (float) packer.cy(), (float) packer.cz());
        box((float) packer.width(), (float) packer.height(), (float) packer.depth());
        popMatrix();

        //stroke(0);
        noFill();

        for (C_BinPackerGuillotine_3D gp : packer) {
            stroke(gp.used ? color(255, 0, 0) : color(0, 255, 0));
            pushMatrix();
            translate((float)gp.cx(), (float)gp.cy(), (float)gp.cz());
            //rect(gp.x, gp.y, gp.w, gp.h);
            // let's hope our drawning is wrong...
            //box((float)gp.width(), (float)gp.height(), (float)gp.depth());
            popMatrix();
        }

        if (!pause && frameCount % 1 == 0) {
            int[] whd = {(int) random(5, 80), (int) random(5, 80), (int) random(5, 80)};

            // return a boolean instead?
            // or the center vector? (nah needs to be calculated)
            //if (packer.pack(whd[0], whd[1], whd[2]) != null) {
            if (packer.pack(whd, whd[0], whd[1], whd[2]) != null) {
                widthHeightDepths.add(whd);
            }

        }

        //randomSeed(1);



        for (C_BinPackerGuillotine_3D<int[]> gp : packer) {
            if (!gp.used) continue;

            _AABB_3D usedArea = getAABBUsedArea(gp);

            pushMatrix();
            if (usedArea != null) {
                translate((float) usedArea.cx(), (float) usedArea.cy(), (float) usedArea.cz());
            }

            int[] whd = gp.item;

            noFill();
            //stroke(darkGreen);
            //stroke(random(255),random(255),random(255));
            stroke(map(whd[0], 5, 80, 0, 255), map(whd[1], 5, 80, 0, 255), map(whd[1], 5, 80, 0, 255));
            box(whd[0], whd[1], whd[2]);
            popMatrix();

        }



        int index = (int) map(mouseX, 0, width, 0, widthHeightDepths.size());

        C_BinPackerGuillotine_3D<int[]> selection = null;

        int c = 0;
        for (C_BinPackerGuillotine_3D<int[]> gp : packer) {
            if (!gp.used) continue;
            if (c == index) {
                selection = gp;
                break;
            }
            c++;
        }

        if (selection != null && selection.item != null) {
            int[] whd = selection.item;
            pushMatrix();
            // this is wrong, we need to get the center of the used area!
            //
            //translate((float) selection.cx(), (float) selection.cy(), (float) selection.cz());
            _AABB_3D usedArea = getAABBUsedArea(selection);

            if (usedArea != null) {
                translate((float) usedArea.cx(), (float) usedArea.cy(), (float) usedArea.cz());
            }

            stroke(0);
            fill(255,0,0);
            box(whd[0], whd[1], whd[2]);
            popMatrix();
        }



        surface.setTitle("items packed: "+widthHeightDepths.size()+" frameCount: "+frameCount);

//        OS_GuillotinePacker selection = packer.forwardFind(mouseX, mouseY);
//        stroke(0,0,255);
//        line(selection.x1(), selection.y1(), selection.x2(), selection.y2());
//        line(selection.x1(), selection.y2(), selection.x2(), selection.y1());



    }

    public _AABB_3D getAABBUsedArea(C_BinPackerGuillotine_3D packer) {

        if (!packer.used) return null;

        float x1, y1, z1, x2, y2, z2;

        x1 = packer.x1();
        y1 = packer.y1();
        z1 = packer.z1();

        x2 = packer.right.x1();
        y2 = packer.down.y1();
        z2 = packer.behind.z1();

        // add AABB to onos shell?
        _AABB_3D result = new _AABB_3D() {

            @Override
            public float x1() {
                return x1;
            }

            @Override
            public float y1() {
                return y1;
            }

            @Override
            public float z1() {
                return z1;
            }

            @Override
            public float x2() {
                return x2;
            }

            @Override
            public float y2() {
                return y2;
            }

            @Override
            public float z2() {
                return z2;
            }
        };

        return result;

    }


    public void keyPressed() {

        if (key == 'p') pause = !pause;
        else {

            println("repack");

            Comparator<int[]> widthComparator = (whd1, whd2) -> {
                // reverse 1 and -1?
                if (whd1[0] > whd2[0]) return -1;
                if (whd1[0] < whd2[0]) return 1;
                return 0;
            };

            Comparator<int[]> heightComparator = (whd1, whd2) -> {
                // reverse 1 and -1?
                if (whd1[1] > whd2[1]) return -1;
                if (whd1[1] < whd2[1]) return 1;
                return 0;
            };

            Comparator<int[]> depthComparator = (whd1, whd2) -> {
                // reverse 1 and -1?
                if (whd1[2] > whd2[2]) return -1;
                if (whd1[2] < whd2[2]) return 1;
                return 0;
            };

            OC_ComparatorStack<int[]> whdComparator = new OC_ComparatorStack<>(widthComparator, heightComparator, depthComparator);
            OC_ComparatorStack<int[]> hdwComparator = new OC_ComparatorStack<>(heightComparator, depthComparator, widthComparator);


//        Collections.sort(widthHeightDepths, (wh1, wh2) -> {
//            int yDiff = wh2[1] - wh1[1];
//            if (yDiff != 0) return yDiff;
//            return wh2[0] - wh1[0];
//        });
            //Collections.sort(widthHeightDepths, whdComparator);
            Collections.sort(widthHeightDepths, hdwComparator);

            for (int i = 0; i < 25; i++) {
                int[] whd = widthHeightDepths.get(i);
                println(whd[0], whd[1], whd[2]);
            }


            packer = new C_BinPackerGuillotine_3D(0, 0, 0, width, height, width);
            for (int[] whd : widthHeightDepths) {
                packer.pack(whd, whd[0], whd[1], whd[2]);
            }
        }


    }





}
