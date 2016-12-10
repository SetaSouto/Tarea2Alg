public class Leaf extends AbstractNode {
    private int value;

    /**
     * Constructor.
     *
     * @param value initial value of the leaf.
     * @param edge the initial edge value.
     */
    public Leaf(int value, String edge) {
        this.value = value;
        this.edge = edge;
    }

    /**
     * Returns the node's value.
     *
     * @return integer with the node's value.
     */
    public int getValue() {
        return this.value;
    }

    @Override
    void extend(String str, int j) {
        int match = match(str.substring(0, str.length() - 2));
        if (match == edgeLength()) {
            edge = edge.concat(str.substring(str.length() - 1));
        } else {
            throw new Error("Substring " + str + " does not match at leaf " + edge);
        }
    }
}
