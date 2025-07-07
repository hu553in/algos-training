package solutions

// Write a method that replaces all spaces in a string with "%20" in-place.
// The input is a string with a memory reserved for the result.
//
// Example:
//
// Input: ['g','o',' ','t','a','s','k', 0, 0]
// Output: ['g','o','%','2','0','t','a','s','k']
//
// Constraints:
//
// - O(1) memory
// - O(N) time
// - the original string can be modified
func UrlEncodeSpaces(str []byte) []byte {
	shift := 0
	for _, el := range str {
		if el == ' ' {
			shift += 2
		}
	}
	if shift == 0 {
		return str
	}
	for i := len(str) - 1; shift > 0 && i >= 0; i-- {
		if str[i-shift] == ' ' {
			str[i] = '0'
			i--
			str[i] = '2'
			i--
			str[i] = '%'
			shift -= 2
		} else {
			str[i] = str[i-shift]
		}
	}
	return str
}
