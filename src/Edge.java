/**
 * Created by souto on 01-12-2016.
 */
public class Edge {
    private String tag;

    /**
     * Constructor.
     * @param tag tag of the edge.
     */
    public Edge (String tag) {
        this.tag = tag;
    }

    /**
     * Returns the tag of the edge.
     * @return the tag of the edge.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Sets tag to empty string.
     */
    public void removeTag() {
        this.tag = "";
    }

}
