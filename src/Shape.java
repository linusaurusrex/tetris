import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Shape implements Iterable<Pair> {
    private static final List<List<Pair>> SHAPES = Arrays.asList(
            Arrays.asList( // Line
                    new Pair(0, 3),
                    new Pair(0, 4),
                    new Pair(0, 5),
                    new Pair(0, 6)
            ),
            Arrays.asList( // Block
                    new Pair(0, 4),
                    new Pair(0, 5),
                    new Pair(1, 4),
                    new Pair(1, 5)
            ),
            Arrays.asList( // Middle bump
                    new Pair(0, 4),
                    new Pair(1, 3),
                    new Pair(1, 4),
                    new Pair(1, 5)
            ),
            Arrays.asList( // Zig
                    new Pair(0, 4),
                    new Pair(0, 5),
                    new Pair(1, 3),
                    new Pair(1, 4)
            ),
            Arrays.asList( // Zag
                    new Pair(0, 3),
                    new Pair(0, 4),
                    new Pair(1, 4),
                    new Pair(1, 5)
            ),
            Arrays.asList( // L
                    new Pair(0, 5),
                    new Pair(1, 3),
                    new Pair(1, 4),
                    new Pair(1, 5)
            ),
            Arrays.asList( // Backwards L
                    new Pair(0, 3),
                    new Pair(1, 3),
                    new Pair(1, 4),
                    new Pair(1, 5)
            )
    );
    private static final List<Color> COLORS = Arrays.asList(
            Color.CYAN,
            Color.YELLOW,
            Color.MAGENTA,
            Color.RED,
            Color.GREEN,
            Color.ORANGE,
            Color.BLUE
            );
    private static final Pair DOWN = new Pair(1, 0);
    private static final Pair LEFT = new Pair(0, -1);
    private static final Pair RIGHT = new Pair(0, 1);
    private List<Pair> squares;
    private Color color;
    private Board board;
    private Tetris tetris;
    private int i;
    public boolean overlap = false;
    public Shape(Board board, Tetris tetris) {
        this.board = board;
        this.tetris = tetris;
        i = StdRandom.uniform(SHAPES.size());
        squares = SHAPES.get(i);
        if (!check(squares)) overlap = true;
        color = COLORS.get(i);
    }

    public void shift(Pair direction) {
        List<Pair> newSquares = new ArrayList<Pair>();
        for (Pair square : squares) newSquares.add(square.plus(direction));
        if (check(newSquares)) squares = newSquares;
        else if (direction.equals(DOWN)) tetris.settle();
    }
    public void settle() {
        for (Pair square : squares)
            board.setSquare(square, color);
    }
    public void rotate() { if (1==1) return;
        List<Pair> newSquares = new ArrayList<Pair>();
        //for (Pair square : squares) newSquares.add(square.plus(direction));
        if (i == 0) {

        }
        if (i == 1) {
            // Block does not rotate
        }
        if (i == 2) {

        }
        if (i == 3) {

        }
        if (i == 4) {

        }
        if (i == 5) {

        }
        if (i == 6) {

        }
        if (check(newSquares)) squares = newSquares;
    }
    public boolean check(List<Pair> squares) {
        for (Pair square : squares)
            if (!board.isValid(square) || board.getSquare(square) != null)
                return false;
        return true;
    }

    @Override
    public Iterator<Pair> iterator() {
        return squares.iterator();
    }

    public Color getColor() {
        return color;
    }
}
