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
        for (; c < 5; c++) {
            board.setSquare(new Pair(4, c), Color.RED);
        }
        assertFalse(board.empty(4));
        assertFalse(board.full(4));
        for (; c < 10; c++) {
            board.setSquare(new Pair(4, c), Color.GREEN);
        }
        assertFalse(board.empty(4));
        assertTrue(board.full(4));
    }

    @Test
    void determinesWhetherSquareIsValid() {
        Board board = new Board();
        assertTrue(board.isValidLocation(new Pair(3, 4)));
        assertFalse(board.isValidLocation(new Pair(3, 11)));
        assertFalse(board.isValidLocation(new Pair(21, 4)));
        assertFalse(board.isValidLocation(new Pair(21, -1)));
    }

    @Test
    void determinesWhetherShapesOverlap() {
        Tetromino tetromino = new Tetromino();
        Board board = new Board();
        assertTrue(board.isInValidPosition(tetromino));
        board.setSquare(new Pair(0, 4), Color.RED);
        board.setSquare(new Pair(0, 3), Color.RED);

    }

    @Test
    void correctlyHandlesEmptyAndFullRows() {
        Board board = new Board();
        int row2 = board.ROWS - 3;
        int row1 = board.ROWS - 2;
        int row0 = board.ROWS - 1;
        // Fill in some rows
        board.setSquare(new Pair(row2, 4), Color.RED);
        for (int c = 0; c < Board.COLUMNS; c++) {
            board.setSquare(new Pair(row1, c), Color.GREEN);
            board.setSquare(new Pair(row0, c), Color.BLUE);
        }
        // Check that full rows get cleared
        board.clear();
        assertFalse(board.empty(row2));
        assertTrue(board.empty(row1));
        assertTrue(board.empty(row0));
        // Check that rows drop properly
        board.clear();
        assertTrue(board.empty(row2));
        assertFalse(board.empty(row1));
        assertTrue(board.empty(row0));
        board.clear();
        assertTrue(board.empty(row2));
        assertTrue(board.empty(row1));
        assertFalse(board.empty(row0));

    }
}
