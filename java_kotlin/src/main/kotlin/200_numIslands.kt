// https://leetcode.com/problems/number-of-islands/
fun numIslands(grid: Array<CharArray>): Int {
    fun handleIsland(i: Int, j: Int, grid: Array<CharArray>) {
        if (grid[i][j] == '1') {
            grid[i][j] = '0'
            if (i < grid.size - 1) {
                handleIsland(i + 1, j, grid)
            }
            if (j < grid[i].size - 1) {
                handleIsland(i, j + 1, grid)
            }
            if (i > 0) {
                handleIsland(i - 1, j, grid)
            }
            if (j > 0) {
                handleIsland(i, j - 1, grid)
            }
        }
    }

    var count = 0
    for (i in 0..grid.lastIndex) {
        for (j in 0..grid[i].lastIndex) {
            if (grid[i][j] == '1') {
                count++
                handleIsland(i, j, grid)
            }
        }
    }
    return count
}
