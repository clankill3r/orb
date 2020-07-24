package orb.___outer_core.util.datastructure;

import orb.____inner_core.IC_Common;
import orb.____inner_core.geom._AABB_2D;
import orb.____inner_core.geom._AABB_3D;
import orb.____inner_core.util.compare.IC_Compare;
import orb.____inner_core.util.datatstructure._Data_2D;
import orb.____inner_core.util.datatstructure._Query_2D;
import orb.____inner_core.util.datatstructure._Data;
import orb.____inner_core.util.datatstructure._Tree_2D;
import orb.____inner_core.util.function._GetFloat_T;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static orb.____inner_core.IC_Math.*;

/**
 * Created by doekewartena on 8/27/15.
 */

/*

Should we make an inner class OC_Cell?





 */


// the big difference is that we can go directly to an cell by calculating an index from xy
// name OC_QuadrantGrid?
public class OC_CellGrid<T, C extends OC_CellGrid>

        implements

        //_Tree_2D<T, C>,
        _AABB_2D,
        _Query_2D<T, C>,
        _Data_2D<T, C>

    { // it's not really a tree but it will be convenient to implement the interface
      // on the other hand it's not convenient cause we have to implement methods we don't want
      // maybe it should implement _Data_2D, kinda makes sense

    float x1, y1, x2, y2;

    int nOfCellsX;
    int nOfCellsY;
    int nOfCells;

    // or _AABB_2D
    public OC_Cell_2D[] cells;

    float cellWidth, cellHeight;


    int nOfItems; // this will only work if not modified outside!

    _GetFloat_T<T> getX;
    _GetFloat_T<T> getY;
    _GetFloat_T<T> getX2;
    _GetFloat_T<T> getY2;


    public OC_CellGrid(float x1, float y1, float x2, float y2, int nOfCellsX, int nOfCellsY) {
        init(x1, y1, x2, y2, nOfCellsX, nOfCellsY);
    }

    protected void init(float x1, float y1, float x2, float y2, int nOfCellsX, int nOfCellsY) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.nOfCellsX = nOfCellsX;
        this.nOfCellsY = nOfCellsY;

        nOfCells = nOfCellsX*nOfCellsY;
        cells = (OC_Cell_2D[]) Array.newInstance(OC_Cell_2D.class, nOfCells);


        for (int y = 0, idx = 0; y < nOfCellsY; y++) {
            for (int x = 0; x < nOfCellsX; x++, idx++) {
                cells[idx] = new OC_Cell_2D(x, y);
                //System.out.println(x+"\t"+y+"\t"+idx);
            }
        }

        cellWidth  = (x2-x1) / nOfCellsX;
        cellHeight = (y2-y1) / nOfCellsY;

        System.out.println("cellWidth: "+cellWidth);
        System.out.println("cellHeight: " + cellHeight);


    }

    public OC_Cell_2D getCell(int col, int row) {
        return cells[col + row * nOfCellsX];
    }

    public OC_Cell_2D getCell(float x, float y) {

        if (x < x1() || x > x2() || y < y1() || y > y2()) return null;

        int col = (int) (x / cellWidth);
        int row = (int) (y / cellHeight);

        if (col == nOfCellsX) col--;
        if (row == nOfCellsY) row--;

        System.out.println("col: "+col+"\trow: "+row);

        int index = col + row * nOfCellsX;

        return cells[index];
    }




    @Override
    public float x1() {
        return x1;
    }

    @Override
    public float y1() {
        return y1;
    }

    @Override
    public float x2() {
        return x2;
    }

    @Override
    public float y2() {
        return y2;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    @Override
    public float getX(T t) {
        return getX.val(t);
    }

    @Override
    public float getY(T t) {
        return getY.val(t);
    }

    @Override
    public float getX2(T t) {
        return getX2.val(t);
    }

    @Override
    public float getY2(T t) {
        return getY2.val(t);
    }

        @Override
    public C queryAll(List<T> dest) {
        return (C) this;
    }

    @Override
    public T query(float tx, float ty) {
        OC_Cell_2D cell = getCell(tx, ty);
        if (cell != null) {
            return cell.data.query(tx, ty);
        }
        return null;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, float tx, float ty) {
        OC_Cell_2D cell = getCell(tx, ty);
        if (cell != null) {
            cell.data.query(containing, intersecting, tx, ty);
        }
        return (C) this;
    }

    @Override
    public C query(List<T> containing, List<T> intersecting, float tx1, float ty1, float tx2, float ty2) {
        // we need all the cells now it intersects with
        // todo
        return (C) this;
    }

    @Override
    public C queryRadiusSq(List<T> containing, List<T> intersecting, float cx, float cy, float radiusSQ) {
        return (C) this;
    }



    @Override
    public C queryMinX(IC_Common.BestMatch<T> bestMatch) {
        // all cells always exist right?
        // yeah else an index will be freaking hard!

        // this is not cache friendly but probably still faster
        for (int x = 0; x < nOfCellsX; x++) {
            for (int y = 0; y < nOfCellsY; y++) {
                OC_Cell_2D cell = getCell(x, y);
                cell.queryMinX(bestMatch);
                // if (bestMatch.val == x1) break; // do we want this optimization? (also other methods!)
            }
        }
        return (C) this;
    }

    @Override
    public C queryMinY(IC_Common.BestMatch<T> bestMatch) {

        for (int y = 0; y < nOfCellsY; y++) {
            for (int x = 0; x < nOfCellsX; x++) {
                OC_Cell_2D cell = getCell(x, y);
                cell.queryMinY(bestMatch);
            }
        }
        return (C) this;
    }

    @Override
    public C queryMaxX(IC_Common.BestMatch<T> bestMatch) {

        for (int x = nOfCellsX-1; x >= 0; x--) {
            for (int y = 0; y < nOfCellsY; y++) {
                OC_Cell_2D cell = getCell(x, y);
                cell.queryMinX(bestMatch);
            }
        }
        return (C) this;
    }

    @Override
    public C queryMaxY(IC_Common.BestMatch<T> bestMatch) {

        for (int y = nOfCellsY-1; y >= 0; y--) {
            for (int x = 0; x < nOfCellsX; x++) {
                OC_Cell_2D cell = getCell(x, y);
                cell.queryMinY(bestMatch);
            }
        }
        return (C) this;
    }


    @Override
    public C queryClosest(float x, float y, IC_Common.BestMatch<T> bestMatch) {




         /*
        // todo, we should work our way around the closest cells§
        // ... (so probably circular motion)
        // keep in mind xy can be outside the cellgrid which is valid!


        int[] indices = new int[nOfCells];

        Comparator<Integer> distComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                OC_Cell_2D cell1 = cells[o1];
                OC_Cell_2D cell2 = cells[o2];
                return IC_Compare.compare(distSq(cell1.cx(), cell1.cy(), x, y), distSq(cell2.cx(), cell2.cy(), x, y));
            }
        };
        */


        /*
        // first get the closest cell to x and y,
        // if that contains no object, get all cells around that cell
        // go on like that


         */

        OC_Cell_2D currentClosest = getCell(constrain(x, x1(), x2()), constrain(y, y1(), y2()));

        // todo should be index
        ArrayList<OC_Cell_2D> open = new ArrayList<>();

        open.add(currentClosest);

        while (open.size() > 0) {

            currentClosest = open.remove(open.size()-1);  // swap with last remove?

            currentClosest.queryClosest(x, y, bestMatch);

            if (bestMatch.item != null) break;

            // get the next circumference
            // we should walk around in a CW or CCW motion
            // where a rule is that the cell we check touches a cell we checked before
            // we would only have to keep the indexes of the outer boundary


        }

        return (C) this;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .





    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return nOfItems;
    }

    @Override
    public C add(T t) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public C remove(T t) {
        return null;
    }


        // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
    // [0][1][2]
    // [3][4][5]
    // [6][7][8]

    // for testing:

    public interface _Cell {
        // getData?
    }

    public interface _Cell_2D extends _Cell, _AABB_2D {

    }


    public interface _Cell_3D extends _Cell, _AABB_3D {

    }



    public class OC_Cell_2D implements _Data_2D<T, OC_Cell_2D>, _AABB_2D {//_Cell_2D {//, _Tree2D_Query<T> {

        // we keep memory usage small
        // by not storing any coordinates that make
        // up the cell

        // we could also store the index instead
        int row;
        int col;

        // data again
        //List<T> items;
        // can't we make a data() and have default methods that use data()?
        public _Data_2D<T, ?> data;


        OC_Cell_2D(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int row() {
            return row;
        }

        public int column() {
            return col;
        }



        @Override
        public boolean isEmpty() {
            return data.isEmpty();
        }

        @Override
        public int size() {
            return data.size();
        }

        @Override
        public OC_Cell_2D add(T t) {
            data.add(t);
            return this;
        }

        @Override
        public void clear() {
            data.clear();
            // todo, return this?
        }

        @Override
        public float getX2(T t) {
            return OC_CellGrid.this.getX2(t);
        }

        @Override
        public float getY2(T t) {
            return OC_CellGrid.this.getY2(t);
        }

        @Override
        public OC_Cell_2D queryAll(List<T> dest) {
            System.out.println("todo queryAll OC_Cell_2D");
            //data.
            // todo, query all in data?
            return this;
        }

        @Override
        public T query(float tx, float ty) {
            System.out.println("todo query OC_Cell_2D");
            return null;
        }

        @Override
        public OC_Cell_2D query(List<T> containing, List<T> intersecting, float tx, float ty) {
            System.out.println("todo query OC_Cell_2D");
            return this;
        }

        @Override
        public OC_Cell_2D query(List<T> containing, List<T> intersecting, float tx1, float ty1, float tx2, float ty2) {
            System.out.println("todo query OC_Cell_2D");
            return this;
        }

        @Override
        public OC_Cell_2D queryRadiusSq(List<T> containing, List<T> intersecting, float cx, float cy, float radiusSQ) {
            System.out.println("todo query OC_Cell_2D");
            return this;
        }

        @Override
        public OC_Cell_2D queryMinX(IC_Common.BestMatch<T> bestMatch) {
            System.out.println("todo query OC_Cell_2D");
            return this;
        }

        @Override
        public OC_Cell_2D queryMinY(IC_Common.BestMatch<T> bestMatch) {
            System.out.println("todo query OC_Cell_2D");
            return this;
        }

        @Override
        public OC_Cell_2D queryMaxX(IC_Common.BestMatch<T> bestMatch) {
            System.out.println("todo query OC_Cell_2D");
            return this;
        }

        @Override
        public OC_Cell_2D queryMaxY(IC_Common.BestMatch<T> bestMatch) {
            System.out.println("todo query OC_Cell_2D");
            return this;
        }

        @Override
        public OC_Cell_2D queryClosest(float x, float y, IC_Common.BestMatch<T> bestMatch) {
            System.out.println("todo query OC_Cell_2D");
            return this;
        }

        @Override
        public float getX(T t) {
            return OC_CellGrid.this.getX(t);
        }

        @Override
        public float getY(T t) {
            return OC_CellGrid.this.getY(t);
        }

        @Override
        public OC_Cell_2D remove(T t) {
            data.remove(t);
            return this;
        }


        @Override
        public float x1() {
            return row * cellWidth;
        }

        @Override
        public float y1() {
            return col * cellHeight;
        }

        @Override
        public float x2() {
            return (row+1) * cellWidth;
        }

        @Override
        public float y2() {
            return (col+1) * cellHeight;
        }


    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
}
