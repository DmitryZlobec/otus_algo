public class Trie {
    private final Node rootNode;

    public Trie() {
        this.rootNode = new Node();
    }

    public Node getRootNode() {
        return rootNode;
    }

    void add(String string, Object object) {
        int[] array = TrieUtils. stringify(string);
        Node node = this.rootNode;
        for(int ch:array) {
            node = node.add(ch);
        }
        node.setValue(object);
    }

    Object get(String string) {
        int[] array = TrieUtils. stringify(string);
        Node node = this.rootNode;
        for(int ch:array) {
            node = node.next(ch);
            if(node == null) {
                return null;
            }
        }
        return node.getValue();
    }


}
