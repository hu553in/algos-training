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
    return root == null || isSymmetric(root.left, root.right)
}

private fun isSymmetric(left: TreeNode?, right: TreeNode?): Boolean {
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

// https://leetcode.com/problems/number-of-islands/
fun numIslands(grid: Array<CharArray>): Int {
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
