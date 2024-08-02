package org.otus.utils;

import org.otus.base.VectorIntArray;
import org.otus.dto.HNode;
import org.otus.base.VectorBitArray;
import org.otus.dto.HuffmanCodecs;
import org.otus.dto.LZHArchImage;
import org.otus.dto.LZHArchText;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

public class HuffmanUtils {



    public static int[] decompressText(LZHArchText lzhArchText) {
        VectorIntArray out = new VectorIntArray();
        VectorBitArray input = lzhArchText.getVectorBitArray();
        HNode hNodeO = lzhArchText.gethNodeO();
        int lenght =  input.size();
        int place =0;
        while (place<lenght) {

            int d =0;
            int bit=1;
            for (int i = place; i < place + 12; i++) {
                boolean b = input.get(i);
                d = d | (b ? bit: 0);
                bit = bit<<1;
            }

            int t= d;
            out.add(t);
            place = place+12;
            HNode tempNode = hNodeO;
            while (tempNode.getLeft() != null) {
                boolean b = input.get(place);
                place++;
                if(b) {
                    tempNode = tempNode.getRight();
                } else {
                    tempNode = tempNode.getLeft();
                }

            }

            int symbol = tempNode.getSymbol();
            out.add(symbol);

            int s =0;
            if (place != lenght) {
                for (int i = place; i < place + 8; i++) {
                    if (input.get(i)) {
                        if (i != place) {
                            s = s << 1;
                        }
                        s = s | 1;
                    } else {
                        s = s << 1;
                    }
                }
                out.add(s);
                place = place + 8;
            }
        }
        return out.array();
    }

    /**
     * Разжатие  по Хаффману
     * @param array сжатые данные
     * @return воходящий массив LZ77
     */
    public static int[] decompressTextByte(byte[] array) {
        byte[] bytes = new byte[4];
        System.arraycopy(array,0, bytes,0,bytes.length);
        int sizeVectorBit = ByteBuffer.wrap(bytes).getInt();
        byte[] inArray = new byte[array.length-4];
        System.arraycopy(array,4, inArray,0,inArray.length);

        VectorIntArray out = new VectorIntArray();
        VectorBitArray input = new VectorBitArray(inArray, sizeVectorBit,1,"" );
        int lenght =  input.size();
        int place =0;

        byte size = input.getArray()[0];
        place=place+8;
        HNode rootNode = new HNode();
        HNode currentNode = rootNode;

        for (int i = 1; i < size; i++) {
            HNode leftNode = new HNode();
            HNode rightNode = new HNode();
            currentNode.setLeft(leftNode);
            currentNode.setRight(rightNode);
            byte aByte = input.getArray()[i];
            leftNode.setSymbol(aByte);
            currentNode = rightNode;
            place=place+8;
        }
        byte aByte = input.getArray()[size];
        currentNode.setSymbol(aByte);
        place = place+8;
        while (place<lenght) {

            int d =0;
            int bit=1;
            for (int i = place; i < place + 12; i++) {
                boolean b = input.get(i);
                d = d | (b ? bit: 0);
                bit = bit<<1;
            }

            int t= d;
            out.add(t);
            place = place+12;
            HNode tempNode = rootNode;
            while (tempNode.getLeft() != null) {
                boolean b = input.get(place);
                place++;
                if(b) {
                    tempNode = tempNode.getRight();
                } else {
                    tempNode = tempNode.getLeft();
                }

            }

            int symbol = tempNode.getSymbol();
            out.add(symbol);

            int s =0;
            if (place != lenght) {
                for (int i = place; i < place + 8; i++) {
                    if (input.get(i)) {
                        if (i != place) {
                            s = s << 1;
                        }
                        s = s | 1;
                    } else {
                        s = s << 1;
                    }
                }
                out.add(s);
                place = place + 8;
            }
        }
        return out.array();
    }

    /**
     * Сжатие по Хаффману
     * @param array воходящий массив LZ77
     * @return сжатые данные
     */
    public static byte[] compressTextByte(int[] array) {
        LZHArchText lzhArchText = new LZHArchText();
        VectorBitArray out = new VectorBitArray(8);
        lzhArchText.setVectorBitArray(out);
        var frequencyMap = HuffmanUtils.getFrequency(array);

        HNode hNodeO = HuffmanUtils.buildTrie(frequencyMap.oCodecMap());

        HashMap<Integer, VectorBitArray> codecMapO = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeO, new VectorBitArray(),codecMapO);


        HashMap<Integer, String> codecMapOString = new HashMap<>();
        HuffmanUtils.buildCodecMapString(hNodeO, "",codecMapOString);

        int size = codecMapOString.entrySet().size();
        List<Integer> offsets = codecMapOString.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).toList();

        out.addByte((byte) size);
        offsets.forEach(i-> out.addByte(i.byteValue()));

        lzhArchText.setCodecMapOString(codecMapOString);
        lzhArchText.sethNodeO(hNodeO);
        int j=0;
        while (j<array.length) {

            for (int i = 0; i <12 ; i++) {
                out.add(getInByte(array[j],i));
            }
            j++;

            int key = array[j];
            String codecO = codecMapOString.get(key);
            String[] split = codecO.split("");
            for (String s : split) {
                out.add("1".equals(s));
            }
            j++;

            if (j < array.length) {
                out.addByte((byte)array[j]);
                j++;
            }

        }
        int tail = out.size() % 8;
        if(tail >0 ) {
        int fill = 8 - tail;
            for (int i = 0; i < fill; i++) {
                out.fill(false);
            }
        }

        byte[] bytes = ByteBuffer.allocate(4).putInt(out.size()).array();
        byte[] outArray = new byte[bytes.length+out.getArray().length];
        System.arraycopy(bytes,0, outArray,0,bytes.length);
        System.arraycopy(out.getArray(),0, outArray,4,out.getArray().length);
        return outArray;
    }

    public static LZHArchText compressText(int[] array) {
        LZHArchText lzhArchText = new LZHArchText();
        VectorBitArray out = new VectorBitArray();
        lzhArchText.setVectorBitArray(out);
        var frequencyMap = HuffmanUtils.getFrequency(array);

        HNode hNodeO = HuffmanUtils.buildTrie(frequencyMap.oCodecMap());

        HashMap<Integer, VectorBitArray> codecMapO = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeO, new VectorBitArray(),codecMapO);


        HashMap<Integer, String> codecMapOString = new HashMap<>();
        HuffmanUtils.buildCodecMapString(hNodeO, "",codecMapOString);

        lzhArchText.setCodecMapOString(codecMapOString);
        lzhArchText.sethNodeO(hNodeO);
        int j=0;
        while (j<array.length) {

            for (int i = 0; i <12 ; i++) {
                out.add(getInByte(array[j],i));
            }
            j++;

            int key = array[j];
            String codecO = codecMapOString.get(key);
            String[] split = codecO.split("");
            for (String s : split) {
                out.add("1".equals(s));
            }
            j++;

            if (j < array.length) {
                out.addByte((byte)array[j]);
                j++;
            }

        }
        int tail = out.size() % 8;
        if(tail >0 ) {
            int fill = 8 - tail;
            for (int i = 0; i < fill; i++) {
                out.fill(false);
            }
        }

        return lzhArchText;
    }

    public static LZHArchImage compressImg(int[] array) {
        LZHArchImage lzhArchImage = new LZHArchImage();
        VectorBitArray out = new VectorBitArray();
        var frequencyMap = HuffmanUtils.getFrequency(array);
        HNode hNodeL = HuffmanUtils.buildTrie(frequencyMap.lCodecMap());
        HNode hNodeO = HuffmanUtils.buildTrie(frequencyMap.oCodecMap());
        HNode hNodeB = HuffmanUtils.buildTrie(frequencyMap.bCodecMap());

        HashMap<Integer, VectorBitArray> codecMapL = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeL, new VectorBitArray(),codecMapL);
        HashMap<Integer, VectorBitArray> codecMapO = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeO, new VectorBitArray(),codecMapO);
        HashMap<Integer, VectorBitArray> codecMapB = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeB, new VectorBitArray(),codecMapB);


        int j=0;
        while (j<array.length) {

            for (int i = 0; i <12 ; i++) {
                out.add(getInByte(array[j],i));
            }
            j++;

            VectorBitArray codecO = codecMapO.get(array[j]);
            for (int i = 0; i <codecO.size() ; i++) {
                out.add(codecO.get(i));
            }
            j++;

            if (j < array.length) {
                VectorBitArray codecB = codecMapB.get(array[j]);
                for (int i = 0; i < codecB.size(); i++) {
                    out.add(codecB.get(i));
                }
                j++;
            }
        }
        lzhArchImage.sethNodeB(hNodeB);
        lzhArchImage.sethNodeO(hNodeO);
        lzhArchImage.setVectorBitArray(out);
        return lzhArchImage;
    }

    public static byte[] compressTest(int[] array) {
        VectorBitArray out = new VectorBitArray();
        var frequencyMap = HuffmanUtils.getFrequency(array);
        HNode hNodeL = HuffmanUtils.buildTrie(frequencyMap.lCodecMap());
        HNode hNodeO = HuffmanUtils.buildTrie(frequencyMap.oCodecMap());
        HNode hNodeB = HuffmanUtils.buildTrie(frequencyMap.bCodecMap());

        HashMap<Integer, VectorBitArray> codecMapL = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeL, new VectorBitArray(),codecMapL);
        HashMap<Integer, VectorBitArray> codecMapO = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeO, new VectorBitArray(),codecMapO);
        HashMap<Integer, VectorBitArray> codecMapB = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeB, new VectorBitArray(),codecMapB);

        out.addByte((byte)1);

        out.addByte((byte)codecMapO.size());
        System.out.println("codecMapB = " + codecMapB.size());
        codecMapO.forEach((key, value) -> {
            VectorBitArray codecO = codecMapO.get(key);
            out.addByte(key.byteValue());
            for (int i = 0; i < codecO.size(); i++) {
                out.add(codecO.get(i));
            }
        });

        int j=0;
        while (j<array.length) {
            for (int i = 0; i <12 ; i++) {
                out.add(getInByte(array[j],i));
            }
            j++;

            VectorBitArray codecO = codecMapO.get(array[j]);
            for (int i = 0; i <codecO.size() ; i++) {
                out.add(codecO.get(i));
            }
            j++;

            if (j < array.length) {
                    out.addByte((byte)array[j]);
                j++;
            }
        }
        return out.array();
    }


    public static byte[] compressImgTest(int[] array) {
        VectorBitArray out = new VectorBitArray();
        var frequencyMap = HuffmanUtils.getFrequency(array);
        HNode hNodeL = HuffmanUtils.buildTrie(frequencyMap.lCodecMap());
        HNode hNodeO = HuffmanUtils.buildTrie(frequencyMap.oCodecMap());
        HNode hNodeB = HuffmanUtils.buildTrie(frequencyMap.bCodecMap());

        HashMap<Integer, VectorBitArray> codecMapL = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeL, new VectorBitArray(),codecMapL);
        HashMap<Integer, VectorBitArray> codecMapO = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeO, new VectorBitArray(),codecMapO);
        HashMap<Integer, VectorBitArray> codecMapB = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeB, new VectorBitArray(),codecMapB);

        out.addByte((byte)2);
        out.addInt(codecMapB.size());
        codecMapB.forEach((key, codecB) -> {
            out.addByte(key.byteValue());
            for (int i = 0; i < codecB.size(); i++) {
                out.add(codecB.get(i));
            }
        });

        for (int k = 0; k < 16; k++) {
            VectorBitArray codecO = codecMapO.get(k);
            for (int i = 0; i <codecO.size() ; i++) {
                out.add(codecO.get(i));
            }
        }


        int j=0;
        while (j<array.length) {

            for (int i = 0; i <12 ; i++) {
                out.add(getInByte(array[j],i));
            }
            j++;

            VectorBitArray codecO = codecMapO.get(array[j]);
            for (int i = 0; i <codecO.size() ; i++) {
                out.add(codecO.get(i));
            }
            j++;

            if (j < array.length) {
                VectorBitArray codecB = codecMapB.get(array[j]);
                for (int i = 0; i < codecB.size(); i++) {
                    out.add(codecB.get(i));
                }
                j++;
            }
        }
        return out.array();
    }

    public static byte[] pack(int[] array) {
        VectorBitArray out = new VectorBitArray();
        var frequencyMap = HuffmanUtils.getFrequency(array);
        HNode hNodeL = HuffmanUtils.buildTrie(frequencyMap.lCodecMap());
        HNode hNodeO = HuffmanUtils.buildTrie(frequencyMap.oCodecMap());
        HNode hNodeB = HuffmanUtils.buildTrie(frequencyMap.bCodecMap());

        HashMap<Integer, VectorBitArray> codecMapL = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeL, new VectorBitArray(),codecMapL);
        HashMap<Integer, VectorBitArray> codecMapO = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeO, new VectorBitArray(),codecMapO);
        HashMap<Integer, VectorBitArray> codecMapB = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeB, new VectorBitArray(),codecMapB);


        int j=0;
        while (j<array.length) {

            for (int i = 0; i <12 ; i++) {
                out.add(getInByte(array[j],i));
            }
            j++;

            for (int i = 0; i <4 ; i++) {
                out.add(getInByte(array[j],i));
            }
            j++;

            if (j < array.length) {
                out.addByte((byte)array[j]);
                j++;
            }
        }
        return out.array();
    }

    public static byte[] compressOld(int[] array) {
        VectorBitArray out = new VectorBitArray();
        var frequencyMap = HuffmanUtils.getFrequency(array);
        HNode hNodeL = HuffmanUtils.buildTrie(frequencyMap.lCodecMap());
        HNode hNodeO = HuffmanUtils.buildTrie(frequencyMap.oCodecMap());
        HNode hNodeB = HuffmanUtils.buildTrie(frequencyMap.bCodecMap());

        HashMap<Integer, VectorBitArray> codecMapL = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeL, new VectorBitArray(),codecMapL);

        codecMapL.entrySet().stream().sorted(Comparator.comparing(k -> k.getValue().size())).forEach((k)-> {
            System.out.println(k.getKey()+" "+k.getValue().size());
        });
        System.out.println();
        HashMap<Integer, VectorBitArray> codecMapO = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeO, new VectorBitArray(),codecMapO);
        System.out.println();
        HashMap<Integer, VectorBitArray> codecMapB = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNodeB, new VectorBitArray(),codecMapB);
        codecMapB.forEach((k,v)-> {
            System.out.println(k+" "+v.size());
        });
        System.out.println();

        int j=0;
        while (j<array.length) {
            VectorBitArray codecL = codecMapL.get(array[j]);

            for (int i = 0; i <codecL.size() ; i++) {
                out.add(codecL.get(i));
            }
            j++;

            VectorBitArray codecO = codecMapO.get(array[j]);
            for (int i = 0; i <codecO.size() ; i++) {
                out.add(codecO.get(i));
            }
            j++;

            if (j < array.length) {
                VectorBitArray codecB = codecMapB.get(array[j]);
                for (int i = 0; i < codecB.size(); i++) {
                    out.add(codecB.get(i));
                }
                j++;
            }
        }
        return out.array();
    }

    public static void buildCodecMap(HNode node, String str,  Map<Integer, String> codecMap) {
        if(node == null) {
            return;
        }

        if(node.getLeft() == null && node.getRight() == null) {
            codecMap.put(node.getSymbol(), str);
        }

        buildCodecMap(node.getLeft(), str+"0",codecMap);
        buildCodecMap(node.getRight(), str+"1",codecMap);
    }

    public static void buildCodecMap(HNode node, VectorBitArray bitSet, Map<Integer, VectorBitArray> codecMap) {
        if(node == null) {
            return;
        }


        if(node.getLeft() == null && node.getRight() == null) {
            codecMap.put(node.getSymbol(), bitSet);
        }
        VectorBitArray leftBitset =  bitSet.clone();
        leftBitset.add(false);
        buildCodecMap(node.getLeft(), leftBitset,codecMap);
        VectorBitArray rightBitset = bitSet.clone();
        rightBitset.add(true);
        buildCodecMap(node.getRight(), rightBitset,codecMap);
    }

    public static void buildCodecMapString(HNode node, String bitSet, Map<Integer, String> codecMap) {
        if(node == null) {
            return;
        }
        if(node.getLeft() == null && node.getRight() == null) {
            codecMap.put(node.getSymbol(), bitSet);
        }
        String leftBitset =  bitSet+"0";
        buildCodecMapString(node.getLeft(), leftBitset,codecMap);
        String rightBitset = bitSet +"1";
        buildCodecMapString(node.getRight(), rightBitset,codecMap);
    }


    public static Map<Integer,Integer> getFrequencyMap(int[] array) {
        Map<Integer,Integer> freqMap = new HashMap<>();
        for (int j : array) {
            Integer orDefault = freqMap.getOrDefault(j, 0);
            int value = orDefault + 1;
            freqMap.put(j, value);
        }
        return freqMap;
    }

    public static HuffmanCodecs getFrequency(int[] array) {

        HuffmanCodecs freqMap = new HuffmanCodecs();
        int i=0;
        while (i<array.length) {
            Integer lorDefault = freqMap.lCodecMap().getOrDefault(array[i], 0);
            int value = lorDefault + 1;
            freqMap.lCodecMap().put(array[i], value);
            i++;

            Integer oorDefault = freqMap.oCodecMap().getOrDefault(array[i], 0);
            int ovalue = oorDefault + 1;
            freqMap.oCodecMap().put(array[i], ovalue);
            i++;

            if(i< array.length) {
                int borDefault = freqMap.bCodecMap().getOrDefault(array[i], 0);
                int bvalue = borDefault + 1;
                freqMap.bCodecMap().put(array[i], bvalue);
                i++;
            }
        }

        return freqMap;
    }



    public static HNode buildTrie(Map<Integer,Integer> freqMap) {
        LinkedHashSet<HNode> collect = freqMap.entrySet().stream()
                .map(HuffmanUtils::mapToNode)
//                .sorted(Comparator.naturalOrder())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        while(collect.size()>1) {
            HNode hNodeR = collect.removeLast();
            HNode hNodeL = collect.removeLast();

            HNode hNodeC = new HNode();
            hNodeC.setRight(hNodeR);
            hNodeC.setLeft(hNodeL);
            hNodeC.setFreq(hNodeR.getFreq()+hNodeL.getFreq());
            collect.addLast(hNodeC);
        }

        HNode hNodeRoot = collect.getFirst();
        return hNodeRoot;
    }

    private static HNode mapToNode(Map.Entry<Integer, Integer> entry) {
        HNode hNode = new HNode();
        hNode.setFreq(entry.getValue());
        hNode.setSymbol(entry.getKey());
        return  hNode;
    }

    public static boolean getInByte(int token, int bitPlace) {
        if(bitPlace == 0) {
            return (token & 1) == 1;
        } else if(bitPlace == 1) {
            return (token & 2) ==  2;
        } else if(bitPlace == 2) {
            return (token & 4) == 4;
        } else if(bitPlace == 3) {
            return (token & 8) == 8;
        }else if(bitPlace == 4) {
            return (token & 16) ==  16;
        } else if(bitPlace == 5) {
            return (token & 32) == 32;
        } else if(bitPlace == 6) {
            return (token & 64) == 64;
        }else if(bitPlace == 7) {
            return (token & 128) == 128;
        }else if(bitPlace == 8) {
            return (token & 256) == 256;
        }else if(bitPlace == 9) {
            return (token & 512) == 512;
        }else if(bitPlace == 10) {
            return (token & 1024) == 1024;
        }else if(bitPlace == 11) {
            return (token & 2048) == 2048;
        }else if(bitPlace == 12) {
            return (token & 4096) == 4096;
        } else if(bitPlace == 13) {
            return (token & 8192) == 8192;
        } else if(bitPlace == 14) {
            return (token & 16384) == 16384;
        } else if(bitPlace == 15) {
            return (token & 32768) == 32768;
        }
        throw new RuntimeException();
    }

   public static Map<Integer, VectorBitArray> getExpandedOCodec(VectorBitArray array, int start) {
        HashMap<Integer, VectorBitArray> codecMap = new HashMap<>();
        int k=32;

//        for(int i=0;i<16;i++) {
//            VectorBitArray vectorBitArray = new VectorBitArray();
//            while (array.get(k)) {
//                vectorBitArray.add(true);
//                k++;
//            }
//            vectorBitArray.add(false);
//            codecMap.put(i, vectorBitArray);
//        }
        return codecMap;
    }

}
