package org.otus.base;

import java.util.Arrays;

public class VectorIntArray {

    private int[] array;
    private final int vector;
    private int size;

    public VectorIntArray(int vector) {
        this.vector = vector;
        array = new int[0];
        size = 0;
    }

    public VectorIntArray() {
        this(10);
    }

    public int size() {
        return size;
    }

    public void add(int item) {
        if (size() == array.length)
            resize();
        array[size] = item;
        size++;
    }

    public void add(int item, int index) {
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

    public int remove(int index) {
        int obj = array[index];
        size--;

        for (int i =  index; i < size; i++) {
            array[i] = array[i+1];
        }
        array[size]=0;
        return obj;
    }



    public int get(int index) {
        return array[index];
    }

    private void resize() {
        int[] newArray = new int[array.length + vector];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public int[] array() {
        return Arrays.copyOf(array,size);
    }
}

