import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that randomizes the bricks, and allows for the program
 * to paint the bricks on the screen.
 */
public class Generator {

    /**
     * The array list that stores the location of each brick.
     */
    private double[][] map;

    /**
     * The width of each brick.
     */
    private static int brickWidth;

    /**
     * the height of each brick.
     */
    private static int brickHeight;

    /**
     * A list that stores the color of each brick.
     */
    private ArrayList<Brick> brickArray = new ArrayList<Brick>();

    /**
     * Allows the user to access the map from other methods.
     *
     * @return the map that is stored locally.
     */
    public double[][] getMap() {
        return this.map;
    }

    /**
     * Allows the user to set the map from other methods.
     *
     * @param map - what the user wants the map to be set to.
     */
    public void setMap(final double[][] map) {
        this.map = map;
    }

    /**
     * Allows the user to access the brickWidth from other methods.
     *
     * @return the width of the bricks.
     */
    public static int getBrickWidth() {
        return brickWidth;
    }

    /**
     * Allows the user to set the brick width from other methods.
     *
     * @param value - the value that the user wants to set the width
     *              of the bricks to.
     */
    public static void setBrickWidth(int value) {
        brickWidth = value;
    }

    /**
     * Allows the user to access the brick height from other methods.
     *
     * @return the brick height.
     */
    public static int getBrickHeight() {
        return brickHeight;
    }

    /**
     * Allows the user to set the brick height form other methods.
     *
     * @param value - the value that the user wants to set the height
     *              of the bricks to.
     */
    public static void setBrickHeight(int value) {
        brickHeight = value;
    }

    /**
     * Allows the user to access the ArrayList of bricks from other methods.
     *
     * @return the list of bricks and their colors.
     */
    public ArrayList<Brick> getBrickArray() {
        return this.brickArray;
    }

    /**
     * Allows the user to set the ArrayList of bricks from other methods.
     *
     * @param brickArray - the new list that the user wants to use to store
     *                   the bricks in.
     */
    public void setBrickArray(ArrayList<Brick> brickArray) {
        this.brickArray = brickArray;
    }

    /**
     * Constructor.
     *
     * @param row number of rows
     * @param col number of columns
     */
    public Generator(final int row, final int col){
        map = new double[row][col];
        for(double[] mmap : map){
            for(int j = 0; j < map[0].length; j++) {
                mmap[j] = 1;
            }
        }
        brickWidth = 540/col;
        brickHeight = 540/col;
        for(int i = 0; i < row * col; i++) {
            brickArray.add(new Brick());
        }

        Random rand = new Random();
        int t = rand.nextInt(200);
        brickArray.get(t).setColor(Color.ORANGE);
    }

    /**
     * Draws the array of bricks.
     *
     * @param g some object that needs to be drawn in the frame
     */
    public void draw(final Graphics2D g) {
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if (map[i][j] > 0) {
                    g.setColor(brickArray.get(i * map[0].length + j).getColor()); // this is essentially the same as doing brickArray[i][j]
                    // ArrayLists are only 1 dimensional, so you need to (i * number of columns + j) to receive the same output.
                    g.fillRect(j*brickWidth+80,i*brickHeight+50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
                }
            }
        }
    }

    /**
     * allows the user to clear the bricks from the map.
     */
    public void clearMap() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                setBrickValue(0, i, j);
            }
        }
    }


    /**
     * used to delete bricks.
     *
     * @param value only uses zero, but removes the brick from the frame
     * @param row the row of the brick that you want to remove
     * @param col the col of the brick that you want to remove
     */
    public void setBrickValue(final int value, final int row, final int col) {
        map[row][col] = value;
    }
}
