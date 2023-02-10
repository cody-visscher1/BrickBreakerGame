import java.awt.*;

public class Ball {
    private double posX;
    private double posY;
    private double xDir;
    private double yDir;
    private int width;
    private int height;
    private Color color;

    public Ball () {
        color = Color.GREEN;
        height = 13;
        width = 13;
        yDir = -2;
        xDir = 1;
        posY = 350;
        posX = 120;
    }

    public Ball(double xDir, double yDir, double posX, double posY) {
        color = Color.GREEN;
        height = 13;
        width = 13;
        this.yDir = yDir;
        this.xDir = xDir;
        this.posX = posX;
        this.posY = posY;
    }

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
