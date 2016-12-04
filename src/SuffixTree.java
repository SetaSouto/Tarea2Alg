/**
 * Created by souto on 04-12-2016.
 */
public class SuffixTree {
    private Node root;
    private String str;
    private Node lastVisited;

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
        int jL = 0;
        this.lastVisited = root;
        for (int i = 0; i < str.length() - 1; i++) {
            // Phase i
            for (int j = jL; j < i + 1; i++) {
                // TODO: Se setea la interpretación de 'e' como i+1 (?).
                int usedRule = this.extend(j, i);
                if (usedRule == 3) {
                    jL = j - 1;
                    break;
                } else {
                    jL = j;
                }
            }
        }
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
        return 0;
    }
}
