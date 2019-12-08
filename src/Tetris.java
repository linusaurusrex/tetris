// Linus Brogan, Thea Gordon-Wingfield, Lauren Keegan, Ena Zepcan
// TODOS:
// JANK

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tetris {
    private Board board;
    private Tetromino tetromino;
    private int delay;
    private boolean gameOver;
    private Queue<Tetromino> nextTetrominos;
    private long start;

    /**
     * Construct a new game.
     */
    public Tetris() {
        board = new Board();
        tetromino = new Tetromino();
        nextTetrominos = new LinkedBlockingQueue<>();
        for (int i = 0; i < 3; i++) {
            nextTetrominos.add(new Tetromino());
        }
        delay = 200;
        gameOver = false;
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
        //TODO: wait before settling a little
        for (Pair square : tetromino) {
            board.setSquare(square, tetromino.getColor());
        }
        tetromino = null;
        draw();
        board.clear();
        StdDraw.pause(delay);
        tetromino = nextTetrominos.remove();
        nextTetrominos.add(new Tetromino());
        if (!board.isInValidPosition(tetromino)) {
            gameOver = true;
        }
    }

    /**
     * Sets up and runs a game.
     */
    public void run() {
        start = System.currentTimeMillis();
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-0.5, 21.5); // Set scale
        while (!gameOver) {
//            if (delay > 0) delay--;
            // TODO: Bump down every 10 loops, draw 10x more
            draw();
            StdDraw.pause(delay);
            board.clear();
            handleKeys();
            tetromino.shift(Pair.DOWN);
            boolean valid = board.isInValidPosition(tetromino);
            tetromino.commitMove(valid);
            if (!valid) {
                settle();
            }
        }
        StdOut.println("OVER");
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.filledRectangle(15.5, 10.5, 3, 1.5);
        StdDraw.setPenColor();
        StdDraw.rectangle(15.5, 10.5, 3, 1.5);
        StdDraw.text(15.5, 10.5, "Game Over!!!");
        StdDraw.show();
        while (!StdDraw.isKeyPressed(KeyEvent.VK_R)) {
            // Wait for keydown
        }
        main(null);
    }

    /**
     * Draws the screen.
     */
    public void draw() {
        StdDraw.clear();
        // Draw score
        StdDraw.text(4.5, 21, "Score: " + (System.currentTimeMillis() - start) / 1000);
        // Draw next 3 shapes
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.filledRectangle(4.5, 13.5, 3, 5);
        int top = 18;
        for (Tetromino tetromino : nextTetrominos) {
            for (Pair square : tetromino) {
                double x = square.getColumn() + 0.5;
                double y = top - square.getRow();
                StdDraw.setPenColor(tetromino.getColor());
                StdDraw.filledSquare(x, y, .5);
                StdDraw.setPenColor();
                StdDraw.square(x, y, 0.5);
            }
            top -= 3;
        }

        // Draw help
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.filledRectangle(4.5, 3, 5, 3.5);
        StdDraw.setPenColor();
        StdDraw.text(4.5, 5, "HELP:");
        StdDraw.text(4.5, 4, "Left, right, down: move");
        StdDraw.text(4.5, 3, "Up: rotate");
        StdDraw.text(4.5, 2, "Space: drop all the way down");
        StdDraw.text(4.5, 1, "P: pause, Q: quit, R: restart");

        // Draw main board
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.filledRectangle(15.5, 10.5, 6, 11);

        // Draw settled shapes
        for (int r = 1; r < board.ROWS; r++) {
            for (int c = 0; c < board.COLUMNS; c++) {
                Pair here = new Pair(r, c);
                int x = c + 11;
                int y = board.ROWS - r;
                Color color = board.getSquare(here);
                if (color != null) {
                    StdDraw.setPenColor(color);
                    StdDraw.filledSquare(x, y, .5);
                }
                StdDraw.setPenColor();
                StdDraw.square(x, y, .5);
            }
        }

        // Draw ghost and falling shape
        if (tetromino != null) {
            // TODO: Draw ghost: Have clone of shape, drop all the way down.
            Tetromino ghost = tetromino.clone();
            for (int i = 0; i < 20; i++) {
                ghost.shift(Pair.DOWN);
                ghost.commitMove(board.isInValidPosition(ghost));
            }
            for (Pair square : ghost) {
                int x = square.getColumn() + 11;
                int y = board.ROWS - square.getRow();
                if (y != board.ROWS) {
                    StdDraw.setPenColor(Color.GRAY);
                    StdDraw.filledSquare(x, y, .5);
                    StdDraw.setPenColor();
                    StdDraw.square(x, y, 0.5);
                }
            }
            for (Pair square : tetromino) {
                int x = square.getColumn() + 11;
                int y = board.ROWS - square.getRow();
                if (y != board.ROWS) {
                    StdDraw.setPenColor(tetromino.getColor());
                    StdDraw.filledSquare(x, y, .5);
                    StdDraw.setPenColor();
                    StdDraw.square(x, y, 0.5);
                }
            }
        }

        StdDraw.show();
    }

    /**
     * Handles user input.
     */
    void handleKeys() { // TODO: Refactor?
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
//            if (!valid) {
//                settle();
//            }
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
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
            main(null);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
            System.exit(0);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_P)) {
            long offset = System.currentTimeMillis();
            while (StdDraw.isKeyPressed(KeyEvent.VK_P)) {
                // Wait for key up
            }
            while (!StdDraw.isKeyPressed(KeyEvent.VK_P)) {
                // Wait for key down
            }
            start += System.currentTimeMillis() - offset;
        }
        draw();
    }
}
