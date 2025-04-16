import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RightSideViewTest {

    @Test
    fun `test rightSideView`() {
        assertEquals(
            listOf(1, 3, 4),
            rightSideView(TreeNode(1, TreeNode(2, right = TreeNode(5)), TreeNode(3, right = TreeNode(4))))
        )
        assertEquals(
            listOf(1, 3, 4, 5),
            rightSideView(TreeNode(1, TreeNode(2, TreeNode(4, TreeNode(5))), TreeNode(3)))
        )
        assertEquals(
            listOf(1, 3),
            rightSideView(TreeNode(1, right = TreeNode(3)))
        )
        assertEquals(listOf<Int>(), rightSideView(null))
    }
}
