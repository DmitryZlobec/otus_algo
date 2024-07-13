import java.util.*;
import java.util.stream.Collectors;

public class Main {


    static String  text = "ABCADSDFCBDNSNCCCNSNDS";
    static String mask="NSN";

    public  static List<Map.Entry<String, Integer>> splitText(String text, int size) {
        List<Map.Entry<String, Integer>> searchList = new LinkedList<>();
         if(text.length()<size) {
             return searchList;
         }
         for(int i=0; i< text.length()-size+1; i++) {
             searchList.add(new AbstractMap.SimpleEntry<>(text.substring(i,i+size), i));
         }
         return searchList;
    }

    public static void main(String[] args) {
        List<Map.Entry<String, Integer>> searchList = splitText(text, mask.length());
        List<Map.Entry<String, Integer>> entryList = searchList.stream()
                .filter(entry -> mask.equals(entry.getKey()))
                .toList();
        entryList.forEach(entyr-> {
            System.out.printf("Найдено: %s%n", entyr.getValue());
        });
        System.out.println("searchMap = " + entryList);
    }
}