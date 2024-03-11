package model;

import model.exeptions.ArrayIndexOutOfBound;

public class SingleArray<T> implements IArray<T> {

    private Object[] array;

    public SingleArray() {
        array = new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        add(item, size());
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index > size()) {
            throw new ArrayIndexOutOfBound(index);
        }
        return (T) array[index];
    }

    private void resize(int index) {
        if (index > size()) {
            throw new ArrayIndexOutOfBound(index);
        }
        Object[] newArray = new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index, newArray, index + 1, size() - index);
        array = newArray;
    }

    @Override
    public void add(T item, int index) {
        resize(index);
        array[index] = item;
    }

    @Override
    public T remove(int index) {
        T obj = get(index);
        Object[] newArray = new Object[size() - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size() - index - 1);
        array = newArray;
        return obj;
    }
}
