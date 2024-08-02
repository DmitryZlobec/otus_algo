package org.otus;

import org.junit.jupiter.api.Test;
import org.otus.utils.ArrayUtils;

class ArraysTest {

    @Test
    public void leftTest() {
        byte[] input = {1,2,3,4,5,6,7,8,9,10};
        byte[] left = ArrayUtils.left(input, 3);

        System.out.println("true = " + left);
    }

    @Test
    public void rightTest() {
        byte[] input = {1,2,3,4,5,6,7,0};
        byte[] right = ArrayUtils.right(input, 0);

        String right1 = right("12345670", 0);
        System.out.println("true = " + right1);
    }

    private static String right(String line, int x) {
        return  line.substring(line.length()-x);
    }

    @Test
    public  void leftC() {
        byte[] input = {1,2,3,4,5,6,7,0};

        byte[] left = ArrayUtils.left(input, 3, (byte)8);
        System.out.println("left = " + left);
    }

}