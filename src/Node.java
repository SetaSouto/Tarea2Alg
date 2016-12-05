import java.util.ArrayList;
import java.util.List;

public class Node extends AbstractNode {
    private List<AbstractNode> children;

    /**
     * Constructor.
     */
    public Node () {
        this.children = new ArrayList<>();
    }

    public void addChild (AbstractNode node) {
        this.children.add(node);
    }

    public List<AbstractNode> getChildren () {
        return this.children;
    }

    @Override
    public void extend(int i, int current) {

    }
}
