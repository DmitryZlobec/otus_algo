import model.*;

import java.util.Date;

public class Program {

    public static void main_(String[] args) {
        IArray singleArray = new SingleArray();
        IArray vectorArray = new FactorArray();
        IArray factorArray = new FactorArray();
        IArray matrixArray = new MatrixArray();
        testAddArray(singleArray, 10_000);
        testAddArray(vectorArray, 100_000);
        testAddArray(factorArray, 100_000);
        testAddArray(matrixArray, 100_000);
    }

    public static void main(String[] args) {
        IArray<Integer> objectSingleArray = new MatrixArray<>();
        for(int i = 0; i< 10; i++) {
            objectSingleArray.add(i);
        }

        objectSingleArray.add(99,5);
        for(int i = 0; i< 11; i++) {
            System.out.printf("%s, %s \n", i ,objectSingleArray.get(i));
        }


        System.out.println();
        objectSingleArray.add(100);
        for(int i = 0; i< 12; i++) {
            System.out.printf("%s, %s \n", i ,objectSingleArray.get(i));
        }

        System.out.println("Remove 0");
        objectSingleArray.remove(0);
        for(int i = 0; i< 11; i++) {
            System.out.printf("%s, %s \n", i ,objectSingleArray.get(i));
        }

        System.out.println("Remove 10");
        objectSingleArray.remove(10);
        for(int i = 0; i< 10; i++) {
            System.out.printf("%s, %s \n", i ,objectSingleArray.get(i));
        }


        System.out.println("Remove 4");
        objectSingleArray.remove(4);
        for(int i = 0; i< 9; i++) {
            System.out.printf("%s, %s \n", i ,objectSingleArray.get(i));
        }

    }

    private static void testAddArray(IArray data, int total) {
        long start = System.currentTimeMillis();

        for (int j = 0; j < total; j ++)
            data.add(new Date());

        System.out.println(data + " testAddArray: " +
                (System.currentTimeMillis() - start));
    }
}
