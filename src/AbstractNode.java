abstract class AbstractNode {

    String edge;
    SuffixTree tree;

    /**
     * Returns the value of the edge leading to de node.
     *
     * @return string with the value of the edge leading to the node.
     */
    String getEdge() {
        return edge;
    }

    /**
     * Matches given substring with the node's tag.
     *
     * @return the index (in the node's tag) where the last matching character occurred.
     */
    int match (String substring) {
        edge.
    }

    /**
     * Extends node with the character at the position i+1 of the suffix tree string.
     * @param i phase index.
     * @param j extension index.
     * @param current current index in path.
     */
    //abstract void extend (int i, int j, int current);
}
