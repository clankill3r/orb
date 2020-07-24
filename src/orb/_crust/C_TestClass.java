package orb._crust;

import nl.doekewartena.orb.crust.wip._InstanceOf;
import nl.doekewartena.orb.inner_core.util.datatstructure._Tree;
import nl.doekewartena.orb.inner_core.util.function._Insert_T;
import nl.doekewartena.orb.inner_core.util.function._Remove_T;

import static nl.doekewartena.orb.inner_core.IC_AllClasses._Remove;

/**
 * Created by doekewartena on 9/12/15.
 */
public class C_TestClass {



    public static void main(String[] args) {

        new C_TestClass();

    }

    C_TestClass() {

        TestClass t = new TestClass();

        System.out.println(instanceOf(t, _Remove_T.class));
        System.out.println(instanceOf(t, _Remove_T.class, _Insert_T.class));
        System.out.println(instanceOf(t, _Remove_T.class, _Tree.class));
        System.out.println(instanceOf(t, _Remove));

        System.out.println("\nfoo\n");

        Foo foo = new Foo();
        System.out.println(foo.instanceOf(_Remove));

        System.out.println("\nbar\n");

        Bar bar = new Bar();
        System.out.println(bar.instanceOf(_Remove));



    }

    public static boolean instanceOf(Object o, Class<?>... aClass) {
        for (Class<?> aClass2 : aClass) {
            if (!aClass2.isInstance(o)) return false;
        }
        return true;
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

    class Bar implements _InstanceOf, _Remove_T {

        @Override
        public _Remove_T remove(Object o) {
            return null;
        }
    }

}
