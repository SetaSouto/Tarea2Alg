import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuffixTreeTest {
    @Test
    void make () {
        SuffixTree tree = new SuffixTree("banana");
        Leaf child1 = (Leaf) tree.getChildren().get(0);
        Leaf child2 = (Leaf) tree.getChildren().get(1);
        Leaf child3 = (Leaf) tree.getChildren().get(2);

        // Check children

        assertEquals(3, tree.getRoot().getChildren().size());
        assertEquals("banana", child1.getEdge());
        assertEquals("anana", child2.getEdge());
        assertEquals("nana", child3.getEdge());
    }

    @Test
    void suffixLinkCreation () {
        SuffixTree tree = new SuffixTree("bananas");

        List<AbstractNode> children = tree.getChildren();
        assertEquals(4, children.size());

        Leaf child1 = (Leaf) children.get(0);
        Node child2 = (Node) children.get(1);
        Node child3 = (Node) children.get(2);
        Leaf child4 = (Leaf) children.get(3);

        assertEquals("bananas", child1.getEdge());
        assertEquals("na", child2.getEdge());
        assertEquals("a", child3.getEdge());
        assertEquals("s", child4.getEdge());
        assertEquals(tree.getRoot(), child1.father);
        assertEquals(tree.getRoot(), child2.father);
        assertEquals(tree.getRoot(), child3.father);
        assertEquals(tree.getRoot(), child4.father);


        // There should be a suffix link from child2 (na) to child3 (a)
        assertEquals(child3, child2.getSuffixLink());
        assertEquals(null, child3.getSuffixLink());

        // Child 2 branch
        assertEquals(2, child2.getChildren().size());
        Leaf child21 = (Leaf) child2.getChildren().get(0);
        Leaf child22 = (Leaf) child2.getChildren().get(1);

        assertEquals("nas", child21.getEdge());
        assertEquals("s", child22.getEdge());
        assertEquals(child2, child21.father);
        assertEquals(child2, child22.father);

        // Child 3 branch
        assertEquals(2, child3.getChildren().size());
        Node child31 = (Node) child3.getChildren().get(0);
        Leaf child32 = (Leaf) child3.getChildren().get(1);

        assertEquals("na", child31.getEdge());
        assertEquals("s", child32.getEdge());
        assertEquals(child3, child31.father);
        assertEquals(child3, child32.father);

        // There should be a suffix link from child31 (ana) to child2 (na)
        assertEquals(child2, child31.getSuffixLink());

        // Child 31 branch
        Leaf child311 = (Leaf) child31.getChildren().get(0);
        Leaf child312 = (Leaf) child31.getChildren().get(1);

        assertEquals("nas", child311.getEdge());
        assertEquals("s", child312.getEdge());
        assertEquals(child31, child311.father);
        assertEquals(child31, child312.father);
    }

    @Test
    void suffixLinkUse () {
        SuffixTree tree = new SuffixTree("bananaso");

        List<AbstractNode> children = tree.getChildren();
        assertEquals(4, children.size());

        Leaf child1 = (Leaf) children.get(0);
        Node child2 = (Node) children.get(1);
        Node child3 = (Node) children.get(2);
        Leaf child4 = (Leaf) children.get(3);

        assertEquals("bananaso", child1.getEdge());
        assertEquals("na", child2.getEdge());
        assertEquals("a", child3.getEdge());
        assertEquals("so", child4.getEdge());
        assertEquals(tree.getRoot(), child1.father);
        assertEquals(tree.getRoot(), child2.father);
        assertEquals(tree.getRoot(), child3.father);
        assertEquals(tree.getRoot(), child4.father);


        // There should be a suffix link from child2 (na) to child3 (a)
        assertEquals(child3, child2.getSuffixLink());
        assertEquals(null, child3.getSuffixLink());

        // Child 2 branch
        assertEquals(2, child2.getChildren().size());
        Leaf child21 = (Leaf) child2.getChildren().get(0);
        Leaf child22 = (Leaf) child2.getChildren().get(1);

        assertEquals("naso", child21.getEdge());
        assertEquals("so", child22.getEdge());
        assertEquals(child2, child21.father);
        assertEquals(child2, child22.father);

        // Child 3 branch
        assertEquals(2, child3.getChildren().size());
        Node child31 = (Node) child3.getChildren().get(0);
        Leaf child32 = (Leaf) child3.getChildren().get(1);

        assertEquals("na", child31.getEdge());
        assertEquals("so", child32.getEdge());
        assertEquals(child3, child31.father);
        assertEquals(child3, child32.father);

        // There should be a suffix link from child31 (ana) to child2 (na)
        assertEquals(child2, child31.getSuffixLink());

        // Child 31 branch
        Leaf child311 = (Leaf) child31.getChildren().get(0);
        Leaf child312 = (Leaf) child31.getChildren().get(1);

        assertEquals("nas", child311.getEdge());
        assertEquals("s", child312.getEdge());
        assertEquals(child31, child311.father);
        assertEquals(child31, child312.father);
    }
}