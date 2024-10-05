package solutions

// https://leetcode.com/problems/median-of-two-sorted-arrays

func FindMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	nums := make([]int, len(nums1)+len(nums2))
	i := 0
	j := 0
	for current := 0; current < len(nums); current++ {
		if i == len(nums1) {
			nums[current] = nums2[j]
			j++
			continue
		}
		if j == len(nums2) {
			nums[current] = nums1[i]
			i++
			continue
		}
		if nums1[i] <= nums2[j] {
			nums[current] = nums1[i]
			i++
		} else {
			nums[current] = nums2[j]
			j++
		}
	}
	if len(nums)%2 == 0 {
		return float64(nums[len(nums)/2]+nums[len(nums)/2-1]) / float64(2)
	}
	return float64(nums[len(nums)/2])
}
