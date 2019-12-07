import java.awt.Color;
import java.util.*;

public class Tetromino implements Iterable<Pair> {
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

    public Tetromino() {
        i = StdRandom.uniform(COLORS.size());
    }
    public Tetromino(int i) {
        this.i = i % COLORS.size();
    }
    private Pair center = new Pair(1, 4);
    private Pair oldCenter = center;
    private int rotation = 0;
    private int oldRotation = 0;
    int i;

    /**
     * Moves the shape in the given direction.
     */
    public void shift(Pair direction) {
        oldCenter = center;
        center = center.plus(direction);
    }

    /**
     * Returns the Shape's location Pairs.
     */
    public List<Pair> getSquares() {
        List<Pair> squares =  new ArrayList<Pair>();
        for (Pair offset : ROTATIONS.get(i).get(rotation))
            squares.add(offset.plus(center));
        return squares;
    }

    /**
     * Keeps move if valid. Restores previous position if not valid.
     */
    public void commitMove(boolean valid) {
        if (valid) {
            // TODO: Is this part necessary? Think about it.
            oldCenter = center;
            oldRotation = rotation;
        } else {
            center = oldCenter;
            rotation = oldRotation;
        }
    }

    /**
     * Rotates the shape clockwise about its center.
     */
    public void rotate() {
        oldRotation = rotation;
        rotation++;
        rotation %= ROTATIONS.get(i).size();
    }
    /**
     * Returns the color.
     */
    public Color getColor() {
        return COLORS.get(i);
    }

    @Override
    public Iterator<Pair> iterator() {
        return getSquares().iterator();
    }
}
