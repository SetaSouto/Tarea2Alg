import org.junit.jupiter.api.Test;

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

}