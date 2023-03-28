
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.event.WindowEvent;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Holds the main method for the user
 * to run the program.
 */
public class Driver {

    private static JFrame frame;

    /**
     * Allows the user to play the game.
     *
     * @param args - any command line arguments
     *             that should be used for the
     *             operation of the program.
     */
    public static void main(final String[] args){
        frame = new JFrame();
        frame.setTitle("Selection Screen");
        BrickGame bg = new BrickGame();
        PongGame pg = new PongGame();
        JPanel panel = new JPanel();
        frame.setName("Selection Screen");
        frame.setBounds(100, 100, 350, 300);
        panel.setBounds(100, 100, 350, 300);
        JButton brickbutton = new JButton("Play Brick Game");
        JButton pongbutton = new JButton("Play Pong");
        panel.add(brickbutton);
        panel.add(pongbutton);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);
        brickbutton.setBounds(40, 40, 50, 50);
        pongbutton.setBounds(100,100,50,50);
        brickbutton.setVisible(true);
        pongbutton.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        brickbutton.addActionListener(e -> {
            frame.setVisible(false);
            panel.setVisible(false);
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
        });
        pongbutton.addActionListener(e -> {
            frame.setVisible(false);
            panel.setVisible(false);

            BufferedImage cursorImg = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0,0), "blank cursor");
            frame.getContentPane().setCursor(blankCursor);

            frame.setBounds(10,10,700,600);
            frame.setTitle("Pong Game");
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(pg);
        });
    }

    public static void kill() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}