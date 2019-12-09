import java.awt.Color;
import java.util.*;

public class Tetromino implements Iterable<Pair> {
    private static final List<Character> LETTERS = Arrays.asList('O', 'I', 'T', 'L', 'J', 'S', 'Z');

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tetromino pairs = (Tetromino) o;
        return rotation == pairs.rotation &&
                oldRotation == pairs.oldRotation &&
                i == pairs.i &&
                Objects.equals(center, pairs.center) &&
                Objects.equals(oldCenter, pairs.oldCenter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, oldCenter, rotation, oldRotation, i);
    }

    private static final List<Color> COLORS = Arrays.asList(
            Color.YELLOW, // O
            Color.CYAN, // I
            Color.MAGENTA, // T
            Color.ORANGE, // L
            Color.BLUE, // J
            Color.GREEN, // S
            Color.RED // Z
    );
    private static final List<List<List<Pair>>> ROTATIONS = Arrays.asList(
            Arrays.asList(
                    Arrays.asList( // O
                            Pair.UP,
                            Pair.UP_RIGHT,
                            Pair.ORIGIN,
                            Pair.RIGHT
                    )
            ),
            Arrays.asList( // I
                    Arrays.asList(
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.RIGHT,
                            Pair.RIGHT.times(2)
                    ),
                    Arrays.asList(
                            Pair.UP_RIGHT,
                            Pair.RIGHT,
                            Pair.DOWN_RIGHT,
                            Pair.DOWN.plus(Pair.DOWN_RIGHT)
                    ),
                    Arrays.asList(
                            Pair.DOWN_LEFT,
                            Pair.DOWN,
                            Pair.DOWN_RIGHT,
                            Pair.DOWN_RIGHT.plus(Pair.RIGHT)
                    ),
                    Arrays.asList(
                            Pair.UP,
                            Pair.ORIGIN,
                            Pair.DOWN,
                            Pair.DOWN.times(2)
                    )
            ),
            Arrays.asList( // T
                    Arrays.asList(
                            Pair.UP,
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.RIGHT
                    ),
                    Arrays.asList(
                            Pair.UP,
                            Pair.ORIGIN,
                            Pair.RIGHT,
                            Pair.DOWN
                    ),
                    Arrays.asList(
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.RIGHT,
                            Pair.DOWN
                    ),
                    Arrays.asList(
                            Pair.UP,
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.DOWN
                    )
            ),
            Arrays.asList( // L
                    Arrays.asList(
                            Pair.UP_RIGHT,
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.RIGHT
                    ),
                    Arrays.asList(
                            Pair.UP,
                            Pair.ORIGIN,
                            Pair.DOWN,
                            Pair.DOWN_RIGHT
                    ),
                    Arrays.asList(
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.RIGHT,
                            Pair.DOWN_LEFT
                    ),
                    Arrays.asList(
                            Pair.UP_LEFT,
                            Pair.UP,
                            Pair.ORIGIN,
                            Pair.DOWN
                    )
            ),
            Arrays.asList( // J
                    Arrays.asList(
                            Pair.UP_LEFT,
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.RIGHT
                    ),
                    Arrays.asList(
                            Pair.UP,
                            Pair.UP_RIGHT,
                            Pair.ORIGIN,
                            Pair.DOWN
                    ),
                    Arrays.asList(
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.RIGHT,
                            Pair.DOWN_RIGHT
                    ),
                    Arrays.asList(
                            Pair.UP,
                            Pair.ORIGIN,
                            Pair.DOWN_LEFT,
                            Pair.DOWN
                    )
            ),
            Arrays.asList( // S
                    Arrays.asList(
                            Pair.UP,
                            Pair.UP_RIGHT,
                            Pair.LEFT,
                            Pair.ORIGIN
                    ),
                    Arrays.asList(
                            Pair.UP,
                            Pair.ORIGIN,
                            Pair.RIGHT,
                            Pair.DOWN_RIGHT
                    ),
                    Arrays.asList(
                            Pair.ORIGIN,
                            Pair.RIGHT,
                            Pair.DOWN_LEFT,
                            Pair.DOWN
                    ),
                    Arrays.asList(
                            Pair.UP_LEFT,
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.DOWN
                    )
            ),
            Arrays.asList( // Z
                    Arrays.asList(
                            Pair.UP_LEFT,
                            Pair.UP,
                            Pair.ORIGIN,
                            Pair.RIGHT
                    ),
                    Arrays.asList(
                            Pair.UP_RIGHT,
                            Pair.ORIGIN,
                            Pair.RIGHT,
                            Pair.DOWN
                    ),
                    Arrays.asList(
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.DOWN,
                            Pair.DOWN_RIGHT
                    ),
                    Arrays.asList(
                            Pair.UP,
                            Pair.LEFT,
                            Pair.ORIGIN,
                            Pair.DOWN_LEFT
                    )
            )
    );

    /**
     * Constructs a random Tetromino.
     */
    public Tetromino() {
        i = StdRandom.uniform(LETTERS.size());
        center = new Pair(1, 4);
        oldCenter = center;
    }

    /**
     * Constructs the Tetromino matching the given letter or an arbitrary Tetromino if no match is found.
     */
    public Tetromino(char shape) {
        int i = LETTERS.indexOf(shape);
        this.i = i != -1 ? i : shape % LETTERS.size();
        center = new Pair(1, 4);
        oldCenter = center;
    }

    private Pair center;
    private Pair oldCenter;
    private int rotation;
    private int oldRotation;
    private int i;

    /**
     * Moves in the given direction.
     */
    public void shift(Pair direction) {
        center = center.plus(direction);
    }

    /**
     * Returns the four location Pairs.
     */
    public List<Pair> getSquares() {
        List<Pair> squares = new ArrayList<Pair>();
        for (Pair offset : ROTATIONS.get(i).get(rotation)) {
            squares.add(offset.plus(center));
        }
        return squares;
    }

    /**
     * Keeps current position if valid; restores previous position otherwise.
     */
    public void commitMove(boolean valid) {
        if (valid) {
            oldCenter = center;
            oldRotation = rotation;
        } else {
            center = oldCenter;
            rotation = oldRotation;
        }
    }

    /**
     * Rotates the tetromino clockwise about its center.
     */
    public void rotate() {
        rotation++;
        rotation %= ROTATIONS.get(i).size();
    }

    /**
     * Returns the color.
     */
    public Color getColor() {
        return COLORS.get(i);
    }

    /**
     * Returns an iterator for the location Pairs.
     */
    @Override
    public Iterator<Pair> iterator() {
        return getSquares().iterator();
    }

    /**
     * Returns a copy of `this`.
     */
    public Tetromino copy() {
        Tetromino clone = new Tetromino();
        clone.i = i;
        clone.center = center;
        clone.oldCenter = center;
        clone.rotation = rotation;
        clone.oldRotation = rotation;
        return clone;
    }
}
