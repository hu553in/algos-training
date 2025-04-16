// https://leetcode.com/problems/minimum-size-subarray-sum/
fun minSubArrayLenUsingPrefixSums(target: Int, nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    val ps = Array(nums.size + 1) { 0 }
    for (i in 1..nums.size) {
        ps[i] = ps[i - 1] + nums[i - 1]
    }

    var min = 0

    for (i in ps.indices) {
        for (j in i + 1..<ps.size) {
            if (ps[j] - ps[i] >= target) {
                val candidate = j - i
                if (min == 0 || candidate < min) {
                    min = candidate
                }
            }
        }
    }

    return min
}
