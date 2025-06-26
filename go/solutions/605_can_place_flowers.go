package solutions

// CanPlaceFlowers https://leetcode.com/problems/can-place-flowers/
func CanPlaceFlowers(flowerbed []int, n int) bool {
	if n == 0 {
		return true
	}

	lastIndex := len(flowerbed)-1
	zeros := 0

	firstZero := false
	if flowerbed[0] == 0 {
		firstZero = true
	}

	for _, ith := range flowerbed {
		if ith == 1 {
			if firstZero {
				zeros += (1 - zeros%2)
				firstZero = false
			}
			if zeros == 3 || zeros >= 5 {
				n -= zeros/2
			}
			zeros = 0
		} else {
			zeros++
		}
		
		if n <= 0 {
			return true
		}
	}
	
	if firstZero {
		zeros++
	}
	if flowerbed[lastIndex] == 0 && zeros >= 2 {
		n -= zeros/2
	}
	
	return n <= 0
}
