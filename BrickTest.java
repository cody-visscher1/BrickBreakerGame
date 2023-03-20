import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

public class BrickTest {

    @Test
    public void testBrickConstructor() {
        Brick b = new Brick();
        assertTrue(b.getColor() == Color.BLUE || b.getColor() == Color.WHITE || b.getColor() == Color.MAGENTA,
                "The brick should be white, blue, or magenta");
    }
}