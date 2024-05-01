public interface ITree<K extends Comparable<K>,V> {

    void add(K obj, V value);

    void remove(K obj);

    V search(K obj);
}
