package _392_is_subsequence

import "testing"

func TestIsSubsequence(t *testing.T) {
	if !IsSubsequence("abc", "ahbgdc") {
		t.Errorf("IsSubsequence(\"abc\", \"ahbgdc\") must be true")
	}
	if IsSubsequence("axc", "ahbgdc") {
		t.Errorf("IsSubsequence(\"axc\", \"ahbgdc\") must be false")
	}
}
