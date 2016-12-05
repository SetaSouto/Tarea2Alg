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
        if (i == current) { // end of the search path
            if (i < edge[1]) {

            } else {
                for (AbstractNode node : children) {
                    if (node.getEdge()[0] == i + 1) { // rule 3
                        return;
                    }
                }
            }
        } else {
            for (AbstractNode node : children) {
                if (node.getEdge()[0] == current + 1) {
                    extend(i, current + 1);
                }
            }
        }
    }
}
