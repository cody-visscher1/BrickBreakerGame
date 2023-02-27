import static org.junit.Assert.*;
import org.junit.Test;

import java.text.DecimalFormat;


public class PongGameTest {
    private PongGame pg;
    @Test
    public void PongGameTestConstructor() {
        pg = new PongGame();
        assertNotNull("Ball should not be null.", pg.getBall());
        assertEquals(500, (int)pg.getBall().getPosY());
        assertEquals(-1, (int)pg.getBall().getXDir());
        assertEquals(-3, (int)pg.getBall().getYDir());
        assertEquals(0, (int)pg.getCompScore());
        assertEquals(0, (int)pg.getPlayerScore());
        assertNotNull("compGoal should not be null.", pg.getCompGoal());
        assertNotNull("playerGoal should not be null.", pg.getPlayerGoal());

    }
}