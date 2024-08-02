package org.otus.base;

import org.checkerframework.common.value.qual.IntRange;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class VectorBitArrayTest {

    @Test
    public void addBit() {
        VectorBitArray vectorBitArray = new VectorBitArray();
        vectorBitArray.addByte((byte) 15);
        System.out.println("vectorBitArray.size() = " + vectorBitArray.size());
    }

    @Test
    public void addInt() {
        VectorBitArray vectorBitArray = new VectorBitArray();
        vectorBitArray.addInt((byte) 15);
        vectorBitArray.addInt((byte) 14);
        System.out.println("vectorBitArray.size() = " + vectorBitArray.size());
    }

    @Test
    public void addArray() {
        VectorBitArray vectorBitArray = new VectorBitArray();
        int[] array = {1,2,3,4,5,6,7,8,9,10,11};
        System.out.println("array.length = " + array.length);
        for (int i: array) {
            vectorBitArray.addInt(i);
        }
        System.out.println("vectorBitArray.size() = " + vectorBitArray.size());
        System.out.println("vectorBitArray.size() = " + vectorBitArray.array().length);
    }
    @Test
    public void largeTest() {
        VectorBitArray vectorBitArray = new VectorBitArray();
        int[] array = new int[788];
        IntStream.range(0,788).forEach((i)-> array[i]=i);
        System.out.println("array.length = " + array.length);
        for (int i: array) {
            vectorBitArray.addInt(i);
        }
        System.out.println("vectorBitArray.size() = " + vectorBitArray.size());
        System.out.println("vectorBitArray.size() = " + vectorBitArray.array().length);
    }

}