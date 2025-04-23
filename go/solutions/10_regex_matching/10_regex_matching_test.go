package _10_regex_matching

import "testing"
import "gotest.tools/v3/assert"

func TestIsMatch(t *testing.T) {
	assert.Assert(t, !IsMatch("aa", "a"))
	assert.Assert(t, IsMatch("aa", "a*"))
	assert.Assert(t, IsMatch("ab", ".*"))
	assert.Assert(t, IsMatch("aab", "c*a*b"))
	assert.Assert(t, !IsMatch("mississippi", "mis*is*p*."))
	assert.Assert(t, IsMatch("aaa", "ab*ac*a"))
	assert.Assert(t, !IsMatch("aaa", "ab*a"))
	assert.Assert(t, !IsMatch("ab", ".*c"))
}
