//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.awt.*;
//import java.util.*;
//import java.util.List;
//
//public class ZigTest {
//    @Test
//    void shiftsCorrectly() {
//        Shape shape = new Zig();
//        List<Pair> squares = shape.getSquares();
//        shape.shift(Pair.RIGHT);
//        List<Pair> newSquares = shape.getSquares();
//        assertEquals(squares.size(), newSquares.size());
//        for (int i = 0; i < squares.size(); i++)
//            assertEquals(squares.get(i).plus(Pair.RIGHT), newSquares.get(i));
//    }
//
//    @Test
//    void commitsMoveCorrectly() {
//        Shape shape = new Zig();
//        List<Pair> squares = shape.getSquares();
//        shape.shift(Pair.RIGHT);
//        shape.rotate();
//        List<Pair> newSquares = shape.getSquares();
//        shape.commitMove(false);
//        assertEquals(squares, shape.getSquares());
//        shape.shift(Pair.RIGHT);
//        shape.rotate();
//        shape.commitMove(true);
//        assertEquals(newSquares, shape.getSquares());
//
//    }
//
//    @Test
//    void rotatesCorrectly() {
//        Shape shape = new Zig();
//        shape.rotate();
//        List<Pair> squares = Arrays.asList(
//                new Pair(0, 4),
//                new Pair(1, 4),
//                new Pair(1, 5),
//                new Pair(2, 5)
//        );
//        assertEquals(squares, shape.getSquares());
//    }
//
//    @Test
//    void gettersWork() {
//        Shape shape = new Zig();
//        assertEquals(Color.GREEN, shape.getColor());
//        List<Pair> squares = Arrays.asList(
//                new Pair(0, 4),
//                new Pair(0, 5),
//                new Pair(1, 3),
//                new Pair(1, 4)
//        );
//        assertEquals(squares, shape.getSquares());
//    }
//}
