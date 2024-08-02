package org.otus.utils;

public class SortingUtils {
    public static void qsort(byte[] array) {
        qsort(array, 0, array.length - 1);
    }

    private static void qsort(byte[] array, int left, int right) {
        if (left >= right) return;
        int m = split(array, left, right);
        qsort(array, left, m - 1);
        qsort(array, m + 1, right);
    }

    private static int split(byte[] array, int left, int right) {
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

    public static void msort(byte[] array) {
        msort(array, 0, array.length - 1);
    }

    private static void msort(byte[] array, int left, int right) {
        if (left >= right) return;
        int m = (left + right) / 2;
        msort(array, left, m);
        msort(array, m + 1, right);
        merge(array, left, m, right);
    }

    private static void merge(byte[] array, int left, int mid, int right) {
        byte[] temp =  new byte[right-left+1];
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

    private static void swap(byte[] array, int i, int j) {
        byte temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
