import java.util.ArrayList;
import java.util.List;

/**
 * Created by souto on 01-12-2016.
 */
public class Node implements INode {
    private List<Edge> edges;
    private Node link;

    /**
     * Constructor. Initialises the collection of edges.
     */
    public Node() {
        this.edges = new ArrayList<>();
    }

    /**
     * Adds a edge to the node.
     *
     * @param edge edge to be added.
     */
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    /**
     * Getter of the collection of Edges.
     *
     * @return the edges of this node.
     */
    public List<Edge> getEdges() {
        return this.edges;
    }

    /**
     * Returns the Node linked by the suffix link of this Node.
     *
     * @return the node linked by this node's suffix link.
     */
    public Node getLink() {
        return this.link;
    }

    /**
     * Sets the suffix link for this node.
     *
     * @param n the node to be linked by this node.
     */
    public void setLink(Node n) {
        this.link = n;
    }

}
