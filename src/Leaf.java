public class Leaf implements INode {
    private int value;

    /**
     * Constructor.
     * @param value initial value of the leaf.
     */
    public Leaf(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int extend(int i, int current) {
        return 0;
    }
}
