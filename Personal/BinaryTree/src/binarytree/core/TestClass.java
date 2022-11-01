package binarytree.core;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TestClass {

    Operations ops = new Operations();

    @Test
    public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElements() {
        BinaryTree bt = new BinaryTree();

        assertTrue(bt.containsNode(6));
        assertTrue(bt.containsNode(4));

        assertFalse(bt.containsNode(1));
    }

    @Test
    public void givenABinaryTree_WhenDeletingElements_ThenTreeDoesNotContainThoseElements() {
        BinaryTree bt = new BinaryTree();

        assertTrue(bt.containsNode(9));
        bt.delete(9);
        assertFalse(bt.containsNode(9));
    }

    @Test
    public void givenABinaryTree_ShowElements(){
        BinaryTree bt = new BinaryTree();

        System.out.println("- TraverseInOrder -");
        ops.traverseInOrder(bt.root);
        System.out.println("\n- TraversePostOrder -");
        ops.traversePostOrder(bt.root);
        System.out.println("\n- TraversePreOrder -");
        ops.traversePreOrder(bt.root);
        System.out.println("\n- TraverseLevelOrder -");
        ops.traverseLevelOrder(bt.root);
        System.out.println("\n- End -");
    }
}
