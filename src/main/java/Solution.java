import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public boolean validateParentheses(final String s) {
        var closingForOpening = Map.of(
                '(', ')',
                '[', ']',
                '{', '}'
        );
        var stack = new ArrayDeque<Character>();
        var chars = s.toCharArray();
        for (var newChar : chars) {
            if (closingForOpening.containsKey(newChar)) {
                stack.push(newChar);
            } else {
                char closingForNewChar = stack.pop();
                var expectedClosing = closingForOpening.get(closingForNewChar);
                if (newChar != expectedClosing) {
                    return false;
                }
            }
        }
        return true;
    }

    public <T extends Comparable<T>> T[] concurrentMergeSort(final T[] array) throws Exception {
        return executor.submit(() -> {
            var length = array.length;
            if (length > 1) {
                var middle = length / 2;
                var left = concurrentMergeSort(Arrays.copyOfRange(array, 0, middle));
                var right = concurrentMergeSort(Arrays.copyOfRange(array, middle, length));
                var leftIndex = 0;
                var rightIndex = 0;
                var arrayIndex = 0;
                while (leftIndex < left.length && rightIndex < right.length) {
                    if (left[leftIndex].compareTo(right[rightIndex]) < 0) {
                        array[arrayIndex] = left[leftIndex];
                        leftIndex++;
                    } else if (left[leftIndex].compareTo(right[rightIndex]) > 0) {
                        array[arrayIndex] = right[rightIndex];
                        rightIndex++;
                    }
                    arrayIndex++;
                }
                while (leftIndex < left.length) {
                    array[arrayIndex] = left[leftIndex];
                    leftIndex++;
                    arrayIndex++;
                }
                while (rightIndex < right.length) {
                    array[arrayIndex] = right[rightIndex];
                    rightIndex++;
                    arrayIndex++;
                }
            }
            return array;
        }).get();
    }

    public LinkedList<Integer> sumTwoNumbers(
            final LinkedList<Integer> first,
            final LinkedList<Integer> second
    ) {
        var sum = new LinkedList<Integer>();
        var remainder = 0;
        for (int i = 0, j = 0; i < first.size() || j < second.size(); i++, j++) {
            var firstDigit = i < first.size() ? first.get(i) : 0;
            var secondDigit = j < second.size() ? second.get(j) : 0;
            var sumDigit = firstDigit + secondDigit + remainder;
            if (sumDigit > 9) {
                remainder = 1;
                sumDigit %= 10;
            }
            sum.add(sumDigit);
        }
        return sum;
    }

    public int[] sortArrayOfFixedNumberSet(int[] array) {
        var countingMap = new TreeMap<Integer, Integer>();
        for (var elem : array) {
            countingMap.compute(elem, (k, v) -> v == null ? 1 : v + 1);
        }
        var arrayIndex = new AtomicInteger();
        countingMap.forEach((k, v) -> {
            for (var i = 0; i < v; i++, arrayIndex.getAndIncrement()) {
                array[arrayIndex.get()] = k;
            }
        });
        return array;
    }

    public int tickets(final int[] requiredTicketCounts, final int humanIndex) {
        var queueLength = requiredTicketCounts.length - 1;
        var iterations = 0;
        var mutableHumanIndex = humanIndex;
        while (true) {
            iterations++;
            var requiredTicketCount = requiredTicketCounts[0] - 1;
            System.arraycopy(requiredTicketCounts, 1, requiredTicketCounts, 0, queueLength);
            requiredTicketCounts[queueLength] = requiredTicketCount;
            if (requiredTicketCount > 0) {
                if (mutableHumanIndex == 0) {
                    mutableHumanIndex = queueLength;
                } else {
                    mutableHumanIndex--;
                }
            } else {
                if (mutableHumanIndex == 0) {
                    return iterations;
                }
                mutableHumanIndex--;
                queueLength--;
            }
        }
    }
}
