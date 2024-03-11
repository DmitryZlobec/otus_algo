package model;

public interface IArray<T> {
    int size();
    void add(T item);
    T get(int index);
    // HW
    default void add(T item, int index) {
        throw new RuntimeException("Not implemented yet");
    } // with shift to tail
    default T remove(int index) {
        throw new RuntimeException("Not implemented yet");
    } // return deleted element
}
