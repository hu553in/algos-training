import java.util.PriorityQueue

// https://leetcode.com/problems/kth-largest-element-in-an-array/
fun findKthLargest(nums: IntArray, k: Int): Int {
    val pq = PriorityQueue<Int>(nums.size, reverseOrder())
    nums.forEach { pq.offer(it) }
    var elem: Int? = null
    for (i in 1..k) {
        elem = pq.poll()
    }
    return elem!!
}
