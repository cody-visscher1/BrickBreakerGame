
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class BrickGame extends JPanel implements KeyListener, ActionListener, MouseMotionListener {

    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;
    private int playerX = 310;
    private Generator map;
    private Ball ball;

    public BrickGame() {
        ball = new Ball();
        map = new Generator(3,7);
        addKeyListener(this); // allows us to use keys for moving the paddle
        addMouseMotionListener(this); // allows us to use the mouse to move the paddle.
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);
        map.draw((Graphics2D) g);
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        g.setColor(Color.RED);
        g.fillRect(playerX, 550, 100, 8);

        // Change to ball
        g.setColor(ball.getColor());
        g.fillOval(ball.getPosX(), ball.getPosY(), ball.getWidth(), ball.getHeight());

        if(ball.getPosY() > 570) {
            play = false;
            ball.setXDir(0);
            ball.setYDir(0);
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over Score : "+score, 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 190, 340);
        }
        if(totalBricks == 0) {
            play = false;
            ball.setYDir(-2);
            ball.setXDir(-1);
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 190, 340);

        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        timer.start();
        if(play) {
            if(new Rectangle(ball.getPosX() + ball.getWidth() / 2, ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle( playerX + 40, 550, 20, 8))){
                ball.setYDir(-1*(ball.getYDir()));
                ball.setXDir(0);
            }
            else if(new Rectangle(ball.getPosX() + ball.getWidth() / 2, ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle(playerX + 20, 550, 20, 8))) {
                ball.setYDir(-1*(ball.getYDir()));
                ball.setXDir(-1);
            }
            else if(new Rectangle(ball.getPosX() + ball.getWidth() / 2, ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle(playerX + 60, 550, 20, 8))) {
                ball.setYDir(-1*(ball.getYDir()));
                ball.setXDir(1);
            }
            else if(new Rectangle(ball.getPosX() + ball.getWidth() / 2, ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle(playerX, 550, 20, 8))) {
                ball.setYDir(-1*(ball.getYDir()));
                ball.setXDir(-2);
            }
            else if(new Rectangle(ball.getPosX() + ball.getWidth() / 2, ball.getPosY(), 1, ball.getHeight()).intersects(new Rectangle(playerX + 80, 550, 20, 8))) {
                ball.setYDir(-1*(ball.getYDir()));
                ball.setXDir(2);
            }
            A:
            for(int i = 0; i < map.map.length; i++) {
                for(int j = 0; j < map.map[0].length; j++){
                    if(map.map[i][j]>0){
                        int brickX = j*map.brickWidth + 80;
                        int brickY = i*map.brickHeight+50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballrect = new Rectangle(ball.getPosX(), ball.getPosY(), ball.getWidth(), ball.getHeight());
                        Rectangle brickrect = rect;
                        if(ballrect.intersects(brickrect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score+=5;
                            if(ball.getPosX()+19 <= brickrect.x || ball.getPosX()+1 >= brickrect.x+brickWidth) {
                                ball.setXDir(-1*(ball.getXDir()));
                            }
                            else {
                                ball.setYDir(-1*(ball.getYDir()));
                            }
                            break A;
                        }
                    }
                }
            }
            ball.setPosX(ball.getPosX() + ball.getXDir());
            ball.setPosY(ball.getPosY() + ball.getYDir());
            if(ball.getPosX()<0) {
                ball.setXDir(-1*(ball.getXDir()));
            }
            if(ball.getPosY()<0) {
                ball.setYDir(-1*(ball.getYDir()));
            }
            if(ball.getPosX() > 670){
                ball.setXDir(-1*(ball.getXDir()));
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
            else {
                moveLeft();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!play) {
                ball.setPosX(120);
                ball.setPosX(120);
                ball.setPosY(350);
                ball.setXDir(-1);
                ball.setYDir(-2);
                score = 0;
                playerX = 310;
                totalBricks = 21;
                map = new Generator(3,7);
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveRight(){
        play = true;
        playerX+=20;
    }

    public void moveLeft(){
        play = true;
        playerX-=20;
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
            playerX = e.getX();
        }
        if(playerX < 10) {
            playerX = 10;
        }
        else {
            play = true;
            playerX = e.getX();
        }
    }
}
