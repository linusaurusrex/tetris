import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairTest {
    @Test
    public void gettersWork() {
        Pair pair = new Pair(3, 5);
        assertEquals(3, pair.getRow());
        assertEquals(5, pair.getColumn());
    }

    @Test
    public void timesWorks() {
        Pair pair = new Pair(3, 5).times(4);
        assertEquals(12, pair.getRow());
        assertEquals(20, pair.getColumn());
    }

    @Test
    public void plusWorks() {
        Pair pair = new Pair(3, 5).plus(new Pair(2, 4));
        assertEquals(5, pair.getRow());
        assertEquals(9, pair.getColumn());
    }

    @Test
    public void minusWorks() {
        Pair pair = new Pair(3, 5).minus(new Pair(2, 4));
        assertEquals(1, pair.getRow());
        assertEquals(1, pair.getColumn());
    }

    @Test
    public void toStringWorks() {
        Pair pair = new Pair(3, 5);
        assertEquals("(3, 5)", pair.toString());
    }

    @Test
    public void equalsWorks() {
        Pair a = new Pair(3, 5);
        Pair b = new Pair(3, 5);
        assertEquals(a, b);
    }

}
