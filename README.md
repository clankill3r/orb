# orb
Resuscitated old project in order to show how bad OOP can get

Original project is from 2015.

2020 changes,

- shortened package structure for easier navigation
- removed double stuff
- String dataPath = "/Users/doekewartena/IdeaProjects/onos/onos_p5/projects/onos/data/";




Examples to fix:

- [ ] OctreeTreePoint



Dump from old TODO:

```java
import java.util.HashMap;

class Dump {

    /*
    1. queryClosest in OC_Octree


    comparator in quadtree / octree


    hasItems -> isEmpty
      */

    // _TreeSettings change name since it's also about the data!

    // ------------------------------------------

    // make the inner core only interfaces
    // make the outer core the classes
    // make the mantle specific algorithms like pathfinding, convexhull etc.
    // make the crust what onos shell is now
    //


    // ------------------------------------------

    // use a LocalHost for all files.
    // this way we can add links in the comments
    // this is great for images explaining something complex

    // ------------------------------------------

}
```

```java
class TODO_move {/*
    Zou er een regel zijn dar er geen helipad mag alse bepaalde restricties voldoen?
     */

    // ------------------------------------------

    class jai {


        // ------------------------------------------
        class multiple_parameter_set {

            class Foo {

                void add(float x, float y, float z) {
                    // ...
                }
            }
            // !!! see how random returns 3 values based on 3 values
            foo.add(randomXYZ(width, height, 0));
        }
        // ------------------------------------------
        class pointer_to_method_to_pointer_optimazation {
            // all we would need in jai is 3 pointers to an x, y and a z
            Foo<V2> foo3 = new Foo<>(v -> v.y, v -> v.y);
            Foo<V3> foo3 = new Foo<>(v -> v.y, v -> v.y, v -> v.z);
        }
        // ------------------------------------------
        class allow_combination_abstract_final {
            // allow combination abstract and final,
            // it should:
            // -be implemented on the first class declaration that is not abstract
        }
        // ------------------------------------------

    }
    // ------------------------------------------
    // ------------------------------------------
    // ------------------------------------------
}
```


```java
// ------------------------------------------
class TODO_ONOS_CORE {

    class dump {/*


        // todo IC_Math, rest of _Vec2 / _Vec3 methods



        distSq can accept a _Vec2 now, which is and experiment (_Vec3 extends _Vec2 which could give problems)

        functions is a poor name for 1 method interfaces, thinks like random and noise
        should be under functions?

        name functions lambdaMethods

        */


        _interface Item<T> {

            T item();
        }


        // ------------------------------------------

        interface _PropertyDistibutor {

        }

        class OC_PropertyDistibutor implements _PropertyDistibutor {

        }

        class SomeSketch {

            _PropertyDistibutor propertyDistibutor;
            _QuadTree quadTree;

            SomeSketch(_PropertyDistibutor propertyDistibutor, OC_QuadTree quadTree) {

                if (propertyDistibutor == null) {
                    // things like this should work with the workd concept,
                    // but they should never have an awereness of the world
                    propertyDistibutor = new OC_PropertyDistibutor();
                }

                //PVector v = new PVector();
                propertyDistibutor.add(getX, PVector, v-> v.x)
                        .add(getY, PVector, v -> v.y);
                .add(getZ, PVector, v-> v.z);

                quadTree = new OC_QuadTree<PVector>();

                int i = 0;
                while (i++ < 100) {
                    quadTree.add(random(width)*noise(0.05*i), random(height)*noise(0.05*i));
                }
            }

        }


        // ------------------------------------------

        // how do we handle normalized?
        interface _Noise {

            double noise(double x);
            double noise(double x, y);
            double noise(double x, y, z);

            void seed(long seed);
        }

        // how do we handle normalized?
        interface _Random {

            double random(double x);
            double random(double x, y);
            double random(double x, y, z);

            void seed(long seed);
        }


        // ------------------------------------------

// je moet een world maken voor je sketches. Dit zou een class moeten zijn die je deeld zodat we een world kunnen traversen.
// zo zouden we kunnen kijken of deze b.v. een quadtree gebruikt.

        // ------------------------------------------

        // je zou classes die je static aanroept moeten opvragen via de world.
// b.v. interface _Math, class OC_Math interface _OC_Math,
// or is this to prone for error?
        class Foo {

            _Math math;

            Foo() {
                math = (_Math) World.getInstance(_Math, this); // <- now the world can decide whatever one is required?
            }
        }

        class World {

            static getInstance(t : type, ? : requister) {
                if (type == _Math) {
                    //
                    if (requester == Foo) {
                        // return specific kind of math?
                        // (Math is probably a to specific example, we need something more abstract)
                        // like a physics engine
                    }
                }
                else if (...) {
                    // ..
                }
                else {
                    ... = findClassOrInterface(type);
                    ...
                }
            }

        }
        // ------------------------------------------

        class trees {


            //-select instead of query?

             //interface for level?


            // -------------

            int index_aabb[]; // from here we use offset

            // used for aabb:
            double x1_2[];
            double y1_2[];
            double z1_2[];

            // this way, finding width of quadtree can be done extreme fast as well?
            // now if we ignore z we don't even have to load it

            // ------------------------
        }

        // ------------------------------------------
        /*
        -add examples to project that don't use processing or any other library
        */
        // ------------------------------------------

        class design_patterns {
            /*
            Avoid recursion, that way we can make a pre and a post?
             */
            // -make a conventions file where we list how to break the conventions!

        }


        // ------------------------------------------
        protected C newInstance() {
            try {
                //System.out.println(this.getClass().getSimpleName());
                C c = (C) this.getClass().newInstance();
                return c;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        // ------------------------------------------
        // we need examples in the core as well that don't draw anything
        // processing should just be able to draw and influence hwat where doing (some kind of compound)
        // we should be able to get out of what we need to create a UI
        // but this without the GUI implementation drawing
        // ------------------------------------------
         /*


          */
        // ------------------------------------------
        // is a final static getX<T> bound to the specific type?
        // as in can we do:
        // we should type in sdeudo if it can run but doesn't help for jai?

        // ------------------------------------------
        // is this possible (if not add it to some document of what java should be able to do)
        abstract class Foo<T> {

            abstract final static double x1() {}

        }
        // ------------------------------------------
         /*


          */
        // ------------------------------------------

        // ------------------------------------------

        // ------------------------------------------
         /*


          */
        // ------------------------------------------

        // ------------------------------------------

    }


}
```


```java
class TODO_ONOS_SHELL {

    // maybe the 2nd shell should only provide static methods and interfaces (or first)

    // shell might be a bad name, since in a later state we could have multiple shells


}
```

```java
class TODO_processing {
    // ------------------------------------------

    class OC_Tree3DTest<T> extends Problessing implements _AABB_3D {


        public static void main(String[] args) {
            Problessing.main(args);
        }


        public void settings() {
            size();
        }

        public void setup() {

        }

        public void draw() {
            draw(g);
        }

        public void draw(PGraphics pg) {


        }
    }
    // ------------------------------------------
    // je zou een PApplet als texture / 3d input etc. moeten kunnen gebruiken. Dan word het zo krachtig!!!!!!!!!!!!!!!!!!!!!
    // je moet het stand alone kunnen runnen
    // je moet doordat draw een pgraphics accepteert verschillende renderes kunnen gebruiken
    // ------------------------------------------
    // ------------------------------------------
    // ------------------------------------------

}
```




Een node met een graphical weergave (zoals een graph) moet
vervangen kunnen worden door een andere weergave.
Stel je hebt een particle system. Dan zou het heel eenvoudig
moeten zijn om een gui op een particle te hebben als je er over heen
hoverd. Dit kan b.v. een interface zijn om de velocity, acceleration,
en mass te beinvloeden met sliders. Het moet ook makkelijk zijn om dit
te vervangen met een systeem waarbij je b.v. een laag van de circel
pressed met de cursor en vervolgens dragged om een lijn te zien van het
centrum naar de muis positie die als velocity vector zal dienen.

-als een patricle langs komt en je probeert er met de muis over heen te
gaan zou een algorithme kunnen detecteren dat je dit probeerd.
Als dit geval is en je voert dit voor x milliseconds met een score hoger dan y
uit dan zou een ghost van de partivle stil staan met de gui.

-inspector!

-user modes: illustrator / videoartist / cartographer / sculpter / ...,,, programmer

-or pixels / vector / geometry / animation / programmer

-sub user modes!!!

-quick switch tussen global user layout en current based on type

-je moet meerdere user modes tegelijk kunnen activeren. Combinaties hebben invloed op de UI.
Stel je hebt cartographer geactiveerd en je werkt met een gebied en je activeert illustrator

-zowel inspector als interface op de positie waar de actie gebeurd (hot item) is belangrijk.
Bij de hot item zullen opties staan gebaseerd op de current user mode.



-We moeten bezig om een interface te gaan ontwikkelen.


-stel je heb de apple bal bovenin. Klik voor lock en gebruik de hele breedte. Hier zou je ook graphs etc. kunnen laten zien.
klik nog een keer op de naam in de apple balk om te sluiten.

-zij balken zoals in intelliJ

-application moet over meerdere schermen kunnen. Ook b.v. op 3 schemen waarbij 1 fullscreen en bij 2 splitscreen.

-OS, operating system dat zo min mogelijk van het oorspronkelijke OS gebruikt. Dit met finder etc.
Dit alles uitgerust met dezelfde advancedheid en simpelheid als de rest van de programmas.

-non gui uodate mode voor elk scherm om cpu cycles en gpu cycles te besparen. (dit moet in de OS)

-het moet makkelijk zijn om een zijtab te maken zoals in intelliJ maar dan met eigen items zoals een run button,
een paar tools voor quick acces. Wat programming snippets. Clipboard view. etc.

-


100 zinlose dingen die simpel moeten zijn:
-lijn numbers met 0 prefix b.v. 0012
-icoontje van elke tab veranderen
-animate window size
-

1 ding wat niet moet kunnen
-makkelijk kwaad doen door een plug in of iets dergelijks!
-misschien moet een plug in permissions hebben die de user kan accpeteren. Zoals chrome plugins.
-

1 ding wat nog simpeler moet zijn:
- 1 of meer van de 100 zinlose dingen ongedaan maken (geen linux crap)






}


// // ain't it faster to have a list of indexes? (can be horrible for cache!)
// as in, is it more memory efficient to have a list of indexes instead of a type of T


// is there a program to watch how memory is layed out (would it be hard with jai?)




// Werknaam voor programma dat draaid op ONOS...

// Interface met node based system moet gelijk UML class diagram
// weergave zijn! (/ tot zich beschikken in het programmma)




// packer:

todo
        thinking out loud:

        -this is a data structure!!

        Maybe the QuadTree node should be even more abstract so we
        can use it here?

        -Setting algorithms like first fit decreasing is more something for the shell?

