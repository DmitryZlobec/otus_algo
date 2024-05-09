//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Trie trie = new Trie();
        trie.insert("abc", "key1");
        trie.insert("abcd", "key2");
        trie.insert("cat", "key3");
        String abc = (String) trie.search("abc");
        String abcd = (String) trie.search("abcd");
        String cat = (String) trie.search("cat");
        String dog = (String) trie.search("dog");
        System.out.println("abc "+ abc);
        System.out.println("abcd "+ abcd);
        System.out.println("cat "+ cat);
        System.out.println("dog "+ dog);

        boolean res = trie.startsWith("ab");
        System.out.println("res = " + res);



    }
}