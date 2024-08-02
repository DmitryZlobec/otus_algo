package org.otus.dto;

import org.otus.base.VectorBitArray;

import java.util.HashMap;
import java.util.Map;

public class HuffmanCodecs {

    private final Map<Integer, Integer> lCodecMap;
    private final Map<Integer, Integer> oCodecMap;
    private final Map<Integer, Integer> bCodecMap;

    public HuffmanCodecs() {
        this.lCodecMap = new HashMap<>();
        this.oCodecMap = new HashMap<>();
        this.bCodecMap = new HashMap<>();
    }

    public Map<Integer, Integer> lCodecMap() {
        return lCodecMap;
    }

    public Map<Integer, Integer> oCodecMap() {
        return oCodecMap;
    }

    public Map<Integer, Integer> bCodecMap() {
        return bCodecMap;
    }
}
