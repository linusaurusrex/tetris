import java.awt.Color;
import java.util.*;

public class Shape implements Iterable<Pair> {
    // All possible configurations of 4 squares
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
    // Corresponding colors
    private static final List<Color> COLORS = Arrays.asList(
            Color.CYAN,
            Color.YELLOW,
            Color.MAGENTA,
            Color.RED,
            Color.GREEN,
            Color.ORANGE,
            Color.BLUE
            );
    // Directions
    private static final Pair DOWN = new Pair(1, 0);
    private static final Pair LEFT = new Pair(0, -1);
    private static final Pair RIGHT = new Pair(0, 1);

    private List<Pair> squares;
    private List<Pair> oldSquares;
    private Color color;
    private int i;

    /**
     * Constructs a random shape.
     */
    public Shape() {
        i = StdRandom.uniform(SHAPES.size());
        color = COLORS.get(i);
        squares = SHAPES.get(i);
    }

    /**
     * Moves the shape in the given direction.
     */
    public void shift(Pair direction) {
        oldSquares = squares;squares = new ArrayList<Pair>();
        for (Pair square : oldSquares) squares.add(square.plus(direction));
    }

    /**
     * Returns the Shape's location Pairs.
     */
    public List<Pair> getSquares() {
        return squares;
    }
    /**
     * Restores the Shape's previous location.
     */
    public void commitMove(boolean valid) {
        if (!valid && oldSquares != null) squares = oldSquares;
    }

    /**
     * Rotates the shape clockwise about its center.
     * Does not do anything yet.
     */
    public void rotate() {
        if (1 == 1) return;
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
    }

    /**
     * Returns an Iterator for the Shape's location Pairs.
     * @return
     */
    @Override
    public Iterator<Pair> iterator() {
        return squares.iterator();
    }

    /**
     * Returns the color.
     */
    public Color getColor() {
        return color;
    }
}
