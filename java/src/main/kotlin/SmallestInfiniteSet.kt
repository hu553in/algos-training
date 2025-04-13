import java.util.PriorityQueue

// https://leetcode.com/problems/smallest-number-in-infinite-set/
class SmallestInfiniteSet {

    private val popped = mutableSetOf<Int>()
    private val pq = PriorityQueue<Int>()
        .also { it.offer(1) }

    fun popSmallest(): Int {
        val elem = pq.poll()
        popped.add(elem)
        val next = pq.peek()
        var returned = elem + 1
        while (popped.contains(returned)) {
            returned++
        }
        if (next == null || next > returned) {
            addBack(returned)
        }
        return elem
    }

    fun addBack(num: Int) {
        popped.remove(num)
        pq.remove(num)
        pq.offer(num)
    }
}
