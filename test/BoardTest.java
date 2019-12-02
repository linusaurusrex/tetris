import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

public class BoardTest {

    @Test
    void gettersAndSettersWork() {
        Pair square = new Pair(4, 5);
        Board board = new Board();
        assertEquals(null, board.getSquare(square));
        board.setSquare(square, Color.RED);
        assertEquals(Color.RED, board.getSquare(square));

    }
    @Test
    void determinesWhenRowIsFullOrEmpty() {
        Board board = new Board();
        assertTrue(board.empty(4));
        assertFalse(board.full(4));
        int c = 0;
        for (; c < 5; c++)
            board.setSquare(new Pair(4, c), Color.RED);
        assertFalse(board.empty(4));
        assertFalse(board.full(4));
        for (; c < 10; c++)
            board.setSquare(new Pair(4, c), Color.GREEN);
        assertFalse(board.empty(4));
        assertTrue(board.full(4));
    }

    @Test
    void determinesWhetherSquareIsValid() {
        Board board = new Board();
        assertTrue(board.isValid(new Pair(3, 4)));
        assertFalse(board.isValid(new Pair(3, 11)));
        assertFalse(board.isValid(new Pair(21, 4)));
        assertFalse(board.isValid(new Pair(21, -1)));
    }

    @Test
    void determinesWhetherShapesOverlap() {
        Shape shape = new Shape();
        Board board = new Board();
        assertTrue(board.check(shape));
        board.setSquare(new Pair(0, 4), Color.RED);
        board.setSquare(new Pair(0, 3), Color.RED);

    }

    @Test
    void correctlyHandlesEmptyAndFullRows() {
        Board board = new Board();
        board.setSquare(new Pair(17, 4), Color.RED);
        for (int c = 0; c < 10; c++) {
            board.setSquare(new Pair(18, c), Color.GREEN);
            board.setSquare(new Pair(19, c), Color.BLUE);
        }
        board.clear();
        assertFalse(board.empty(17));
        assertTrue(board.empty(18));
        assertTrue(board.empty(19));
        board.clear();
        assertTrue(board.empty(17));
        assertFalse(board.empty(18));
        assertTrue(board.empty(19));
        board.clear();
        assertTrue(board.empty(17));
        assertTrue(board.empty(18));
        assertFalse(board.empty(19));

    }
}
