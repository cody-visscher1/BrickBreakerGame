
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BrickGame extends JPanel implements KeyListener, ActionListener, MouseMotionListener {

    private boolean play = false;
    private int score = 0;
    private int totalBricks = 200;
    private Timer timer;
    private int delay = 8;
    private int playerX = 310;
    private Generator map;
    private Generator temp;
    private ArrayList<Ball> ballList = new ArrayList<>();
    private boolean win = false;

    /**
     * Constructor
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
     * Shows all the objects that are in the frame
     *
     * Also checks win and lose conditions
     * @param g the graphics tool that is being used
     */
    public void paint(Graphics g){
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
            map.randomBricks(); // Reassigns the colors of the bricks in the map.
            play = false; // Stops the game from running without further user interaction.
            for(int i = 0; i < ballList.size(); i++) { // Removes all the balls from the ballList to make sure that the game starts with only one ball
                ballList.remove(i);
            }
            g.setColor(Color.red); // sets color to red and draws the win message
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU WIN",220,300);
            g.drawString("Press Enter to Restart", 190, 340);

        }
        if (ballList.isEmpty() && totalBricks != 0) { // Lose condition
            map.randomBricks(); // Reassigns the colors of the bricks in the map.
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
     * Checks for intersections between balls and bricks, as well as intersections between balls and the paddle
     * @param e event that happens
     */
    @Override
    public void actionPerformed(ActionEvent e){
        timer.start();
        if(play) {
            // Checking for intersections between the balls and the player's paddle.
            for(Ball ball : ballList) {
                if (new Rectangle((int) (ball.getPosX() + ball.getWidth() / 2), (int) ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle(playerX, 550, 100, 8))) {
                    ball.setYDir(-1 * (ball.getYDir()));
                    double center = playerX + 50;
                    double adjustment = (center - ball.getPosX()) * 0.05;
                    // Adjusts the  x-axis direction of the ball
                    if (Math.abs(adjustment) < 3.01) { // Checks to ensure the ball does not begin to move too quickly along the x-axis.
                        ball.setXDir(-1 * adjustment);
                    }
                    else {
                        ball.setXDir(-3 * ball.getXDir());
                    }
                }
            }
            // Checking each brick for an intersection
            A:
            for(int i = 0; i < map.map.length; i++) {
                for(int j = 0; j < map.map[0].length; j++){
                    if(map.map[i][j]>0){
                        int brickX = j*map.brickWidth + 80;
                        int brickY = i*map.brickHeight+50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

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
                                if(Generator.brickArray.get(i * 20 + j).getColor() == Color.MAGENTA) { // If the brick is Magenta color
                                    ballList.add(new Ball(-1*ball.getXDir(), ball.getYDir(), ball.getPosX(), ball.getPosY())); // Creates a new ball, but makes it move the opposite direction.
                                    if(ball.getXDir() == 0) { // if the ball is not moving in a direction across the x-axis.
                                        ballList.get(ballList.size() - 1).setXDir(-1); // Sets the newest ball's x-axis direction to -1 if the parent's x-axis direction is 0
                                    }
                                }
                                // If the color is blue, three balls are added going three different directions from where the users paddle is.
                                else if(Generator.brickArray.get(i * 20 + j).getColor() == Color.BLUE) { // Checks to see if the brick is blue color
                                    // Shoots three new balls out of the paddle.
                                    ballList.add(new Ball(-1, -2, playerX + 50, 520));
                                    ballList.add(new Ball(0, -2, playerX + 50, 520));
                                    ballList.add(new Ball(1, -2, playerX + 50, 520));
                                }
                                break A;
                            }
                        }
                    }
                }
            }
            // Changes the direction of the ball
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
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Checks to see if the user presses a key
     * @param e the user pressing a key
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) { // if the key pressed is the right-arrow key
            if(playerX >= 600) { // doesn't allow the user to go off the screen
                playerX = 600;
            }
            else {
                moveRight(); // adjusts the paddle position
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) { // if the key pressed is the left-arrow key
            if(playerX < 0) {
                playerX = 0;
            }
            else {
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
                repaint(); // paints the new game
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Moves the paddle to the right 20 pixels
     */
    public void moveRight(){
        play = true;
        playerX+=20;
    }

    /**
     * Moves the paddle to the left 20 pixels
     */
    public void moveLeft(){
        play = true;
        playerX-=20;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Checks to see if the mouse moved within the frame, and then updates the location of the paddle.
     * @param e the mouse moving within the frame
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        // The iff statements just ensure that the user paddle cannot leave the screen.
        if(playerX >= 580) {
            playerX = 580;
        }
        else {
            play = true;
            playerX = e.getX();
        }
        if(playerX < 1) {
            playerX = 1;
        }
        else {
            play = true;
            playerX = e.getX();
        }
    }
}
