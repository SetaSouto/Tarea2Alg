import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    private static Node root;
    private static Node ba;
    private static Node na;

    // Setup

    @BeforeAll
    static void setup () {
        root = new Node("", null); // this line es repeated throughout, as the BeforeEach tag caused errors
        ba = new Node("ba", root);
        na = new Node("na", root);
    }

    // Static methods

    @Test
    void link () {
        root = new Node("", null);
        Node a = new Node("a", root);

        Node.link(ba);

        assertEquals(ba, Node.toLink);
        assertEquals(null, ba.getSuffixLink());
        assertEquals(null, a.getSuffixLink());

        Node.link(a);

        assertEquals(a, ba.getSuffixLink());
        assertEquals(null, a.getSuffixLink());

        Node.link(a);

        assertEquals(a, Node.toLink);
        assertEquals(a, ba.getSuffixLink());
        assertEquals(null, a.getSuffixLink());

        Node.link(ba);

        assertEquals(ba, a.getSuffixLink());
        assertEquals(a, ba.getSuffixLink());
    }

    @Test
    void checkLink () {
        root = new Node("", null);
        assertFalse(Node.checkLink(ba, na));

        Node a = new Node("a", null);

        assertTrue(Node.checkLink(a, ba));
        assertTrue(Node.checkLink(na, a));
    }

    // Inherited methods

    @Test
    void firstChar () {
        root = new Node("", null);
        assertEquals('b', ba.firstChar());
        assertEquals('n', na.firstChar());
        assertNotEquals('b', na.firstChar());
    }

    @Test
    void edgeLength () {
        root = new Node("", null);
        Leaf empty = new Leaf("", 1, null);

        assertEquals(0, empty.edgeLength());
        assertEquals(2, ba.edgeLength());
        assertNotEquals(3, ba.edgeLength());
    }

    @Test
    void getEdge () {
        root = new Node("", null);
        assertEquals("ba", ba.getEdge());
        assertEquals("na", na.getEdge());
        assertNotEquals("ba", na.getEdge());
    }

    @Test
    void match () {
        root = new Node("", null);
        Node banana = new Node("banana", null);

        assertEquals(0, banana.match("b"));
        assertEquals(2, banana.match("ban"));
        assertEquals(5, banana.match("banana"));
        assertEquals(-1, banana.match("canana"));
    }

    @Test
    void splitEdge () {
        root = new Node("", null);
        Node banana = new Node("banana", root);
        String extension = "banono";

        assertEquals(2, banana.match(extension));

        banana.splitEdge(2, extension, 2);
        Node result = (Node) root.getChildren().get(0);

        assertEquals("ban", result.edge);
        assertEquals("ana", result.getChildren().get(0).edge);
        assertEquals("ono", result.getChildren().get(1).edge);
        assertEquals(0, result.getChildren().get(0).getChildren().size());
        assertEquals(2, ((Leaf) result.getChildren().get(1)).getValue());
    }

    // Node methods

    @Test
    void getSuffixLinks () {
        root = new Node("", null);
        ba = new Node("ba", root);
        na = new Node("na", root);

        root.setLink(ba);
        assertEquals(ba, root.getSuffixLink());

        root.setLink(na);
        assertEquals(na, root.getSuffixLink());
    }

    @Test
    void getChildren () {
        root = new Node("", null);
        ba = new Node("ba", root);
        na = new Node("na", root);

        root.addChild(ba);
        root.addChild(na);
        root.addChild(na);

        assertEquals(3, root.getChildren().size());
        assertEquals(ba, root.getChildren().get(0));
        assertEquals(na, root.getChildren().get(1));
        assertEquals(na, root.getChildren().get(2));
    }

    // Overridden methods

    @Test
    void extend () {
        root = new Node("", null);
        Node bana = new Node( "bana", root);
        bana.addChild(new Leaf("nas", 1, bana));
        bana.addChild(new Leaf("so", 2, bana));
        root.addChild(bana);

        // First case: implicit extension (rule 3)

        try {
            bana.extend("banas", 3, "");
            fail("Expected an ImplicitExtensionException to be thrown");
        } catch (ImplicitExtensionException e) {
            System.out.println(e.getMessage());
        }
        Node result = (Node) root.getChildren().get(0);

        assertEquals(2, result.getChildren().size());
        assertEquals("nas", result.getChildren().get(0).edge);
        assertEquals("so", result.getChildren().get(1).edge);
        assertEquals(1, ((Leaf) result.getChildren().get(0)).getValue());
        assertEquals(2, ((Leaf) result.getChildren().get(1)).getValue());

        // Second case: simple Leaf extension (rule 2)

        try {
            result.extend("banar", 3, "");
        } catch (ImplicitExtensionException e) {
            fail("Unexpected ImplicitExtensionException thrown");
        }
        result = (Node) root.getChildren().get(0);

        assertEquals(3, result.getChildren().size());
        assertEquals("nas", result.getChildren().get(0).edge);
        assertEquals("so", result.getChildren().get(1).edge);
        assertEquals("r", result.getChildren().get(2).edge);
        assertEquals(1, ((Leaf) result.getChildren().get(0)).getValue());
        assertEquals(2, ((Leaf) result.getChildren().get(1)).getValue());
        assertEquals(3, ((Leaf) result.getChildren().get(2)).getValue());

        // Third case: edge split (rule 2)

        try {
            result.extend("bas", 4, "");
        } catch (ImplicitExtensionException e) {
            fail("Unexpected ImplicitExtensionException thrown");
        }
        result = (Node) root.getChildren().get(0);
        Node child1 = (Node) result.getChildren().get(0);
        Leaf child2 = (Leaf) result.getChildren().get(1);

        assertEquals("ba", result.edge);
        assertEquals(2, result.getChildren().size());

        assertEquals("s", child2.edge);
        assertEquals(4, child2.getValue());

        assertEquals("na", child1.edge);
        assertEquals(3, child1.getChildren().size());
        assertEquals("nas", child1.getChildren().get(0).edge);
        assertEquals("so", child1.getChildren().get(1).edge);
        assertEquals("r", child1.getChildren().get(2).edge);
        assertEquals(1, ((Leaf) child1.getChildren().get(0)).getValue());
        assertEquals(2, ((Leaf) child1.getChildren().get(1)).getValue());
        assertEquals(3, ((Leaf) child1.getChildren().get(2)).getValue());
    }
}