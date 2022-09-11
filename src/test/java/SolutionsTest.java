import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class SolutionsTest {
    private final Solutions solutions = new Solutions();

    @Test
    void validateDifferentBracketsViaStack() {
        assertTrue(solutions.validateDifferentBracketsViaStack("({({})})"));
        assertFalse(solutions.validateDifferentBracketsViaStack("({)}"));
        assertFalse(solutions.validateDifferentBracketsViaStack("}"));
    }

    @Test
    void validateRoundBracketsConcurrently() throws InterruptedException {
        assertTrue(solutions.validateRoundBracketsViaMapReduce("(()())", 4));
        assertFalse(solutions.validateRoundBracketsViaMapReduce(")()(", 3));
        assertTrue(solutions.validateRoundBracketsViaMapReduce("()", 1));
    }

    @Test
    void concurrentMergeSort() throws Exception {
        assertArrayEquals(
            new Integer[] {0, 1, 2, 5, 8, 22, 152},
            solutions.concurrentMergeSort(new Integer[] {1, 5, 22, 2, 152, 0, 8})
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
        assertEquals(expected, solutions.sumTwoNumbers(first, second));
    }

    @Test
    void sortArrayOfFixedNumberSet() {
        assertArrayEquals(
            new int[] {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3},
            solutions.sortArrayOfFixedNumberSet(new int[] {0, 1, 3, 3, 1, 0, 1, 0, 0, 2, 2, 1})
        );
    }

    @Test
    void tickets() {
        assertEquals(6, solutions.tickets(
            new int[] {1, 1, 1, 2, 1},
            3
        ));
        assertEquals(36, solutions.tickets(
            new int[] {3, 2, 10, 17, 4},
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
        assertEquals(expected, actual);
    }

    @Test
    void convertBinarySearchTreeToList_singleElement() {
        var root = new BinaryTreeNode<>(10);
        var actual = solutions.convertBinarySearchTreeToList(root);
        var expected = List.of(10);
        assertEquals(expected, actual);
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
        assertEquals(expected, actual);
    }

    @Test
    void convertBinarySearchTreeToListWithRecursion_singleElement() {
        var root = new BinaryTreeNode<>(10);
        var actual = solutions.convertBinarySearchTreeToListWithRecursion(root);
        var expected = List.of(10);
        assertEquals(expected, actual);
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

        assertTrue(solutions.isBinaryTreeSearch(root, null, null));
    }

    @Test
    void isBinaryTreeSearch_singleElementTrue() {
        var root = new BinaryTreeNode<>(10);
        assertTrue(solutions.isBinaryTreeSearch(root, null, null));
    }

    @Test
    void isBinaryTreeSearch_false() {
        var root = new BinaryTreeNode<>(10);
        var leftChild = new BinaryTreeNode<>(12);
        var rightChild = new BinaryTreeNode<>(15);
        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);
        assertFalse(solutions.isBinaryTreeSearch(root, null, null));
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

        assertEquals(10, mirroredRoot.getValue());
        assertEquals(5, node5.getValue());
        assertEquals(15, node15.getValue());
        assertEquals(3, node3.getValue());
        assertEquals(7, node7.getValue());
        assertEquals(13, node13.getValue());

        assertNull(mirroredRoot.getParent());
        assertNull(node3.getLeftChild());
        assertNull(node3.getRightChild());
        assertNull(node7.getLeftChild());
        assertNull(node7.getRightChild());
        assertNull(node15.getLeftChild());
        assertNull(node13.getLeftChild());
        assertNull(node13.getRightChild());
    }

    @Test
    void findUnpairedElementInArrayOfPairedElements() {
        assertEquals(-1, solutions.findUnpairedElementInArrayOfPairedElements(null));
        assertEquals(-1, solutions.findUnpairedElementInArrayOfPairedElements(new Integer[] {}));
        assertEquals(0, solutions.findUnpairedElementInArrayOfPairedElements(new Integer[] {1}));
        assertEquals(2, solutions.findUnpairedElementInArrayOfPairedElements(
            new Integer[] {1, 1, 3, 4, 4, 5, 5, 6, 6})
        );
        assertEquals(4, solutions.findUnpairedElementInArrayOfPairedElements(
            new Integer[] {1, 1, 4, 4, 3, 5, 5})
        );
        assertEquals(6, solutions.findUnpairedElementInArrayOfPairedElements(
            new Integer[] {1, 1, 6, 6, 4, 4, 3, 5, 5})
        );
        assertEquals(2, solutions.findUnpairedElementInArrayOfPairedElements(
            new Integer[] {1, 1, 3, 4, 4, 5, 5})
        );
    }

    @Test
    void reverse() {
        assertEquals(321, solutions.reverseInteger(123));
        assertEquals(-321, solutions.reverseInteger(-123));
        assertEquals(21, solutions.reverseInteger(120));
        assertEquals(0, solutions.reverseInteger(1534236469));
    }

    @Test
    void myAtoi() {
        assertEquals(42, solutions.myAtoi("42"));
        assertEquals(-42, solutions.myAtoi("    -42"));
        assertEquals(4193, solutions.myAtoi("4193 with words"));
        assertEquals(Integer.MAX_VALUE, solutions.myAtoi("97412637846238764827364"));
        assertEquals(Integer.MIN_VALUE, solutions.myAtoi("-97412637846238764827364"));
        assertEquals(Integer.MAX_VALUE, solutions.myAtoi("2147483648"));
        assertEquals(Integer.MIN_VALUE, solutions.myAtoi("-91283472332"));
    }

    @Test
    void threeSum() {
        assertEquals(
            List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)),
            solutions.threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
        assertEquals(List.of(), solutions.threeSum(new int[] {0, 1, 1}));
        assertEquals(List.of(List.of(0, 0, 0)), solutions.threeSum(new int[] {0, 0, 0}));
    }

    @Test
    void threeSumClosest() {
        assertEquals(2, solutions.threeSumClosest(new int[] {-1, 2, 1, -4}, 1));
        assertEquals(0, solutions.threeSumClosest(new int[] {0, 0, 0}, 1));
        assertEquals(-2, solutions.threeSumClosest(new int[] {4, 0, 5, -5, 3, 3, 0, -4, -5}, -2));
        assertEquals(-2805, solutions.threeSumClosest(new int[] {13, 252, -87, -431, -148, 387, -290, 572,
            -311, -721, 222, 673, 538, 919, 483, -128, -518, 7, -36, -840, 233, -184, -541, 522, -162, 127, -935,
            -397, 761, 903, -217, 543, 906, -503, -826, -342, 599, -726, 960, -235, 436, -91, -511, -793, -658, -143,
            -524, -609, -728, -734, 273, -19, -10, 630, -294, -453, 149, -581, -405, 984, 154, -968, 623, -631, 384,
            -825, 308, 779, -7, 617, 221, 394, 151, -282, 472, 332, -5, -509, 611, -116, 113, 672, -497, -182, 307,
            -592, 925, 766, -62, 237, -8, 789, 318, -314, -792, -632, -781, 375, 939, -304, -149, 544, -742, 663, 484,
            802, 616, 501, -269, -458, -763, -950, -390, -816, 683, -219, 381, 478, -129, 602, -931, 128, 502, 508,
            -565, -243, -695, -943, -987, -692, 346, -13, -225, -740, -441, -112, 658, 855, -531, 542, 839, 795, -664,
            404, -844, -164, -709, 167, 953, -941, -848, 211, -75, 792, -208, 569, -647, -714, -76, -603, -852, -665,
            -897, -627, 123, -177, -35, -519, -241, -711, -74, 420, -2, -101, 715, 708, 256, -307, 466, -602, -636, 990,
            857, 70, 590, -4, 610, -151, 196, -981, 385, -689, -617, 827, 360, -959, -289, 620, 933, -522, 597, -667,
            -882, 524, 181, -854, 275, -600, 453, -942, 134}, -2805));
    }

    @Test
    void numberOfWeakCharacters() {
        assertEquals(0, solutions.numberOfWeakCharacters(new int[][] {
            new int[] {5, 5},
            new int[] {6, 3},
            new int[] {3, 6}
        }));
        assertEquals(1, solutions.numberOfWeakCharacters(new int[][] {
            new int[] {2, 2},
            new int[] {3, 3}
        }));
        assertEquals(1, solutions.numberOfWeakCharacters(new int[][] {
            new int[] {1, 5},
            new int[] {10, 4},
            new int[] {4, 3}
        }));
        assertEquals(3, solutions.numberOfWeakCharacters(new int[][] {
            new int[] {1, 5},
            new int[] {4, 3},
            new int[] {10, 4},
            new int[] {12, 6}
        }));
        assertEquals(1, solutions.numberOfWeakCharacters(new int[][] {
            new int[] {1, 1},
            new int[] {2, 1},
            new int[] {2, 2},
            new int[] {1, 2}
        }));
        assertEquals(2, solutions.numberOfWeakCharacters(new int[][] {
            new int[] {7, 9},
            new int[] {10, 7},
            new int[] {6, 9},
            new int[] {10, 4},
            new int[] {7, 5},
            new int[] {7, 10}
        }));
        assertEquals(4, solutions.numberOfWeakCharacters(new int[][] {
            new int[] {4, 10},
            new int[] {2, 2},
            new int[] {8, 8},
            new int[] {10, 2},
            new int[] {5, 5},
            new int[] {9, 10},
            new int[] {2, 6}
        }));
    }

    @Test
    void letterCombinations() {
        assertCollectionsEqualUnordered(
            new ArrayList<>(List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")),
            solutions.letterCombinations("23"));
        assertCollectionsEqualUnordered(
            new ArrayList<>(List.of("adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh", "afi", "bdg",
                "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh", "bfi", "cdg", "cdh", "cdi", "ceg", "ceh", "cei",
                "cfg", "cfh", "cfi")),
            solutions.letterCombinations("234"));
    }

    @Test
    void removeNthFromEnd() {
        assertEquals(
            List.of(1, 2, 3, 5),
            convertListNodesToList(solutions.removeNthFromEnd(convertListToListNodes(List.of(1, 2, 3, 4, 5)), 2)));
        assertEquals(
            List.of(1),
            convertListNodesToList(solutions.removeNthFromEnd(convertListToListNodes(List.of(1, 2)), 1)));
        assertEquals(
            List.of(2),
            convertListNodesToList(solutions.removeNthFromEnd(convertListToListNodes(List.of(1, 2)), 2)));
        assertEquals(
            List.of(),
            convertListNodesToList(solutions.removeNthFromEnd(convertListToListNodes(List.of(1)), 1)));
    }

    @Test
    void swapPairs() {
        assertEquals(
            List.of(2, 1, 4, 3, 6, 5, 8, 7, 9),
            convertListNodesToList(solutions.swapPairs(convertListToListNodes(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)))));
        assertEquals(
            List.of(2, 1, 4, 3, 6, 5, 8, 7),
            convertListNodesToList(solutions.swapPairs(convertListToListNodes(List.of(1, 2, 3, 4, 5, 6, 7, 8)))));
        assertEquals(
            List.of(),
            convertListNodesToList(solutions.swapPairs(convertListToListNodes(List.of()))));
        assertEquals(
            List.of(1),
            convertListNodesToList(solutions.swapPairs(convertListToListNodes(List.of(1)))));
    }

    @Test
    void generateParenthesis() {
        assertCollectionsEqualUnordered(List.of("()"), solutions.generateParenthesis(1));
        assertCollectionsEqualUnordered(List.of("(())", "()()"), solutions.generateParenthesis(2));
        assertCollectionsEqualUnordered(
            List.of("((()))", "(()())", "(())()", "()(())", "()()()"),
            solutions.generateParenthesis(3));
    }

    @Test
    void findLongest1SequenceAfterDeletingSingleElement() {
        assertEquals(
            6,
            solutions.findLongest1SequenceAfterDeletingSingleElement(new int[] {1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1}));
        assertEquals(
            5,
            solutions.findLongest1SequenceAfterDeletingSingleElement(new int[] {1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1}));
        assertEquals(
            2,
            solutions.findLongest1SequenceAfterDeletingSingleElement(new int[] {1, 1, 1}));
    }

    @Test
    void findLongest1SequenceInBinaryVector() {
        assertEquals(
            5,
            solutions.findLongest1SequenceInBinaryVector(new int[] {1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1}));
        assertEquals(0, solutions.findLongest1SequenceInBinaryVector(new int[] {0, 0, 0}));
    }

    @Test
    void areAnagrams() {
        assertTrue(solutions.areAnagrams("number", "bernum"));
        assertFalse(solutions.areAnagrams("number", "numbers"));
    }

    @Test
    void getIntersectionOfSortedArrays() {
        assertArrayEquals(new int[] {2, 3}, solutions.getIntersectionOfSortedArrays(
            new int[] {1, 2, 3, 5},
            new int[] {0, 2, 3, 8}
        ));
        assertArrayEquals(new int[0], solutions.getIntersectionOfSortedArrays(
            new int[] {1, 5, 7, 9},
            new int[] {0, 2, 3, 8}
        ));
    }

    private <T extends Comparable<T>> void assertCollectionsEqualUnordered(List<T> first, List<T> second) {
        assertTrue(first.size() == second.size() && first.containsAll(second) && second.containsAll(first));
    }

    private List<Integer> convertListNodesToList(ListNode head) {
        LinkedList<Integer> result = new LinkedList<>();
        ListNode current = head;
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        return result;
    }

    private ListNode convertListToListNodes(List<Integer> list) {
        if (list.isEmpty()) {
            return null;
        }
        ListNode head = new ListNode(list.get(0));
        ListNode current = head;
        for (int i = 1; i < list.size(); i++) {
            ListNode newNode = new ListNode(list.get(i));
            current.next = newNode;
            current = newNode;
        }
        return head;
    }
}
