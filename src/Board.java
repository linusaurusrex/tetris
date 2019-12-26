import java.awt.*;

public class Board {
    public static final int ROWS = 21;
    public static final int COLUMNS = 10;
    private Color[][] board = new Color[ROWS][COLUMNS];

    /**
     * Returns the requested square from the grid.
     */
    public Color getSquare(Pair square) {
        return board[square.getRow()][square.getColumn()];
    }

    /**
     * Sets the given square in the grid.
     */
    public void setSquare(Pair square, Color color) {
        board[square.getRow()][square.getColumn()] = color;
    }

    /**
     * Returns whether the given square is in the grid.
     */
    public boolean isValidLocation(Pair square) {
        int r = square.getRow();
        int c = square.getColumn();
        return r >= 0 && r < ROWS && c >= 0 && c < COLUMNS;
    }

    /**
     * Returns whether the current piece is in a valid position.
     */
    public boolean isInValidPosition(Tetromino tetromino) {
        for (Pair square : tetromino)
            if (!isValidLocation(square) || getSquare(square) != null)
                return false;
        return true;
    }

    /**
     * Returns whether the given row is full.
     */
    public boolean full(int row) {
        for (Color color : board[row])
            if (color == null)
                return false;
        return true;
    }

    /**
     * Returns whether the given row is empty.
     */
    public boolean empty(int row) {
        for (Color color : board[row])
            if (color != null)
                return false;
        return true;
    }

    /**
     * Clears full rows and shifts rows down to fill empty rows. Returns the number of full rows cleared.
     */
    int clear() {
        int clearedRows = 0;
        for (int r = ROWS - 1; r > 0; r--)
            if (full(r)) {
                board[r] = new Color[COLUMNS];
                clearedRows++;
            } else if (empty(r)) {
                board[r] = board[r - 1];
                board[r - 1] = new Color[COLUMNS];
            }
        return clearedRows;
    }
}
