public interface ITree<K extends Comparable<K>, V> {

    V search(K key);
}
