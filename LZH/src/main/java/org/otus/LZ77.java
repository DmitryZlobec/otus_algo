package org.otus;

import org.otus.base.VectorByteArray;
import org.otus.base.VectorIntArray;

import java.io.*;

public class LZ77 {
    static int WINDOW_SIZE = 4095;
    static int PATTERN_SIZE = 7;

    /**
     * Алгоритм сжатия LZ77
     * @param array массив входящий данных
     * @return  массив сжатых данны L, O, S
     */
    public int[] compressArray(byte[] array) {

        VectorIntArray out = new VectorIntArray();
        VectorByteArray token = new VectorByteArray();
        int prevPlace = 0;
        KMP kmp = new KMP();
        int windowPosition = 0;
        int startPosition = 0;
        for (int i = 0; i <array.length ; i++) {
            byte next = array[i];
            token.add(next);
            kmp.initDelta(token.array());
            int place = kmp.search(array,startPosition, windowPosition);
            if(place != -1  && token.size()<PATTERN_SIZE) {
                windowPosition++;
                prevPlace = place;
                continue;
            }
             int offset = token.size()-1>0 ?windowPosition-prevPlace -token.size()+1:0;
             int length = token.size()-1;
             out.add(offset);
             out.add(length);
             out.add(next);

            token = new VectorByteArray();
            windowPosition++;
            if(windowPosition-startPosition> WINDOW_SIZE) {
                startPosition = windowPosition - 1;
            }
        }
        if(token.size()>0) {
            int offset = windowPosition-prevPlace -token.size();;
            int length = token.size();
            out.add(offset);
            out.add(length);
        }
      return out.array();
    }


    /**
     * Алгоритм разархивирования LZ77
     * @param array массив сжатых данны L, O, S
     * @return  массив входящий данных
     */
    public byte[] decompress(int[] array) {
        VectorByteArray out =  new VectorByteArray();
        int i=0;
        while (i<array.length){
            int offset = array[i];
            i++;
            int length = array[i];
            if(offset!=0) {
                for (int j = 0; j < length; j++) {
                    out.add(out.get(out.size()-offset));
                }
            }
            i++;
            if(i<array.length) {
                int symbol = array[i];
                out.add((byte)symbol);
            }
            i++;
        }
        return out.array();
    }
}
