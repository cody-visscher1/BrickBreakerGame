import java.awt.*;

public class Ball {
    private int posX;
    private int posY;
    private int xDir;
    private int yDir;
    private int width;
    private int height;
    private Color color;

    public Ball () {
        color = Color.GREEN;
        height = 20;
        width = 20;
        yDir = -2;
        xDir = -1;
        posY = 350;
        posX = 120;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int PosX) {
        this.posX = PosX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int PosY) {
        this.posY = PosY;
    }

    public int getXDir() {
        return xDir;
    }

    public void setXDir(int XDir) {
        this.xDir = XDir;
    }

    public int getYDir() {
        return yDir;
    }

    public void setYDir(int YDir) {
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
