// KidsWithCandies https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
func KidsWithCandies(candies []int, extraCandies int) []bool {
	max := 0
	for _, ith := range candies {
		if ith > max {
			max = ith
		}
	}

	delta := max - extraCandies
	result := make([]bool, len(candies))
	for i, ith := range candies {
		if ith >= delta {
			result[i] = true
		}
	}
	return result
}
