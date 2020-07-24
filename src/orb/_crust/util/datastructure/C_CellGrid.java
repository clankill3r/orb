package nl.doekewartena.orb.crust.util.datastructure;

import nl.doekewartena.orb.outer_core.util.datastructure.OC_CellGrid;

/**
 * Created by doekewartena on 8/30/15.
 */
public class C_CellGrid<T> extends OC_CellGrid<T, C_CellGrid> {

    public C_CellGrid(double x1, double y1, double x2, double y2, int nOfCellsX, int nOfCellsY) {
        super(x1, y1, x2, y2, nOfCellsX, nOfCellsY);
    }

}
