package org.otus;

import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class LZ77Test {

    @Test
    public void compressArray() throws IOException {
        LZ77 lz77 = new LZ77();
        String text="барракуда";
        int[] s = lz77.compressArray(text.getBytes());
        System.out.println("s = " + s);
    }

    @Test
    public void compressFile() throws IOException {
        LZ77 lz77 = new LZ77(); 
        URL url = Resources.getResource("vp.txt");
        String text = Resources.toString(url, StandardCharsets.UTF_8);
        int[] s = lz77.compressArray(text.getBytes());
        System.out.println("s.length = " + s.length);
    }

    @Test
    public void decompressArray() throws IOException {
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
        byte[] r = lz77.decompress(s);

        String s1 = new String(r, StandardCharsets.UTF_8);
        System.out.println("s1 = " + s1);

    }
}