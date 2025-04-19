package _392_is_subsequence

import (
	"gotest.tools/v3/assert"
	"testing"
)

func TestIsSubsequence(t *testing.T) {
	assert.Assert(t, IsSubsequence("abc", "ahbgdc"))
	assert.Assert(t, !IsSubsequence("axc", "ahbgdc"))
}
