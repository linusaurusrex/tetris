//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//
//public class Block implements Shape {
//    private static final Color COLOR =  Color.YELLOW;
//
//    private List<Pair> squares = Arrays.asList(
//        new Pair(0, 4),
//        new Pair(0, 5),
//        new Pair(1, 4),
//        new Pair(1, 5)
//    );
//    private List<Pair> oldSquares;
//
//    @Override
//    public void shift(Pair direction) {
//        oldSquares = squares;
//        squares = new ArrayList<Pair>();
//        for (Pair square : oldSquares)
//            squares.add(square.plus(direction));
//    }
//
//    @Override
//    public List<Pair> getSquares() {
//        return squares;
//    }
//
//    @Override
//    public void commitMove(boolean valid) {
//        if (!valid && oldSquares != null) squares = oldSquares;
//    }
//
//    @Override
//    public void rotate() {
//        // Does not do anything
//    }
//    @Override
//    public Iterator<Pair> iterator() {
//        return squares.iterator();
//    }
//
//    @Override
//    public Color getColor() {
//        return COLOR;
//    }
//}
