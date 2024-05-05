import javax.swing.*;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Map<String, String> myHashMap  = new DmitryHashMap<>();

        myHashMap.put("Key1", "Value1");
        myHashMap.put("Key2", "Value2");
        myHashMap.put("Key3", "Value3");
        myHashMap.put("Key4", "Value4");
        myHashMap.put("Key5", "Value5");
        System.out.println("myHashMap = " + myHashMap);
        System.out.println("Key1 value " + myHashMap.get("Key1"));
        System.out.println("Key2 value " + myHashMap.get("Key2"));
        System.out.println("End");


    }
}