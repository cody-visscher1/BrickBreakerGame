import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BrickTest {

    @Test
    public void testBrickConstructor() {
        Brick b = new Brick();
        assertNotNull(b, "b should not be null");
    }

}