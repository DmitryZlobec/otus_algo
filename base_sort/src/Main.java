import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        int[] arr = new int[10];

        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(999);
        }
//        bubbleSort(arr);
//        insertSort(arr);
        shellSort(arr);
        System.out.println("res = ");
    }

    public static void bubbleSort(int[] array) {
        for (int j = array.length - 1; j > 0; j--) {
            for (int i = 0; i < j ; i++) {
                if (array[i] > array[j]) {
                    swap(i, j, array);
                }
            }
        }

    }

    public static void insertSort(int[] array) {
        for(int j=1; j<array.length; j++ ) {
            for(int i=j; i>0 && array[i-1] > array[i]; i--)  {
                if(array[i-1] > array[i]) {
                    swap(i-1,i, array);
                }
            }
        }
    }

    public static void shellSort(int[] array) {
        for(int g = array.length; g>0; g/=2) {
            for(int j = g; j< array.length; j++) {
                for(int i=j; i>=g && array[i-g]>array[i];i-=g) {
                    System.out.println("g="+g+" j=  " +
                            "" + j+" i-g="+(i-g)+" i= "+i);
                    swap(i-g, i, array);
                }
            }
        }
    }

    private static void swap(int i, int j, int[] array) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}