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
     * Creates the suffix tree for str using Ukkonen's algorithm.
     */
    private void makeTree() {
        // Create first implicit suffix tree by adding the first character index.
        root.addEdge(new Edge(0,0, new Leaf(0)));

        for (int i = 0; i < str.length(); i++) {    // phase
            for (int j = 0; j < i; j++) {           // extension
                root.extend(i,j);
            }
        }
    }
}
