import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BrickGameTest {

    @Test
    public void testBrickGameConstructor() {
        BrickGame bg = new BrickGame();
        assertNotNull(bg.getMap(), "map should not be null");
        assertNotNull(bg.getBallList());
        assertEquals(1, bg.getBallList().size(), "ballList should contain one ball");
        assertTrue(bg.isFocusable(), "brick game should be focusable");
        assertNotNull(bg.getTimer(), "timer should not be null");
        assertEquals(8, bg.getDelay(), "delay should be 8");
        assertTrue(bg.getTimer().isRunning(), "timer should be running");
    }

    @Test
    public void testBrickIntersection() {
        BrickGame bg = new BrickGame();
        Ball ball = new Ball(true, 80, 50);
        bg.testHelpBrickIntersection(ball);
    }

    @Test
    public void testPlayerIntersection() {
        BrickGame bg = new BrickGame();
        Ball ball = new Ball(true, bg.getPlayerX(), 550);
        double xDir = ball.getXDir();
        int yDir = (int)ball.getYDir();
        bg.testHelpPaddleIntersection(ball);
        assertEquals(-1*yDir, (int)ball.getYDir(), "The ball should have changed direction");
    }

    @Test
    public void testChangeDirection() {
        BrickGame bg = new BrickGame();
        Ball ball = bg.getBallList().get(0);
        double posY = ball.getPosY();
        bg.testHelpChangeDirection(ball);
        assertNotEquals(posY, ball.getPosY(), "the position of the ball should have updated");
    }
}