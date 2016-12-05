public interface INode {
    /**
     * Extends the suffix tree recursively with the character S[i+1].
     * @param i         the phase index. Indicates that the tree will be extended with S[i+1].
     * @param current   the current substring index. Used to determine paths along the tree.
     * @param str       the suffix tree string.
     * @return          the extension rule used. Either 1, 2 or 3.
     */
    int extend(int i, int current, String str);
}
