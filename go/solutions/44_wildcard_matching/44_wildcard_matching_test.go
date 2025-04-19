package _44_wildcard_matching

import "testing"
import "gotest.tools/v3/assert"

func TestIsMatch(t *testing.T) {
	assert.Assert(t, !IsMatch("aa", "a"))
	assert.Assert(t, IsMatch("aa", "*"))
	assert.Assert(t, !IsMatch("cb", "?a"))
	assert.Assert(t, IsMatch("adceb", "*a*b"))
	assert.Assert(t, !IsMatch("aab", "c*a*b"))
	assert.Assert(t, !IsMatch("mississippi", "m??*ss*?i*pi"))
	assert.Assert(t, IsMatch("abcabczzzde", "*abc???de*"))
}
