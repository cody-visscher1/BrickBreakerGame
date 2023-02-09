import java.awt.*;
import java.util.ArrayList;

public class Generator {
    public double[][] map;
    public int brickWidth;
    public int brickHeight;
    private ArrayList<Brick> brickArray = new ArrayList<Brick>();
    public Generator(int row, int col){
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
    }

    /**
     * Draws the array of bricks
     * @param g some object that needs to be drawn in the frame
     */
    public void draw(Graphics2D g) {
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
     * used to delete bricks
     * @param value only uses zero, but removes the brick from the frame
     * @param row the row of the brick that you want to remove
     * @param col the col of the brick that you want to remove
     */
    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
