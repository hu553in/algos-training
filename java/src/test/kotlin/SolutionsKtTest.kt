import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SolutionsKtTest {

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

    @Test
    fun `test isSymmetric`() {
        assertTrue(
            isSymmetric(
                TreeNode(
                    1,
                    TreeNode(2, TreeNode(3), TreeNode(4)),
                    TreeNode(2, TreeNode(4), TreeNode(3))
                )
            )
        )
        assertFalse(
            isSymmetric(
                TreeNode(
                    1,
                    TreeNode(2, right = TreeNode(3)),
                    TreeNode(2, right = TreeNode(3))
                )
            )
        )
    }
}
