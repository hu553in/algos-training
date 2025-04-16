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
