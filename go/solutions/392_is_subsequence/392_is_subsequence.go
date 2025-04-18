package _392_is_subsequence

// https://leetcode.com/problems/is-subsequence/
func IsSubsequence(s string, t string) bool {
	if s == t {
		return true
	}
	sp := 0
	tp := 0
	for {
		if sp == len(s) {
			return true
		} else if tp == len(t) {
			return false
		}
		if s[sp] == t[tp] {
			sp++
		}
		tp++
	}
}
