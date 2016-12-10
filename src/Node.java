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
    void addChild(AbstractNode node) {
        this.children.add(node);
    }

    /**
     * Returns the list of child nodes.
     *
     * @return a list of node objects.
     */
    public List<AbstractNode> getChildren () {
        return this.children;
    }

    @Override
    AbstractNode extend (String str, int j) {
        int match = match(str.substring(0, str.length() - 2));
        if (match == edgeLength() - 1) { // the substring started with the complete edge
            if (match == str.length() - 2) { // path down tree ended, extension must be made
                for (AbstractNode node : children) {
                    if (node.firstChar() == str.charAt(str.length() - 1)) {
                        return this; // if found, no extension is made (rule 3)
                    }
                }
                // if last char of substring not found in any path, a new node must be created
                addChild(new Leaf(j, str.substring(str.length() - 1)));
                return this;
            } else { // descent through tree must continue
                for (AbstractNode node : children) {
                    if (node.firstChar() == str.charAt(match)) {
                        // extend child with the unmatched remainder of the substring
                        AbstractNode extended = node.extend(str.substring(match, str.length() - 1), j);
                        children.remove(node);
                        addChild(extended);
                        return this;
                    }
                }
                throw new Error("No available path for str " + str + " in node " + edge);
            }
        } else { // str ran out of chars before edge ended, thus the edge must be split
            return splitEdge(match, str, j);
        }
    }
}
