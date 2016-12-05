import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeafTest {
    @Test
    void getValue() {
        Leaf leaf = new Leaf(1);
        assertEquals(1, leaf.getValue());
    }
}