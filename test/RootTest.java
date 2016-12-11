import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RootTest {

    @Test
    void getChildren () {
        Leaf ba = new Leaf("ba", 1);
        Leaf na = new Leaf("na", 2);
        Root root = new Root();
        root.addChild(ba);
        root.addChild(na);
        root.addChild(na);

        assertEquals(3, root.getChildren().size());
        assertEquals(ba, root.getChildren().get(0));
        assertEquals(na, root.getChildren().get(1));
        assertEquals(na, root.getChildren().get(2));
    }

    @Test
    void extend () {
        Root root = new Root();

        // First extension
        root.extend("b", 1);
        assertEquals(1, root.getChildren().size());
        assertEquals("b", root.getChildren().get(0).edge);
        assertEquals(1, ((Leaf) root.getChildren().get(0)).getValue());

        // Second extension
        root.extend("a", 2);
        assertEquals(2, root.getChildren().size());
        assertEquals("b", root.getChildren().get(0).edge);
        assertEquals(1, ((Leaf) root.getChildren().get(0)).getValue());
        assertEquals("a", root.getChildren().get(1).edge);
        assertEquals(2, ((Leaf) root.getChildren().get(1)).getValue());

        // Third extension: extension happens the first child
        root.extend("ba", 3);
        assertEquals(2, root.getChildren().size());
        assertEquals("ba", root.getChildren().get(1).edge);
        assertEquals(1, ((Leaf) root.getChildren().get(1)).getValue());
        assertEquals("a", root.getChildren().get(0).edge);
        assertEquals(2, ((Leaf) root.getChildren().get(0)).getValue());
    }
}