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
    AbstractNode extend(String str, int j) {
        int match = match(str.substring(0, str.length() - 2));
        if (match == edgeLength() - 1) {
            if (match == str.length() - 1) {
                edge = edge.concat(str.substring(str.length() - 1));
                return this;
            } else {
                throw new Error("Substring " + str + " does not match at leaf " + edge);
            }
        } else { // str ran out of chars before edge ended, thus the edge must be split
            return splitEdge(match, str, j);
        }
    }
}
