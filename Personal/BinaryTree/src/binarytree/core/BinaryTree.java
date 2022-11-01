package binarytree.core;

public class BinaryTree {
//Tutorial Link
//https://www.baeldung.com/java-binary-tree

    //Starting point
    Node root;
    Operations ops = new Operations();

    public BinaryTree(){
        add(6);
        add(4);
        add(8);
        add(3);
        add(5);
        add(7);
        add(9);
    }

    //Start "addRecursive" from the root
    private void add(int value) {
        root = ops.addRecursive(root, value);
    }

    public boolean containsNode(int value) {
        return ops.containsNodeRecursive(root, value);
    }

    public void delete(int value) {
        root = ops.deleteRecursive(root, value);
    }
}
