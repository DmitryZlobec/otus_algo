import java.util.Random;
import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int SIZE = 999;
    static int BUCKETS = 10;
    static int MAX_ARRAY_VALUE = 9999;

    public static void main(String[] args) {


        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int[] array = new int[SIZE];
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            array[i] = random.nextInt(MAX_ARRAY_VALUE);
        }
//        int[] ints = buketSort(array);
        int[] ints = countingSort(array);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
    }

    static int[] countingSort(int[] array) {
        int[] countArray = new int[MAX_ARRAY_VALUE];
        int[] outArray = new int[array.length];

        for(int i=0;i<array.length; i++) {
            countArray[array[i]] = countArray[array[i]]+1;
        }

        for(int i=0;i< countArray.length-1; i++) {
            countArray[i+1]=countArray[i+1]+countArray[i];
        }

        for(int i=array.length-1; i>=0; i--) {
            int num = array[i];
            int place = countArray[num] -1;
            countArray[num] = place;
            outArray[place] = num;
        }

        return outArray;
    }

    static int[] buketSort(int[] array) {
        LinkedList<Integer>[] linkedLists = new LinkedList[BUCKETS];
        for (int i = 0; i < BUCKETS; i++) {
            linkedLists[i] = new LinkedList<>();
        }
        for (int i = 0; i < array.length; i++) {
            int place = (array[i] * BUCKETS) / (MAX_ARRAY_VALUE + 1);
            if (linkedLists[place].size() == 0) {
                linkedLists[place].add(array[i]);
            } else {
                int index = 0;
                while ((index < linkedLists[place].size()) && (array[i] > linkedLists[place].get(index))) {
                    index++;
                }
                linkedLists[place].add(index, array[i]);
            }
        }
        int[] returnArray = new int[SIZE];
        int j = 0;
        for (int i = 0; i < linkedLists.length; i++) {
            if (linkedLists[i].size() > 0) {
                for (int k = 0; k < linkedLists[i].size(); k++) {
                    returnArray[j] = linkedLists[i].get(k);
                    j++;
                }
            }
        }

        return returnArray;
    }
}