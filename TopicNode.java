import java.util.ArrayList;
import java.util.List;

public class TopicNode extends RedBlackTree.Node<String> {
    public List<Question> questions = new ArrayList<>();

    public TopicNode() {
        super(null);
    }

    public TopicNode(String data) {
        super(data);
    }
}