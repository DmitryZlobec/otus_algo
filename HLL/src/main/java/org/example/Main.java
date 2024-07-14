package org.example;

import com.google.common.annotations.Beta;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import net.agkn.hll.HLL;

import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        HashFunction hashFunction = Hashing.murmur3_128();
        long numberOfElements = 100_000_000;
        long toleratedDifference = 1_000_000;
        var hll = new HLL(14, 5);
        LongStream.range(0, numberOfElements).forEach(element -> {
                    long hashedValue = hashFunction.newHasher().putLong(element).hash().asLong();
                    hll.addRaw(hashedValue);
                }
        );
        long cardinality = hll.cardinality();
        System.out.println("cardinality = " + cardinality);
        System.out.println("toleratedDifference = " + toleratedDifference);
    }
}