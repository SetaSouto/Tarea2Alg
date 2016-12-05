public abstract class AbstractNode {

    protected int[] edge;

    public int[] getEdge () {
        return edge;
    }

    /**
     * Extends node with the character at the position i+1 of the suffix tree string.
     *  @param i phase index.
     * @param current current index in path.
     */
    abstract void extend(int i, int current);
}
