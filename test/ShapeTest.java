import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ShapeTest {
//    Pieces can shift left, right, and down in the grid.
//    Pieces rotate 90 degrees clockwise about their approximate center of mass.
    private static final Pair RIGHT = new Pair(0, 1);

    @Test
    void shiftsCorrectly() {
        Shape shape = new Shape();
        List<Pair> squares = shape.getSquares();
        shape.shift(RIGHT);
        List<Pair> newSquares = shape.getSquares();
        assertEquals(squares.size(), newSquares.size());
        for (int i = 0; i < squares.size(); i++)
            assertEquals(squares.get(i).plus(RIGHT), newSquares.get(i));
    }
    @Test
    void commitsMoveCorrectly() {
        Shape shape = new Shape();
        List<Pair> squares = shape.getSquares();
        shape.shift(RIGHT);
        List<Pair> newSquares = shape.getSquares();
        shape.commitMove(false);
        assertEquals(squares, shape.getSquares());
        shape.shift(RIGHT);
        shape.commitMove(true);
        assertEquals(newSquares, shape.getSquares());

    }

    @Test
    void rotatesCorrectly() {
        // Does not do anything yet.
    }

    @Test
    void gettersWork() {
        Shape shape = new Shape();
        assertNotNull(shape.getColor());
        assertEquals(4, shape.getSquares().size());
    }
}
