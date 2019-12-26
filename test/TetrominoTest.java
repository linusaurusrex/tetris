import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class TetrominoTest {
    @Test
    void gettersWork() {
        Tetromino tetromino = new Tetromino('T');
        assertEquals(Color.MAGENTA, tetromino.getColor());
        List<Pair> squares = Arrays.asList(
                new Pair(0, 4),
                new Pair(1, 3),
                new Pair(1, 4),
                new Pair(1, 5)
        );
        assertEquals(squares, tetromino.getSquares());
    }

    @Test
    void clonesCorrectly() {
        Tetromino a = new Tetromino();
        Tetromino b = a.copy();
        assertEquals(a, b);
        assertNotSame(a, b);
    }

    @Test
    void shiftsCorrectly() {
        Tetromino tetromino = new Tetromino();
        List<Pair> squares = tetromino.getSquares();
        tetromino.shift(Pair.RIGHT);
        List<Pair> newSquares = tetromino.getSquares();
        assertEquals(squares.size(), newSquares.size());
        for (int i = 0; i < squares.size(); i++)
            assertEquals(squares.get(i).plus(Pair.RIGHT), newSquares.get(i));
    }

    @Test
    void rotatesCorrectly() {
        Tetromino tetromino = new Tetromino('T');
        tetromino.rotate();
        List<Pair> squares = Arrays.asList(
                new Pair(0, 4),
                new Pair(1, 4),
                new Pair(1, 5),
                new Pair(2, 4)
        );
        assertEquals(squares, tetromino.getSquares());
    }

    @Test
    void commitsMoveCorrectly() {
        Tetromino tetromino = new Tetromino();
        List<Pair> squares = tetromino.getSquares();
        tetromino.shift(Pair.RIGHT);
        tetromino.rotate();
        List<Pair> newSquares = tetromino.getSquares();
        tetromino.commitMove(false);
        assertEquals(squares, tetromino.getSquares());
        tetromino.shift(Pair.RIGHT);
        tetromino.rotate();
        tetromino.commitMove(true);
        assertEquals(newSquares, tetromino.getSquares());

    }
}
