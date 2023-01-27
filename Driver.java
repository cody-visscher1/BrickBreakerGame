import javax.swing.*;


/**
 *
 */
public class Driver {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        BrickGame bg = new BrickGame();
        frame.setBounds(10,10,700,600);
        frame.setTitle("Brick Breaker Game");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(bg);
    }
}
