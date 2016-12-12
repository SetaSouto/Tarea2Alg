import java.util.List;

public interface INode {
    /**
     * Returns the list of child nodes.
     *
     * @return a list of node objects.
     */
    List<AbstractNode> getChildren();
}
