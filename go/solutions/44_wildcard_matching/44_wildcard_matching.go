package _44_wildcard_matching

import (
	"fmt"
)

// IsMatch https://leetcode.com/problems/wildcard-matching/
func IsMatch(s string, p string) bool {
	s = "#" + s
	p = "#" + p
	rows := len(s)
	cols := len(p)
	m := make([][]bool, rows)
	for row := 0; row < rows; row++ {
		m[row] = make([]bool, cols)
	}
	m[0][0] = true
	for col := 1; col < cols; col++ {
		m[0][col] = p[col] == '*' && m[0][col-1]
	}
	for col := 1; col < cols; col++ {
		anyTrue := m[0][col]
		for row := 1; row < rows; row++ {
			curr := s[row] == p[col] || p[col] == '?' || p[col] == '*'
			prev := m[row-1][col-1] || (p[col] == '*' && (m[row-1][col] || m[row][col-1]))
			m[row][col] = curr && prev
			anyTrue = anyTrue || m[row][col]
		}
		if !anyTrue {
			return false
		}
	}
	render(s, p, m)
	return m[rows-1][cols-1]
}

func render(s string, p string, m [][]bool) {
	fmt.Print("  ")
	for i, code := range p {
		fmt.Print(string(code))
		if i < len(p)-1 {
			fmt.Print("     ")
		}
	}
	fmt.Println()
	for i, row := range m {
		fmt.Print(string(s[i]) + " ")
		for j, elem := range row {
			fmt.Print(elem)
			if j < len(row)-1 {
				if elem {
					fmt.Print(" ")
				}
				fmt.Print(" ")
			}
		}
		fmt.Println()
	}
	fmt.Println()
}
