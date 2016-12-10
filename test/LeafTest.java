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

        assertEquals(1, banana.match("b"));
        assertEquals(3, banana.match("ban"));
        assertEquals(6, banana.match("banana"));
        assertEquals(-1, banana.match("canana"));
    }

    @Test
    void splitEdge () {
        Leaf banana = new Leaf(1, "banana");
        String extension = "banono";

        assertEquals(3, banana.match(extension));

        Node result = (Node) banana.splitEdge(3, extension, 2);

        assertEquals("ban", result.edge);
        assertEquals("ana", result.getChildren().get(0).edge);
        assertEquals("ono", result.getChildren().get(1).edge);
        assertEquals(1, ((Leaf) result.getChildren().get(0)).getValue());
        assertEquals(2, ((Leaf) result.getChildren().get(1)).getValue());
    }

    // Leaf methods

    @Test
    void getValue () {
        assertEquals(0, ba.getValue());
        assertEquals(10000000, na.getValue());
        assertNotEquals(10000000, ba.getValue());
    }

    // Overridden methods

    @Test
    void extend () {
        // First case: simple edge extension (rule 1).
        Leaf banana = new Leaf(1, "banana");
        String extension = "bananas";
        AbstractNode result = banana.extend(extension, 2);

        assertEquals(7, banana.match(extension));
        assertEquals("bananas", result.edge);
        assertEquals(1, ((Leaf) result).getValue());

        // Second case: edge split (rule 2)
        extension = "banas";
        result = result.extend(extension, 3);
        Leaf child1 = (Leaf) ((Node) result).getChildren().get(0);
        Leaf child2 = (Leaf) ((Node) result).getChildren().get(1);

        assertEquals("bana", result.edge);
        assertEquals("nas", child1.edge);
        assertEquals("s", child2.edge);
        assertEquals(1, child1.getValue());
        assertEquals(3, child2.getValue());
    }
}