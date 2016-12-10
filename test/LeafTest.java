import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeafTest {
    private static Leaf ba;
    private static Leaf na;

    // Setup

    @BeforeAll
    static void setup () {
        ba = new Leaf(0, "ba");
        na = new Leaf(10000000, "na");
    }

    // Inherited methods

    @Test
    void firstChar () {
        assertEquals('b', ba.firstChar());
        assertEquals('n', na.firstChar());
        assertNotEquals('b', na.firstChar());
    }

    @Test
    void edgeLength () {
        Leaf empty = new Leaf(1, "");

        assertEquals(0, empty.edgeLength());
        assertEquals(2, ba.edgeLength());
        assertNotEquals(3, ba.edgeLength());
    }

    @Test
    void getEdge () {
        assertEquals("ba", ba.getEdge());
        assertEquals("na", na.getEdge());
        assertNotEquals("ba", na.getEdge());
    }

    @Test
    void match () {
        Leaf banana = new Leaf(1, "banana");

        assertEquals(0, banana.match("b"));
        assertEquals(2, banana.match("ban"));
        assertEquals(5, banana.match("banana"));
        assertEquals(-1, banana.match("canana"));
    }

    @Test
    void splitEdge () {

    }

    // Leaf methods

    @Test
    void getValue () {
        assertEquals(0, ba.getValue());
        assertEquals(10000000, na.getValue());
        assertNotEquals(10000000, ba.getValue());
    }

    // Overridden methods
}