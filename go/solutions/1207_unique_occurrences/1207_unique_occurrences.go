package _1207_unique_occurrences

// UniqueOccurrences https://leetcode.com/problems/unique-number-of-occurrences/
func UniqueOccurrences(arr []int) bool {
	countMap := make(map[int]int)
	for _, el := range arr {
		count := countMap[el]
		count++
		countMap[el] = count
	}
	countSet := make(map[int]bool)
	for _, v := range countMap {
		sameCount := countSet[v]
		if sameCount {
			return false
		}
		countSet[v] = true
	}
	return true
}
