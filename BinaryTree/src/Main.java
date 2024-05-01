import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
public static void main(String[] args) {

    Random random = new Random();
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    ITree<Integer, Integer> tree = new BinaryTree<>();

    tree.add(100,100);
    tree.add(200,101);
    tree.add(250,102);
    tree.add(150,103);
    tree.add(125,104);
    tree.add(175,105);
    tree.add(225,106);
    tree.add(275,107);
    tree.add(90,108);
    tree.add(85,109);
    tree.add(95,110);
    System.out.println("tree.search(200) = " + tree.search(200));
    tree.remove(200);
    System.out.println("tree.search(95) = " + tree.search(95));
    System.out.println("tree.search(200) = " + tree.search(200));
    System.out.println("End");
}
}