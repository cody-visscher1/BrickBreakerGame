import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the BrickGame class.
 */
public class BrickGameTest {

    /**
     * Tests the constructor of BrickGame.
     */
    @Test
    public void testBrickGameConstructor() {
        BrickGame bg = new BrickGame();
        assertNotNull(bg.getMap(), "map should not be null");
        assertNotNull(bg.getBallList(), "ballList should not be null");
        assertNotEquals(0, bg.getBallList().size(), "there should be a ball in ballList");
        assertNotNull(bg.getTimer(), "timer should not be null");
        assertTrue(bg.getTimer().isRunning(), "timer should be running");
    }


}