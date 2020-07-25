package orb_examples;

import orb._crust.geom.V2;
import orb._crust.geom.V3;
import orb.____inner_core.util.compare.IC_Compare;
import orb.___outer_core.util.compare.OC_ComparatorStack;
import java.util.*;


/**
 * Created by doekewartena on 9/13/15.
 */
public class AllKindsOfTests {

    public static void main(String[] args) {
        AllKindsOfTests a = new AllKindsOfTests();
        a.foo();
    }

    public void foo() {

        Random rnd = new Random();

        ArrayList<V2> vecs = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            V2 v = new V2(rnd.nextFloat(), rnd.nextFloat());
            vecs.add(v);

        }

        //Collections.sort(vecs, _CompareXY_T::compareX);

        //vecs.sort((o1, o2) -> o1.compareAngleToPoint(o2, 50, 100));






        for (V2 v : vecs) {
            System.out.println(v);
        }

        // why can't we compare against V2?
        // that would be impossible!
        // might also be good that it is not possible since it makes things more clear
        V3 v3 = new V3(10, 20, 30);
        //v3.compareX(new V2());

        V2 v2 = new V2(1, 2);

        // should we allow this?
        //v2.compareX(v3);
        // also, we should allow a compare to _Vec2 instead of V2

        // nice!!
        //v2.compareXY(v3.x, v3.y);


        // goal:
        //
        //IC_Collections.sort(vecs, v -> v.x, v -> v.y);

        //sort(vecs, (o1, o2) -> o1.compareX(o2), (o1, o2) -> o1.compareY(o2));


        // this is nice:
        sort(vecs,  (o1, o2) -> IC_Compare.compare(o1.x, o2.x),
                    (o1, o2) -> IC_Compare.compare(o1.y, o2.y));


        for (V2 v : vecs) {
            System.out.println(v);
        }


    }


    // todo crust, this is really nice!
    public <E> void sort(List<E> l, Comparator<E> ... comparators) {
        OC_ComparatorStack<E> comparatorStack = new OC_ComparatorStack<>(comparators);
        l.sort(comparatorStack);
    }



}
