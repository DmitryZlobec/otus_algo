public class Node<K extends Comparable<K>,V> {
    K key;
    V value;
    Node<K,V> leftNode;
    Node<K,V> rightNode;

    public Node(K key, V value) {
        this.key=key;
        this.value=value;
    }

    public Node<K, V> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node<K, V> leftNode) {
        this.leftNode = leftNode;
    }

    public Node<K, V> getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node<K, V> rightNode) {
        this.rightNode = rightNode;
    }
}
