import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

class PongGameTest {

    @Test
    public void testPongGameConstructor() {
        PongGame pg = new PongGame();
        assertEquals(500, pg.getBall().getPosY(), "the ball's y-axis position should be 500");
        assertEquals(-1, pg.getBall().getXDir(), "the ball's x-axis direction should be -1");
        assertEquals(-3, pg.getBall().getYDir(), "the ball's y-axis direction should be -3");
        assertEquals(0, pg.getCompScore(), "the computer's score should be 0");
        assertEquals(0, pg.getPlayerScore(), "the player's score should be 0");
        assertNotNull(pg.getCompGoal(), "the computer's goal should not be null");
        assertNotNull(pg.getPlayerGoal(), "the player's goal should not be null");
        assertEquals(0.75, pg.getDifficulty(), "the default difficulty should be 0.75");
    }
}