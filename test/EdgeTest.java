import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    Leaf leaf = new Leaf(1);
    Edge edge = new Edge(5, 10, leaf);

    @Test
    void getTagIndex() {
        int[] index = new int[]{5, 10};
        for (int i=0; i<2; i++) {
            assertEquals(index[i], this.edge.getTagIndex()[i]);
        }
    }

    @Test
    void addCharIndex() {
        this.edge.addCharIndex(11);
        assertEquals(11, this.edge.getTagIndex()[1]);
    }

    @Test
    void getLength() {
        // This edge has as tag S[5..10], so it has 6 characters.
        assertEquals(6, this.edge.getLength());
    }

    @Test
    void getChild() {
        assertEquals(leaf, this.edge.getChild());
    }

    @Test
    void splitEdge() {
        Edge edge = new Edge(1, 10, new Leaf(10));
        Node innerNode = edge.splitEdge(5);
        int[] index = new int[]{1, 5};
        for (int i=0; i<2; i++) {
            assertEquals(index[i], edge.getTagIndex()[i]);
        }
        List<Edge> edges = innerNode.getEdges();
        // The innerNode has only one Edge:
        Edge newEdge = edges.get(0);
        index = new int[]{6, 10};
        for (int i=0; i<2; i++) {
            assertEquals(index[i], newEdge.getTagIndex()[i]);
        }
    }

}