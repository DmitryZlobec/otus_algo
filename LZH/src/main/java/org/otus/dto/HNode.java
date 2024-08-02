package org.otus.dto;

public class HNode implements Comparable<HNode>{
    private int symbol;
    private int freq;
    private HNode left;
    private HNode right;

    @Override
    public int compareTo(HNode o) {
        if(this.freq>o.freq)
            return 1;
        else if (this.freq<o.freq)
            return -1;
        return 0;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public HNode getLeft() {
        return left;
    }

    public void setLeft(HNode left) {
        this.left = left;
    }

    public HNode getRight() {
        return right;
    }

    public void setRight(HNode right) {
        this.right = right;
    }

}
