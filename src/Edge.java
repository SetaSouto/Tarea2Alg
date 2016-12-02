/**
 * Created by souto on 01-12-2016.
 *
 */
public class Edge {
    private String tag;
    private INode child;

    /**
     * Constructor.
     * @param tag tag of the edge.
     * @param child child node.
     */
    public Edge (String tag, INode child) {
        this.tag = tag;
        this.child = child;
    }

    /**
     * Returns the tag of the edge.
     * @return the tag of the edge.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Adds a character at the end of the tag.
     * @param c
     */
    public void addChar(String c) {
        if (c.length() > 1) {
            throw new Error("String is not only one character.");
        }
        this.tag += c;
    }

    /**
     * Indicated the prefix of the tag to keep, splitEdge does a split of the edge, keeping prefix
     * as the tag, created a new Node that is connected with the former child. The Edge connecting
     * the new Node with the former Child has as tag the current tag without the prefix.
     * @param prefix prefix to keep of the tag.
     * @return the Node created during the split.
     */
    public Node splitEdge(String prefix) {
        if (this.tag.startsWith(prefix)) {
            String newTag = this.tag.substring(prefix.length());
            Edge newEdge = new Edge(newTag, this.child);
            // Now, modify this Edge:
            this.tag = prefix;
            Node node = new Node();
            node.addEdge(newEdge);
            this.child = node;
            return node;
        } else {
            throw new Error("String 'prefix' isn't a prefix of tag. Given: " + prefix + ", Tag: " + this.tag);
        }
    }

}
