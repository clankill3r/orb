package orb.____inner_core.util.compare.excluded;

/**
 * Created by doekewartena on 9/13/15.
 */
public interface _Compare {

    default int compare(float a, float b) {
        if      (a < b) return -1;
        else if (a > b) return 1;
        else            return 0;
    }

}
