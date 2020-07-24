package orb._crust.util.datastructure;

import orb.___outer_core.util.datastructure.OC_CellGrid;

/**
 * Created by doekewartena on 8/30/15.
 */
public class C_CellGrid<T> extends OC_CellGrid<T, C_CellGrid> {

    public C_CellGrid(float x1, float y1, float x2, float y2, int nOfCellsX, int nOfCellsY) {
        super(x1, y1, x2, y2, nOfCellsX, nOfCellsY);
    }

}
