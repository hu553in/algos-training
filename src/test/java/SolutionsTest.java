import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
        var root = new BinarySearchTreeNode<>(10);
        var leftChild = new BinarySearchTreeNode<>(5);
        var rightChild = new BinarySearchTreeNode<>(15);
        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);
        leftChild.setLeftChild(new BinarySearchTreeNode<>(3));
        leftChild.setRightChild(new BinarySearchTreeNode<>(7));
        rightChild.setLeftChild(new BinarySearchTreeNode<>(13));
        var actual = solutions.convertBinarySearchTreeToList(root);
        var expected = List.of(3, 5, 7, 10, 13, 15);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void convertBinarySearchTreeToList_singleElement() {
        var root = new BinarySearchTreeNode<>(10);
        var actual = solutions.convertBinarySearchTreeToList(root);
        var expected = List.of(10);
        Assertions.assertEquals(expected, actual);
    }

    // TODO: enable when method is implemented
    @Test
    @Disabled
    void convertBinarySearchTreeToListWithRecursion() {
        var root = new BinarySearchTreeNode<>(10);
        var leftChild = new BinarySearchTreeNode<>(5);
        var rightChild = new BinarySearchTreeNode<>(15);
        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);
        leftChild.setLeftChild(new BinarySearchTreeNode<>(3));
        leftChild.setRightChild(new BinarySearchTreeNode<>(7));
        rightChild.setLeftChild(new BinarySearchTreeNode<>(13));
        var actual = solutions.convertBinarySearchTreeToListWithRecursion(root);
        var expected = List.of(3, 5, 7, 10, 13, 15);
        Assertions.assertEquals(expected, actual);
    }

    // TODO: enable when method is implemented
    @Test
    @Disabled
    void convertBinarySearchTreeToListWithRecursion_singleElement() {
        var root = new BinarySearchTreeNode<>(10);
        var actual = solutions.convertBinarySearchTreeToListWithRecursion(root);
        var expected = List.of(10);
        Assertions.assertEquals(expected, actual);
    }
}
