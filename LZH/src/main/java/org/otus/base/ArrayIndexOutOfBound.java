package org.otus.base;

public class ArrayIndexOutOfBound extends RuntimeException{
    public ArrayIndexOutOfBound(int index) {
        super(String.format("Index out of bound %s", index));
    }
}
