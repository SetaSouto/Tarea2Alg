/**
 * Created by souto on 01-12-2016.
 *
 */
public class Edge {
    private String tag;
    private Node father;
    private INode child;

    /**
     * Constructor.
     * @param tag tag of the edge.
     * @param father father node.
     * @param child child node.
     */
    public Edge (String tag, Node father, INode child) {
        this.tag = tag;
        this.father = father;
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

}
