package model;

import model.exeptions.ArrayIndexOutOfBound;

public class VectorArray<T> implements IArray<T> {

    private Object[] array;
    private int vector;
    private int size;

    public VectorArray(int vector) {
        this.vector = vector;
        array = new Object[0];
        size = 0;
    }

    public VectorArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size() == array.length)
            resize();
        array[size] = item;
        size++;
    }

    @Override
    public void add(T item, int index) {
        if(index > size()) {
            throw new ArrayIndexOutOfBound(index);
        }
        if (size() == array.length)
            resize();

        size++;
        for (int i = size -2 ; i>=index; i--) {
            array[i+1] = array[i];
        }

        array[index] = item;
    }

    @Override
    public T remove(int index) {
        T obj = (T) array[index];
        size--;

        for (int i =  index; i < size; i++) {
            array[i] = array[i+1];
        }
        array[size]=null;
        return obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)array[index];
    }

    private void resize() {
        Object[] newArray = new Object[array.length + vector];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
