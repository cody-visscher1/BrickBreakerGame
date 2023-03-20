import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

public class BallTest {
    @Test
    public void testBaseBallConstructor() {
        Ball b = new Ball();
        assertEquals(Color.GREEN, b.getColor(), "The ball's color should be green");
        assertEquals(Generator.getBrickHeight() - 5, b.getHeight(), "The height of the ball should" +
                "be equal to the height of the brick - 5");
        assertEquals(Generator.getBrickWidth() - 5, b.getWidth(), "The width of the ball should be the" +
                "equal to the width of the brick - 5");
        assertEquals(-2, b.getYDir(), "The ball's y-axis direction should be -2");
        assertEquals(1, b.getXDir(), "The ball's x-axis direction should be 1");
        assertEquals(350, b.getPosY(), "The ball's y-axis position should be 350");
        assertEquals(120, b.getPosX(), "The ball's x-axis position should be 120");
    }

    @Test
    public void testPositionalBallConstructor() {
        Ball ball = new Ball(true, 1, 1);
        assertEquals(1, ball.getPosX(), "The ball's x-axis position should equal 1");
        assertEquals(1, ball.getPosY(), "The ball's y-axis position should equal 1");
    }

    @Test
    public void testDirectionalBallConstructor() {
        Ball ball = new Ball(1, -2, 100, 140);
        assertEquals(1, ball.getXDir(), "The ball's x-axis direction should equal 1");
        assertEquals(-2, ball.getYDir(), "The ball's y-axis direction should equal -2");
        assertEquals(100, ball.getPosX(), "The ball's x-axis position should equal 100");
        assertEquals(140, ball.getPosY(), "The ball's y-axis position should equal 140");
    }

    @Test
    public void testSizeableBallConstructor() {
        Ball ball = new Ball(10, 20);
        assertEquals(10, ball.getHeight(), "The ball's height should be 10 units");
        assertEquals(20, ball.getWidth(), "The ball's width should be 20 units");
    }

}