package merge_channels

import (
	"runtime"
	"sync"
)

var maxConcurrency = runtime.NumCPU() * 10

func MergeChannels(chs ...chan string) chan string {
	resultCh := make(chan string)
	sem := make(chan int, min(len(chs), maxConcurrency))

	var wg sync.WaitGroup
	wg.Add(len(chs))

	go func() {
		for _, ch := range chs {
			sem <- 1
			go func(ch chan string) {
				defer wg.Done()
				for s := range ch {
					resultCh <- s
				}
				<-sem
			}(ch)
		}

		close(sem)

		wg.Wait()
		close(resultCh)
	}()

	return resultCh
}
