import java.util.PriorityQueue

// https://leetcode.com/problems/maximum-subsequence-score/
fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
    // build pairs and sort by nums2 descending
    val pairs = Array(nums1.size) { i -> nums1[i] to nums2[i] }
        .sortedByDescending { it.second }

    // min‑heap for the top‑k nums1 values
    val minHeap = PriorityQueue<Int>()
    var sum = 0L
    var best = 0L

    for ((a1, a2) in pairs) {
        minHeap.add(a1)
        sum += a1

        // if we have more than k, remove the smallest
        if (minHeap.size > k) {
            sum -= minHeap.poll().toLong()
        }

        // once we have exactly k elements, compute a candidate score
        if (minHeap.size == k) {
            best = maxOf(best, sum * a2)
        }
    }

    return best
}
