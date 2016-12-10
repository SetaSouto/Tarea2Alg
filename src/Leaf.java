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
    void extend(String substr) {
        int match = match(substr.substring(0, substr.length() - 2));
        if (match == edgeLength()) {
            edge = edge.concat(substr.substring(substr.length() - 1));
        } else {
            throw new Error("Substring " + substr + " does not match at leaf " + edge);
        }
    }
}
