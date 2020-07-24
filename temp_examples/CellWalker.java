package orb_examples;

import orb.crust.geom.V2;
import orb.____inner_core.geom._Vec2;
import org.problessing.Problessing;
import processing.core.PImage;

/**
 * Created by doekewartena on 9/16/15.
 */
public class CellWalker extends Problessing {

    PImage img;

    Walker walker;

    P RIGHT = new P(1, 0);
    P DOWN = new P(0, 1);
    P LEFT = new P(-1, 0);
    P UP = new P(0, -1);

    P LEFT_DOWN = new P(-1, 1);



    @Override
    public void settings() {
        size(512, 512);
        noSmooth();
    }

    @Override
    public void setup() {
        img = createImage(32, 32, RGB);
        img.loadPixels();
        img.set(16, 16, white);
        img.updatePixels();

        walker = new Walker();

//        walker.x = 16;
//        walker.y = 16;
        walker.pos = new P(16, 15); // one above the one that was set
        walker.dir = RIGHT;// new P(1, 0); // to the right
        walker.checkDir = LEFT_DOWN;

        frameRate(1);
    }

    @Override
    public void draw() {
        //background(black);
        //stroke(white);
        image(img, 0, 0, width, height);
        walker.walk();

    }

    class Walker {

        P pos;
        P dir; // = new P(1, 0);
        P checkDir; // = new P(0, 1);

        public void walk() {

            //P nextPos = new P().add(pos).add(dir);

            // check if nextPos is valid..
            // ...
            P nextPos = new P();
            nextPos.add(pos).add(dir);
            P checkPos = new P();
            checkPos.add(nextPos).add(checkDir);

            if (img.pixels[index(nextPos)] == black) {
                // check if we still walk close
                if (img.pixels[index(checkPos)] == white) {
                    // good to go!
                    img.set(nextPos.x, nextPos.y, white);
                    pos = nextPos;
                }
                else {
                    println("checkPos not white");
                    // change direction

                }
            }
            else {
               println("nextPos not black");
            }




           // img.set(checkPos.x, checkPos.y, red);





            //img.set(pos.x++, pos.y, white);


            img.updatePixels();
        }

        int index(P p) {
            return p.x + p.y * img.width;
        }


    }

    /*
    right
    down
    left
    up

    [ ][ ][ ][ ][ ][ ][ ]
    [ ][ ][ ][ ][ ][ ][ ]
    [ ][ ][ ][S][ ][ ][ ]
    [ ][ ][ ][X][ ][ ][ ]
    [ ][ ][ ][ ][ ][ ][ ]
    [ ][ ][ ][ ][ ][ ][ ]
    [ ][ ][ ][ ][ ][ ][ ]

     */

    class P implements _Vec2<P> {

        int x, y;

        P() {}

        P(int x, int y) {
            set(x, y);
        }

        @Override
        public float x() {
            return x;
        }

        @Override
        public float y() {
            return y;
        }

        @Override
        public P set(float x, float y) {
            this.x = (int)x;
            this.y = (int)y;
            return this;
        }
    }


    public static void main(String[] args) {
        Problessing.main("CellWalker", args);
    }

}
