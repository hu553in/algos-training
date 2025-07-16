package merge_channels

import (
	"fmt"
	"testing"

	"gotest.tools/v3/assert"
)

func TestMergeChannels(t *testing.T) {
	chs := []chan string{
		make(chan string),
		make(chan string),
		make(chan string),
		make(chan string),
		make(chan string),
	}

	for i := range 5 {
		go func() {
			for j := range 10 {
				chs[i] <- fmt.Sprintf("element %d from channel %d", j+1, i+1)
			}
			close(chs[i])
		}()
	}

	resultCh := MergeChannels(chs...)
	count := 0

	for range resultCh {
		count++
	}

	assert.Assert(t, count == 5*10)
}
