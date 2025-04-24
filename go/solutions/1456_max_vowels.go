package solutions

// MaxVowels https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
func MaxVowels(s string, k int) int {
	l := 0
	r := k - 1
	var maxC int
	for i := l; i <= r; i++ {
		if isVowel(s[i]) {
			maxC++
		}
	}
	c := maxC
	for i := 1; i <= len(s)-k; i++ {
		l++
		r++
		if isVowel(s[l-1]) {
			c--
		}
		if isVowel(s[r]) {
			c++
		}
		if c > maxC {
			maxC = c
		}
	}
	return maxC
}

func isVowel(c byte) bool {
	return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
}
