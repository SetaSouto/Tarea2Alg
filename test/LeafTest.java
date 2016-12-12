import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeafTest {
    private static Leaf ba;
    private static Leaf na;

    // Setup

    @BeforeAll
    static void setup () {
        ba = new Leaf("ba", 0, null);
        na = new Leaf("na", 10000000, null);
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
        Leaf empty = new Leaf("", 1, null);

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
        Leaf banana = new Leaf("banana", 1, null);

        assertEquals(0, banana.match("b"));
        assertEquals(2, banana.match("ban"));
        assertEquals(5, banana.match("banana"));
        assertEquals(-1, banana.match("canana"));
    }

    @Test
    void splitEdge () {
        Node root = new Node("", null);
        Leaf banana = new Leaf("banana", 1, root);
        root.addChild(banana);
        String extension = "banono";

        assertEquals(2, banana.match(extension));

        banana.splitEdge(2, extension, 2);
        Node result = (Node) root.getChildren().get(0);

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
        Node root = new Node("", null);
        Leaf banana = new Leaf("banana", 1, root);
        root.addChild(banana);
        String extension = "bananas";
        banana.extend(extension, 2, "");
        AbstractNode result = root.getChildren().get(0);

        assertEquals(6, banana.match(extension));
        assertEquals("bananas", result.edge);
        assertEquals(1, ((Leaf) result).getValue());

        // Second case: edge split (rule 2)
        extension = "banas";
        result.extend(extension, 3, "");
        result = root.getChildren().get(0);
        Leaf child1 = (Leaf) result.getChildren().get(0);
        Leaf child2 = (Leaf) result.getChildren().get(1);

        assertEquals("bana", result.edge);
        assertEquals("nas", child1.edge);
        assertEquals("s", child2.edge);
        assertEquals(1, child1.getValue());
        assertEquals(3, child2.getValue());
    }
}