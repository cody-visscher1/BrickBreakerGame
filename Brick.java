import java.awt.Color;
import java.util.Random;

public class Brick {
    private Random rand = new Random();
    private Color c;

    /**
     * Base constructor for Bricks
     * colors are to hold power-ups.
     */
    public Brick() {
        int color = rand.nextInt(100);
        if(color < 90)
            c = Color.WHITE;
        else if(color < 95)
            c = Color.MAGENTA;
        else
            c = Color.BLUE;
    }

    public Brick(Color c) {
        this.c = c;
    }

    public Color getColor() {
        return c;
    }

    public void setColor(Color c) {
        this.c = c;
    }
}
