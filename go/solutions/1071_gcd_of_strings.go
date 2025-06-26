package solutions

// GcdOfStrings https://leetcode.com/problems/greatest-common-divisor-of-strings/
func GcdOfStrings(str1 string, str2 string) string {
	l1 := len(str1)
	l2 := len(str2)

	gcd := gcd(l1, l2)
	var builder strings.Builder

	for i := 0; i < gcd; i++ {
		if str1[i] != str2[i] {
			return ""
		}
		builder.WriteByte(str1[i])
	}

	candidate := builder.String()
	for i := gcd; i < max(l1, l2); i++ {
		if i < l1 {
			if str1[i%gcd] != str1[i] {
				return ""
			}
		}
		if i < l2 {
			if str2[i%gcd] != str2[i] {
				return ""
			}
		}
	}

	return candidate
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}
