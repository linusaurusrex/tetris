// Linus Brogan, Thea Gordon-Wingfield, Lauren Keegan, Ena Zepcan
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tetris {
    private static final int DELAY = 20;
    
    private Board board;
    private Tetromino tetromino;
    private boolean gameOver;
    private Queue<Tetromino> nextTetrominos;
    private long startTime;
    private long lastKeyPressTime;

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
    }

    /**
     * Constructs and runs a new game.
     */
    public static void main(String[] args) {
        new Tetris().run();
    }

    /**
     * Sets up and runs a game.
     */
    public void run() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-0.5, 21.5);
        int loops = 0;
        int loopsBeforeFall = 10;
        startTime = System.currentTimeMillis();
        draw();
        while (!gameOver) {
            StdDraw.pause(DELAY);
            if (System.currentTimeMillis() - lastKeyPressTime > 150) {
                handleKeys();
            }
            // Speed up
            if (loops % 1000 == 0 && loopsBeforeFall > 3) {
                loopsBeforeFall--;
            }
            // Drop tetrominos
            if (loops % loopsBeforeFall == 0) {
                board.clear();
                tetromino.shift(Pair.DOWN);
                boolean valid = board.isInValidPosition(tetromino);
                tetromino.commitMove(valid);
                if (!valid) {
                    handleKeys();
                    settle();
                }
                draw();
            }
            loops++;
        }
        // Draw Game Over
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.filledRectangle(15.5, 10.5, 3, 1.5);
        StdDraw.setPenColor();
        StdDraw.rectangle(15.5, 10.5, 3, 1.5);
        StdDraw.text(15.5, 10.5, "Game Over!");
        StdDraw.show();
        while (true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
                main(null);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                System.exit(0);
            }
        }
    }

    /**
     * Sets the falling shape's final location in the board and sets the next falling shape.
     */
    public void settle() {
        // Drop as far as possible
        for (int i = 0; i < board.ROWS; i++) {
            tetromino.shift(Pair.DOWN);
            tetromino.commitMove(board.isInValidPosition(tetromino));
        }
        // Move into board
        if (board.isInValidPosition(tetromino)) {
            for (Pair square : tetromino) {
                board.setSquare(square, tetromino.getColor());
            }
        }

        draw();
        StdDraw.pause(DELAY * 10);
        board.clear();
        tetromino = nextTetrominos.remove();
        nextTetrominos.add(new Tetromino());
        if (!board.isInValidPosition(tetromino)) {
            gameOver = true;
        }
        draw();
    }

    /**
     * Draws the screen.
     */
    public void draw() {
        StdDraw.clear();

        // Draw score
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledRectangle(4.5, 20, 5, 1.5);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(4.5, 20, "Time: " + (System.currentTimeMillis() - startTime) / 1000);

        // Draw next 3 shapes
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.filledRectangle(4.5, 12, 5, 6.5);
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.filledRectangle(4.5, 11.5, 3, 5);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(4.5, 17.5, "Next 3:");
        int top = 15;
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
        StdDraw.filledRectangle(4.5, 2.5, 5, 3);
        StdDraw.setPenColor();
        StdDraw.text(4.5, 4.5, "HELP:");
        StdDraw.text(4.5, 3.5, "Left, right, down: move");
        StdDraw.text(4.5, 2.5, "Up: rotate");
        StdDraw.text(4.5, 1.5, "Space: drop all the way down");
        StdDraw.text(4.5, 0.5, "P: pause, Q: quit, R: restart");

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

        // Draw falling tetromino and shadow
        if (board.isInValidPosition(tetromino)) {
            Tetromino shadow = tetromino.copy();
            for (int i = 0; i < 20; i++) {
                shadow.shift(Pair.DOWN);
                shadow.commitMove(board.isInValidPosition(shadow));
            }
            StdDraw.setPenColor(Color.GRAY);
            for (Pair square : shadow) {
                int x = square.getColumn() + 11;
                int y = board.ROWS - square.getRow();
                if (y != board.ROWS) {
                    StdDraw.filledSquare(x, y, .5);
                    StdDraw.setPenColor();
                    StdDraw.square(x, y, 0.5);
                }
            } // TODO: Remove extra top row from grid
            StdDraw.setPenColor(tetromino.getColor());
            for (Pair square : tetromino) {
                int x = square.getColumn() + 11;
                int y = board.ROWS - square.getRow();
                if (y != board.ROWS) {
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
    void handleKeys() {
        if (System.currentTimeMillis() - lastKeyPressTime < 100) return;
        if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
            lastKeyPressTime = System.currentTimeMillis();
            tetromino.rotate();
            tetromino.commitMove(board.isInValidPosition(tetromino));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            lastKeyPressTime = System.currentTimeMillis();
            tetromino.shift(Pair.LEFT);
            tetromino.commitMove(board.isInValidPosition(tetromino));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
            lastKeyPressTime = System.currentTimeMillis();
            tetromino.shift(Pair.DOWN);
            tetromino.commitMove(board.isInValidPosition(tetromino));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            lastKeyPressTime = System.currentTimeMillis();
            tetromino.shift(Pair.RIGHT);
            tetromino.commitMove(board.isInValidPosition(tetromino));
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            lastKeyPressTime = System.currentTimeMillis();
            settle();
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
            StdDraw.pause(DELAY * 10);
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
            while (StdDraw.isKeyPressed(KeyEvent.VK_P)) {
                // Wait for key up
            }
            startTime += System.currentTimeMillis() - offset;
        }
        draw();
    }
}
