import java.awt.event.KeyEvent;

public class Tetris {
    public static void main(String[] args) {
        new Tetris().run();
    }

    public void run() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(); // Set scale
        while (true) {
            StdDraw.pause(10);
            //get input
            //draw
        }
    }

    void handleKeys() {
        if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {} // Handle A
    }
}
