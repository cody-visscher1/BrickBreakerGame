import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame extends JPanel implements KeyListener, ActionListener, MouseMotionListener {
    private boolean play = false;
    private Timer timer;
    private int delay = 8;
    private int playerX = 310;
    private double compX = 310;
    private Rectangle compGoal;
    private Rectangle playerGoal;
    private int playerScore;
    private int compScore;
    private double difficulty;
    private Ball ball;

    /**
     * Base Constructor
     */
    public PongGame() {
        ball = new Ball(20,20);
        ball.setPosY(500);
        ball.setXDir(-1);
        ball.setYDir(-3);
        compScore = 0;
        playerScore = 0;
        compGoal = new Rectangle(245, 2,200,5);
        playerGoal = new Rectangle(245, 556, 200, 5);
        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        difficulty = 0.75;
        timer = new Timer(delay, this);
        timer.start();
    }

    /**
     * Constructor that allows the user to select the difficulty
     *
     * This should be implemented later, but is not ready yet with driver.
     * @param difficulty the amount that the paddle moves per iteration
     */
    public PongGame(double difficulty) {
        super();
        this.difficulty = difficulty;
    }

    /**
     * Paints the panel for every iteration.
     * @param g the graphics that are being used to paint the panel
     */
    public void paint(Graphics g) {
        // Painting the background of the panel
        g.setColor(Color.BLACK); // sets the color that we are working with
        g.fillRect(1,1,692, 592); // creates a rectangle and fill sit with the color set to g
        // Adding player and AI paddles
        g.setColor(Color.RED);
        g.fillRect(playerX, 535, 100, 8);
        g.fillRect((int)compX, 20, 100, 8);
        // Adding player and AI Goals
        g.setColor(Color.BLUE);
        g.fillRect(compGoal.x, compGoal.y, compGoal.width, compGoal.height);
        g.fillRect(playerGoal.x, playerGoal.y, playerGoal.width, playerGoal.height);


        //Adding ball
        g.setColor(ball.getColor());
        g.fillOval((int) ball.getPosX(), (int) ball.getPosY(), ball.getWidth(), ball.getHeight());
        if(ball.getPosX() - 50 > compX) // adjusting the position of the computer paddle to make sure that it is always moving in the right direction.
            compX += difficulty;
        else if(ball.getPosX() -50 < compX) {
            compX -= difficulty;
        }
        // Player win condition
        if(playerScore == 5) {
            ball.setPosY(-30);
            ball.setXDir(0);
            ball.setYDir(0);
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU WIN",220,300);
            g.drawString("Press Enter to Restart", 190, 340);
        }

        // Computer win condition
        if(compScore == 5) {
            ball.setPosY(-30);
            ball.setXDir(0);
            ball.setYDir(0);
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU LOSE",220,300);
            g.drawString("Press Enter to Restart", 190, 340);
        }
        g.dispose();
    }

    /**
     * Checks for intersections between the ball and anything other object on the panel,
     * as well as intersections between the ball and the edges of the panel.
     * @param e an event that occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play) {
            // Checking to see if the ball intersects with the player's paddle
            if (new Rectangle((int) (ball.getPosX() + ball.getWidth() / 2), (int) ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle(playerX, 535, 100, 8))) {
                ball.setYDir(-1 * (ball.getYDir()));
                double center = playerX + 50;
                // Creating an adjustment to the x-axis direction of the ball.
                double adjustment = (center - ball.getPosX()) * 0.05;
                // Checking to make sure that the ball's x-axis direction is not moving too fast
                if (Math.abs(adjustment) < 3.01) {
                    ball.setXDir(-1 * adjustment);
                } else {
                    ball.setXDir(-3 * ball.getXDir());
                }
            }

            // Checking to see if the ball intersects with the Computer's goal
            if(new Rectangle((int) (ball.getPosX() + ball.getWidth() / 2), (int) ball.getPosY(), 1, ball.getHeight()).intersects(compGoal)) {
                playerScore++; // adjust playerScore by one
                ball.setPosX(200); // Reset ball position to give the computer time to adjust.
                ball.setPosY(500);
                ball.setXDir(0); // The ball moves in a straight line, so that both players can have a chance to hit
                ball.setYDir(-3);
            }

            // Checking to see if the ball intersects with the Player's goal
            if(new Rectangle((int) (ball.getPosX() + ball.getWidth() / 2), (int) ball.getPosY(), 1, ball.getHeight()).intersects(playerGoal)) {
                compScore++;
                ball.setPosX(200);
                ball.setPosY(50);
                ball.setXDir(0);
                ball.setYDir(3); // Sets the direction of the ball towards the player so they have a chance to hit first.
            }

            // Checking to see if the ball intersects with the AI's paddle
            if (new Rectangle((int) (ball.getPosX() + ball.getWidth() / 2), (int) ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle((int) compX, 20, 100, 8))) {
                ball.setYDir(-1 * (ball.getYDir()));
                // Same adjustment technique as for when the ball intersects with the user paddle.
                double center = compX + 50;
                double adjustment = (center - ball.getPosX()) * 0.05;
                if (Math.abs(adjustment) < 3.01) {
                    ball.setXDir(-1 * adjustment);
                } else {
                    ball.setXDir(-3 * ball.getXDir());
                }
            }
            // Changes the direction of the ball
            ball.setPosX(ball.getPosX() + ball.getXDir());
            ball.setPosY(ball.getPosY() + ball.getYDir());
            // Checks to see if the ball is at the edge of the frame
            if (ball.getPosX() < 0) {
                ball.setXDir(-1 * (ball.getXDir()));
            }
            if (ball.getPosY() > 550) {
                ball.setYDir(-1 * (ball.getYDir()));
            }
            if (ball.getPosY() < 0) {
                ball.setYDir(-1 * ball.getYDir());
            }
            if (ball.getPosX() > 670) {
                ball.setXDir(-1 * ball.getXDir());
            }
        }
        repaint(); // Draws the updated version of the frame per iteration
    }

    /**
     * Checks to see if a key is typed, and then updates the game
     * to respond correctly.
     * @param e a key being typed by the user.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) { // If the key pressed is the right-arrow key
            if(playerX >= 600) { // Makes sure that the player cannot move the paddle out of the panel
                playerX = 600;
            }
            else {
                moveRight(); // adjusts the position of the paddle by 20
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) { // If the key pressed is the left-arrow key
            if(playerX < 1) { // same as above comments
                playerX = 1;
            }
            else moveLeft();
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) { // If the key pressed is the enter key
            ball = new Ball(); // resets the ball
            ball.setPosX(120);
            ball.setPosY(350);
            ball.setXDir(-1);
            ball.setYDir(-2);
            playerX = 310; // Resets the player and computer x positions
            compX = 310;
            repaint();
        }
    }

    /**
     * adjusts the user's paddle to the right
     */
    public void moveRight() {
        play = true;
        playerX += 20;
    }

    /**
     * adjusts the user's paddle to the left
     */
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

    /**
     * Checks for mouse movement, and then adjusts the players paddle
     * to follow the x-axis position of the mouse.
     * @param e the mouse moving on the panel.
     */
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

    // Getter and setters, that are unused.
    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getCompScore() {
        return compScore;
    }

    public void setCompScore(int compScore) {
        this.compScore = compScore;
    }
}
