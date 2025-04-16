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
