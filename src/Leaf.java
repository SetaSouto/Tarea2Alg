class Leaf extends AbstractNode {
    private int value;

    /**
     * Constructor.
     *
     * @param edge the initial edge value.
     * @param value initial value of the leaf.
     * @param father the father node.
     */
    Leaf(String edge, int value, INode father) {
        this.value = value;
        this.edge = edge;
        this.father = father;
    }

    /**
     * Returns the node's value.
     *
     * @return integer with the node's value.
     */
    int getValue () {
        return this.value;
    }

    @Override
    AbstractNode extend (String str, int j) {
        int match = match(str);
        if (match < edgeLength() - 1) {
            if (match == str.length() - 1) { // str fits completely in edge: implicit extension
                return this;
            } else { // mismatch or str ran out of cars before the edge: edge split extension
                return splitEdge(match, str, j);
            }
        } else {
            edge = edge.concat(str.substring(str.length() - 1));
            return this;
        }
    }
}
