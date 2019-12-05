// Linus Brogan, Thea Gordon-Wingfield, Lauren Keegan, Ena Zepcan

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tetris {
    private static final double MAX = 21.5;
    private static final double MIN = -0.5;

    private boolean gameOver = false;
    private int delay = 200;
    private Board board = new Board();
    private Shape shape = randomShape();

    /**
     * Returns a random Shape.
     */
    private Shape randomShape() {
        int i = StdRandom.uniform(7);
        i = StdRandom.uniform(4); // FOR DEV
        if (i == 0) return new Block();
        if (i == 1) return new T();
        if (i == 2) return new Zig();
        if (i == 3) return new Zag();
        //if (i == 4) return new L();
        //if (i == 5) return new ReverseL();
        //if (i == 6) return new Bar();
        return new Block();
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
        for (Pair square : shape)
            board.setSquare(square, shape.getColor());
        shape = null;
        draw();
        board.clear();
        StdDraw.pause(delay);
        shape = randomShape();
        if (!board.check(shape)) gameOver = true;
    }

    /**
     * Sets up and runs a game.
     */
    public void run() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-0.5, 21.5); // Set scale
        shape.shift(Pair.DOWN);
        shape.shift(Pair.DOWN);
        while (!gameOver) {
            // TODO: Bump down every 10 loops, draw 10x more
            draw();
            StdDraw.pause(delay);
            board.clear();
            handleKeys();
            shape.shift(Pair.DOWN);
            boolean valid = board.check(shape);
            shape.commitMove(valid);
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
        if (shape != null)
            for (Pair square : shape) {
                int x = square.getColumn() + 1 + board.COLUMNS;
                int y = board.ROWS - square.getRow();
                if (y != board.ROWS) {
                    StdDraw.setPenColor(shape.getColor());
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
            shape.rotate();
            shape.commitMove(board.check(shape));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            shape.shift(Pair.LEFT);
            shape.commitMove(board.check(shape));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
            shape.shift(Pair.DOWN);
            boolean valid = board.check(shape);
            shape.commitMove(valid);
            if (!valid) settle();
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            shape.shift(Pair.RIGHT);
            shape.commitMove(board.check(shape));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
            for (int i = 0; i < board.ROWS; i++) {
                shape.shift(Pair.DOWN);
                shape.commitMove(board.check(shape));
            }
        draw();
    }
}
