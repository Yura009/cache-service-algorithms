package com.example.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.example.tree.BinaryTreeTraversalTest.TraversalType.IN_ORDER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeTraversalTest {

    enum TraversalType { IN_ORDER, PRE_ORDER, POST_ORDER }

    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        return root;
    }

    @ParameterizedTest(name = "{1} traversal should return {2}")
    @MethodSource("provideTraversalTestData")
    void testTraversal(TreeNode root, TraversalType type, List<Integer> expected) {
        BinaryTreeTraversal traversal = new BinaryTreeTraversal();

        List<Integer> result = null;
        switch (type) {
            case IN_ORDER:
                result = traversal.inOrder(root);
                break;
            case PRE_ORDER:
                result = traversal.preOrder(root);
                break;
            case POST_ORDER:
                result = traversal.postOrder(root);
                break;
        }

        assertEquals(expected, result);
    }

    static Stream<Arguments> provideTraversalTestData() {
        TreeNode sampleTree = createSampleTree();
        return Stream.of(
                Arguments.of(sampleTree, IN_ORDER, Arrays.asList(4, 2, 5, 1, 3)),
                Arguments.of(sampleTree, TraversalType.PRE_ORDER, Arrays.asList(1, 2, 4, 5, 3)),
                Arguments.of(sampleTree, TraversalType.POST_ORDER, Arrays.asList(4, 5, 2, 3, 1))
        );
    }
}