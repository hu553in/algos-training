// https://leetcode.com/problems/minimum-size-subarray-sum/
fun minSubArrayLenUsingSlidingWindow(target: Int, nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    var left = 0
    var min = 0
    var sum = 0

    for (right in nums.indices) {
        sum += nums[right]

        while (sum >= target) {
            val candidate = right - left + 1
            if (min == 0 || candidate < min) {
                min = candidate
            }
            sum -= nums[left]
            left++
        }
    }

    return min
}
