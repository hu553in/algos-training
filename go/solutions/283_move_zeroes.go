package solutions

// https://leetcode.com/problems/move-zeroes/
func moveZeroes(nums []int)  {
    newIndex := 0
	for i := 0; i < len(nums); i++ {
        if nums[i] != 0 {
            nums[newIndex] = nums[i]
            newIndex = newIndex + 1
        }
	}
    for ; newIndex < len(nums); newIndex++ {
        nums[newIndex] = 0
    }
}
