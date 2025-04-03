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
