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

    @Test
    fun `test numIslands`() {
        assertEquals(
            1,
            numIslands(
                arrayOf(
                    charArrayOf('1', '1', '1', '1', '0'),
                    charArrayOf('1', '1', '0', '1', '0'),
                    charArrayOf('1', '1', '0', '0', '0'),
                    charArrayOf('0', '0', '0', '0', '0')
                )
            )
        )
        assertEquals(
            3,
            numIslands(
                arrayOf(
                    charArrayOf('1', '1', '0', '0', '0'),
                    charArrayOf('1', '1', '0', '0', '0'),
                    charArrayOf('0', '0', '1', '0', '0'),
                    charArrayOf('0', '0', '0', '1', '1')
                )
            )
        )
    }

    @Test
    fun `test combine`() {
        assertEquals(
            // [1, 2, 3, 4] ->
            // [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
            listOf(listOf(1, 2), listOf(1, 3), listOf(1, 4), listOf(2, 3), listOf(2, 4), listOf(3, 4)),
            combine(4, 2)
        )
        assertEquals(
            // [1, 2] ->
            // [[1, 2]]
            listOf(listOf(1, 2)),
            combine(2, 2)
        )
        assertEquals(
            // [1, 2, 3] ->
            // [[1, 2], [1, 3], [2, 3]]
            listOf(listOf(1, 2), listOf(1, 3), listOf(2, 3)),
            combine(3, 2)
        )
    }

    @Test
    fun `test calculateTop`() {
        assertEquals(
            Top(listOf(2), 2500),
            calculateTop(
                listOf(
                    listOf(UserPerDay(1, 1000), UserPerDay(2, 1500)),
                    listOf(UserPerDay(2, 1000)),
                )
            )
        )
        assertEquals(
            Top(listOf(1, 2), 5500),
            calculateTop(
                listOf(
                    listOf(UserPerDay(1, 2000), UserPerDay(2, 1500)),
                    listOf(UserPerDay(2, 4000), UserPerDay(1, 3500)),
                )
            )
        )
        assertEquals(
            Top(listOf(), 0),
            calculateTop(
                listOf(
                    listOf(UserPerDay(1, 1000), UserPerDay(2, 1500)),
                    listOf(UserPerDay(3, 1000)),
                )
            )
        )
    }
}
