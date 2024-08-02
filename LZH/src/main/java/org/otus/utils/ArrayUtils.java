package org.otus.utils;

public class ArrayUtils {
    public static byte[] right(byte[] line, int k) {
        byte[] returnArray = new byte[k];
        System.arraycopy(line,line.length -k,returnArray,0, k);
        return returnArray;
    }

    public static byte[] left(byte[] line, int k) {
        byte[] returnArray = new byte[k];
        System.arraycopy(line,0,returnArray,0, k);
        return returnArray;
    }

    public static byte[] left(byte[] line, int k, byte c) {
        byte[] returnArray = new byte[k+1];
        System.arraycopy(line,0,returnArray,0, k);
        returnArray[returnArray.length-1] = c;
        return returnArray;
    }

    public static byte getChar(byte[] alphas, byte search) {
        if(alphas.length == 255) {
            return search;
        }

        byte pos=0;
        for (byte b : alphas) {
            if (search == b) {
                return pos;
            }
            pos++;
        }
        return (byte) (alphas.length - 1);
    }

    public static String left(String line, int x) {
        return line.substring(0,x);
    }

    public static String right(String line, int x) {
        return  line.substring(line.length()-x);
    }

}
