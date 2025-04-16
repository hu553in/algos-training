import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class IsSymmetricTest {

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
