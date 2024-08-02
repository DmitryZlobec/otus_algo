package org.otus.base;

import java.util.Arrays;

public class VectorByteArray {

    private byte[] array;
    private final int vector;
    private int size;

    public VectorByteArray(int vector) {
        this.vector = vector;
        array = new byte[0];
        size = 0;
    }

    public VectorByteArray() {
        this(10);
    }

    public byte[] array() {
        return Arrays.copyOf(array,size);
    }

    public int size() {
        return size;
    }

    public void add(byte item) {
        if (size() == array.length)
            resize();
        array[size] = item;
        size++;
    }

    public void add(byte item, int index) {
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

    public byte remove(int index) {
        byte obj = array[index];
        size--;

        for (int i =  index; i < size; i++) {
            array[i] = array[i+1];
        }
        array[size]=0;
        return obj;
    }



    public byte get(int index) {
        return array[index];
    }

    private void resize() {
        byte[] newArray = new byte[array.length + vector];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}

