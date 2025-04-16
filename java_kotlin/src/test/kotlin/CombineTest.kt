import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CombineTest {

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
}
