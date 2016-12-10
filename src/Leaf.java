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
}
