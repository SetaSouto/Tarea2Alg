import java.util.ArrayList;
import java.util.List;

class Node extends AbstractNode {
    private List<AbstractNode> children;

    /**
     * Constructor.
     *
     * @param edge the initial edge value for the node.
     */
    public Node (String edge) {
        this.edge = edge;
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

    @Override
    AbstractNode extend (String str, int j) {
        int match = match(str);
        if (match < edgeLength() - 1) {
            if (match == str.length() - 1) { // str fits completely in edge: implicit extension
                return this;
            } else { // mismatch or str ran out of cars before the edge: edge split extension
                return splitEdge(match, str, j);
            }
        } else { // the substring started with the complete edge
            for (AbstractNode child : children) {
                if (child.firstChar() == str.charAt(match + 1)) {
                    // extend child with the unmatched remainder of the substring
                    AbstractNode extended = child.extend(str.substring(match + 1), j);
                    children.remove(child);
                    addChild(extended);
                    return this;
                }
            }
            // if last char of substring not found in any path, a new node must be created
            addChild(new Leaf(str.substring(match + 1), j));
            return this;
        }
    }
}
