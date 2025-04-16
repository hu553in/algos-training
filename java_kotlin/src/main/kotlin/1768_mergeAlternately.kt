// https://leetcode.com/problems/merge-strings-alternately/
fun mergeAlternately(word1: String, word2: String): String {
    val w1 = word1.toCharArray()
    val w2 = word2.toCharArray()
    return buildString {
        for (i in 0..<maxOf(w1.size, w2.size)) {
            if (i < w1.size) append(w1[i])
            if (i < w2.size) append(w2[i])
        }
    }
}
