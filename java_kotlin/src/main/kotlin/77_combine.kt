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
