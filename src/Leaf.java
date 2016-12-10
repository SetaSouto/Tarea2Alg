public class Leaf extends AbstractNode {
    private int value;

    /**
     * Constructor.
     * @param value initial value of the leaf.
     */
    public Leaf(SuffixTree tree, int value) {
        this.tree = tree;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public void extend(int i, int j, int current) {
        if (current == i) {         // end of the path
            if (i == edge[1]) {
                edge[1]++;          // Rule 1: i+1 added to the edge leading to the leaf.
            }
        } else {
            throw new Error("Leaf reached before the end of the search path");
        }
    }
}
