package _10_regex_matching

import (
	"fmt"
)

// IsMatch https://leetcode.com/problems/regular-expression-matching/
func IsMatch(s string, p string) bool {
	rows, cols := len(s), len(p)
	m := make([][]bool, rows+1)
	for row := range m {
		m[row] = make([]bool, cols+1)
	}
	m[0][0] = true
	// 0-row prefill
	for col := 2; col <= cols; col++ {
		if p[col-1] == '*' {
			m[0][col] = m[0][col-2]
		}
	}
	for row := 1; row <= rows; row++ {
		for col := 1; col <= cols; col++ {
			if p[col-1] != '*' { // char or '.'
				m[row][col] = m[row-1][col-1] && (p[col-1] == '.' || p[col-1] == s[row-1])
			} else { // '*'
				// no copies of char before '*'
				m[row][col] = m[row][col-2]
				// 1+ copies
				if p[col-2] == '.' || p[col-2] == s[row-1] {
					m[row][col] = m[row][col] || m[row-1][col]
				}
			}
		}
	}
	render(s, p, m)
	return m[rows][cols]
}

func render(s string, p string, m [][]bool) {
	s = "#" + s
	p = "#" + p
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
