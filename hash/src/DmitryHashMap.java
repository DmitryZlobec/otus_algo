import java.security.interfaces.ECKey;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DmitryHashMap<K,V> implements Map<K,V> {
    public static final int INITIAL_CAPACITY = 11;
    private ArrayList<LinkedList<Entry<K,V>>> internalTable;

    public DmitryHashMap() {
        this.internalTable = new ArrayList<>(INITIAL_CAPACITY);
        IntStream.range(0, INITIAL_CAPACITY).forEach(i-> internalTable.add(new LinkedList<>()));
    }

    @Override
    public int size() {
        return internalTable.stream()
                .mapToInt(Collection::size)
                .sum();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        int place = key.hashCode() % INITIAL_CAPACITY;
        Optional<Entry<K, V>> first = Optional.ofNullable(internalTable.get(place))
                .orElse(new LinkedList<>())
                .stream()
                .filter(k -> k.equals(key))
                .findFirst();
        return first.isPresent();
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        int place = key.hashCode() % INITIAL_CAPACITY;
        Optional<Entry<K, V>> first = Optional.ofNullable(internalTable.get(place))
                .orElse(new LinkedList<>())
                .stream()
                .filter(k -> k.getKey().equals(key))
                .findFirst();
        return first.map(Entry::getValue).orElse(null);
    }

    @Override
    public V put(K key, V value) {
        int place = key.hashCode() % INITIAL_CAPACITY;
        if(internalTable.get(place) == null) {
            internalTable.set(place, new LinkedList<>());
        }
            internalTable.get(place).add(new DmitryEntry<>(key,value));
        return value;
    }

    @Override
    public V remove(Object key) {
        Entry<K,V> entry = getEntry(key);
        int place = key.hashCode() % INITIAL_CAPACITY;
        if(internalTable.get(place) != null) {
            internalTable.get(place).remove(entry);
        }
        return entry.getValue();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public Collection<V> values() {
        return List.of();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Set.of();
    }

    @Override
    public String toString() {
    return       internalTable.stream()
                .flatMap(Collection::stream)
                .map(e-> String.format("%s -> %s", e.getKey(), e.getValue()))
                .collect(Collectors.joining(",\n"));
    }

    public Entry<K,V> getEntry(Object key) {
        int place = key.hashCode() % INITIAL_CAPACITY;
        Optional<Entry<K, V>> first = Optional.ofNullable(internalTable.get(place))
                .orElse(new LinkedList<>())
                .stream()
                .filter(k -> k.getKey().equals(key))
                .findFirst();
        return first.orElse(null);
    }
}
