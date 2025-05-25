package com.example.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTraversal {

    public List<Integer> inOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrderHelper(root, result);
        return result;
    }

    private void inOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inOrderHelper(node.left, result);
        result.add(node.value);
        inOrderHelper(node.right, result);
    }

    public List<Integer> preOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }

    private void preOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.value);
        preOrderHelper(node.left, result);
        preOrderHelper(node.right, result);
    }

    public List<Integer> postOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    private void postOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postOrderHelper(node.left, result);
        postOrderHelper(node.right, result);
        result.add(node.value);
    }
}
