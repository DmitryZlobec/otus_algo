package org.otus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KPMTest {

    @Test
    public void testAlphabet()
    {
        byte[] pattern = {1,2,3,4,3,4,5,4,3,2,1};
        byte[] alphabet = KMP.getAlphabet(pattern);
        for( byte b: alphabet) {
            System.out.println(b);
        }
    }

    @Test
    public void initDelta() {
        KMP kmp = new KMP();
        byte[] pattern = "ABC".getBytes();
        kmp.initDelta(pattern);
        System.out.println("pattern = " + pattern);
    }

    @Test
    public void search() {
        KMP kmp = new KMP();
        byte[] text = "abracadabra".getBytes();
        byte[] pattern = "cada".getBytes();

        kmp.initDelta(pattern);
        int search = kmp.search(text);
        System.out.println("search = " + search);
    }

}