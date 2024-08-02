package org.otus.dto;

import org.otus.base.VectorBitArray;

import java.io.Serializable;

public class LZHArchImage implements Serializable {
    private VectorBitArray vectorBitArray;
    private HNode hNodeO;
    private HNode hNodeB;

    public VectorBitArray getVectorBitArray() {
        return vectorBitArray;
    }

    public void setVectorBitArray(VectorBitArray vectorBitArray) {
        this.vectorBitArray = vectorBitArray;
    }

    public HNode gethNodeO() {
        return hNodeO;
    }

    public void sethNodeO(HNode hNodeO) {
        this.hNodeO = hNodeO;
    }

    public HNode gethNodeB() {
        return hNodeB;
    }

    public void sethNodeB(HNode hNodeB) {
        this.hNodeB = hNodeB;
    }
}
