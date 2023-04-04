
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

/**
 * A class that allows users to play a pong game.
 */
public class PongGame extends JPanel implements KeyListener, ActionListener, MouseMotionListener {

    /**
     * The boolean that determines if a player has scored.
     */
    private boolean scored = false;

    private Random random;

    /**
     * The boolean that determines whether the game is currently being played or not.
     */
    private boolean play = false;

    /**
     * The timer that updates the objects on the user's screen.
     */
    private Timer timer;

    /**
     * The delay of the timer.
     */
    private int delay = 8;

    /**
     * The x-axis position of the player's paddle.
     */
    private int playerX = 310;

    /**
     * The x-axis position of the computer's paddle.
     */
    private double compX = 310;

    /**
     * The Rectangle that stores the position of the computer's goal.
     */
    private Rectangle compGoal;

    /**
     * The Rectangle that stores the position of the player's goal.
     */
    private Rectangle playerGoal;

    /**
     * The player's score.
     */
    private int playerScore;

    /**
     * The computer's score.
     */
    private int compScore;

    /**
     * The difficulty of the game.
     */
    private double difficulty;

    /**
     * The ball that is being used in the game.
     */
    private Ball ball;

    /**
     * Base Constructor for pong game.
     */
    public PongGame() {
        ball = new Ball(20, 20);
        ball.setPosY(500);
        ball.setXDir(-1);
        ball.setYDir(-3);
        random = new Random();
        compScore = 0;
        playerScore = 0;
        compGoal = new Rectangle(245, 2, 200, 5);
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
     * Paints the panel for every iteration.
     *
     * @param g the graphics that are being used to paint the panel.
     */
    public void paint(Graphics g) {
        // Painting the background of the panel
        g.setColor(Color.BLACK); // sets the color that we are working with
        g.fillRect(1, 1, 692, 592); // creates a rectangle and fill sit with the color set to g
        // Adding player and AI paddles
        g.setColor(Color.RED);
        g.fillRect(playerX, 535, 100, 8);
        g.fillRect((int) compX, 20, 100, 8);
        // Adding player and AI Goals
        g.setColor(Color.BLUE);
        g.fillRect(compGoal.x, compGoal.y, compGoal.width, compGoal.height);
        g.fillRect(playerGoal.x, playerGoal.y, playerGoal.width, playerGoal.height);


        //Adding ball
        g.setColor(ball.getColor());
        g.fillOval((int) ball.getPosX(), (int) ball.getPosY(), ball.getWidth(), ball.getHeight());
        if (ball.getPosX() - 50 > compX) { // adjusting the position of the computer paddle to make sure that it is always moving in the right direction.
            compX += difficulty;
        } else if (ball.getPosX() - 50 < compX) {
            compX -= difficulty;
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("USER: " + playerScore, 20, 20);
        g.drawString("COMP: " + compScore, 610, 20);
        // Player win condition
        if (playerScore == 5) {
            play = false;
            ball.setPosY(-30);
            ball.setXDir(0);
            ball.setYDir(0);
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU WIN", 220, 300);
            g.drawString("Press Enter to Restart", 190, 340);
        }

        // Computer win condition
        if (compScore == 5) {
            play = false;
            ball.setPosY(-30);
            ball.setXDir(0);
            ball.setYDir(0);
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU LOSE", 220, 300);
            g.drawString("Press Enter to Restart", 190, 340);
        }

        g.dispose();
    }

    /**
     * Checks for intersections between the ball and anything other object on the panel,
     * as well as intersections between the ball and the edges of the panel.
     *
     * @param e an event that occurs.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        timer.start();
        if (play) {
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
            if (new Rectangle((int) (ball.getPosX() + ball.getWidth() / 2), (int) ball.getPosY(), 1, ball.getHeight()).intersects(compGoal)) {
                playerScore++; // adjust playerScore by one
                ball.setPosX(random.nextInt(200) + 200); // Reset ball position to give the computer time to adjust.
                ball.setPosY(500);
                ball.setXDir(0); // The ball moves in a straight line, so that both players can have a chance to hit
                ball.setYDir(-3);
                scored = true;
            }

            // Checking to see if the ball intersects with the Player's goal
            if (new Rectangle((int) (ball.getPosX() + ball.getWidth() / 2), (int) ball.getPosY(), 1, ball.getHeight()).intersects(playerGoal)) {
                compScore++;
                ball.setPosX(200);
                ball.setPosY(50);
                ball.setXDir(0);
                ball.setYDir(3); // Sets the direction of the ball towards the player so they have a chance to hit first.
                scored = true;
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
     * unused method.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(final KeyEvent e) {

    }

    /**
     * adjusts the user's paddle to the right.
     */
    public void moveRight() {
        play = true;
        playerX += 20;
    }

    /**
     * adjusts the user's paddle to the left.
     */
    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

    /**
     * Checks to see if a key is typed, and then updates the game
     * to respond correctly.
     *
     * @param e a key being typed by the user.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // If the key pressed is the right-arrow key
            if (playerX >= 600) { // Makes sure that the player cannot move the paddle out of the panel
                playerX = 600;
            } else {
                moveRight(); // adjusts the position of the paddle by 20
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { // If the key pressed is the left-arrow key
            if (playerX < 1) { // same as above comments
                playerX = 1;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) { // If the key pressed is the enter key
            if (playerScore == 5 || compScore == 5) {
                ball = new Ball(); // resets the ball
                ball.setPosX(120);
                ball.setPosY(350);
                ball.setXDir(-1);
                ball.setYDir(-2);
                playerX = 310; // Resets the player and computer x positions
                compX = 310;
                playerScore = 0;
                compScore = 0;
                repaint();
            }
            else {
                return;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Driver.kill();
            Driver.main(null);
        }

        else {
            scored = false;
            play = true;
        }
    }

    /**
     * unused method.
     *
     * @param e the event to be processed.
     */
    @Override
    public void keyReleased(final KeyEvent e) {

    }

    /**
     * unused method.
     *
     * @param e the event to be processed.
     */
    @Override
    public void mouseDragged(final MouseEvent e) {

    }

    /**
     * Checks for mouse movement, and then adjusts the players paddle
     * to follow the x-axis position of the mouse.
     *
     * @param e the mouse moving on the panel.
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        if (playerX >= 580) {
            playerX = 580;
        } else {
            play = true;
            playerX = e.getX() - 50;
        }
        if (playerX < 0) {
            playerX = 0;
        } else {
            play = true;
            playerX = e.getX() - 50;
        }
    }

    /**
     * Allows the user to retrieve the player's
     * score.
     *
     * @return the value stored in the instance
     * variable player score.
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Allows the user to set the player's score.
     *
     * @param playerScore - the value that the
     *                    instance variable
     *                    playerScore will be set to.
     */
    public void setPlayerScore(final int playerScore) {
        this.playerScore = playerScore;
    }

    /**
     * Allows the user to retrieve the computer's score.
     *
     * @return the value stored in the instance variable
     * compScore.
     */
    public int getCompScore() {
        return compScore;
    }

    /**
     * Allows the user to set the computer's score.
     *
     * @param compScore - the value that the instance
     *                  variable compScore will be set
     *                  to.
     */
    public void setCompScore(final int compScore) {
        this.compScore = compScore;
    }

    /**
     * Allows the user to access the boolean play in other methods.
     *
     * @return the value stored by the instance variable play.
     */
    public boolean getPlay() {
        return this.play;
    }

    /**
     * Allows the user to set the boolean play in other methods.
     *
     * @param play - the true or false value that play should be set to.
     */
    public void setPlay(final boolean play) {
        this.play = play;
    }

    /**
     * Allows the user to access the Timer that is being used to update
     * the GUI from other methods.
     *
     * @return the Timer that is being used to update the GUI.
     */
    public Timer getTimer() {
        return this.timer;
    }

    /**
     * Allows the user to set the Timer in other methods.
     *
     * @param timer - the timer that the instance variable timer is being
     *              set to.
     */
    public void setTimer(final Timer timer) {
        this.timer = timer;
    }

    /**
     * Allows the user to access the delay of the timer that is being used
     * in the Timer.
     *
     * @return the delay of the timer.
     */
    public int getDelay() {
        return this.delay;
    }

    /**
     * Allows the user to set the delay of the timer from other methods.
     *
     * @param delay - the value that the user wants to set the delay of the
     *              timer to.
     */
    public void setDelay(final int delay) {
        this.delay = delay;
    }

    /**
     * Allows the user to access the x-axis position of the player's paddle.
     *
     * @return the x-axis position of the player's paddle.
     */
    public int getPlayerX() {
        return this.playerX;
    }

    /**
     * Allows the user to set the x-axis position of the player's paddle.
     *
     * @param playerX - the value that the user wants to set the player's
     *                x-axis position.
     */
    public void setPlayerX(final int playerX) {
        this.playerX = playerX;
    }

    /**
     * Allows the user to access the x-axis position of the computer's paddle.
     *
     * @return the x-axis position of the computer's paddle.
     */
    public double getCompX() {
        return this.compX;
    }

    /**
     * Allows the user to set the x-axis position of the computer's paddle.
     *
     * @param compX - the value that the user wants to set the computer's
     *              x-axis position.
     */
    public void setCompX(final double compX) {
        this.compX = compX;
    }

    /**
     * Allows the user to access the variable that stores the Rectangle
     * that is being used to store the coordinates of the computer's goal.
     *
     * @return the coordinates of the computer's goal.
     */
    public Rectangle getCompGoal() {
        return this.compGoal;
    }

    /**
     * Allows the user to set the variable that stores the Rectangle
     * that is being used to store the coordinates of the computer's goal.
     *
     * @param compGoal - the Rectangle that the user wants to set the computer's
     *                 goal to.
     */
    public void setCompGoal(final Rectangle compGoal) {
        this.compGoal = compGoal;
    }

    /**
     * Allows the user to access the Rectangle variable that stores the
     * coordinates of the player's goal.
     *
     * @return the Rectangle holding the coordinates of the player's goal.
     */
    public Rectangle getPlayerGoal() {
        return this.playerGoal;
    }

    /**
     * Allows the user to change the coordinates of the player's goal.
     *
     * @param playerGoal - the Rectangle that the user wants the player's
     *                   goal to be set to.
     */
    public void setPlayerGoal(final Rectangle playerGoal) {
        this.playerGoal = playerGoal;
    }

    /**
     * Allows the user to access the variable that stores the difficulty,
     * which updates how much the computer's paddle increments by each
     * iteration.
     *
     * @return the difficulty of the game.
     */
    public double getDifficulty() {
        return this.difficulty;
    }

    /**
     * Allows the user to set the variable that stores the difficulty of the game.
     *
     * @param difficulty - the amount that the user wants the computer's paddle
     *                   to increment by during each iteration.
     */
    public void setDifficulty(final double difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Allows the user to access the object that stores the ball being used to play
     * the game.
     *
     * @return the ball that is being used to play the game.
     */
    public Ball getBall() {
        return this.ball;
    }

    /**
     * Allows the user to set the object that stores the ball being used to play
     * the game.
     *
     * @param ball - the Ball that the object should be set to.
     */
    public void setBall(final Ball ball) {
        this.ball = ball;
    }

    /**
     * Allows the user to access the scored variable from other methods.
     *
     * @return if a player has scored or not.
     */
    public boolean getScored() {
        return this.scored;
    }

    /**
     * Allows the user to set the boolean that stores if a player has scored
     * or not.
     *
     * @param scored - the boolean that determines if a player has scored.
     */
    public void setScored(boolean scored) {
        this.scored = scored;
    }
}
