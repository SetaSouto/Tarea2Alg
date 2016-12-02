import java.util.ArrayList;
import java.util.List;

/**
 * Created by souto on 01-12-2016.
 *
 */
public class Node implements INode {
    private List<Edge> edges;

    /**
     * Constructor. Initialises the collection of edges.
     */
    public Node() {
        this.edges = new ArrayList<>();
    }

    /**
     * Adds a edge to the node.
     * @param edge edge to be added.
     */
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    /**
     * Getter of the collection of Edges.
     * @return
     */
    public List<Edge> getEdges() {
        return this.edges;
    }


}
