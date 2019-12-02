import java.awt.*;
import java.util.Arrays;

public class Board {
    private static final int ROWS = 20;
    private static final int COLUMNS = 10;
    private Color[][] board = new Color[ROWS][COLUMNS]; // Grid of settled shapes
    private Shape fallingShape;

    /**
     * Returns whether the current piece can move in the given direction;
     * @param direction
     * @return
     */
    public boolean canMove(Pair direction) {
        return false;
    }

    public void setSquare(Pair square, Color color) {
        board[square.getRow()][square.getColumn()] = color;
    }

    public Color getSquare(Pair square) {
        return board[square.getRow()][square.getColumn()];
    }

    public boolean isValid(Pair square) {
        int r = square.getRow();
        int c = square.getColumn();
        return r >= 0 && r < ROWS && c >= 0 && c < COLUMNS;
    }
    void clear() {
        for (int r = ROWS - 1; r > 0; r--) {
            if (full(board[r])) board[r] = new Color[COLUMNS];
            else if (empty(board[r])) {
                board[r] = board[r - 1];
                board[r - 1] = new Color[COLUMNS];
            }
        }
    }
    public boolean full(Color[] row) {
        for (Color color : row)
            if (color == null)
                return false;
        return true;
    }
    public boolean empty(Color[] row) {
        for (Color color : row)
            if (color != null)
                return false;
        return true;
    }
}
