import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculateTopTest {

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
