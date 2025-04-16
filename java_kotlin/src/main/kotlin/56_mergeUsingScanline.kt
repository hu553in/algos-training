// https://leetcode.com/problems/merge-intervals/
fun mergeUsingScanline(intervals: Array<IntArray>): Array<IntArray> {
    val events = mutableListOf<Pair<Int, Int>>()
    for ((start, end) in intervals) {
        events.add(start to 1)
        events.add(end to -1)
    }

    events.sortWith(compareBy({ it.first }, { -it.second }))

    val result = mutableListOf<IntArray>()
    var active = 0
    var currentStart = -1

    for ((point, type) in events) {
        if (type == 1) {
            if (active == 0) currentStart = point
            active++
        } else {
            active--
            if (active == 0) result.add(intArrayOf(currentStart, point))
        }
    }

    return result.toTypedArray()
}
