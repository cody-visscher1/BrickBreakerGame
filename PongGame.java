import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame extends JPanel implements KeyListener, ActionListener, MouseMotionListener {
    private boolean play = false;
    private Timer timer;
    private int delay = 8;
    private int playerX = 310;
    private int compX = 310;
    private Ball ball;

    public PongGame() {
        ball = new Ball(20,20);
        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    private int direction(int a) {
        if(a >= 0) {
            return 1;
        }
        else
            return -1;
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692, 592);
        // Adding player and AI paddles
        g.setColor(Color.RED);
        g.fillRect(playerX, 550, 100, 8);
        g.fillRect(compX, 42, 100, 8);

        //Adding ball
        g.setColor(ball.getColor());
        g.fillOval((int) ball.getPosX(), (int) ball.getPosY(), ball.getWidth(), ball.getHeight());

        compX = (int)ball.getPosX();
        //TODO: add win condition
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play) {
            if(new Rectangle((int) (ball.getPosX() + ball.getWidth() / 2), (int) ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle(playerX, 550, 100, 8)))
            {
                ball.setYDir(-1 * (ball.getYDir()));
                double center = playerX + 50;
                double adjustment = (center - ball.getPosX()) * 0.05;
                if (Math.abs(adjustment) < 3.01) {
                    ball.setXDir(-1 * adjustment);
                }
                else {
                    ball.setXDir(-3 * ball.getXDir());
                }
            }
        }

        if(new Rectangle((int) (ball.getPosX() + ball.getWidth() / 2), (int) ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle(compX, 42, 100, 8))){
            ball.setYDir(-1 * (ball.getYDir()));
            double center = compX + 50;
            double adjustment = (center - ball.getPosX()) * 0.05;
            if(Math.abs(adjustment) < 3.01) {
                ball.setXDir(-1 * adjustment);
            }
            else {
                ball.setXDir(-3 * ball.getXDir());
            }
        }
        // Changes the direction of the ball
        ball.setPosX(ball.getPosX() + ball.getXDir());
        ball.setPosY(ball.getPosY() + ball.getYDir());
        if(ball.getPosX() < 0) {
            ball.setXDir(-1 * (ball.getXDir()));
        }
        if(ball.getPosY() > 670) {
            ball.setYDir(-1 * (ball.getXDir()));
        }
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerX >= 600) {
                playerX = 600;
            }
            else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(playerX < 10) {
                playerX = 10;
            }
            else moveLeft();
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            ball = new Ball();
            ball.setPosX(120);
            ball.setPosY(350);
            ball.setXDir(-1);
            ball.setYDir(-2);
            playerX = 310;
            repaint();
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(playerX >= 580) {
            playerX = 580;
        }
        else {
            play = true;
            playerX = e.getX()-50;
        }
        if(playerX < 0) {
            playerX = 0;
        }
        else {
            play = true;
            playerX = e.getX()-50;
        }
    }
}
