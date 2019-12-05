import java.awt.Color;
import java.util.*;

public interface Shape extends Iterable<Pair> {
    /**
     * Moves the shape in the given direction.
     */
    public void shift(Pair direction);

    /**
     * Returns the Shape's location Pairs.
     */
    public List<Pair> getSquares();

    /**
     * Keeps move if valid. Restores previous position if not valid.
     */
    public void commitMove(boolean valid);

    /**
     * Rotates the shape clockwise about its center.
     */
    public void rotate();

    /**
     * Returns the color.
     */
    public Color getColor();
}
