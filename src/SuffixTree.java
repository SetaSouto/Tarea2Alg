public class SuffixTree {
    private Node root;
    private String str;

    /**
     * Constructor. Receives a string and creates its suffix tree.
     */
    public SuffixTree(String str) {
        this.root = new Node();
        this.str = str;
        this.makeTree();
    }

    /**
     * Creates the suffix tree of the string.
     */
    private void makeTree() {

    }

    /**
     * Extends the current implicit suffix tree. Returns an integer indicating the rule used, it
     * could be 1, 2 or 3.
     *
     * @param j number of the extension.
     * @param i number of the phase.
     * @return the number of the rule used.
     */
    public int extend(int j, int i) {

    }
}
