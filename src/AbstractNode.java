abstract class AbstractNode {

    int[] edge;
    SuffixTree tree;

    /**
     * Returns tag of the edge leading to the node. The tag is defined as the starting and ending indexes
     * in the Suffix Tree string.
     *
     * @return integer pair with the starting index in the first position and ending index in the second position.
     */
    int[] getEdge() {
        return edge;
    }

    /**
     * Create a new internal node belonging to the same tree.
     *
     * @return a new Node object.
     */
    Node newNode() {
        return tree.newNode();
    }

    /**
     * Creates a new leaf node belonging to the same tree.
     *
     * @param value the value for the new leaf.
     * @return a new Leaf object.
     */
    Leaf newLeaf(int value) {
        return tree.newLeaf(value);
    }

    /**
     * Checks whether S[i:j] = S[edge[0]:edge[1]].
     *
     * @param i starting index for the substring to be matched.
     * @param j ending index for the substring to be matched.
     * @return
     */
    abstract boolean match(int i, int j);

    /**
     * Extends node with the character at the position i+1 of the suffix tree string.
     * @param i phase index.
     * @param j extension index.
     * @param current current index in path.
     */
    abstract void extend(int i, int j, int current);
}
