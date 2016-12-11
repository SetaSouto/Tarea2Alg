import java.util.ArrayList;
import java.util.List;

class Node extends AbstractNode {
    static Node toLink = new Node("", null);

    /**
     * Sets up the received Node for a suffix link. If there is a Node with a pending link,
     * the received node corresponds its destination node (property 1).
     *
     * @param node a newly created node that needs linking.
     */
    static void link (Node node) {
        if (checkLink(node, toLink)) {
            toLink.setLink(node);
        }
        toLink = node;
    }

    /**
     * Checks whether the given nodes should be suffix linked, that is, one's edge has the form a, while
     * the second has the form xa, where a is a (possibly empty) string and x a character.
     *
     * @param node1 the first node being checked.
     * @param node2 the second node being checked.
     * @return whether there should be a suffix link between both nodes.
     */
    static boolean checkLink (Node node1, Node node2) {
        String edge1, edge2;
        if (node1.edgeLength() == 1 + node2.edgeLength()) {
            edge1 = node1.getEdge();
            edge2 = node2.getEdge();
        } else if (node2.edgeLength() == 1 + node1.edgeLength()) {
            edge1 = node2.getEdge();
            edge2 = node1.getEdge();
        } else {
            return false;
        }

        boolean cond = true;
        for (int i = 0; i < edge2.length(); i++) {
            if (edge1.charAt(i + 1) != edge2.charAt(i)) {
                cond = false;
                break;
            }
        }

        return cond;
    }

    private List<AbstractNode> children;
    private Node suffixLink;

    /**
     * Constructor.
     *
     * @param edge the initial edge value for the node.
     * @param father the father node.
     */
    public Node (String edge, INode father) {
        this.edge = edge;
        this.father = father;
        this.children = new ArrayList<>();
        this.suffixLink = null;
    }

    /**
     * Set the suffix link.
     *
     * @param link the suffix link destination node.
     */
    void setLink (Node link) {
        this.suffixLink = link;
    }

    /**
     * Returns the list of suffix links.
     *
     * @return a list of Node objects.
     */
    Node getSuffixLink () {
        return this.suffixLink;
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
            Root.vString = Root.vString.concat(edge);
            if (suffixLink == null) Root.vNode = this;

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
            addChild(new Leaf(str.substring(match + 1), j, this));
            return this;
        }
    }
}
