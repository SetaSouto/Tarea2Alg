/**
 * Created by souto on 01-12-2016.
 *
 */
public class Edge {
    private String tag;
    private Node father;
    private Node child;

    /**
     * Constructor.
     * @param tag tag of the edge.
     * @param father father node.
     * @param child child node.
     */
    public Edge (String tag, Node father, Node child) {
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

}
