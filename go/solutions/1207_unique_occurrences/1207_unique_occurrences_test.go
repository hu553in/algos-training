package _1207_unique_occurrences

import (
	"gotest.tools/v3/assert"
	"testing"
)

func TestUniqueOccurrences(t *testing.T) {
	assert.Assert(t, UniqueOccurrences([]int{1, 2, 2, 1, 1, 3}))
	assert.Assert(t, !UniqueOccurrences([]int{1, 2}))
	assert.Assert(t, UniqueOccurrences([]int{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}))
}
