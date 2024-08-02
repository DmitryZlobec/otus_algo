package org.otus.base;

import java.io.Serializable;
import java.util.Arrays;

public class VectorBitArray implements Cloneable, Serializable {

    private byte[] array;
    private final int vector;
    private int size;
    private String controlString;

    public VectorBitArray(int vector) {
        this.vector = vector;
        array = new byte[vector];
        size = 0;
        controlString = "";
    }

    public VectorBitArray(byte[] array, int size, int vector, String str) {
        this.array = array;
        this.size = size;
        this.vector = vector;
        controlString = str;
    }

    public VectorBitArray() {
        this(1);
    }

    public byte[] array() {
        return Arrays.copyOf(array,array.length);
    }

    public int size() {
        return size;
    }

    public void add(boolean value) {
        int bytePlace = size/8;
        if (bytePlace == array.length)
            resize();
        if(value) {
            if (size != 0) {
                array[bytePlace] = (byte) (array[bytePlace] << 1);
            }
            array[bytePlace] = (byte) (array[bytePlace] |1);
            if(size<1024) {
                controlString=controlString+"1";
            }
        } else {
            array[bytePlace] = (byte) (array[bytePlace]<<1);
            if(size<1024) {
                controlString=controlString+"0";
            }
        }
        size++;
    }

    public void fill(boolean value) {
        int bytePlace = size/8;
        if (bytePlace == array.length)
            resize();
        if(value) {
            if (size != 0) {
                array[bytePlace] = (byte) (array[bytePlace] << 1);
            }
            array[bytePlace] = (byte) (array[bytePlace] |1);
            if(size<1024) {
                controlString=controlString+"X";
            }
        } else {
            array[bytePlace] = (byte) (array[bytePlace]<<1);
            if(size<1024) {
                controlString=controlString+"X";
            }
        }
    }

    public void addByte(byte b) {
        for (int i = 7; i >=0; i--) {
            add(getInByte(b,i));
        }
    }

    public void addInt(int it) {
        for(int k=4;k>=1;k--) {
            byte b = (byte) (it>>k*8);
            addByte(b);
        }
    }

    private void resize() {
        byte[] newArray = new byte[array.length + vector];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public VectorBitArray clone() {
        return new VectorBitArray(this.array(), this.size(), this.vector, this.controlString);
    }

    public boolean get(int place) {
        int bytePlace = place/8;
        int bitPlace = place%8;

        if(bitPlace == 7) {
            return (array[bytePlace] & 1) == 1;
        } else if(bitPlace == 6) {
            return (array[bytePlace] & 2) ==  2;
        } else if(bitPlace == 5) {
            return (array[bytePlace] & 4) == 4;
        } else if(bitPlace == 4) {
            return (array[bytePlace] & 8) == 8;
        }else if(bitPlace == 3) {
            return (array[bytePlace] & 16) ==  16;
        } else if(bitPlace == 2) {
            return (array[bytePlace] & 32) == 32;
        } else if(bitPlace == 1) {
            return (array[bytePlace] & 64) == 64;
        }else if(bitPlace == 0) {
            return (array[bytePlace] & 128) == 128;
        }
        throw new RuntimeException();
    }

    public boolean getOld(int place) {
        int bytePlace = place/8;
        int bitPlace = place%8;

        if(bitPlace == 0) {
            return (array[bytePlace] & 1) == 1;
        } else if(bitPlace == 1) {
            return (array[bytePlace] & 2) ==  2;
        } else if(bitPlace == 2) {
            return (array[bytePlace] & 4) == 4;
        } else if(bitPlace == 3) {
            return (array[bytePlace] & 8) == 8;
        }else if(bitPlace == 4) {
            return (array[bytePlace] & 16) ==  16;
        } else if(bitPlace == 5) {
            return (array[bytePlace] & 32) == 32;
        } else if(bitPlace == 6) {
            return (array[bytePlace] & 64) == 64;
        }else if(bitPlace == 7) {
            return (array[bytePlace] & 128) == 128;
        }
        throw new RuntimeException();
    }

    public boolean getBytePlace(int bitPlace, int bytePlace) {
        if(bitPlace == 0) {
            return (array[bytePlace] & 1) == 1;
        } else if(bitPlace == 1) {
            return (array[bytePlace] & 2) ==  2;
        } else if(bitPlace == 2) {
            return (array[bytePlace] & 4) == 4;
        } else if(bitPlace == 3) {
            return (array[bytePlace] & 8) == 8;
        }else if(bitPlace == 4) {
            return (array[bytePlace] & 16) ==  16;
        } else if(bitPlace == 5) {
            return (array[bytePlace] & 32) == 32;
        } else if(bitPlace == 6) {
            return (array[bytePlace] & 64) == 64;
        }else if(bitPlace == 7) {
            return (array[bytePlace] & 128) == 128;
        }
        throw new RuntimeException();
    }


    public boolean getInByteOld(byte bt,int place) {
        if(place == 7) {
            return (bt & 1) == 1;
        } else if(place == 6) {
            return (bt & 2) ==  2;
        } else if(place == 5) {
            return (bt & 4) == 4;
        } else if(place == 4) {
            return (bt & 8) == 8;
        }else if(place == 3) {
            return (bt & 16) ==  16;
        } else if(place == 2) {
            return (bt & 32) == 32;
        } else if(place == 1) {
            return (bt & 64) == 64;
        }else if(place == 0) {
            return (bt & 128) == 128;
        }
        throw new RuntimeException();
    }

    public boolean getInByte(byte bt,int place) {
        if(place == 0) {
            return (bt & 1) == 1;
        } else if(place == 1) {
            return (bt & 2) ==  2;
        } else if(place == 2) {
            return (bt & 4) == 4;
        } else if(place == 3) {
            return (bt & 8) == 8;
        }else if(place == 4) {
            return (bt & 16) ==  16;
        } else if(place == 5) {
            return (bt & 32) == 32;
        } else if(place == 6) {
            return (bt & 64) == 64;
        }else if(place == 7) {
            return (bt & 128) == 128;
        }
        throw new RuntimeException();
    }

    public byte getByte(int place) {
        byte out=0;
        for (int i = place+7; i >=place ; i--) {
            boolean value = get(i);
            if(value) {
//                if (i != place) {
                    out = (byte) (out << 1);
//                }
                out = (byte) (out |1);
            } else {
                out = (byte) (out<<1);
            }
        }
        return out;
    }

    public byte[] getArray() {
        return array;
    }

    public void setArray(byte[] array) {
        this.array = array;
    }

    public int getVector() {
        return vector;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

