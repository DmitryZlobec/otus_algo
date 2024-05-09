//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Trie trie = new Trie();
        trie.add("abc", "key1");
        trie.add("abcd", "key2");
        trie.add("cat", "key3");
        String abc = (String) trie.get("abc");
        String abcd = (String) trie.get("abcd");
        String cat = (String) trie.get("cat");
        String dog = (String) trie.get("dog");
        System.out.println("abc "+ abc);
        System.out.println("abcd "+ abcd);
        System.out.println("cat "+ cat);
        System.out.println("dog "+ dog);

    }
}