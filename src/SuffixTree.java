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
     * Creates the suffix tree for str using Ukkonen's algorithm.
     */
    private void makeTree () {
        // Create first implicit suffix tree by adding the first character index.

        for (int i = 1; i <= str.length(); i++) {    // phase
            for (int j = 0; j < i; j++) {            // extension
                root.extend(str.substring(j, i), j);
            }
        }
    }
}
