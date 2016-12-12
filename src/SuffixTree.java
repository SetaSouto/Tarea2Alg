import java.util.List;

class SuffixTree {
    private Root root;
    private String str;

    /**
     * Constructor. Receives a string and creates its suffix tree.
     */
    SuffixTree(String str) {
        this.root = new Root();
        this.str = str;
        this.makeTree();
    }

    /**
     * Returns the root of the tree.
     *
     * @return the root node.
     */
    Root getRoot () {
        return this.root;
    }

    /**
     * Returns the root's children.
     *
     * @return a list with the tree's root's children.
     */
    List<AbstractNode> getChildren () {
        return root.getChildren();
    }

    /**
     * Creates the suffix tree for str using Ukkonen's algorithm.
     */
    private void makeTree () {
        // Create first implicit suffix tree by adding the first character index.

        for (int i = 1; i <= str.length(); i++) {    // phase
            for (int j = 0; j < i; j++) {            // extension
                try {
                    root.extend(str.substring(j, i), j);
                } catch (ImplicitExtensionException e) {
                    break; // rule 3 ends the current phase
                }
            }
        }
    }
}
