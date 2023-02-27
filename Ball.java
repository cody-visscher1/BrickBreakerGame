import java.awt.*;

public class Ball {
    private double posX;
    private double posY;
    private double xDir;
    private double yDir;
    private int width;
    private int height;
    private Color color;

    /**
     * Base Constructor for balls
     */
    public Ball () {
        color = Color.GREEN;
        height = Generator.brickHeight - 5;
        width = Generator.brickWidth - 5;
        yDir = -2;
        xDir = 1;
        posY = 350;
        posX = 120;
    }

    /**
     * Specified Constructor for balls
     * @param xDir the horizontal direction that the ball should move
     * @param yDir the vertical direction that the ball should move
     * @param posX the position of the ball on the x-axis
     * @param posY the position of the ball on the y-axis
     */
    public Ball(double xDir, double yDir, double posX, double posY) {
        color = Color.GREEN;
        height = Generator.brickWidth - 5;
        width = Generator.brickWidth - 5;
        this.yDir = yDir;
        this.xDir = xDir;
        this.posX = posX;
        this.posY = posY;
    }

    public Ball(int height, int width) {
        this.height = height;
        this.width = width;
        color = Color.GREEN;
        this.yDir = -2;
        this.xDir = -1;
        this.posX = 120;
        this.posY = 350;
    }

    /**
     * Getter and setter methods below
     * @return the value of each variable stored within the ball.
     */
    public double getPosX() {
        return posX;
    }

    public void setPosX(double PosX) {
        this.posX = PosX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double PosY) {
        this.posY = PosY;
    }

    public double getXDir() {
        return xDir;
    }

    public void setXDir(double XDir) {
        this.xDir = XDir;
    }

    public double getYDir() {
        return yDir;
    }

    public void setYDir(double YDir) {
        this.yDir = YDir;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color Color) {
        this.color = Color;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int Height) {
        this.height = Height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int Width) {
        this.width = Width;
    }
}
