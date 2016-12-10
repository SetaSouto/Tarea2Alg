abstract class AbstractNode {
    String edge;

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
     * @return the index (in the node's tag) where the first mismatch occurred.
     */
    int match (String str) {
        int lastMatch = -1;
        for (int i = 0; i < edge.length(); i++) {
            if (i >= str.length() || edge.charAt(i) != str.charAt(i)) break;
            lastMatch++;
        }
        return lastMatch == -1 ? -1 : ++lastMatch;
    }

    /**
     * Splits the edge on the match index, adding a new path for the extension defined by str.
     *
     * @param index the position where the second segment of the split edge begins.
     * @param str the substring with which the tree will be extended.
     * @param j extension index. If a new leaf node is created, this value will be assigned to it.
     * @return the uppermost node of the resulting split.
     */
    AbstractNode splitEdge(int index, String str, int j) {
        Node node = new Node(edge.substring(0, index));
        edge = edge.substring(index);
        node.addChild(this);
        node.addChild(new Leaf(j, str.substring(index)));
        return node;
    }

    /**
     * Extends the SuffixTree with a new substring. Extensions are made with the specified rules.
     *
     * @param str the substring with which the tree must be extended.
     * @param j extension index. If a new leaf node is created, this value will be assigned to it.
     * @return the resulting node after the extension.
     */
    abstract AbstractNode extend(String str, int j);
}
