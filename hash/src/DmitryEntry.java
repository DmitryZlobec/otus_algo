import java.util.Map;

public class DmitryEntry<K,V> implements Map.Entry<K,V> {
    private final K key;
    private V value;

    public DmitryEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public V setValue(V value) {
        return this.value = value;
    }
}
