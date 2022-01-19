import binary_tree.BinaryTreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import queue.consumer.EndlessSortedConsumerImpl;
import queue.producer.EndlessSortedProducerImpl;

import java.util.LinkedList;
import java.util.List;

class SolutionsTest {
    private final Solutions solutions = new Solutions();

    @Test
    void validateDifferentBracketsViaStack() {
        Assertions.assertTrue(solutions.validateDifferentBracketsViaStack("({({})})"));
        Assertions.assertFalse(solutions.validateDifferentBracketsViaStack("({)}"));
        Assertions.assertFalse(solutions.validateDifferentBracketsViaStack("}"));
    }

    @Test
    void validateRoundBracketsConcurrently() throws InterruptedException {
        Assertions.assertTrue(solutions.validateRoundBracketsViaMapReduce("(()())", 4));
        Assertions.assertFalse(solutions.validateRoundBracketsViaMapReduce(")()(", 3));
        Assertions.assertTrue(solutions.validateRoundBracketsViaMapReduce("()", 1));
    }

    @Test
    void concurrentMergeSort() throws Exception {
        Assertions.assertArrayEquals(
                new Integer[]{0, 1, 2, 5, 8, 22, 152},
                solutions.concurrentMergeSort(new Integer[]{1, 5, 22, 2, 152, 0, 8})
        );
    }

    @Test
    void sumTwoNumbers() {
        var first = new LinkedList<Integer>() {{
            add(6);
            add(7);
            add(8);
        }};
        var second = new LinkedList<Integer>() {{
            add(4);
            add(5);
            add(6);
            add(7);
        }};
        var expected = new LinkedList<Integer>() {{
            add(0);
            add(3);
            add(5);
            add(8);
        }};
        Assertions.assertEquals(expected, solutions.sumTwoNumbers(first, second));
    }

    @Test
    void sortArrayOfFixedNumberSet() {
        Assertions.assertArrayEquals(
                new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3},
                solutions.sortArrayOfFixedNumberSet(new int[]{0, 1, 3, 3, 1, 0, 1, 0, 0, 2, 2, 1})
        );
    }

    @Test
    void tickets() {
        Assertions.assertEquals(6, solutions.tickets(
                new int[]{1, 1, 1, 2, 1},
                3
        ));
        Assertions.assertEquals(36, solutions.tickets(
                new int[]{3, 2, 10, 17, 4},
                3
        ));
    }

    @Test
    void convertBinarySearchTreeToList() {
        var root = new BinaryTreeNode<>(10);
        var leftChild = new BinaryTreeNode<>(5);
        var rightChild = new BinaryTreeNode<>(15);

        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);

        leftChild.setLeftChild(new BinaryTreeNode<>(3));
        leftChild.setRightChild(new BinaryTreeNode<>(7));
        rightChild.setLeftChild(new BinaryTreeNode<>(13));

        var actual = solutions.convertBinarySearchTreeToList(root);
        var expected = List.of(3, 5, 7, 10, 13, 15);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void convertBinarySearchTreeToList_singleElement() {
        var root = new BinaryTreeNode<>(10);
        var actual = solutions.convertBinarySearchTreeToList(root);
        var expected = List.of(10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void convertBinarySearchTreeToListWithRecursion() {
        var root = new BinaryTreeNode<>(10);
        var leftChild = new BinaryTreeNode<>(5);
        var rightChild = new BinaryTreeNode<>(15);

        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);

        leftChild.setLeftChild(new BinaryTreeNode<>(3));
        leftChild.setRightChild(new BinaryTreeNode<>(7));
        rightChild.setLeftChild(new BinaryTreeNode<>(13));

        var actual = solutions.convertBinarySearchTreeToListWithRecursion(root);
        var expected = List.of(3, 5, 7, 10, 13, 15);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void convertBinarySearchTreeToListWithRecursion_singleElement() {
        var root = new BinaryTreeNode<>(10);
        var actual = solutions.convertBinarySearchTreeToListWithRecursion(root);
        var expected = List.of(10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void isBinaryTreeSearch_true() {
        var root = new BinaryTreeNode<>(10);
        var leftChild = new BinaryTreeNode<>(5);
        var rightChild = new BinaryTreeNode<>(15);

        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);

        leftChild.setLeftChild(new BinaryTreeNode<>(3));
        leftChild.setRightChild(new BinaryTreeNode<>(7));
        rightChild.setLeftChild(new BinaryTreeNode<>(13));

        Assertions.assertTrue(solutions.isBinaryTreeSearch(root));
    }

    @Test
    void isBinaryTreeSearch_singleElementTrue() {
        var root = new BinaryTreeNode<>(10);
        Assertions.assertTrue(solutions.isBinaryTreeSearch(root));
    }

    @Test
    void isBinaryTreeSearch_false() {
        var root = new BinaryTreeNode<>(10);
        var leftChild = new BinaryTreeNode<>(12);
        var rightChild = new BinaryTreeNode<>(15);
        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);
        Assertions.assertFalse(solutions.isBinaryTreeSearch(root));
    }

    @Test
    void mergeEndlessSortedProducerQueuesIntoSingleEndlessSortedConsumerQueue() throws InterruptedException {
        var producers = List.of(
                new EndlessSortedProducerImpl(),
                new EndlessSortedProducerImpl()
        );
        var actualConsumer = new EndlessSortedConsumerImpl<Long>();
        var shutdown = solutions.mergeEndlessSortedProducerQueuesIntoSingleEndlessSortedConsumerQueue(
                producers,
                actualConsumer
        );
        Thread.sleep(5000);
        shutdown.run();
        var actualConsumerList = actualConsumer.toList();
        var expectedConsumer = new EndlessSortedConsumerImpl<Long>();
        for (var i = 0L; i < actualConsumerList.size() / 2; i++) {
            expectedConsumer.offer(i);
            expectedConsumer.offer(i);
        }
        Assertions.assertEquals(
                expectedConsumer.toList(),
                actualConsumerList
        );
    }

    @Test
    void mirrorBinaryTree() {
        var root = new BinaryTreeNode<>(10);
        var leftChild = new BinaryTreeNode<>(5);
        var rightChild = new BinaryTreeNode<>(15);

        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);

        leftChild.setLeftChild(new BinaryTreeNode<>(3));
        leftChild.setRightChild(new BinaryTreeNode<>(7));
        rightChild.setLeftChild(new BinaryTreeNode<>(13));

        var mirroredRoot = solutions.mirrorBinaryTree(root);
        var node5 = mirroredRoot.getRightChild();
        var node15 = mirroredRoot.getLeftChild();
        var node3 = node5.getRightChild();
        var node7 = node5.getLeftChild();
        var node13 = node15.getRightChild();

        Assertions.assertEquals(10, mirroredRoot.getValue());
        Assertions.assertEquals(5, node5.getValue());
        Assertions.assertEquals(15, node15.getValue());
        Assertions.assertEquals(3, node3.getValue());
        Assertions.assertEquals(7, node7.getValue());
        Assertions.assertEquals(13, node13.getValue());

        Assertions.assertNull(mirroredRoot.getParent());
        Assertions.assertNull(node3.getLeftChild());
        Assertions.assertNull(node3.getRightChild());
        Assertions.assertNull(node7.getLeftChild());
        Assertions.assertNull(node7.getRightChild());
        Assertions.assertNull(node15.getLeftChild());
        Assertions.assertNull(node13.getLeftChild());
        Assertions.assertNull(node13.getRightChild());
    }
}
