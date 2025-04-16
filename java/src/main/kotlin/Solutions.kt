import java.util.PriorityQueue

class TreeNode(var `val`: Int) {
    constructor(`val`: Int, left: TreeNode? = null, right: TreeNode? = null) : this(`val`) {
        this.left = left
        this.right = right
    }

    var left: TreeNode? = null
    var right: TreeNode? = null
}

// https://leetcode.com/problems/binary-tree-right-side-view/
fun rightSideView(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()
    if (root == null) return result
    var currentLayer = mutableListOf(root)
    var nextLayer = mutableListOf<TreeNode>()
    while (currentLayer.isNotEmpty()) {
        result.add(currentLayer.last().`val`)
        currentLayer.forEach { elem ->
            elem.left?.let { nextLayer.add(it) }
            elem.right?.let { nextLayer.add(it) }
        }
        currentLayer = nextLayer
        nextLayer = mutableListOf()
    }
    return result
}

// https://leetcode.com/problems/symmetric-tree/
fun isSymmetric(root: TreeNode?): Boolean {
    fun isSymmetric(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null || right == null) {
            return left == null && right == null
        }
        val n1 = left.left
        val n2 = left.right
        val n3 = right.left
        val n4 = right.right
        return left.`val` == right.`val`
                && isSymmetric(n1, n4)
                && isSymmetric(n2, n3)
    }

    return root == null || isSymmetric(root.left, root.right)
}

// https://leetcode.com/problems/number-of-islands/
fun numIslands(grid: Array<CharArray>): Int {
    fun handleIsland(i: Int, j: Int, grid: Array<CharArray>) {
        if (grid[i][j] == '1') {
            grid[i][j] = '0'
            if (i < grid.size - 1) {
                handleIsland(i + 1, j, grid)
            }
            if (j < grid[i].size - 1) {
                handleIsland(i, j + 1, grid)
            }
            if (i > 0) {
                handleIsland(i - 1, j, grid)
            }
            if (j > 0) {
                handleIsland(i, j - 1, grid)
            }
        }
    }

    var count = 0
    for (i in 0..grid.lastIndex) {
        for (j in 0..grid[i].lastIndex) {
            if (grid[i][j] == '1') {
                count++
                handleIsland(i, j, grid)
            }
        }
    }
    return count
}

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

// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
fun twoSum(numbers: IntArray, target: Int): IntArray {
    var i1 = 0
    var i2 = numbers.size - 1
    while (numbers[i1] + numbers[i2] != target) {
        if (numbers[i1] + numbers[i2] > target) {
            i2--
        } else i1++
    }
    return intArrayOf(i1 + 1, i2 + 1)
}

// https://leetcode.com/problems/minimum-size-subarray-sum/
fun `minSubArrayLen - slidingWindow`(target: Int, nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    var left = 0
    var min = 0
    var sum = 0

    for (right in nums.indices) {
        sum += nums[right]

        while (sum >= target) {
            val candidate = right - left + 1
            if (min == 0 || candidate < min) {
                min = candidate
            }
            sum -= nums[left]
            left++
        }
    }

    return min
}

// https://leetcode.com/problems/minimum-size-subarray-sum/
fun `minSubArrayLen - prefixSums`(target: Int, nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    val ps = Array(nums.size + 1) { 0 }
    for (i in 1..nums.size) {
        ps[i] = ps[i - 1] + nums[i - 1]
    }

    var min = 0

    for (i in ps.indices) {
        for (j in i + 1..<ps.size) {
            if (ps[j] - ps[i] >= target) {
                val candidate = j - i
                if (min == 0 || candidate < min) {
                    min = candidate
                }
            }
        }
    }

    return min
}

// https://leetcode.com/problems/group-anagrams/
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    return strs.groupBy { it.toCharArray().sorted().joinToString() }.values.toList()
}

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

// https://leetcode.com/problems/merge-intervals/
fun `merge - simple`(intervals: Array<IntArray>): Array<IntArray> {
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

// https://leetcode.com/problems/merge-intervals/
fun `merge - scanline`(intervals: Array<IntArray>): Array<IntArray> {
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

// https://leetcode.com/problems/permutations/
fun permute(nums: IntArray): List<List<Int>> {
    if (nums.size == 1) return listOf(nums.toList())
    val result = mutableListOf<List<Int>>()

    fun backtrack(path: MutableList<Int>, remaining: MutableList<Int>) {
        if (remaining.isEmpty()) {
            result.add(path.toList())
            return
        }
        for (i in remaining.indices) {
            val num = remaining.removeAt(i)
            path.add(num)

            backtrack(path, remaining)

            // backtrack
            path.removeLast()
            remaining.add(i, num)
        }
    }

    backtrack(mutableListOf(), nums.toMutableList())
    return result
}

// https://leetcode.com/problems/combinations/
fun combine(n: Int, k: Int): List<List<Int>> {
    if (n == k) return listOf((1..n).toMutableList())
    val result = mutableListOf<List<Int>>()

    fun backtrack(start: Int, path: MutableList<Int>) {
        if (path.size == k) {
            result.add(path.toList())
            return
        }
        for (i in start..n) {
            path.add(i)
            backtrack(i + 1, path)
            path.removeLast() // backtrack
        }
    }

    backtrack(1, mutableListOf())
    return result
}

data class UserPerDay(val id: Int, val steps: Int)
data class Top(val userIds: List<Int>, val steps: Int)

/**
 * Мы в нашей компании любим проводить соревнования,
 * — недавно мы устроили чемпионат по шагам. И вот настало время подводить итоги!
 * Необходимо определить userIds участников, которые прошли наибольшее количество шагов steps за все дни,
 * не пропустив ни одного дня соревнований.
 */
fun calculateTop(statsPerDay: List<List<UserPerDay>>): Top {
    val map = mutableMapOf<Int, Int>()
    statsPerDay.withIndex().forEach { (day, statsPerDay) ->
        val usersPerDay = mutableSetOf<Int>()
        statsPerDay.forEach { statsPerUserPerDay ->
            usersPerDay.add(statsPerUserPerDay.id)
            if (day == 0) {
                map[statsPerUserPerDay.id] = statsPerUserPerDay.steps
            } else {
                map.computeIfPresent(statsPerUserPerDay.id) { _, v -> v + statsPerUserPerDay.steps }
            }
        }
        map.entries.removeIf { it.key !in usersPerDay }
    }
    if (map.isEmpty()) return Top(listOf(), 0)
    val max = map.maxOf { it.value }
    return Top(
        map.entries.filter { it.value == max }.map { it.key },
        max
    )
}

// https://leetcode.com/problems/merge-strings-alternately/
fun mergeAlternately(word1: String, word2: String): String {
    val w1 = word1.toCharArray()
    val w2 = word2.toCharArray()
    return buildString {
        for (i in 0..<maxOf(w1.size, w2.size)) {
            if (i < w1.size) append(w1[i])
            if (i < w2.size) append(w2[i])
        }
    }
}
