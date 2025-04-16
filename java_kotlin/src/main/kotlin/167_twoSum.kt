// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
fun twoSum(numbers: IntArray, target: Int): IntArray {
    var i1 = 0
    var i2 = numbers.size - 1
    while (numbers[i1] + numbers[i2] != target) {
        if (numbers[i1] + numbers[i2] > target) {
            i2--
        } else i1++
    }
    return intArrayOf(i1 + 1, i2 + 1)
}
