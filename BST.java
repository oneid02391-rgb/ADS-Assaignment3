import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Entry> {

    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public class Entry {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return;
        }

        Node current = root;

        while (true) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                current.value = value;
                return;
            } else if (cmp < 0) {
                if (current.left == null) {
                    current.left = new Node(key, value);
                    size++;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(key, value);
                    size++;
                    return;
                }
                current = current.right;
            }
        }
    }

    public V get(K key) {
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                return current.value;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node current = root;

        while (current != null && !current.key.equals(key)) {
            parent = current;

            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return;
        }

        if (current.left != null && current.right != null) {
            Node minParent = current;
            Node min = current.right;

            while (min.left != null) {
                minParent = min;
                min = min.left;
            }

            current.key = min.key;
            current.value = min.value;

            parent = minParent;
            current = min;
        }

        Node child;

        if (current.left != null) {
            child = current.left;
        } else {
            child = current.right;
        }

        if (parent == null) {
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        size--;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Entry> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator() {
            Node current = root;

            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Entry next() {
            Node node = stack.pop();

            Node current = node.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            return new Entry(node.key, node.value);
        }
    }
}
