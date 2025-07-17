package main

import (
	"testing"

	"gotest.tools/v3/assert"
)

var processor ProcessorImpl

func TestProcess(t *testing.T) {
	doc := &Document{
		FetchTime: 2222,
		Text:      "1",
		PubDate:   1,
		Url:       "https://google.com",
	}

	processed, err := processor.Process(doc)

	assert.NilError(t, err)
	assert.Assert(t, processed.FetchTime == doc.FetchTime)
	assert.Assert(t, processed.FirstFetchTime == doc.FetchTime)
	assert.Assert(t, processed.Text == doc.Text)
	assert.Assert(t, processed.PubDate == doc.PubDate)
	assert.Assert(t, processed.Url == doc.Url)

	docFetchTimeInPast := &Document{
		FetchTime: 1111,
		Text:      "1",
		PubDate:   2,
		Url:       "https://google.com",
	}

	processed, err = processor.Process(docFetchTimeInPast)

	assert.NilError(t, err)
	assert.Assert(t, processed.FetchTime == doc.FetchTime)
	assert.Assert(t, processed.FirstFetchTime == docFetchTimeInPast.FetchTime)
	assert.Assert(t, processed.Text == doc.Text)
	assert.Assert(t, processed.PubDate == docFetchTimeInPast.PubDate)
	assert.Assert(t, processed.Url == doc.Url)

	docFetchTimeInFuture := &Document{
		FetchTime: 5555,
		Text:      "2",
		PubDate:   3,
		Url:       "https://google.com",
	}

	processed, err = processor.Process(docFetchTimeInFuture)

	assert.NilError(t, err)
	assert.Assert(t, processed.FetchTime == docFetchTimeInFuture.FetchTime)
	assert.Assert(t, processed.FirstFetchTime == docFetchTimeInPast.FetchTime)
	assert.Assert(t, processed.Text == docFetchTimeInFuture.Text)
	assert.Assert(t, processed.PubDate == docFetchTimeInPast.PubDate)
	assert.Assert(t, processed.Url == doc.Url)
}
