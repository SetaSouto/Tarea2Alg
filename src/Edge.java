public class Edge {
    private int[] tag;
    private INode child;

    /**
     * Constructor.
     * The edge tag is defined by two integer values corresponding to the indexes of the first and last
     * characters of edge in the original string. If the edge is S[j..k], these indexes are i1 = j, i2 = k.
     *
     * @param i1    index of the first character of the tag.
     * @param i2    index of the last character of the tag.
     * @param child child node.
     */
    public Edge (int i1, int i2, INode child) {
        this.tag = new int[]{i1, i2};
        this.child = child;
    }

    /**
     * Returns the edge's tag.
     *
     * @return the edge's tag indexes.
     */
    public int[] getTag() {
        return this.tag;
    }

    /**
     * Returns the length of the edge.
     *
     * @return the tag's length.
     */
    public int getLength() {
        return this.tag[1] - this.tag[0] + 1;
    }

    /**
     * Returns the INode that is indicated by this Edge.
     *
     * @return INode indicated by this Edge.
     */
    public INode getChild() {
        return this.child;
    }

    public int extend (int i, int j) {
        // Rule 1:
    }

    /**
     * Adds a character at the end of the tag. It only needs the index of the character in the
     * real string S. If you want to add the character S[k], k must be provided.
     *
     * @param c index representing the character added to the tag.
     */
    public void addCharIndex (int c) {
        if (c == this.tag[1] + 1) {
            this.tag[1] = c;
        } else {
            throw new Error("You must add a character that is after the last character of the current tag");
        }
    }

    /**
     * Indicated the index where the tag must be divided (index is included in the first part of the
     * split), splitEdge does a split of the edge, keeping as tag its first index and the index
     * given like the last, and creates a new Node that is connected with the former child. The edge
     * that connects the new Node with the former child has as tag the index given plus one, and
     * the current last index, ie [untilIndex+1, this.tag[1]].
     *
     * @param untilIndex index until this edge has to keep its tag.
     */
    public Node splitEdge (int untilIndex) {
        if (untilIndex > this.tag[0] && untilIndex < this.tag[1]) {
            Edge newEdge = new Edge(untilIndex + 1, this.tag[1], this.child);
            this.tag[1] = untilIndex;
            Node node = new Node();
            node.addEdge(newEdge);
            this.child = node;
            return node;
        } else {
            throw new Error("The index given is not between the current index of this edge's tag.");
        }
    }

}
