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
    void extend(String substring) {
        int match = match(substring.substring(0, substring.length() - 2));
        if (match == edgeLength()) { // the substring started with the complete edge
            for (AbstractNode node : children) { // descent through tree must continue
                if (node.firstChar() == substring.charAt(match)) {
                    // extend child with the unmatched remainder of the substring
                    node.extend(substring.substring(match, substring.length() - 1));
                }
            }
        } else {

        }
    }
}
