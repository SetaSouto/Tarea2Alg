import java.util.ArrayList;
import java.util.List;

public class Root {
    private List<AbstractNode> children;

    public Root () {
        this.children = new ArrayList<>();
    }

    /**
     * Adds a new child node to this node's children list
     *
     * @param node the node to be included in the children list.
     */
    void addChild (AbstractNode node) {
        this.children.add(node);
    }

    /**
     * Returns the list of child nodes.
     *
     * @return a list of node objects.
     */
    List<AbstractNode> getChildren () {
        return this.children;
    }

    /**
     * Extends the SuffixTree with a new substring. Extensions are made with the specified rules.
     *
     * @param str the substring with which the tree must be extended.
     * @param j extension index. If a new leaf node is created, this value will be assigned to it.
     */
    void extend(String str, int j) {
        // Try to find a matching path for str
        for (AbstractNode child : children) {
            if (child.firstChar() == str.charAt(0)) {
                AbstractNode extended = child.extend(str, j);
                children.remove(child);
                addChild(extended);
                return;
            }
        }
        // If none available, add a new leaf node
        addChild(new Leaf(str, j));
    }
}
