import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuffixTreeTest {
    @Test
    void match() {
        SuffixTree tree = new SuffixTree("banana");
        assertTrue(tree.match(1,1,1,1));
        assertTrue(tree.match(1,1,5,5));
        assertTrue(tree.match(1,2,3,4));
        assertFalse(tree.match(0,0,1,1));
        assertFalse(tree.match(0,0,1,5));
    }
}