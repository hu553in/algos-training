import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    void validateParentheses() {
        Assertions.assertTrue(solution.validateParentheses("({({})})"));
        Assertions.assertFalse(solution.validateParentheses("({)}"));
        Assertions.assertFalse(solution.validateParentheses("}"));
    }

    @Test
    void concurrentMergeSort() throws Exception {
        Assertions.assertArrayEquals(
                new Integer[]{0, 1, 2, 5, 8, 22, 152},
                solution.concurrentMergeSort(new Integer[]{1, 5, 22, 2, 152, 0, 8})
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
        Assertions.assertEquals(expected, solution.sumTwoNumbers(first, second));
    }

    @Test
    void sortArrayOfFixedNumberSet() {
        Assertions.assertArrayEquals(
                new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3},
                solution.sortArrayOfFixedNumberSet(new int[]{0, 1, 3, 3, 1, 0, 1, 0, 0, 2, 2, 1})
        );
    }

    @Test
    void tickets() {
        Assertions.assertEquals(6, solution.tickets(
                new int[]{1, 1, 1, 2, 1},
                3
        ));
        Assertions.assertEquals(36, solution.tickets(
                new int[]{3, 2, 10, 17, 4},
                3
        ));
    }
}
