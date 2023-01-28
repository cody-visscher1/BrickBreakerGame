import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;


/**
 *
 */
public class Driver {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        BrickGame bg = new BrickGame();

        // Creates a blank cursor
        BufferedImage cursorImg = new BufferedImage(16,16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0,0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);


        frame.setBounds(10,10,700,600);
        frame.setTitle("Brick Breaker Game");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(bg);
    }
}
