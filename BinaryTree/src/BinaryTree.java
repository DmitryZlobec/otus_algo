public class BinaryTree<K extends Comparable<K>,V> implements ITree<K, V>{
    private Node<K,V> rootNode;

    @Override
    public void add(K key, V value) {
        if(rootNode == null) {
            System.out.println("Root " + key );
            rootNode = new Node<>(key, value);
        }
        else {
            addToEmptyNode(key, value, rootNode);
        }
    }

    @Override
    public void remove(K key) {
        if(rootNode == null || rootNode.key == null) {
            return;
        }
         rootNode = remove(key, rootNode);
    }

    private Node<K,V> remove(K key, Node<K,V> node) {
        if(node == null ) {
            return null;
        }
        if(key.compareTo(node.key) == 0) {
            if(node.leftNode == null && node.rightNode==null) {
                return null;
            }
            if(node.rightNode == null) {
                return node.leftNode;
            }
            if(node.leftNode == null) {
                return node.rightNode;
            }
            Node<K, V> max = findMax(node.leftNode);
            max.leftNode = node.leftNode;
            max.rightNode = node.rightNode;
            clearRight(node.leftNode);
            return  max;
        }
        if(key.compareTo(node.key)<0) {
            node.leftNode = remove(key, node.leftNode);
        }
        if(key.compareTo(node.key)>0) {
            node.rightNode = remove(key, node.rightNode);
        }
        return  node;
    }

    public K findMax() {
        Node<K, V> min = findMax(rootNode);
        return min.key;
    }

    private Node<K,V> findMax(Node<K,V> node) {
        if(node.rightNode == null) {
            return node;
        }
        return findMax(node.rightNode);
    }

    private void clearRight(Node<K,V> node) {
        if(node == null) {
            return;
        }
        if(node.rightNode.rightNode == null) {
            node.rightNode=null;
        }
        clearRight(node.rightNode);
    }

    @Override
    public V search(K obj) {
        if(rootNode == null || rootNode.key == null) {
            return null;
        }
            return search(obj, rootNode);
    }

    private V search(K key, Node<K,V> node) {
        if(node == null ) {
            return  null;
        }
        System.out.println("Key "+ key+" Node: Key - " + node.key + " Value " + node.value);
        if(key.compareTo(node.key)==0) {
            System.out.println("Find!" + node.value);
            return node.value;
        }
        if(key.compareTo(node.key) < 0) {
            System.out.println("Left "+ node.key );
               return search(key, node.leftNode);
        }
        else if (key.compareTo(node.key) > 0) {
            System.out.println("Right " + node.key);
            return search(key, node.rightNode);
        }
        return null;
    }

    /**
     * Поиск ноды
     * @param key
     * @param node
     * @return
     */
    private Node<K,V> searchNode(K key, Node<K,V> node) {
        if(node == null ) {
            return  null;
        }
        System.out.println("Key "+ key+" Node: Key - " + node.key + " Value " + node.value);
        if(key.compareTo(node.key)==0) {
            System.out.println("Find!" + node.value);
            return node;
        }
        if(key.compareTo(node.key) < 0) {
            System.out.println("Left "+ node.key );
            return searchNode(key, node.leftNode);
        }
        else if (key.compareTo(node.key) > 0) {
            System.out.println("Right " + node.key);
            return searchNode(key, node.rightNode);
        }
        return null;
    }


    public void addToEmptyNode(K key, V value, Node<K,V> node) {
        if (key.compareTo(node.key)<0) {
            System.out.println("Less " + key +"<"+node.key);
            if (node.leftNode == null) {
                System.out.println("Add to left node ");
                node.leftNode = new Node<>(key, value);
            } else {
                System.out.println("Go left");
                addToEmptyNode(key, value, node.leftNode);
            }
            return;
        }
        else if (key.compareTo(node.key)>0) {
            System.out.println("More " + key +">"+node.key);
            if(node.rightNode == null)
            {
                System.out.println("Add to right node ");
                node.rightNode= new Node<>(key,value);
            }
            else {
                System.out.println("Go right");
                addToEmptyNode(key,value, node.rightNode);
            }
            return;
        }
        node.value = value;
    }
}
