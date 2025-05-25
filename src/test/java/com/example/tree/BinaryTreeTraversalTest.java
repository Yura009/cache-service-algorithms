package com.example.tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeTraversalTest {

    private TreeNode createSampleTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        return root;
    }

    @Test
    void testInOrderTraversal() {
        BinaryTreeTraversal traversal = new BinaryTreeTraversal();
        TreeNode root = createSampleTree();

        List<Integer> expected = Arrays.asList(4, 2, 5, 1, 3);
        assertEquals(expected, traversal.inOrder(root));
    }

    @Test
    void testPreOrderTraversal() {
        BinaryTreeTraversal traversal = new BinaryTreeTraversal();
        TreeNode root = createSampleTree();

        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3);
        assertEquals(expected, traversal.preOrder(root));
    }

    @Test
    void testPostOrderTraversal() {
        BinaryTreeTraversal traversal = new BinaryTreeTraversal();
        TreeNode root = createSampleTree();

        List<Integer> expected = Arrays.asList(4, 5, 2, 3, 1);
        assertEquals(expected, traversal.postOrder(root));
    }
}
