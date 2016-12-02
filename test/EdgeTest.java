import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by souto on 01-12-2016.
 *
 */
class EdgeTest {
    Edge edge = new Edge("a", new Node(), new Leaf(1));
    @Test
    void getTag() {
        assertEquals("a", edge.getTag());
    }

    @Test
    void addChar() {
        edge.addChar("b");
        assertEquals("ab", edge.getTag());
        edge.addChar("c");
        assertEquals("abc", edge.getTag());
    }

    @Test
    void splitEdge() {
        Edge edge = new Edge("BANANA", new Node(), new Leaf(10));
        Node innerNode = edge.splitEdge("BAN");
        assertEquals("BAN", edge.getTag());
        List<Edge> edges = innerNode.getEdges();
        // The innerNode has only one Edge:
        Edge newEdge = edges.get(0);
        assertEquals("ANA", newEdge.getTag());
    }

}