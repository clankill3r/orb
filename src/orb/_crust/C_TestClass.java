package orb._crust;

import orb.____inner_core.util.datatstructure._Tree;
import orb.____inner_core.util.function._Insert_T;
import orb.____inner_core.util.function._Remove_T;

import static orb.____inner_core.IC_AllClasses._Remove;

/**
 * Created by doekewartena on 9/12/15.
 */
public class C_TestClass {



    public static void main(String[] args) {

        new C_TestClass();

    }

    C_TestClass() {

        TestClass t = new TestClass();

       
        System.out.println("\nfoo\n");

        Foo foo = new Foo();
        System.out.println(foo.instanceOf(_Remove));

        System.out.println("\nbar\n");

        Bar bar = new Bar();



    }

    class TestClass implements _Remove_T, _Insert_T {

        @Override
        public _Remove_T remove(Object o) {
            return null;
        }

        @Override
        public _Insert_T insert(Object o) {
            return null;
        }
    }

    class Foo extends C_Object implements _Remove_T {

        @Override
        public _Remove_T remove(Object o) {
            return null;
        }
    }

    class Bar implements _Remove_T {

        @Override
        public _Remove_T remove(Object o) {
            return null;
        }
    }

}
