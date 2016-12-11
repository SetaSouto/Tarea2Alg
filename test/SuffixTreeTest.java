import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuffixTreeTest {
    @Test
    void make () {
        SuffixTree tree = new SuffixTree("banana");
        Leaf child1 = (Leaf) tree.getRoot().getChildren().get(0);
        Leaf child2 = (Leaf) tree.getRoot().getChildren().get(1);
        Leaf child3 = (Leaf) tree.getRoot().getChildren().get(2);

        assertEquals(3, tree.getRoot().getChildren().size());
        assertEquals("banana", child1.getEdge());
        assertEquals("anana", child2.getEdge());
        assertEquals("nana", child3.getEdge());
    }
}