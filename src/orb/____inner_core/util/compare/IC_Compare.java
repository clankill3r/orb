package orb.____inner_core.util.compare;

/**
 * Created by doekewartena on 9/13/15.
 */
public class IC_Compare {

    // do we need this or will it upcast?
    /*
    public static int compare(char a, char b) {
        if      (a < b) return -1;
        else if (a > b) return 1;
        else            return 0;
    }
    */

    public static int compare(int a, int b) {
        if      (a < b) return -1;
        else if (a > b) return 1;
        else            return 0;
    }

    public static int compare(long a, long b) {
        if      (a < b) return -1;
        else if (a > b) return 1;
        else            return 0;
    }

    public static int compare(float a, float b) {
        if      (a < b) return -1;
        else if (a > b) return 1;
        else            return 0;
    }

    public static int compare(double a, double b) {
        if      (a < b) return -1;
        else if (a > b) return 1;
        else            return 0;
    }




}
