import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TetrominoTest {
    @Test
    void clonesCorrectly() {
        Tetromino a = new Tetromino();
        Tetromino b = a.clone();
        assertEquals(a, b);
        assertNotSame(a, b);
    }
}
