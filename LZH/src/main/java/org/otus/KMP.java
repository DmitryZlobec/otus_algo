package org.otus;

import org.otus.utils.ArrayUtils;
import org.otus.utils.SortingUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.otus.utils.ArrayUtils.left;
import static org.otus.utils.ArrayUtils.right;

public class KMP {

    byte[] alphabet;
    int[][] fsm;
    int length;

    public KMP() {
    }

    public void initDelta(byte[] pattern) {
        length = pattern.length;
        alphabet = getAlphabet(pattern);

        fsm = new int[length + 1][alphabet.length];
        IntStream.range(0, length + 1).forEach(q -> {
            for (byte c : alphabet) {
                int k = q + 1;
                if (q == length) k--;
                byte[] line = left(pattern, q, c);
                while (!Arrays.equals(left(pattern, k), right(line, k))) {
                    k--;
                }
                fsm[q][inArray(alphabet, c)] = k;
            }
        });
    }

    public void initDeltaStr(String pattern) {
        length = pattern.length();
        alphabet = getAlphabet(pattern.getBytes());
        fsm = new int[length + 1][alphabet.length];

        IntStream.range(0, length + 1).forEach(q -> {
            for (byte c : alphabet) {
                int k = q + 1;
                if (q == length) k--;
                String line = left(pattern, q) + (char) c;
                while (!left(pattern, k).equals(right(line, k))) {
                    k--;
                }
                fsm[q][c - alphabet[0]] = k;
            }
        });
    }


    public int search(byte[] text) {
        int q = 0;
        for (int i = 0; i < text.length; i++) {
            int aByte = ArrayUtils.getChar(alphabet,text[i]);
            int aByte1 =  ArrayUtils.getChar(alphabet,alphabet[0]); // alphabet[0];
            int i1 = aByte - aByte1;
            q = fsm[q][i1];
            if (q == length) {
                return i - length + 1;
            }
        }
        return -1;
    }

    public int search(byte[] text, int start, int stop) {
        int q = 0;
        for (int i = start; i < stop; i++) {
            int aByte = ArrayUtils.getChar(alphabet,text[i]);
            int aByte1 =  ArrayUtils.getChar(alphabet,alphabet[0]);
            int i1 = aByte - aByte1;
            q = fsm[q][i1];
            if (q == length) {
                return i - length + 1;
            }
        }
        return -1;
    }

    public static byte[] getAlphabet(byte[] pattern) {
        if (pattern.length == 0) {
            return new byte[0];
        }
        byte[] alphabetLong = new byte[pattern.length];
        System.arraycopy(pattern, 0, alphabetLong, 0, pattern.length);
        SortingUtils.qsort(alphabetLong);

        byte[] toSet = new byte[alphabetLong.length];
        byte max = alphabetLong[0];
        int size = 0;
        for (byte b : alphabetLong) {
            if (b > max) {
                size++;
                max = b;
            }
            toSet[size] = b;
        }
        byte[] returnArray;
        if(size<255) {
            returnArray = new byte[size + 2];
            System.arraycopy(toSet, 0, returnArray, 0, size+1);
            returnArray[size+1] = ++toSet[size];
        }
        else {
            returnArray = new byte[size];
            System.arraycopy(toSet, 0, returnArray, 0, size);
        }
        return returnArray;
    }

    private int inArray(byte[] array,byte b ) {
        int i =0 ;
        for (byte a: array) {
            if(a==b) {
                return  i;
            }
            i++;
        }
        return  -1;
    }
}
