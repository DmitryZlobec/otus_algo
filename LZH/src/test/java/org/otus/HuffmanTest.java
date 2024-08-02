package org.otus;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;
import org.otus.base.VectorBitArray;
import org.otus.dto.HNode;
import org.otus.dto.LZHArchText;
import org.otus.dto.RawBytes;
import org.otus.utils.HuffmanUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HuffmanTest {


    @Test
    public void largeMapPPMByte() throws IOException {
        URL url = Resources.getResource("1.ppm");
        String text = Resources.toString(url, StandardCharsets.ISO_8859_1);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        var compress1 = HuffmanUtils.compressTextByte(lz77Compressed);
        System.out.println("HuffmanCompressText = " + compress1.length);
        int[] ints = HuffmanUtils.decompressTextByte(compress1);
        System.out.println("HuffmanDecompressed = " + ints.length);
        byte[] decompress = lz77.decompress(ints);
        System.out.println("lz77Decompressed = " + decompress.length);
    }

    @Test
    public void largeMapSVGByte() throws IOException {
        URL url = Resources.getResource("1.svg");
        String text = Resources.toString(url, StandardCharsets.ISO_8859_1);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        var compress1 = HuffmanUtils.compressTextByte(lz77Compressed);
        System.out.println("HuffmanCompressText = " + compress1.length);
        int[] ints = HuffmanUtils.decompressTextByte(compress1);
        System.out.println("HuffmanDecompressed = " + ints.length);
        byte[] decompress = lz77.decompress(ints);
        System.out.println("lz77Decompressed = " + decompress.length);
    }

    @Test
    public void largeMapAkByte() throws IOException {
        URL url = Resources.getResource("ak.txt");
        String text = Resources.toString(url, StandardCharsets.ISO_8859_1);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        var compress1 = HuffmanUtils.compressTextByte(lz77Compressed);
        System.out.println("HuffmanCompressText = " + compress1.length);
        int[] ints = HuffmanUtils.decompressTextByte(compress1);
        System.out.println("HuffmanDecompressed = " + ints.length);
        byte[] decompress = lz77.decompress(ints);
        System.out.println("lz77Decompressed = " + decompress.length);
    }


    @Test
    public void largeMapAlByte() throws IOException {
        URL url = Resources.getResource("al.txt");
        String text = Resources.toString(url, StandardCharsets.ISO_8859_1);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        var compress1 = HuffmanUtils.compressTextByte(lz77Compressed);
        System.out.println("HuffmanCompressText = " + compress1.length);
        int[] ints = HuffmanUtils.decompressTextByte(compress1);
        System.out.println("HuffmanDecompressed = " + ints.length);
        byte[] decompress = lz77.decompress(ints);
        System.out.println("lz77Decompressed = " + decompress.length);
    }

    @Test
    public void mainTestHuffmanBarmaglotNew() throws IOException {
        LZ77 lz77 = new LZ77();
        String text="Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.\n" +
                "\n" +
                "“Beware the Jabberwock, my son!\n" +
                "      The jaws that bite, the claws that catch!\n" +
                "Beware the Jubjub bird, and shun\n" +
                "      The frumious Bandersnatch!”\bn" +
                "\n" +
                "He took his vorpal sword in hand;\n" +
                "      Long time the manxome foe he sought—\n" +
                "So rested he by the Tumtum tree\n" +
                "      And stood awhile in thought.\n" +
                "\n" +
                "And, as in uffish thought he stood,\n" +
                "      The Jabberwock, with eyes of flame,\n" +
                "Came whiffling through the tulgey wood,\n" +
                "      And burbled as it came!\n" +
                "\n" +
                "One, two! One, two! And through and through\n" +
                "      The vorpal blade went snicker-snack!\n" +
                "He left it dead, and with its head\n" +
                "      He went galumphing back.\n" +
                "\n" +
                "“And hast thou slain the Jabberwock?\n" +
                "      Come to my arms, my beamish boy!\n" +
                "O frabjous day! Callooh! Callay!”\n" +
                "      He chortled in his joy.\n" +
                "\n" +
                "’Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.";

        byte[] in =  text.getBytes(Charset.defaultCharset());

        System.out.println("in = " + in.length);
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);

        var hufCompress = HuffmanUtils.compressTextByte(lz77Compressed);
        System.out.println("hufCompress = " + hufCompress.length);
        int[] ints = HuffmanUtils.decompressTextByte(hufCompress);
        byte[] decompress = lz77.decompress(ints);
        System.out.println("ints.length = " + new String(decompress, StandardCharsets.UTF_8));
    }

    @Test
    public void largeMapAk() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = Resources.getResource("ak.txt");
        String text = Resources.toString(url, StandardCharsets.UTF_8);
        RawBytes rawBytes = new RawBytes(text.getBytes(StandardCharsets.ISO_8859_1));
        String rawJSON = objectMapper.writeValueAsString(rawBytes);
        System.out.println("rawJSON.length() = " + rawJSON.length());
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        LZHArchText compress1 = HuffmanUtils.compressText(lz77Compressed);
        System.out.println("HuffmanCompressText = " + compress1.getVectorBitArray().size()/8);
        String compressedJSON = objectMapper.writeValueAsString(compress1);
        System.out.println("compressedJSON.length() = " + compressedJSON.length());
        int[] ints = HuffmanUtils.decompressText(compress1);
        System.out.println("HuffmanDecompressed = " + ints.length);
        byte[] decompress = lz77.decompress(ints);
        System.out.println("lz77Decompressed = " + decompress.length);
    }


    @Test
    public void largeMapAl() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = Resources.getResource("al.txt");
        String text = Resources.toString(url, StandardCharsets.UTF_8);
        RawBytes rawBytes = new RawBytes(text.getBytes(StandardCharsets.ISO_8859_1));
        String rawJSON = objectMapper.writeValueAsString(rawBytes);
        System.out.println("rawJSON.length() = " + rawJSON.length());
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        LZHArchText compress1 = HuffmanUtils.compressText(lz77Compressed);
        System.out.println("HuffmanCompressText = " + compress1.getVectorBitArray().size()/8);
        String compressedJSON = objectMapper.writeValueAsString(compress1);
        System.out.println("compressedJSON.length() = " + compressedJSON.length());
        int[] ints = HuffmanUtils.decompressText(compress1);
        System.out.println("HuffmanDecompressed = " + ints.length);
        byte[] decompress = lz77.decompress(ints);
        System.out.println("lz77Decompressed = " + decompress.length);
    }

    @Test
    public void mainTestHuffmanBarmaglot() throws IOException {
        LZ77 lz77 = new LZ77();
        String text="Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.\n" +
                "\n" +
                "“Beware the Jabberwock, my son!\n" +
                "      The jaws that bite, the claws that catch!\n" +
                "Beware the Jubjub bird, and shun\n" +
                "      The frumious Bandersnatch!”\bn" +
                "\n" +
                "He took his vorpal sword in hand;\n" +
                "      Long time the manxome foe he sought—\n" +
                "So rested he by the Tumtum tree\n" +
                "      And stood awhile in thought.\n" +
                "\n" +
                "And, as in uffish thought he stood,\n" +
                "      The Jabberwock, with eyes of flame,\n" +
                "Came whiffling through the tulgey wood,\n" +
                "      And burbled as it came!\n" +
                "\n" +
                "One, two! One, two! And through and through\n" +
                "      The vorpal blade went snicker-snack!\n" +
                "He left it dead, and with its head\n" +
                "      He went galumphing back.\n" +
                "\n" +
                "“And hast thou slain the Jabberwock?\n" +
                "      Come to my arms, my beamish boy!\n" +
                "O frabjous day! Callooh! Callay!”\n" +
                "      He chortled in his joy.\n" +
                "\n" +
                "’Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.";

        byte[] in =  text.getBytes(Charset.defaultCharset());


        System.out.println("in = " + in.length);
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);

        LZHArchText hufCompress = HuffmanUtils.compressText(lz77Compressed);
        System.out.println("hufCompress = " + hufCompress.getVectorBitArray().size()/8);
        int[] ints = HuffmanUtils.decompressText(hufCompress);
        byte[] decompress = lz77.decompress(ints);

        System.out.println("ints.length = " + new String(decompress, StandardCharsets.UTF_8));
    }


    @Test
    public void mainTestHuffmanAbraCadabra() throws IOException {
        LZ77 lz77 = new LZ77();
        String text="abra cada braz g ghh ghh  gh gh   gh  gh  gh gh braz";
        byte[] in =  text.getBytes(Charset.defaultCharset());

    
        System.out.println("in = " + in.length);
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);

        LZHArchText hufCompress = HuffmanUtils.compressText(lz77Compressed);
        System.out.println("hufCompress = " + hufCompress.getVectorBitArray().size()/8);
        int[] ints = HuffmanUtils.decompressText(hufCompress);
        byte[] decompress = lz77.decompress(ints);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(hufCompress);
        System.out.println("ints.length = " + new String(decompress, StandardCharsets.UTF_8));
    }


    @Test
    public void testFreq() throws IOException {
        LZ77 lz77 = new LZ77();
        int[] s = {0,0,1,0,0,2,3,4,0,0,5,7,8};

        Map<Integer, Integer> frequencyMap = HuffmanUtils.getFrequencyMap(s);
        System.out.println("frequencyMap = " + frequencyMap);
    }

    @Test
    public void statTestFreq() throws IOException {
        LZ77 lz77 = new LZ77();
        String text="Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.\n" +
                "\n" +
                "“Beware the Jabberwock, my son!\n" +
                "      The jaws that bite, the claws that catch!\n" +
                "Beware the Jubjub bird, and shun\n" +
                "      The frumious Bandersnatch!”\bn" +
                "\n" +
                "He took his vorpal sword in hand;\n" +
                "      Long time the manxome foe he sought—\n" +
                "So rested he by the Tumtum tree\n" +
                "      And stood awhile in thought.\n" +
                "\n" +
                "And, as in uffish thought he stood,\n" +
                "      The Jabberwock, with eyes of flame,\n" +
                "Came whiffling through the tulgey wood,\n" +
                "      And burbled as it came!\n" +
                "\n" +
                "One, two! One, two! And through and through\n" +
                "      The vorpal blade went snicker-snack!\n" +
                "He left it dead, and with its head\n" +
                "      He went galumphing back.\n" +
                "\n" +
                "“And hast thou slain the Jabberwock?\n" +
                "      Come to my arms, my beamish boy!\n" +
                "O frabjous day! Callooh! Callay!”\n" +
                "      He chortled in his joy.\n" +
                "\n" +
                "’Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.";
        byte[] in =  text.getBytes(Charset.defaultCharset());
        int[] s = lz77.compressArray(in);

        Map<Integer, Integer> frequencyMap = HuffmanUtils.getFrequencyMap(s);
        System.out.println("frequencyMap = " + frequencyMap);
    }

    @Test
    public void testTrie() throws IOException {
        LZ77 lz77 = new LZ77();
        int[] s = {0,0,1,0,0,2,3,4,0,0,5,7,8};

        Map<Integer, Integer> frequencyMap = HuffmanUtils.getFrequencyMap(s);
        HNode hNode = HuffmanUtils.buildTrie(frequencyMap);
        System.out.println("frequencyMap = " + hNode);
    }



    @Test
    public void testCodecBarmaglot() throws IOException {
        LZ77 lz77 = new LZ77();
        String text="Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.\n" +
                "\n" +
                "“Beware the Jabberwock, my son!\n" +
                "      The jaws that bite, the claws that catch!\n" +
                "Beware the Jubjub bird, and shun\n" +
                "      The frumious Bandersnatch!”\n" +
                "\n" +
                "He took his vorpal sword in hand;\n" +
                "      Long time the manxome foe he sought—\n" +
                "So rested he by the Tumtum tree\n" +
                "      And stood awhile in thought.\n" +
                "\n" +
                "And, as in uffish thought he stood,\n" +
                "      The Jabberwock, with eyes of flame,\n" +
                "Came whiffling through the tulgey wood,\n" +
                "      And burbled as it came!\n" +
                "\n" +
                "One, two! One, two! And through and through\n" +
                "      The vorpal blade went snicker-snack!\n" +
                "He left it dead, and with its head\n" +
                "      He went galumphing back.\n" +
                "\n" +
                "“And hast thou slain the Jabberwock?\n" +
                "      Come to my arms, my beamish boy!\n" +
                "O frabjous day! Callooh! Callay!”\n" +
                "      He chortled in his joy.\n" +
                "\n" +
                "’Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.";
        byte[] in =  text.getBytes(Charset.defaultCharset());
        int[] s = lz77.compressArray(in);
        Map<Integer, Integer> frequencyMap = HuffmanUtils.getFrequencyMap(s);
        HNode hNode = HuffmanUtils.buildTrie(frequencyMap);
        HashMap<Integer, String> codecMap = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNode, "",codecMap);
        System.out.println("frequencyMap = " + codecMap);
    }

    @Test
    public void testCodec() throws IOException {
        LZ77 lz77 = new LZ77();
        int[] s = {0,0,1,0,0,2,3,4,0,0,5,7,8};

        Map<Integer, Integer> frequencyMap = HuffmanUtils.getFrequencyMap(s);
        HNode hNode = HuffmanUtils.buildTrie(frequencyMap);
        HashMap<Integer, String> codecMap = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNode, "",codecMap);
        System.out.println("frequencyMap = " + codecMap);
    }

    @Test
    public void testCodecBitSet() throws IOException {
        LZ77 lz77 = new LZ77();
        int[] s = {0,0,1,0,0,2,3,4,0,0,5,7,8};

        Map<Integer, Integer> frequencyMap = HuffmanUtils.getFrequencyMap(s);
        HNode hNode = HuffmanUtils.buildTrie(frequencyMap);
        HashMap<Integer, VectorBitArray> codecMap = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNode, new VectorBitArray(),codecMap);
        System.out.println("frequencyMap = " + codecMap);
    }


    @Test
    public void testCodecBitSetBarmaglot() throws IOException {
        LZ77 lz77 = new LZ77();
        String text="Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.\n" +
                "\n" +
                "“Beware the Jabberwock, my son!\n" +
                "      The jaws that bite, the claws that catch!\n" +
                "Beware the Jubjub bird, and shun\n" +
                "      The frumious Bandersnatch!”\n" +
                "\n" +
                "He took his vorpal sword in hand;\n" +
                "      Long time the manxome foe he sought—\n" +
                "So rested he by the Tumtum tree\n" +
                "      And stood awhile in thought.\n" +
                "\n" +
                "And, as in uffish thought he stood,\n" +
                "      The Jabberwock, with eyes of flame,\n" +
                "Came whiffling through the tulgey wood,\n" +
                "      And burbled as it came!\n" +
                "\n" +
                "One, two! One, two! And through and through\n" +
                "      The vorpal blade went snicker-snack!\n" +
                "He left it dead, and with its head\n" +
                "      He went galumphing back.\n" +
                "\n" +
                "“And hast thou slain the Jabberwock?\n" +
                "      Come to my arms, my beamish boy!\n" +
                "O frabjous day! Callooh! Callay!”\n" +
                "      He chortled in his joy.\n" +
                "\n" +
                "’Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.";
        byte[] in =  text.getBytes(Charset.defaultCharset());
        int[] s = lz77.compressArray(in);

        Map<Integer, Integer> frequencyMap = HuffmanUtils.getFrequencyMap(s);
        HNode hNode = HuffmanUtils.buildTrie(frequencyMap);
        HashMap<Integer, VectorBitArray> codecMap = new HashMap<>();
        HuffmanUtils.buildCodecMap(hNode, new VectorBitArray(),codecMap);
        System.out.println("frequencyMap = " + codecMap);
    }

    @Test
    public void testHuffmanBarmaglot() throws IOException {
        LZ77 lz77 = new LZ77();
        String text="Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.\n" +
                "\n" +
                "“Beware the Jabberwock, my son!\n" +
                "      The jaws that bite, the claws that catch!\n" +
                "Beware the Jubjub bird, and shun\n" +
                "      The frumious Bandersnatch!”\n" +
                "\n" +
                "He took his vorpal sword in hand;\n" +
                "      Long time the manxome foe he sought—\n" +
                "So rested he by the Tumtum tree\n" +
                "      And stood awhile in thought.\n" +
                "\n" +
                "And, as in uffish thought he stood,\n" +
                "      The Jabberwock, with eyes of flame,\n" +
                "Came whiffling through the tulgey wood,\n" +
                "      And burbled as it came!\n" +
                "\n" +
                "One, two! One, two! And through and through\n" +
                "      The vorpal blade went snicker-snack!\n" +
                "He left it dead, and with its head\n" +
                "      He went galumphing back.\n" +
                "\n" +
                "“And hast thou slain the Jabberwock?\n" +
                "      Come to my arms, my beamish boy!\n" +
                "O frabjous day! Callooh! Callay!”\n" +
                "      He chortled in his joy.\n" +
                "\n" +
                "’Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.";
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);

        byte[] hufCompress = HuffmanUtils.compressTest(lz77Compressed);
        System.out.println("hufCompress = " + hufCompress.length);

        byte[] packCompress = HuffmanUtils.pack(lz77Compressed);
        System.out.println("packCompress = " + packCompress.length);
    }



    @Test
    public void large() throws IOException {
        URL url = Resources.getResource("al.txt");
        String text = Resources.toString(url, StandardCharsets.UTF_8);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);

        byte[] compress = HuffmanUtils.compressTest(lz77Compressed);
        System.out.println("compress = " + compress.length);
    }

    @Test
    public void largeMap() throws IOException {
        URL url = Resources.getResource("al.txt");
        String text = Resources.toString(url, StandardCharsets.ISO_8859_1);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);

        byte[] compress = HuffmanUtils.compressTest(lz77Compressed);
        System.out.println("compress.length = " + compress.length);
    }

    @Test
    public void largeMapAK() throws IOException {
        URL url = Resources.getResource("ak.txt");
        String text = Resources.toString(url, StandardCharsets.ISO_8859_1);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        byte[] compress = HuffmanUtils.compressImgTest(lz77Compressed);
        System.out.println("compress.length = " + compress.length);
    }

    @Test
    public void largeMapVP() throws IOException {
        URL url = Resources.getResource("vp.txt");
        String text = Resources.toString(url, StandardCharsets.UTF_8);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        byte[] compress = HuffmanUtils.compressImgTest(lz77Compressed);
        System.out.println("compress.length = " + compress.length);
    }

    @Test
    public void largeMapSVG() throws IOException {
        URL url = Resources.getResource("1.svg");
        String text = Resources.toString(url, StandardCharsets.UTF_8);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        byte[] compress1 = HuffmanUtils.compressTest(lz77Compressed);
        System.out.println("compress1.length = " + compress1.length);
        byte[] compress2 = HuffmanUtils.compressImgTest(lz77Compressed);
        System.out.println("compress2.length = " + compress2.length);
    }

    @Test
    public void largeMapPPM() throws IOException {
        URL url = Resources.getResource("1.ppm");
        String text = Resources.toString(url, StandardCharsets.UTF_8);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        byte[] compress1 = HuffmanUtils.compressTest(lz77Compressed);
        System.out.println("compress1.length = " + compress1.length);
        byte[] compress2 = HuffmanUtils.compressImgTest(lz77Compressed);
        System.out.println("compress2.length = " + compress2.length);
    }

    @Test
    public void largeMapPPMIndexed() throws IOException {
        URL url = Resources.getResource("2.ppm");
        String text = Resources.toString(url, StandardCharsets.UTF_8);
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        LZ77 lz77 = new LZ77();
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);
        byte[] compress1 = HuffmanUtils.compressTest(lz77Compressed);
        System.out.println("compress1.length = " + compress1.length);
        byte[] compress2 = HuffmanUtils.compressImgTest(lz77Compressed);
        System.out.println("compress2.length = " + compress2.length);
    }

    @Test
    public void testExpandOCodec() throws IOException {
        LZ77 lz77 = new LZ77();
        String text="Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.\n" +
                "\n" +
                "“Beware the Jabberwock, my son!\n" +
                "      The jaws that bite, the claws that catch!\n" +
                "Beware the Jubjub bird, and shun\n" +
                "      The frumious Bandersnatch!”\n" +
                "\n" +
                "He took his vorpal sword in hand;\n" +
                "      Long time the manxome foe he sought—\n" +
                "So rested he by the Tumtum tree\n" +
                "      And stood awhile in thought.\n" +
                "\n" +
                "And, as in uffish thought he stood,\n" +
                "      The Jabberwock, with eyes of flame,\n" +
                "Came whiffling through the tulgey wood,\n" +
                "      And burbled as it came!\n" +
                "\n" +
                "One, two! One, two! And through and through\n" +
                "      The vorpal blade went snicker-snack!\n" +
                "He left it dead, and with its head\n" +
                "      He went galumphing back.\n" +
                "\n" +
                "“And hast thou slain the Jabberwock?\n" +
                "      Come to my arms, my beamish boy!\n" +
                "O frabjous day! Callooh! Callay!”\n" +
                "      He chortled in his joy.\n" +
                "\n" +
                "’Twas brillig, and the slithy toves\n" +
                "      Did gyre and gimble in the wabe:\n" +
                "All mimsy were the borogoves,\n" +
                "      And the mome raths outgrabe.";
        byte[] in =  text.getBytes(Charset.defaultCharset());
        System.out.println("in = " + in.length);
        int[] lz77Compressed = lz77.compressArray(in);
        System.out.println("lz77Compressed = " + lz77Compressed.length);

        byte[] hufCompress = HuffmanUtils.compressTest(lz77Compressed);
        System.out.println("hufCompress = " + hufCompress.length);

        VectorBitArray vectorBitArray = new VectorBitArray(hufCompress, hufCompress.length * 8, 1, "");
        byte aByte = vectorBitArray.getByte(0);
        System.out.println("aByte = " + aByte);

        aByte = vectorBitArray.getByte(8);
        System.out.println("aByte = " + aByte);
        int token = 0;
        int startPlace = 16;
        Map<Integer, VectorBitArray> integerVectorBitArrayMap = new HashMap<>();
        while (token<aByte) {
            VectorBitArray vectorBitArray1 = new VectorBitArray();
            byte o = vectorBitArray.getByte(startPlace);
            startPlace = startPlace +8;
            startPlace++;
            integerVectorBitArrayMap.put((int) o,vectorBitArray1);
            while (vectorBitArray.get(startPlace)) {
                vectorBitArray1.add(true);
                startPlace++;
            }
            vectorBitArray1.add(false);
            startPlace++;
            token ++;
        }
        System.out.println("integerVectorBitArrayMap = " + integerVectorBitArrayMap);
    }

    @Test
    public void addByteTes() {
        VectorBitArray vectorBitArray = new VectorBitArray();
        vectorBitArray.addByte((byte)1);
        vectorBitArray.addByte((byte) 48);
        System.out.println("vectorBitArray = " + vectorBitArray);
    }



}
