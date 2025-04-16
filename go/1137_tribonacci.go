package solutions

// https://leetcode.com/problems/n-th-tribonacci-number
func Tribonacci(n int) int {
	arr := make([]int, n+1)
	arr[0] = 0
	if n > 0 {
		arr[1] = 1
	}
	if n > 1 {
		arr[2] = 1
	}
	for i := 3; i <= n; i++ {
		arr[i] = arr[i-3] + arr[i-2] + arr[i-1]
	}
	return arr[n]
}
