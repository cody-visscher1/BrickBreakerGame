import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

/**
 * JUnit tests for the ball class.
 */
public class BallTest {

    /**
     * Tests the ball class' default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        Ball ball = new Ball();
        assertEquals(Color.GREEN, ball.getColor(), "the ball should be green");
        assertEquals(27, ball.getHeight(), "the height of the ball should be 27");
        assertEquals(27, ball.getWidth(), "the width of the ball should be 27");
        assertEquals(-2, ball.getYDir(), "the balls y-axis direction should be -2");
        assertEquals(1, ball.getXDir(), "the balls x-axis direction should be 1");
        assertEquals(350, ball.getPosY(), "the balls y-axis position should be 350");
        assertEquals(120, ball.getPosX(), "the balls x-axis position should 120");
    }

    /**
     * Tests the ball class' directional constructor.
     */
    @Test
    public void testDirectionalConstructor() {
        Ball ball = new Ball(1,1,1,1);
        assertEquals(1, ball.getYDir(), "the ball's y-axis direction should be 1");
        assertEquals(1, ball.getXDir(), "the ball's x-axis direction should be 1");
        assertEquals(1, ball.getPosY(), "the ball's y-axis position should be");
        assertEquals(1, ball.getPosX(), "the ball's x-axis position should be");
    }

    /**
     * Tests the ball class' size constructor.
     */
    @Test
    public void testSizeConstructor() {
        Ball ball = new Ball(10, 20);
        assertEquals(10, ball.getHeight(), "the ball's height should be 10");
        assertEquals(20, ball.getWidth(), "the ball's width should be 20");
    }

    /**
     * Tests unused setter methods in the ball class for code coverage.
     */
    @Test
    public void testUnusedSetters() {
        Ball ball = new Ball();
        ball.setColor(Color.RED);
        assertEquals(Color.RED, ball.getColor());
        ball.setHeight(1);
        ball.setWidth(1);
        assertEquals(1, ball.getHeight(), "the ball's height should be 1");
        assertEquals(1, ball.getWidth(), "the ball's width should be ");
    }
}