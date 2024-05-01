public class WNode<K extends Comparable<K>, W extends Comparable<W>,  V> {
    K key;
    V value;
    W weight;
    WNode<K,W, V> leftNode;
    WNode<K,W, V> rightNode;

    public WNode(K key, V value, W weight) {
        this.key = key;
        this.value = value;
        this.weight = weight;
    }
}
