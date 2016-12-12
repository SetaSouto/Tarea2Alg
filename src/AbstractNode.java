abstract class AbstractNode implements INode {
    INode father;
    String edge;

    /**
     * Gets the first char of the edge leading to the node.
     *
     * @return the first char of the edge leading to the node.
     */
    char firstChar () {
        return edge.charAt(0);
    }

    /**
     * Gets the length of the edge leading to the node.
     *
     * @return the length of the edge leading to the node.
     */
    int edgeLength () {
        return edge.length();
    }

    /**
     * Returns the value of the edge leading to de node.
     *
     * @return string with the value of the edge leading to the node.
     */
    String getEdge () {
        return edge;
    }

    /**
     * Matches given substring with the node's tag.
     *
     * @return the index (in the node's tag) where the last match occurs
     */
    int match (String str) {
        int lastMatch = -1;
        for (int i = 0; i < edge.length(); i++) {
            if (i >= str.length() || edge.charAt(i) != str.charAt(i)) break;
            lastMatch++;
        }
        return lastMatch;
    }

    /**
     * Splits the edge on the match index, adding a new path for the extension defined by str.
     *
     * @param index the position where the first segment of the split result ends.
     * @param str the substring with which the tree will be extended.
     * @param j extension index. If a new leaf node is created, this value will be assigned to it.
     */
    void splitEdge (int index, String str, int j) {
        Node node = new Node(edge.substring(0, index + 1), father);
        Node.link(node);

        node.addChild(this);
        node.addChild(new Leaf(str.substring(index + 1), j, node));

        // Change reference in father
        father.getChildren().remove(this);
        father.getChildren().add(node);

        edge = edge.substring(index + 1);
        father = node;
    }

    /**
     * Returns the list of suffix links.
     *
     * @return a list of Node objects.
     */
    abstract Node getSuffixLink ();

    /**
     * Set the suffix link.
     *
     * @param link the suffix link destination node.
     */
    abstract void setLink (Node link);

    /**
     * Extends the SuffixTree with a new substring. Extensions are made with the specified rules.
     *
     * @param str the substring with which the tree must be extended.
     * @param j extension index. If a new leaf node is created, this value will be assigned to it.
     */
    abstract void extend (String str, int j);
}
