import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.text.DecimalFormat;
import static org.junit.Assert.assertTrue;


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
        assertEquals(0.75, pg.getDifficulty(), 0);


    }

    @Test
    public void moveRightTestConstructor() {
        pg= new PongGame();
        pg.SetPlay(false);
        pg. setPlayerX(20);
        pg.moveRight();
        assertTrue(pg.GetPlay());
        assertEquals(40,pg.getPlayerX(),0);

    }


    @Test
    public void moveLeftTestConstructor() {
        pg= new PongGame();
        pg.SetPlay(false);
        pg.setPlayerX(20);
        pg.moveLeft();
        assertTrue(pg.GetPlay());
        assertEquals(0,pg.getPlayerX(),0);

    }
}