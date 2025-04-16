class TreeNode(var `val`: Int) {
    constructor(`val`: Int, left: TreeNode? = null, right: TreeNode? = null) : this(`val`) {
        this.left = left
        this.right = right
    }

    var left: TreeNode? = null
    var right: TreeNode? = null
}
