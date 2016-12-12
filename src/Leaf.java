import java.util.List;

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
    Node getSuffixLink() {
        return null;
    }

    @Override
    void setLink(Node link) {

    }

    @Override
    void extend(String str, int j, String traversed) throws ImplicitExtensionException {
        int match = match(str);
        if (match < edgeLength() - 1) {
            if ((match == str.length() - 1)) { // if str fits completely in edge: implicit extension
                throw new ImplicitExtensionException(traversed + str);
            } else { // else mismatch or str ran out of cars before the edge: edge split extension
                splitEdge(match, str, j);
            }
        } else {
            edge = edge.concat(str.substring(str.length() - 1));
        }
    }

    @Override
    public List<AbstractNode> getChildren() {
        return null;
    }
}
