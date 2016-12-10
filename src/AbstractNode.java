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
    int match (String substr) {
        int lastMatch = 0;
        for (int i = 0; i < edge.length(); i++) {
            if (edge.charAt(i) != substr.charAt(i)) lastMatch++;
        }
        return lastMatch;
    }

    /**
     * Gets the first char of the edge leading to the node.
     *
     * @return the first char of the edge leading to the node.
     */
    char firstChar() {
        return edge.charAt(0);
    }

    /**
     * Gets the length of the edge leading to the node.
     *
     * @return the length of the edge leading to the node.
     */
    int edgeLength() {
        return edge.length();
    }

    /**
     * Extends the SuffixTree with a new substring. Extensions are made with the specified rules.
     *
     * @param substring the substring with which the tree must be extended.
     */
    abstract void extend (String substring);
}
