package nl.doekewartena.orb.crust;


/**
 * Created by doekewartena on 7/22/15.
 */

/*
todo, we could reserve a few of the bits in oc_flags.
 0 : 1
 1 : 2
 2 : 4
 3 : 8
 4 : 16
 5 : 32
 6 : 64
 7 : 128
 8 : 256
 9 : 512
10 : 1024
11 : 2048
12 : 4096
13 : 8192
14 : 16384
15 : 32768
16 : 65536

If we would reserve 8 bits then we can create a value up to 256 which can help
a lot with indexing moving objects for example.
On the other hand, adding a extra int or short makes things really a lot easier.

 */


                      // todo? <C extends OC_Object> // probably not
public class C_Object extends Object {


    // 2 + 4 +32
    // todo, name different (including related methods)
    // maybe not public but we should be able to get it
    // so we can store it
    // todo, make interface method to access? this way we don't expand on size
    public long oc_flags;

    // is this bad?
    // yeah it is, let's see we have a milion particles and every frame we run a method that
    // creates a object of type OC_Object then the oc_id would overflow quite fast and become meaningless
    // also it would get to big to soon
    //static AtomicInteger nextId = new AtomicInteger();
    ////static int oc_id_count = 0;
    //public final int oc_id = nextId.getAndIncrement(); //oc_id_count++; // make id a long?

    //public final int oc_id;

    public C_Object() {
        System.out.println("constructed IC_Object");
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public boolean instanceOf(Class<?> c) {
        return (c.isInstance(this));
    }

    public boolean instanceOf(Class<?>... classes) {
        for (Class<?> c : classes) {
            if (!c.isInstance(this)) return false;
        }
        return true;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . .



//    public OC_Object() {
//        //oc_id = -1;
//    }

    // make a global index assigner?
//    public OC_Object(AtomicInteger indexAsigner) {
//        if (indexAsigner == null) oc_id = -1;
//        else {
//            oc_id = indexAsigner.getAndIncrement(); // todo, do we need incrementAndGet for safety?
//            //_set(OC_Constants.HAS_ID); // we could just check against -1
//        }
//    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public void _set(long maskBits) {
        oc_flags |= maskBits;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    // returns only true if all matches?
    public boolean _has(long maskBits) {
        return (oc_flags & maskBits) == maskBits;
    }



    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    public void _remove(long maskBits) {
        oc_flags &= ~maskBits;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


    public void _toggle(long maskBits) {
        oc_flags ^= maskBits;
    }


    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .


}


