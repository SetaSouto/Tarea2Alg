class Leaf extends AbstractNode {
    private int value;

    /**
     * Constructor.
     *
     * @param edge the initial edge value.
     * @param value initial value of the leaf.
     */
    Leaf(String edge, int value) {
        this.value = value;
        this.edge = edge;
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
    AbstractNode splitEdge(int index, String str, int j) {
        Node node = new Node(edge.substring(0, index + 1));
        Node.link(node);
        edge = edge.substring(index + 1);
        node.addChild(this);
        node.addChild(new Leaf(str.substring(index + 1), j));
        return node;
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
