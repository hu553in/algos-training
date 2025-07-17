package main

import (
	"fmt"
	"sync"
)

var history map[string]Metadata
var mutexes map[string]*sync.Mutex
var creationMutex sync.Mutex

type Processor interface {
	Process(doc *Document) (*Document, error)
}

type ProcessorImpl struct {
}

type Metadata struct {
	FetchTime uint64
	Text      string

	FirstFetchTime uint64
	PubDate        uint64
}

type Document struct {
	Url string

	FetchTime uint64
	Text      string

	FirstFetchTime uint64
	PubDate        uint64
}

func init() {
	history = make(map[string]Metadata)
	mutexes = make(map[string]*sync.Mutex)
}

func (p *ProcessorImpl) Process(doc *Document) (*Document, error) {
	if doc == nil {
		return nil, fmt.Errorf("doc is nil")
	}

	mutex := getOrCreateMutexAndLock(doc.Url)
	defer mutex.Unlock()

	updated := Metadata{}

	if oldDoc, ok := history[doc.Url]; !ok {
		updated.FetchTime = doc.FetchTime
		updated.Text = doc.Text

		updated.FirstFetchTime = doc.FetchTime
		updated.PubDate = doc.PubDate
	} else {
		if doc.FetchTime == oldDoc.FetchTime {
			return nil, fmt.Errorf("doc is duplicate")
		}

		if doc.FetchTime > oldDoc.FetchTime {
			updated.FetchTime = doc.FetchTime
			updated.FirstFetchTime = oldDoc.FirstFetchTime

			updated.Text = doc.Text
			updated.PubDate = oldDoc.PubDate
		} else {
			updated.FetchTime = oldDoc.FetchTime
			updated.FirstFetchTime = doc.FetchTime

			updated.Text = oldDoc.Text
			updated.PubDate = doc.PubDate
		}
	}

	history[doc.Url] = updated

	return &Document{
		Url: doc.Url,

		FetchTime: updated.FetchTime,
		Text:      updated.Text,

		FirstFetchTime: updated.FirstFetchTime,
		PubDate:        updated.PubDate,
	}, nil
}

func getOrCreateMutexAndLock(url string) *sync.Mutex {
	if mutex, exists := mutexes[url]; exists {
		mutex.Lock()
		return mutex
	} else {
		creationMutex.Lock()
		defer creationMutex.Unlock()

		// check again if mutex was created
		// between existence check and creation lock
		if mutex, exists := mutexes[url]; exists {
			mutex.Lock()
			return mutex
		}

		mutex := sync.Mutex{}
		mutexes[url] = &mutex
		mutex.Lock()

		return &mutex
	}
}
