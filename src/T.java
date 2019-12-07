//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//
//public class T implements Shape {
//    private static final Color COLOR =  Color.MAGENTA;
//    private static List<List<Pair>> rotations = Arrays.asList(Arrays.asList(
//                    Pair.UP,
//                    Pair.LEFT,
//                    Pair.ORIGIN,
//                    Pair.RIGHT
//            ),
//            Arrays.asList(
//                    Pair.UP,
//                    Pair.ORIGIN,
//                    Pair.RIGHT,
//                    Pair.DOWN
//            ),
//            Arrays.asList(
//                    Pair.LEFT,
//                    Pair.ORIGIN,
//                    Pair.RIGHT,
//                    Pair.DOWN
//            ),
//            Arrays.asList(
//                    Pair.UP,
//                    Pair.LEFT,
//                    Pair.ORIGIN,
//                    Pair.DOWN
//            )
//    );
//    private Pair center = new Pair(1, 4);
//    private Pair oldCenter = center;
//    private int rotation = 0;
//    private int oldRotation = 0;
//
//    @Override
//    public void shift(Pair direction) {
//        oldCenter = center;
//        center = center.plus(direction);
//    }
//
//    @Override
//    public List<Pair> getSquares() {
//        List<Pair> squares =  new ArrayList<Pair>();
//        for (Pair offset : rotations.get(rotation))
//            squares.add(offset.plus(center));
//        return squares;
//    }
//
//    @Override
//    public void commitMove(boolean valid) {
//        if (valid) {
//            // TODO: Is this part necessary? Think about it.
//            oldCenter = center;
//            oldRotation = rotation;
//        } else {
//            center = oldCenter;
//            rotation = oldRotation;
//        }
//    }
//
//    @Override
//    public void rotate() {
//        oldRotation = rotation;
//        rotation++;
//        rotation %= rotations.size();
//
//    }
//
//
//    @Override
//    public Iterator<Pair> iterator() {
//        return getSquares().iterator();
//    }
//
//    @Override
//    public Color getColor() {
//        return COLOR;
//    }
//}
