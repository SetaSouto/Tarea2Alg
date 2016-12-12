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
        Node root = new Node("", null);
        Leaf banana = new Leaf("banana", 1, root);
        root.addChild(banana);

        // First case: implicit extension (rule 3).

        String extension = "banan";
        try {
            banana.extend(extension, 2, "");
            fail("Expected an ImplicitExtensionException to be thrown");
        } catch (ImplicitExtensionException e) {
            System.out.println(e.getMessage());
        }
        AbstractNode result = root.getChildren().get(0);

        assertEquals("banana", result.getEdge());

        // Second case: simple edge extension (rule 1).

        extension = "bananas";
        try {
            banana.extend(extension, 3, "");
        } catch (ImplicitExtensionException e) {
            fail("Unexpected ImplicitExtensionException thrown");
        }
        result = root.getChildren().get(0);

        assertEquals(6, banana.match(extension));
        assertEquals("bananas", result.edge);
        assertEquals(1, ((Leaf) result).getValue());

        // Third case: edge split (rule 2)

        extension = "banas";
        try {
            result.extend(extension, 4, "");
        } catch (ImplicitExtensionException e) {
            e.printStackTrace();
        }
        result = root.getChildren().get(0);
        Leaf child1 = (Leaf) result.getChildren().get(0);
        Leaf child2 = (Leaf) result.getChildren().get(1);

        assertEquals("bana", result.edge);
        assertEquals("nas", child1.edge);
        assertEquals("s", child2.edge);
        assertEquals(1, child1.getValue());
        assertEquals(4, child2.getValue());
    }
}