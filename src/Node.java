import java.util.ArrayList;
import java.util.List;

/**
 * Created by souto on 01-12-2016.
 *
 */
public class Node implements INode {
    private List<Edge> edges;

    public Node() {
        this.edges = new ArrayList<>();
    }
}
