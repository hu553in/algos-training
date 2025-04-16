data class UserPerDay(val id: Int, val steps: Int)

data class Top(val userIds: List<Int>, val steps: Int)

/**
 * Мы в нашей компании любим проводить соревнования,
 * — недавно мы устроили чемпионат по шагам. И вот настало время подводить итоги!
 * Необходимо определить userIds участников, которые прошли наибольшее количество шагов steps за все дни,
 * не пропустив ни одного дня соревнований.
 */
fun calculateTop(statsPerDay: List<List<UserPerDay>>): Top {
    val map = mutableMapOf<Int, Int>()
    statsPerDay.withIndex().forEach { (day, statsPerDay) ->
        val usersPerDay = mutableSetOf<Int>()
        statsPerDay.forEach { statsPerUserPerDay ->
            usersPerDay.add(statsPerUserPerDay.id)
            if (day == 0) {
                map[statsPerUserPerDay.id] = statsPerUserPerDay.steps
            } else {
                map.computeIfPresent(statsPerUserPerDay.id) { _, v -> v + statsPerUserPerDay.steps }
            }
        }
        map.entries.removeIf { it.key !in usersPerDay }
    }
    if (map.isEmpty()) return Top(listOf(), 0)
    val max = map.maxOf { it.value }
    return Top(
        map.entries.filter { it.value == max }.map { it.key },
        max
    )
}
