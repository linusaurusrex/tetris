// Linus Brogan, Thea Gordon-Wingfield, Lauren Keegan, Ena Zepcan
// TODOS:
// TIMER
// SPEED UP
// NEXT 3
// GHOST
// JANK
// BRACES

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tetris {
    private Board board;
    private Tetromino tetromino;
    private int delay;
    private boolean gameOver;

    public Tetris() {
        board = new Board();
        tetromino = new Tetromino();
        delay = 200;
        gameOver = false;
    }
    /**
     * Returns a random Shape.
     */
    private Tetromino randomShape() {
        return new Tetromino();
//        int i = StdRandom.uniform(7);
//        if (i == 0) return new Block();
//        if (i == 1) return new T();
//        if (i == 2) return new Zig();
//        if (i == 3) return new Zag();
//        if (i == 4) return new L();
//        if (i == 5) return new ReverseL();
//        if (i == 6) return new Bar();
//        return new Block();
    }

    /**
     * Constructs and runs a new game.
     */
    public static void main(String[] args) {
        new Tetris().run();
    }

    /**
     * Sets the falling shape's final location in the board and sets the next falling shape.
     */
    public void settle() {
        for (Pair square : tetromino)
            board.setSquare(square, tetromino.getColor());
        tetromino = null;
        draw();
        board.clear();
        StdDraw.pause(delay);
        tetromino = randomShape();
        if (!board.isInValidPosition(tetromino)) gameOver = true;
    }

    /**
     * Sets up and runs a game.
     */
    public void run() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-0.5, 21.5); // Set scale
        tetromino.shift(Pair.DOWN);
        tetromino.shift(Pair.DOWN);
        while (!gameOver) {
            // TODO: Bump down every 10 loops, draw 10x more
            draw();
            StdDraw.pause(delay);
            board.clear();
            handleKeys();
            tetromino.shift(Pair.DOWN);
            boolean valid = board.isInValidPosition(tetromino);
            tetromino.commitMove(valid);
            if (!valid) settle();
        }
        StdOut.println("OVER");
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.filledRectangle(15.5, 10.5, 3, 1.5);
        StdDraw.setPenColor();
        StdDraw.rectangle(15.5, 10.5, 3, 1.5);
        StdDraw.text(15.5, 10.5, "Game Over!!!");
        StdDraw.show();
    }

    /**
     * Draws the screen.
     */
    public void draw() {
        StdDraw.clear();
        // TODO: Draw sidebar
        // TODO: Draw score
        // TODO: Draw next 3 shapes
        // Draw help
        StdDraw.text(4.5, 10, "HELP:");
        StdDraw.text(4.5, 9, "Use arrow keys to move");
        StdDraw.text(4.5, 8, "left, right, and down.");
        StdDraw.text(4.5, 7, "Use up to rotate clockwise.");
        StdDraw.text(4.5, 6, "Use space to drop all the way.");

        // Draw main board
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.filledRectangle(15.5, 10.5, 6, 11);
        // Draw settled shapes
        for (int r = 1; r < board.ROWS; r++)
            for (int c = 0; c < board.COLUMNS; c++) {
                Pair here = new Pair(r, c);
                int x = c + 1 + board.COLUMNS;
                int y = board.ROWS - r;
                Color color = board.getSquare(here);
                if (color != null) {
                    StdDraw.setPenColor(color);
                    StdDraw.filledSquare(x, y, .5);
                }
                StdDraw.setPenColor();
                StdDraw.square(x, y, .5);
            }
        // Draw falling shape
        if (tetromino != null)
            for (Pair square : tetromino) {
                int x = square.getColumn() + 1 + board.COLUMNS;
                int y = board.ROWS - square.getRow();
                if (y != board.ROWS) {
                    StdDraw.setPenColor(tetromino.getColor());
                    StdDraw.filledSquare(x, y, .5);
                    StdDraw.setPenColor();
                    StdDraw.square(x, y, 0.5);
                }
            }
        // TODO: Draw ghost: Have clone of shape, drop all the way down.

        StdDraw.show();
    }

    /**
     * Handles user input.
     */
    void handleKeys() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
            tetromino.rotate();
            tetromino.commitMove(board.isInValidPosition(tetromino));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            tetromino.shift(Pair.LEFT);
            tetromino.commitMove(board.isInValidPosition(tetromino));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
            tetromino.shift(Pair.DOWN);
            boolean valid = board.isInValidPosition(tetromino);
            tetromino.commitMove(valid);
            if (!valid) settle();
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            tetromino.shift(Pair.RIGHT);
            tetromino.commitMove(board.isInValidPosition(tetromino));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
            for (int i = 0; i < board.ROWS; i++) {
                tetromino.shift(Pair.DOWN);
                tetromino.commitMove(board.isInValidPosition(tetromino));
            }
        draw();
    }
}
