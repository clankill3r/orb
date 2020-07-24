package util.datastructure;


import nl.doekewartena.orb.crust.util.datastructure.C_BinPackerGuillotine;
import org.problessing.Problessing;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by doekewartena on 6/11/15.
 */
public class BinPackerGuillotine_2D extends Problessing {

    public static void main(String[] args) {
        Problessing.main("util.datastructure.BinPackerGuillotine_2D", args);
    }

    public void settings() {
        size(512, 512);
    }

    C_BinPackerGuillotine<?> packer;

    ArrayList<int[]> widthHeights = new ArrayList<>();

    public void setup() {

        frameRate(5);

        packer = new C_BinPackerGuillotine(0, 0, width, height);

    }

    public void draw() {

        background(255);

        stroke(0);
        for (C_BinPackerGuillotine gp : packer) {
            fill(gp.used ? color(255, 0, 0) : color(0, 255, 0));
            rect(gp.x, gp.y, gp.w, gp.h);
        }

        int[] wh = {(int)random(5, 80), (int)random(5, 80)};
        widthHeights.add(wh);

        packer.pack(wh[0], wh[1]);

        C_BinPackerGuillotine selection = packer.forwardFind(mouseX, mouseY);
        stroke(0,0,255);
        line(selection.x1(), selection.y1(), selection.x2(), selection.y2());
        line(selection.x1(), selection.y2(), selection.x2(), selection.y1());



    }


    public void keyPressed() {

        println("repack");
        Collections.sort(widthHeights, (wh1, wh2) -> {
            int yDiff = wh2[1] - wh1[1];
            if (yDiff != 0) return yDiff;
            return wh2[0] - wh1[0];
        });
        packer = new C_BinPackerGuillotine(0, 0, width, height);
        for (int[] wh : widthHeights) {
            packer.pack(wh[0], wh[1]);
        }


    }





}
