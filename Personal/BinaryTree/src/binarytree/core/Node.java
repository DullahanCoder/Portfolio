package binarytree.core;

public class Node {
    int value;
    //Child
    Node left;
    //Child
    Node right;

    //Auxiliary Node
    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}
