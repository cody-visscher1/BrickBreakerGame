import org.junit.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {

    @Test
    public void testGeneratorConstructor() {
        Generator g = new Generator(10, 20);
        assertEquals(10, g.getMap().length, "there should be 10 rows in map");
        assertEquals(20, g.getMap()[0].length, "there should be 20 columns in map");
        assertEquals(27, Generator.getBrickWidth(), "brick width should be 27");
        assertEquals(27, Generator.getBrickHeight(), "brick height should be 27");
        assertFalse(g.getBrickArray().isEmpty(), "brick array should not be empty");
        int index = -1;
        for(int i = 0; i < g.getBrickArray().size(); i++) {
            if(g.getBrickArray().get(i).getColor() == Color.ORANGE) {
                index = i;
            }
        }
        assertNotEquals(-1, index, "there should be an orange brick in brick array");
    }

    @Test
    public void testClearMap() {
        Generator g = new Generator(10, 20);
        g.clearMap();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 20; j++) {
                assertEquals(0, g.getMap()[i][j], "the value of each brick should be 0");
            }
        }
    }

    @Test
    public void testSetBrickValue() {
        Generator g = new Generator(10,20);
        g.setBrickValue(0,0,0);
        assertEquals(0, g.getMap()[0][0], "map[0][0] should equal 0");
    }
}