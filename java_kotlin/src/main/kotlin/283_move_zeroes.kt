// https://leetcode.com/problems/move-zeroes/
fun moveZeroes(nums: IntArray): Unit {
    val size = nums.size
    if (size < 2) return
    var index = 0
    var nonZeroIndex = nums.indexOfFirst { it != 0 }
    if (nonZeroIndex == -1) return
    while (nonZeroIndex != size) {
        val temp = nums[index]
        nums[index++] = nums[nonZeroIndex]
        nums[nonZeroIndex] = temp
        if (index == size) return
        while (++nonZeroIndex != size && nums[nonZeroIndex] == 0) {}
    }
}
