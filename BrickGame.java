import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/** Creates an object that allows the user to play a brick-breaker game.
 *
 * @author codyv, jacobshoe, brownky
 *
 * @version 1.0.0
 */
public class BrickGame extends JPanel implements KeyListener, ActionListener, MouseMotionListener {

    /**
     * The boolean that determines whether the game is currently
     * being played or not.
     */
    private boolean play = false;

    /**
     * The player's current score.
     */
    private int score = 0;

    /**
     * The number of bricks that can be hit before
     * the win condition is invoked.
     */
    private int totalBricks = 200;

    /**
     * The timer that is being used to update
     * the objects on the screen.
     */
    private Timer timer;

    /**
     * The delay of the timer.
     */
    private int delay = 8;

    /**
     * The paddle's current x-axis position.
     */
    private int playerX = 310;

    /**
     * The object that stores the bricks
     * and their locations.
     */
    private Generator map;

    /**
     * The object that stores all the ball's
     * that have been produced as a result of
     * power-ups.
     */
    private ArrayList<Ball> ballList = new ArrayList<>();

    /**
     * Used to determine if the player's score
     * should continue to increase, or if it
     * should be reset to zero.
     */
    private boolean win = false;

    /**
     * Creates an object of the BrickGame class in order to have
     * an executable brick-breaker game.
     */
    public BrickGame() {
        map = new Generator(10,20);
        ballList.add(new Ball());
        addKeyListener(this); // allows us to use keys for moving the paddle
        addMouseMotionListener(this); // allows us to use the mouse to move the paddle.
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    /**
     * Shows all the objects that are in the frame,
     * also used to check win and lose conditions.
     *
     * @param b - Graphics component used to draw
     *          all the game's objects to the user's
     *          screen.
     */
    public void paint(final Graphics b){
        Graphics g = b;
        // Sets the color you are using to draw an object to black
        g.setColor(Color.BLACK);
        // Creates a rectangle and paints it black
        g.fillRect(1,1,692,592);
        // Draws the map onto the frame.
        map.draw((Graphics2D) g);
        g.setColor(Color.YELLOW);
        // Creates a yellow border on the x-axis
        g.fillRect(0,0,3,592);
        // creates a yellow border on the y-axis
        g.fillRect(0,0,692,3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        g.setColor(Color.RED);
        g.fillRect(playerX, 550, 100, 8); // creates the player paddle

        // Iterating through all the balls in the ballList and draws them in the panel
        for(int i = 0; i < ballList.size(); i++) {
            Ball ball = ballList.get(i);
            g.setColor(ball.getColor());
            g.fillOval((int) ball.getPosX(), (int) ball.getPosY(), ball.getWidth(), ball.getHeight());

            // Checks to see if the current ball is outside the boundaries of the game,
            // and if it is, it removes it.
            if (ball.getPosY() > 570) {
                ball.setYDir(0);
                ball.setXDir(0);
                ballList.remove(i);
            }
        }
        // Win condition
        if(totalBricks == 0) {
            win = true;
            play = false; // Stops the game from running without further user interaction.
            ballList.clear();
            g.setColor(Color.red); // sets color to red and draws the win message
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU WIN",220,300);
            g.drawString("Press Enter to Restart", 190, 340);

        }
        if (ballList.isEmpty() && totalBricks != 0) { // Lose condition
            play = false; // Stops the game from running without further user interaction.
            map.clearMap(); // Clears the map, so that the user can see the lose condition message completely.
            g.setColor(Color.RED); // displays lose condition message.
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over Score : " + score, 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 190, 340);
        }
        g.dispose();
    }

    /**
     * Executed by the actionPerformed method, checks to
     * see if any of the ball objects are intersecting
     * with the player's paddle.
     */
    private void checkPlayerBallIntersection() {
        for(Ball ball : ballList) {
            if (new Rectangle((int) (ball.getPosX() + ball.getWidth() / 2), (int) ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ball.setYDir(-1 * (ball.getYDir()));
                double center = playerX + 50;
                double adjustment = (center - ball.getPosX()) * 0.05;
                // Adjusts the  x-axis direction of the ball
                if (Math.abs(adjustment) < 3.01) { // Checks to ensure the ball does not begin to move too quickly along the x-axis.
                    ball.setXDir(-1 * adjustment);
                } else {
                    ball.setXDir(-3 * ball.getXDir());
                }
            }
        }
    }

    /**
     * Executed by the actionPerformed method, checks to
     * see if any of the ball objects in the ballList are
     * currently intersecting with any of the bricks on
     * screen.
     */
    private void checkBrickBallIntersection() {
        A:
        for(int i = 0; i < map.getMap().length; i++) {
            for(int j = 0; j < map.getMap()[0].length; j++){
                if(map.getMap()[i][j]>0){
                    int brickX = j*Generator.getBrickHeight() + 80;
                    int brickY = i*Generator.getBrickHeight()+50;
                    int brickWidth = Generator.getBrickWidth();
                    int brickHeight = Generator.getBrickHeight();

                    // Checks to see if the ball intersects with a brick, and if it does, removes the brick.
                    for(Ball ball : ballList) {
                        Rectangle brickrect = new Rectangle(brickX, brickY, brickWidth, brickHeight); // Creates a rectangle for the brick to check for intersections
                        Rectangle ballrect = new Rectangle((int) ball.getPosX(), (int) ball.getPosY(), ball.getWidth(), ball.getHeight()); // Same as above, but ball
                        if (ballrect.intersects(brickrect)) { // Checks for intersections between the brick and ball
                            map.setBrickValue(0, i, j); // removes the brick from the frame
                            totalBricks--; // removes the brick from the count
                            score += 5; // adjusts the player score
                            if (ball.getPosX() + 19 <= brickrect.x || ball.getPosX() + 1 >= brickrect.x + brickWidth) { // Checks for an intersection between the ball and brick
                                ball.setXDir(-1 * (ball.getXDir())); // adjusts the x-axis direction
                            } else {
                                ball.setYDir(-1 * (ball.getYDir()));
                            }
                            // Checking the color of the brick to see if there is a power up to be applied
                            if(map.getBrickArray().get(i * 20 + j).getColor() == Color.MAGENTA) { // If the brick is Magenta color
                                ballList.add(new Ball(-1*ball.getXDir(), ball.getYDir(), ball.getPosX(), ball.getPosY())); // Creates a new ball, but makes it move the opposite direction.
                                if(ball.getXDir() == 0) { // if the ball is not moving in a direction across the x-axis.
                                    ballList.get(ballList.size() - 1).setXDir(-1); // Sets the newest ball's x-axis direction to -1 if the parent's x-axis direction is 0
                                }
                                // If the color is blue, three balls are added going three different directions from where the users paddle is.
                            } else if(map.getBrickArray().get(i * 20 + j).getColor() == Color.BLUE) { // Checks to see if the brick is blue color
                                // Shoots three new balls out of the paddle.
                                ballList.add(new Ball(-1, -2, playerX + 50, 520));
                                ballList.add(new Ball(0, -2, playerX + 50, 520));
                                ballList.add(new Ball(1, -2, playerX + 50, 520));
                            } else if(map.getBrickArray().get(i*20+j).getColor() == Color.ORANGE) {
                                int temp = ballList.size();
                                for(int x = 0; x < temp; x++) {
                                    Ball ball1 = ballList.get(x);
                                    Ball b1 = new Ball(ball1.getXDir() -1, ball1.getYDir() * -1, ball1.getPosX(), ball1.getPosY());
                                    Ball b2 = new Ball(ball1.getXDir() + 1, ball1.getYDir(), ball1.getPosX(), ball1.getPosY());
                                    ballList.add(b1);
                                    ballList.add(b2);
                                }
                            }
                            break A;
                        }
                    }
                }
            }
        }
    }

    /**
     * Increments the value of the ball's position
     * by the balls direction.
     */
    private void changeBallDirection() {
        for(Ball ball : ballList) {
            ball.setPosX(ball.getPosX() + ball.getXDir()); // adjusts the direction of the ball by incrementing the direction
            ball.setPosY(ball.getPosY() + ball.getYDir());
            // Checks if the ball is at the edge of the panel, and changes the direction if it is
            if (ball.getPosX() < 0) {
                ball.setXDir(-1 * (ball.getXDir()));
            }
            if (ball.getPosY() < 0) {
                ball.setYDir(-1 * (ball.getYDir()));
            }
            if (ball.getPosX() > 670) {
                ball.setXDir(-1 * (ball.getXDir()));
            }
        }
    }

    /**
     * Uses all the private methods above to see if there are
     * any intersections, as well as incrementing the positions
     * of the balls stored in ballList.
     *
     * @param e - any even that is performed by the user within
     *          the frame or by the user pressing the keyboard.
     */
    @Override
    public void actionPerformed(final ActionEvent e){
        timer.start();
        if(play) {
            // Checking for intersections between the balls and the player's paddle.
            checkPlayerBallIntersection();
            // Checking each brick for an intersection
            checkBrickBallIntersection();
            // Changes the direction of the ball
            changeBallDirection();
        }
        repaint();
    }

    /**
     * unused method.
     *
     * @param e the event to be processed.
     */
    @Override
    public void keyTyped(final KeyEvent e) {

    }

    /**
     * Checks to see if the user presses a key.
     *
     * @param e the user pressing a key.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) { // if the key pressed is the right-arrow key
            if(playerX >= 600) { // doesn't allow the user to go off the screen
                playerX = 600;
            } else {
                moveRight(); // adjusts the paddle position
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) { // if the key pressed is the left-arrow key
            if(playerX < 0) {
                playerX = 0;
            } else {
                moveLeft(); // adjusts the paddle position
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) { // if the key pressed is enter
            if(!play) { // if the game is over
                Ball ball = new Ball(); // creates a new ball
                ballList.add(ball); // adds it to the ballList
                ball.setPosX(120); // resets the new ball's position
                ball.setPosX(120);
                ball.setPosY(350);
                ball.setXDir(-1);
                ball.setYDir(-2);
                if(!win) // if the player lost
                    score = 0; // resets score to zero
                playerX = 310; // resets player position
                totalBricks = 200; // resets the number of bricks
                map = new Generator(10, 20); // creates a new map (replaces bricks that were gone)
                play = true;
                repaint(); // paints the new game
            }
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
     * Moves the paddle to the right 20 pixels.
     */
    public void moveRight(){
        play = true;
        playerX+=20;
    }

    /**
     * Moves the paddle to the left 20 pixels.
     */
    public void moveLeft(){
        play = true;
        playerX-=20;
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
     * Checks to see if the mouse moved within the frame, and then updates the location of the paddle.
     *
     * @param e the mouse moving within the frame.
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        // The iff statements just ensure that the user paddle cannot leave the screen.
        if(playerX >= 580) {
            playerX = 580;
        }
        if(playerX < 1) {
            playerX = 1;
        } else {
            play = true;
            playerX = e.getX();
        }
    }

    /**
     * Returns the boolean that determines if the game is
     * currently in motion.
     *
     * @return true or false determined by the current
     * state of the game.
     */
    public boolean getPlay() {
        return this.play;
    }

    /**
     * Allows the user to set the boolean play to help
     * with testing.
     *
     * @param play - the boolean, true or false, that
     *             is going to be cast to the instance
     *             variable, play.
     */
    public void setPlay(final boolean play){
        this.play = play;
    }

    /**
     * Returns the Generator, map, that the stores the
     * bricks.
     *
     * @return the Generator object stored by the instance
     * variable map.
     */
    public Generator getMap() {
        return this.map;
    }

    /**
     * Allows the user to change the object that
     * determines the position of bricks in the GUI.
     *
     * @param map - the Generator object that is going
     *            to be set to the instance variable
     *            map.
     */
    public void setMap(final Generator map) {
        this.map = map;
    }

    /**
     * Gives the user the amount of bricks left on the
     * screen for testing.
     *
     * @return the amount of bricks left on the screen.
     */
    public int getTotalBricks() {
        return this.totalBricks;
    }

    /**
     * Allows the user to change the amount of collisions
     * possible before the win condition is invoked.
     *
     * @param totalBricks - the amount of ball and brick
     *                    collisions possible before the
     *                    win condition is invoked.
     */
    public void setTotalBricks(final int totalBricks) {
        this.totalBricks = totalBricks;
    }

    /**
     * Gives the user the timer that is being used to
     * update the objects shown on the user's screen.
     *
     * @return the timer that is being used to update
     * the game.
     */
    public Timer getTimer() {
        return this.timer;
    }

    /**
     * Allows the user to change the Timer used to
     * update the objects shown on the user's screen.
     *
     * @param timer - the Timer that is being
     *              assigned to the instance variable
     *              timer.
     */
    public void setTimer(final Timer timer) {
        this.timer = timer;
    }

    /**
     * Gives the user the delay that the timer uses
     * between updating the screen.
     *
     * @return the amount of time before the Timer updates
     * the user's screen again.
     */
    public int getDelay() {
        return this.delay;
    }

    /**
     * Allows the user to change the delay that the
     * timer uses between updating the screen.
     *
     * @param delay - the amount of time between
     *              updates of the object's shown
     *              on the user's screen.
     */
    public void setDelay(final int delay) {
        this.delay = delay;
    }

    /**
     * Gives the user the x-axis position of the
     * current player's paddle.
     *
     * @return the x-axis position of the player's paddle.
     */
    public int getPlayerX() {
        return this.playerX;
    }

    /**
     * Allows the user to change the x-axis position
     * of the player's paddle on screen.
     *
     * @param playerX - value that the player's
     *                x-axis position will be updated to.
     */
    public void setPlayerX(final int playerX) {
        this.playerX = playerX;
    }

    /**
     * Allows the user to retrieve the information stored
     * in the ballList.
     *
     * @return the list that stores all the balls from the game.
     */
    public ArrayList<Ball> getBallList() {
        return this.ballList;
    }
}