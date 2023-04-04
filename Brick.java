import java.awt.Color;
import java.util.Random;

/**
 * Class that is used to store the colors of the bricks.
 *
 * @author codyv, jacobshoe, brownky
 */
public class Brick {
    /**
     * The random object that allows the program to randomize the colors of the bricks.
     */
    private Random rand = new Random();

    /**
     * The color of each brick that determines that bricks power up.
     */
    private Color c;

    /**
     * Base constructor for Bricks
     * colors are to hold power-ups.
     */
    public Brick() {
        int color = rand.nextInt(100);
        if (color < 90) {
            c = Color.WHITE;
        } else if (color < 95) {
            c = Color.MAGENTA;
        } else {
            c = Color.BLUE;
        }
    }

    /**
     * Allows the user to access the brick's color from other methods.
     *
     * @return the color of the brick.
     */
    public Color getColor() {
        return c;
    }

    /**
     * Allows the user to set the brick's color from other methods.
     *
     * @param c - the color that the brick is being set to.
     */
    public void setColor(final Color c) {
        this.c = c;
    }

    /**
     * Allows the user to access the Random objects from other methods.
     *
     * @return the random object.
     */
    public Random getRand() {
        return this.rand;
    }

    /**
     * Allows the user to set the Random object from other methods.
     *
     * @param rand - the Random object that will be used to determine
     *             the colors of the bricks.
     */
    public void setRand(final Random rand) {
        this.rand = rand;
    }
}
