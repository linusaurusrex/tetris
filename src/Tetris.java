import java.awt.*;
import java.awt.event.KeyEvent;

public class Tetris {
    private static final double MAX = 21.5;
    private static final double MIN = -0.5;
    private static final double MID = (MAX + MIN) / 2;
    private static final double WIDTH = MAX - MIN;


    private boolean gameOver = false;
    private int delay = 200;
    private Board board = new Board();
    private Shape shape = new Shape();

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
        shape = new Shape();
        if (!board.check(shape)) gameOver = true;
    }

    /**
     * Sets up and runs a game.
     */
    public void run() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-0.5, 21.5); // Set scale
        while (!gameOver) {
            draw();
            StdDraw.pause(delay);
            board.clear();
            handleKeys();
            shape.shift(new Pair(1, 0));
            boolean valid = board.check(shape);
            shape.commitMove(valid);
            if (!valid) settle();
        }
        StdOut.println("OVER");
    }

    /**
     * Draws the screen.
     */
    public void draw() {
        StdDraw.clear();
        // Draw sidebar
        // Draw score
        // Draw next 3 shapes
        // Draw help
        // Draw main board
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.filledRectangle(15.5, 10.5, 6, 11);
        // Draw settled shapes
        for (int r = 0; r < 20; r++)
            for (int c = 0; c < 10; c++) {
                Pair here = new Pair(r, c);
                int x = c + 11;
                int y = 20 - r;
                Color color = board.getSquare(here);
                if (color != null) {
                    StdDraw.setPenColor(color);
                    StdDraw.filledSquare(x, y, .5);
                }
                StdDraw.setPenColor();
                StdDraw.square(x, y, .5);
            }
        if (shape != null)
            for (Pair square : shape) {
                int x = square.getColumn() + 11;
                int y = 20 - square.getRow();
                StdDraw.setPenColor(shape.getColor());
                StdDraw.filledSquare(x, y, .5);
                StdDraw.setPenColor();
                StdDraw.square(x, y, 0.5);
            }
        // Draw ghost
        StdDraw.show();
    }

    /**
     * Accepts user input.
     */
    void handleKeys() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
            shape.rotate();
            shape.commitMove(board.check(shape));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            shape.shift(new Pair(0, -1));
            shape.commitMove(board.check(shape));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
            shape.shift(new Pair(1, 0));
            boolean valid = board.check(shape);
            shape.commitMove(valid);
            if (!valid) settle();
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            shape.shift(new Pair(0, 1));
            shape.commitMove(board.check(shape));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) { // Change to drop piece
            shape.shift(new Pair(1, 0));
            shape.commitMove(board.check(shape));
        }
        draw();
    }
}
