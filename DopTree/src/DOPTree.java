public class DOPTree<K extends Comparable<K>,  V> implements ITree<K,V>{

    private WNode<K,Integer,V> rootNode;

    public void add(WNode<K,Integer,V> node) {
        if(rootNode == null) {
            rootNode = node;
        }
        add(node, rootNode);
    }

    private void add(WNode<K, Integer, V> node, WNode<K, Integer, V> rootNode) {
        if(node.key.compareTo(rootNode.key)<0) {
            if(rootNode.leftNode == null) {
                rootNode.leftNode = node;
            }
            else {
                add(node, rootNode.leftNode);
            }
            return;
        }
        if(node.key.compareTo(rootNode.key)>0) {
            if(rootNode.rightNode == null)
            {
                rootNode.rightNode = node;
            }
            else {
                add(node,rootNode.rightNode);
            }
        }
    }

    @Override
    public V search(K key) {
        return search(key, rootNode);
    }

    public V search(K key, WNode<K, Integer, V> node) {
        if (node.key.compareTo(key) == 0) {
            return node.value;
        } else if (node.key.compareTo(key) < 0) {
            return search(key, node.leftNode);
        }
        else if (node.key.compareTo(key) > 0) {
            return search(key, node.rightNode);
        }
        return null;
    }
}
