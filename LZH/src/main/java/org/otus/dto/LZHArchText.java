package org.otus.dto;

import org.otus.base.VectorBitArray;

import java.io.Serializable;
import java.util.HashMap;

public class LZHArchText implements Serializable {
    private VectorBitArray vectorBitArray;
    private HNode hNodeO;
    HashMap<Integer, String> codecMapOString;

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

    public HashMap<Integer, String> getCodecMapOString() {
        return codecMapOString;
    }

    public void setCodecMapOString(HashMap<Integer, String> codecMapOString) {
        this.codecMapOString = codecMapOString;
    }
}
