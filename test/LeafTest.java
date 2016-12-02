import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by souto on 01-12-2016.
 */
class LeafTest {
    @Test
    void getValue() {
        Leaf leaf = new Leaf(1);
        assertEquals(1, leaf.getValue());
    }

}