//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[10];
        var rnd = new Random();
        for (int i = 0; i < 10; i++) {
            array[i] = rnd.nextInt(999);
        }
        msort(array);
        System.out.println("array.length = " + array.length);
    }

    private static void qsort(int[] array) {
        qsort(array, 0, array.length - 1);
    }

    private static void qsort(int[] array, int left, int right) {
        if (left >= right) return;
        int m = split(array, left, right);
        qsort(array, left, m - 1);
        qsort(array, m + 1, right);
    }

    private static int split(int[] array, int left, int right) {
        int p = array[right];
        int m = left - 1;
        for (int j = left; j <= right; j++) {
            if (array[j] <= p) {
                m = m + 1;
                swap(array, m, j);
            }
        }
        return m;
    }


    private static void msort(int[] array) {
        msort(array, 0, array.length - 1);
    }

    private static void msort(int[] array, int left, int right) {
        if (left >= right) return;
        int m = (left + right) / 2;
        msort(array, left, m);
        msort(array, m + 1, right);
        merge(array, left, m, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] temp =  new int[right-left+1];
        int a = left;
        int b = mid+1;
        int m = 0;
        while (a<=mid && b<= right) {
            if(array[a]<array[b]) {
                temp[m] = array[a];
                m++; a++;
            } else  {
                temp[m] = array[b];
                m++; b++;
            }
        }
        while (a<=mid) {
            temp[m] = array[a];
            m++; a++;
        }
        while (b<=right) {
            temp[m] = array[b];
            m++; b++;
        }
        for(int i=left; i<=right; i++) {
            array[i] = temp[i-left];
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}