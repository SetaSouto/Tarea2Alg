import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    private static Node ba;
    private static Node na;

    // Setup

    @BeforeAll
    static void setup () {
        ba = new Node("ba");
        na = new Node("na");
    }

    // Static methos

    @Test
    void link () {
        Node a = new Node("a");

        Node.link(ba);

        assertTrue(Node.linkPending);
        assertEquals(ba, Node.toLink);
        assertEquals(0, ba.getSuffixLinks().size());
        assertEquals(0, a.getSuffixLinks().size());

        Node.link(a);

        assertFalse(Node.linkPending);
        assertEquals(1, ba.getSuffixLinks().size());
        assertEquals(a, ba.getSuffixLinks().get(0));
        assertEquals(0, a.getSuffixLinks().size());

        Node.link(a);

        assertTrue(Node.linkPending);
        assertEquals(a, Node.toLink);
        assertEquals(1, ba.getSuffixLinks().size());
        assertEquals(a, ba.getSuffixLinks().get(0));
        assertEquals(0, a.getSuffixLinks().size());

        Node.link(ba);

        assertFalse(Node.linkPending);
        assertEquals(1, ba.getSuffixLinks().size());
        assertEquals(a, ba.getSuffixLinks().get(0));
        assertEquals(1, a.getSuffixLinks().size());
        assertEquals(ba, a.getSuffixLinks().get(0));
    }

    @Test
    void checkLink () {
        assertFalse(Node.checkLink(ba, na));

        Node a = new Node("a");

        assertTrue(Node.checkLink(a, ba));
        assertTrue(Node.checkLink(na, a));
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
        Leaf empty = new Leaf("", 1);

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
        Node banana = new Node("banana");

        assertEquals(0, banana.match("b"));
        assertEquals(2, banana.match("ban"));
        assertEquals(5, banana.match("banana"));
        assertEquals(-1, banana.match("canana"));
    }

    @Test
    void splitEdge () {
        Node banana = new Node("banana");
        String extension = "banono";

        assertEquals(2, banana.match(extension));

        Node result = (Node) banana.splitEdge(2, extension, 2);

        assertEquals("ban", result.edge);
        assertEquals("ana", result.getChildren().get(0).edge);
        assertEquals("ono", result.getChildren().get(1).edge);
        assertEquals(0, ((Node) result.getChildren().get(0)).getChildren().size());
        assertEquals(2, ((Leaf) result.getChildren().get(1)).getValue());
    }

    // Node methods

    @Test
    void getSuffixLinks () {
        Node root = new Node("");
        root.addLink(ba);
        root.addLink(na);
        root.addLink(na);

        assertEquals(3, root.getSuffixLinks().size());
        assertEquals(ba, root.getSuffixLinks().get(0));
        assertEquals(na, root.getSuffixLinks().get(1));
        assertEquals(na, root.getSuffixLinks().get(2));
    }

    @Test
    void getChildren () {
        Node root = new Node("");
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
        // First case: implicit extension (rule 3)
        Node bana = new Node( "bana");
        bana.addChild(new Leaf("nas", 1));
        bana.addChild(new Leaf("so", 2));
        Node result = (Node) bana.extend("banas", 3);

        assertEquals(2, result.getChildren().size());
        assertEquals("nas", result.getChildren().get(0).edge);
        assertEquals("so", result.getChildren().get(1).edge);
        assertEquals(1, ((Leaf) result.getChildren().get(0)).getValue());
        assertEquals(2, ((Leaf) result.getChildren().get(1)).getValue());

        // Second case: simple Leaf extension (rule 2)
        result = (Node) result.extend("banar", 3);

        assertEquals(3, result.getChildren().size());
        assertEquals("nas", result.getChildren().get(0).edge);
        assertEquals("so", result.getChildren().get(1).edge);
        assertEquals("r", result.getChildren().get(2).edge);
        assertEquals(1, ((Leaf) result.getChildren().get(0)).getValue());
        assertEquals(2, ((Leaf) result.getChildren().get(1)).getValue());
        assertEquals(3, ((Leaf) result.getChildren().get(2)).getValue());

        // Third case: edge split (rule 2)
        result = (Node) result.extend("bas", 4);
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