import java.awt.Color;

/**
 * Class that allows the program to store the position, color, and directions of the ball.
 *
 * @author codyv, jacobshoe, brownky
 * @version 1.0.0
 */
public class Ball {

    /**
     * The ball's x-axis position.
     */
    private double posX;

    /**
     * The ball's y-axis position.
     */
    private double posY;

    /**
     * The ball's x-axis direction.
     */
    private double xDir;

    /**
     * The ball's y-axis direction.
     */
    private double yDir;

    /**
     * The ball's width.
     */
    private int width;

    /**
     * The ball's height.
     */
    private int height;

    /**
     * The ball's color.
     */
    private Color color;

    /**
     * Base Constructor for balls.
     */
    public Ball () {
        color = Color.GREEN;
        height = Generator.getBrickHeight() - 5;
        width = Generator.getBrickWidth() - 5;
        yDir = -2;
        xDir = 1;
        posY = 350;
        posX = 120;
    }

    /**
     * Allows the user to construct a ball with a specific x-axis and y-axis position
     *
     * @param game - if the game is running
     * @param posX - the x-axis position of the ball
     * @param posY - the y-axis position of the ball
     */
    public Ball(final boolean game, final int posX, final int posY) {
        super();
        if(game) {
            this.posX = posX;
            this.posY = posY;
        }
    }

    /**
     * Specified Constructor for balls.
     *
     * @param xDir the horizontal direction that the ball should move.
     * @param yDir the vertical direction that the ball should move.
     * @param posX the position of the ball on the x-axis.
     * @param posY the position of the ball on the y-axis.
     */
    public Ball(final double xDir, final double yDir, final double posX, final double posY) {
        super();
        this.yDir = yDir;
        this.xDir = xDir;
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Constructor for balls that allows users to set a specified
     * height and width of the ball.
     *
     * @param height - the height of the ball.
     * @param width - the width of the ball.
     */
    public Ball(final int height, final int width) {
        super();
        this.height = height;
        this.width = width;
    }

    /**
     * Allows the user to access the x-axis position of the ball
     * from other methods.
     *
     * @return the x-axis position of the ball.
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Allows the user to set the x-axis position of the ball.
     *
     * @param posX - the value that the user wants to set the
     *             x-axis position of the ball to.
     */
    public void setPosX(final double posX) {
        this.posX = posX;
    }

    /**
     * Allows the user to access the y-axis position of the ball
     * from other methods.
     *
     * @return the y-axis position of the ball.
     */
    public double getPosY() {
        return posY;
    }

    /**
     * Allows the user to set the y-axis position of the ball.
     *
     * @param posY - the value that the user wants to set the
     *             y-axis position of the ball to.
     */
    public void setPosY(final double posY) {
        this.posY = posY;
    }

    /**
     * Allows the user to access the x-axis direction of the ball
     * from other methods.
     *
     * @return the x-axis direction of the ball.
     */
    public double getXDir() {
        return xDir;
    }

    /**
     * Allows the user to set the x-axis direction of the ball.
     *
     * @param xDir - the value that the user wants to set the
     *             x-axis direction of the ball to.
     */
    public void setXDir(final double xDir) {
        this.xDir = xDir;
    }

    /**
     * Allows the user to access the y-axis direction of the ball
     * from other methods.
     *
     * @return the y-axis direction of the ball.
     */
    public double getYDir() {
        return yDir;
    }

    /**
     * Allows the user to set the y-axis direction of the ball
     * from other methods.
     *
     * @param yDir - the value that the user wants to set the
     *             y-axis direction of the ball to.
     */
    public void setYDir(final double yDir) {
        this.yDir = yDir;
    }

    /**
     * Allows the user to access the color of the ball from
     * other methods.
     *
     * @return the ball's color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Allows the user to set the color of the ball from
     * other methods.
     *
     * @param color - the color the ball is being set to.
     */
    public void setColor(final Color color) {
        this.color = color;
    }

    /**
     * Allows the user to access the height of the ball from other methods.
     *
     * @return the height of the ball.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Allows the user to set the height of the ball from other methods.
     *
     * @param height - the value that the user wants the ball's height
     *               to be.
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Allows the user to access the width of the ball from other methods.
     *
     * @return the width of the ball.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Allows the user to set the width of the ball from other methods.
     *
     * @param width - the value that the user wants the ball's width to be.
     */
    public void setWidth(final int width) {
        this.width = width;
    }
}
