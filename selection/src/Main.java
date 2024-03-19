import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int[] array = new int[10];

        Random random = new Random();
        for (int i=0; i < array.length; i++) {
            array[i] = random.nextInt(999);
        }
//        selecttionSort(array);
        heapSort(array);
        System.out.println("array.length = " + array.length);
    }


    private static void heapSort(int[] array) {
        for (int h = array.length / 2 - 1; h >= 0; h--) {
            heapify(h, array.length, array);
        }
        for (int j = array.length-1; j > 0; j--) {
            swap(0, j, array);
            heapify(0, j, array);
        }
    }

    private static void heapify(int root, int size, int[] array) {
        int p = root;
        int l = 2 * p + 1;
        int r = l + 1;
        if (l< size && array[l] > array[p]) {
            p = l;
        }
        if (r< size && array[r] > array[p]) {
            p = r;
        }
        if (p == root) return;
        swap(p, root, array);
        heapify(p, size, array);
    }

    private static void selecttionSort(int[] array) {
        for (int j = array.length-1; j > 0; j--) {
            swap(findMax(j, array), j, array);
        }
    }

    private static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int findMax(int j, int[] array) {
        int max = 0;
        for (int i = 0; i <= j; i++) {
            if (array[i] > array[max]) {
                max = i;
            }
        }
        return max;
    }
}