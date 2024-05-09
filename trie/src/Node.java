import java.util.ArrayList;
import java.util.List;

public class Node {

    private static Integer SIZE = 128;

    private  Node[] nodes;

    private Object value;

    public Node() {
        List<Node> arr = new ArrayList<>();
        this.nodes = new Node[SIZE];
    }

    public  Node next(int c) {
        if(c>=SIZE) {
            return null;
        }
        return nodes[c];
    }

    public Node add(int ch) {
        Node node = new Node();
        if(this.nodes[ch] != null) {
            return this.nodes[ch];
        }
        this.nodes[ch] = node;
        return node;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
