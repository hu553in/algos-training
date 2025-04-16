// https://leetcode.com/problems/merge-intervals/
fun merge(intervals: Array<IntArray>): Array<IntArray> {
    val sorted = intervals.sortedBy { it[0] }
    val result = mutableListOf<IntArray>()
    result.add(sorted[0])
    for (i in 1..<sorted.size) {
        if (sorted[i][0] <= result.last()[1]) {
            result.last()[1] = maxOf(result.last()[1], sorted[i][1])
        } else {
            result.add(sorted[i])
        }
    }
    return result.toTypedArray()
}
