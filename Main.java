public class Main {
    public static void main(String[] args) {

        System.out.println("----- HASH TABLE TEST -----");

        MyHashTable<MyTestingClass, String> table = new MyHashTable<>(11);

        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(i, "Student" + i);
            table.put(key, "Value" + i);
        }

        System.out.println("Size: " + table.size());
        table.printBucketSizes();

        MyTestingClass testKey = new MyTestingClass(100, "Student100");

        System.out.println("Get key 100: " + table.get(testKey));
        System.out.println("Contains Value100: " + table.contains("Value100"));
        System.out.println("Remove key 100: " + table.remove(testKey));
        System.out.println("Get key 100 after remove: " + table.get(testKey));

        System.out.println();
        System.out.println("----- BST TEST -----");

        BST<Integer, String> tree = new BST<>();

        tree.put(50, "A");
        tree.put(30, "B");
        tree.put(70, "C");
        tree.put(20, "D");
        tree.put(40, "E");
        tree.put(60, "F");
        tree.put(80, "G");

        System.out.println("Size: " + tree.size());
        System.out.println("Get 40: " + tree.get(40));

        System.out.println("In-order traversal:");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        tree.delete(70);

        System.out.println("After delete 70:");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
