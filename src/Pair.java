// From Lines of Action by Linus Brogan and Lauren Keegan
import java.util.Objects;

public class Pair {
    private int row;
    private int column;

    @Override
    public String toString() {
        return "(" + this.row + ", " + this.column + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return row == pair.row &&
                column == pair.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    /**
     * Constructs a pair with the given row and column.
     */
    public Pair(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    /**
     * Returns the sum of `this` and the given Pair.
     */
    public Pair plus(Pair pair) {
        return new Pair(row + pair.row, column + pair.column);
    }

    /**
     * Returns the difference between `this` and the given Pair.
     */
    public Pair minus(Pair pair) {
        return new Pair(row - pair.row, column - pair.column);
    }

    /**
     * Returns the product of the `this` and the given scalar.
     */
    public Pair times(int factor) {
        return new Pair(row * factor, column * factor);
    }
}
