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
