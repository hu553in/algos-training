// https://leetcode.com/problems/longest-consecutive-sequence/
fun longestConsecutive(nums: IntArray): Int {
    val m = mutableMapOf<Int, Int>()
    var max = 0
    nums.sorted().forEach { num ->
        val count = m[num - 1]?.inc() ?: m[num] ?: 1
        m[num] = count
        max = maxOf(max, count)
        m.remove(num - 1)
    }
    return max
}
