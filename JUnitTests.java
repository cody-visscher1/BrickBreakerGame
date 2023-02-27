import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class JUnitTests {

    @Before
    public void initial() {

    }

    @Test
    public void testBallStart() {
        ArrayList<Ball> newball = new ArrayList();
        newball.add(new Ball());
        newball.add(new Ball());


        assertTrue(newball.get(0).getPosY() >= 320 && newball.get(0).getPosY() <= 550);
        assertTrue(newball.get(0).getPosX() >= 120 && newball.get(0).getPosX() <= 350);

        assertTrue(newball.get(1).getPosY() >= 320 && newball.get(1).getPosY() <= 550);
        assertTrue(newball.get(1).getPosX() >= 120 && newball.get(1).getPosX() <= 350);

        assertEquals(-2, newball.get(0).getYDir(), 0);
        assertEquals(1, newball.get(0).getXDir(), 0);

        assertEquals(-2, newball.get(1).getYDir(), 0);
        assertEquals(1, newball.get(1).getXDir(), 0);
    }

    @Test
    public void testballconstructor() {
        //public Ball(double xDir, double yDir, double posX, double posY)
        //Ball(int height, int width)
        ArrayList<Ball> newball = new ArrayList();
        newball.add(new Ball(12, 12));

        newball.add(new Ball(-2, -2, 350, 350));

        assertEquals(Color.GREEN, newball.get(0).getColor());
        assertEquals(120, newball.get(0).getPosX(), 0);
        assertEquals(350, newball.get(0).getPosY(), 0);
        assertEquals(-2, newball.get(0).getYDir(), 0);
        assertEquals(-1, newball.get(0).getXDir(), 0);
        assertEquals(12, newball.get(0).getWidth(), 0);
        assertEquals(12, newball.get(0).getHeight(), 0);


        assertEquals(Color.GREEN, newball.get(1).getColor());
        assertEquals(350, newball.get(1).getPosX(), 0);
        assertEquals(350, newball.get(1).getPosY(), 0);
        assertEquals(-2, newball.get(1).getYDir(), 0);
        assertEquals(-2, newball.get(1).getXDir(), 0);
        assertEquals(11, newball.get(1).getWidth(), 0);
        assertEquals(11, newball.get(1).getHeight(), 0);


    }

    @Test
    public void testBallGettersandSetters() {
        ArrayList<Ball> newball = new ArrayList();
        newball.add(new Ball());
        newball.add(new Ball());

        newball.get(0).setColor(Color.BLUE);
        assertEquals(Color.BLUE, newball.get(0).getColor());

        newball.get(1).setColor(Color.RED);
        assertEquals(Color.RED, newball.get(1).getColor());

        newball.get(0).setPosY(300);
        newball.get(0).setPosX(300);

        assertEquals(300, newball.get(0).getPosX(), 0);
        assertEquals(300, newball.get(0).getPosY(), 0);

        newball.get(1).setPosY(550);
        newball.get(1).setPosX(325);

        assertEquals(325, newball.get(1).getPosX(), 0);
        assertEquals(550, newball.get(1).getPosY(), 0);

        newball.get(0).setYDir(2);
        newball.get(0).setXDir(-2);

        assertEquals(2, newball.get(0).getYDir(), 0);
        assertEquals(-2, newball.get(0).getXDir(), 0);

        newball.get(1).setYDir(-2);
        newball.get(1).setXDir(2);

        assertEquals(-2, newball.get(1).getYDir(), 0);
        assertEquals(2, newball.get(1).getXDir(), 0);

        newball.get(0).setWidth(12);
        assertEquals(12, newball.get(0).getWidth(), 0);

        newball.get(0).setHeight(12);
        assertEquals(12, newball.get(0).getHeight(), 0);

        newball.get(1).setWidth(14);
        assertEquals(14, newball.get(1).getWidth(), 0);

        newball.get(1).setHeight(13);
        assertEquals(13, newball.get(1).getHeight(), 0);
    }

    @Test
    public void testBrickGameGetAndSet() {
        BrickGame pong=new BrickGame();

        pong.setPlay(false);
        assertEquals(false,pong.getPlay());

        pong.setPlay(true);
        assertEquals(true,pong.getPlay());

        assertEquals(27,pong.getMap().brickHeight,0);
        assertEquals(27,pong.getMap().brickWidth,0);
        assertEquals(pong.getMap().brickHeight,pong.getMap().brickWidth,0);
        assertTrue(pong.getMap().brickArray.size()==200);

        pong.setTotalBricks(500);
        assertEquals(500,pong.getTotalBricks(),0);
        pong.setDelay(10);
        assertEquals(10,pong.getDelay(),0);
        pong.setPlayerX(375);
        assertEquals(375,pong.getPlayerX(),0);






    }


    @Test(expected = NegativeArraySizeException.class)
    public void testConstructorNegativeRow() {
        Generator generator = new Generator(-1, 2);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testConstructorNegativeCol() {
        Generator generator = new Generator(3, -1);
    }

    

}



















