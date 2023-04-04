import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

public class GeneratorTest {

    @Test
    public void testGeneratorConstructor() {
        Generator generator = new Generator(10, 20);
        assertEquals(10, generator.getMap().length, "the length of the map should be 10");
        assertEquals(20, generator.getMap()[0].length, "the length of the map[0] should be 20");
        assertNotNull(generator.getBrickArray(), "the brick array should not be null");
        assertEquals(200, generator.getBrickArray().size(), "the brick array should contain 200 bricks");
        assertEquals(27, Generator.getBrickHeight(), "the height of bricks should be 27");
        assertEquals(27, Generator.getBrickWidth(), "the width of bricks should be 27");
        boolean containsOrange = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                assertEquals(1, generator.getMap()[i][j], "the value at the index of the array should be 1");
            }
        }
        for (Brick b : generator.getBrickArray()) {
            if (b.getColor() == Color.orange) {
                containsOrange = true;
                break;
            }
        }
        assertTrue(containsOrange, "there should be an orange brick");
    }

    @Test
    public void testClearMap() {
        Generator generator = new Generator(10, 20);
        generator.clearMap();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                assertEquals(0, generator.getMap()[i][j], "The value of the specified index of the array should be 0");
            }
        }
    }
}