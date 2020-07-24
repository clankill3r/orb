package  orb_examples.util.datastructure;


import orb.crust.geom.V2;
import orb.crust.util.datastructure.C_CellGrid;
import orb.outer_core.util.datastructure.OC_CellGrid;
import org.problessing.Problessing;

import java.util.ArrayList;

/**
 * Created by doekewartena on 8/30/15.
 */
public class CellGrid extends Problessing {

    public static  void main(String[] args) {
        Problessing.main("util.datastructure.CellGrid", args);
    }


    @Override
    public void settings() {
        size(512, 512);
    }

    C_CellGrid<V2> cellGrid;

    // temp
    ArrayList<V2> vecs = new ArrayList<>();


    @Override
    public void setup() {
        cellGrid = new C_CellGrid<>(0, 0, width, height, 8, 8);

        int i = 0;
        while(i++ < 1000) {
            V2 v = new V2(random(width)*noise(i), random(height)*noise(i));
            vecs.add(v);
        }
    }

    @Override
    public void draw() {
        background(255);

        stroke(0);
        strokeWeight(3);
        strokeCap(ROUND);
        for (V2 v : vecs) {
            point((float)v.x, (float)v.y);
        }


        // this is one way
        stroke(darkRed);
        noFill();
        rectMode(CORNERS);
        strokeWeight(1);
        for (OC_CellGrid.OC_Cell_2D cell : cellGrid.cells) {
            rect(cell.x1(), cell.y1(), cell.x2(), cell.y2());
        }

        // todo, we have a bug if we go lower then y2!
        OC_CellGrid.OC_Cell_2D hot = cellGrid.getCell((float)mouseX, (float)mouseY);

        if (hot != null) {
            stroke(green);
            strokeWeight(2);
            rect(hot.x1(), hot.y1(), hot.x2(), hot.y2());
        }

        // let's do an attempt of getting the closest, according to mouse first
        // we could sort an array but this would be very unefiecient with a very large grid

        // todo, method closestCell?
        OC_CellGrid.OC_Cell_2D currentClosest = cellGrid.getCell((float) mouseX, (float) mouseY);
        if (currentClosest == null) {
            currentClosest = cellGrid.getCell(constrain(mouseX, (float) cellGrid.x1(), (float) cellGrid.x2()), constrain(mouseY, (float) cellGrid.y1(), (float) cellGrid.y2()));
        }

        stroke(orangeRed);
        line(currentClosest.x1(), currentClosest.y1(), currentClosest.x2(), currentClosest.y2());
        line(currentClosest.x2(), currentClosest.y1(), currentClosest.x1(), currentClosest.y2());

        // now get the next closest until where done
        // fuck it let's just sort, let's only sort an array of indices?
        /*

        int[] indices = new int[nOfCells];

        Comparator<int[]> distComparator = new Comparator<>() {
            public int compare(
        };


         */





    }




}
