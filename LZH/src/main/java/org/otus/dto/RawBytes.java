package org.otus.dto;

import org.otus.base.VectorByteArray;

public class RawBytes {
    byte[] raw;

    public RawBytes(byte[] raw) {
        this.raw = raw;
    }

    public byte[] getRaw() {
        return raw;
    }

    public void setRaw(byte[] raw) {
        this.raw = raw;
    }
}
