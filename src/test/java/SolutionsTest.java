import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.mockito.Mockito.*;

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

        Assertions.assertTrue(solutions.isBinaryTreeSearch(root, null, null));
    }

    @Test
    void isBinaryTreeSearch_singleElementTrue() {
        var root = new BinaryTreeNode<>(10);
        Assertions.assertTrue(solutions.isBinaryTreeSearch(root, null, null));
    }

    @Test
    void isBinaryTreeSearch_false() {
        var root = new BinaryTreeNode<>(10);
        var leftChild = new BinaryTreeNode<>(12);
        var rightChild = new BinaryTreeNode<>(15);
        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);
        Assertions.assertFalse(solutions.isBinaryTreeSearch(root, null, null));
    }

    @Test
    void mergeEndlessSortedProducerQueuesIntoSingleEndlessSortedConsumerQueue() throws InterruptedException {
        @SuppressWarnings("unchecked")
        var firstProducer = (Queue<Integer>) mock(Queue.class);
        @SuppressWarnings("unchecked")
        var secondProducer = (Queue<Integer>) mock(Queue.class);
        @SuppressWarnings("unchecked")
        var consumer = (Queue<Integer>) mock(Queue.class);

        when(firstProducer.poll()).thenReturn(1, 2, 3, null);
        when(secondProducer.poll()).thenReturn(1, 2, 3, null);
        when(consumer.offer(Mockito.anyInt())).thenReturn(true, true, true, true, true, true, false);

        var producers = List.of(firstProducer, secondProducer);
        solutions.mergeSortedProducersIntoSingleSortedConsumer(
                producers,
                consumer,
                Comparator.naturalOrder()
        ).thenRunAsync(() -> {
            var inOrder = Mockito.inOrder(consumer);
            inOrder.verify(consumer, times(2)).offer(1);
            inOrder.verify(consumer, times(2)).offer(2);
            inOrder.verify(consumer, times(2)).offer(3);
        });
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

    @Test
    void findUnpairedElementInArrayOfPairedElements() {
        Assertions.assertEquals(-1, solutions.findUnpairedElementInArrayOfPairedElements(null));
        Assertions.assertEquals(-1, solutions.findUnpairedElementInArrayOfPairedElements(new Integer[]{}));
        Assertions.assertEquals(0, solutions.findUnpairedElementInArrayOfPairedElements(new Integer[]{1}));
        Assertions.assertEquals(2, solutions.findUnpairedElementInArrayOfPairedElements(
                new Integer[]{1, 1, 3, 4, 4, 5, 5, 6, 6})
        );
        Assertions.assertEquals(4, solutions.findUnpairedElementInArrayOfPairedElements(
                new Integer[]{1, 1, 4, 4, 3, 5, 5})
        );
        Assertions.assertEquals(6, solutions.findUnpairedElementInArrayOfPairedElements(
                new Integer[]{1, 1, 6, 6, 4, 4, 3, 5, 5})
        );
        Assertions.assertEquals(2, solutions.findUnpairedElementInArrayOfPairedElements(
                new Integer[]{1, 1, 3, 4, 4, 5, 5})
        );
    }
}
