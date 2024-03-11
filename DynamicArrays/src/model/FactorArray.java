package model;

import model.exeptions.ArrayIndexOutOfBound;

public class FactorArray<T> implements IArray<T> {

    private Object[] array;
    private int factor;
    private int size;

    public FactorArray(int factor, int initLength) {
        this.factor = factor;
        array = new Object[initLength];
        size = 0;
    }

    public FactorArray() {
        this(50, 10);
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
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)array[index];
    }


    @Override
    public void add(T item, int index) {
        if(index > size()) {
            throw new ArrayIndexOutOfBound(index);
        }
        if (size() == array.length)
            resize();

        size++;
        for (int i = size -1 ; i>=index; i--) {
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
        return obj;
    }

    private void resize() {
        Object[] newArray = new Object[array.length + array.length * factor / 100];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
