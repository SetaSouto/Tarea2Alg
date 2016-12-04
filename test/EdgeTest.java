import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by souto on 01-12-2016.
 */
class EdgeTest {
    Edge edge = new Edge(5, 10, new Leaf(1));

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