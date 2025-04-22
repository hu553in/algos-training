/**
 * Given an English uppercase string,
 * need to replace all 2+ equal letter sequences with letter + number.
 * No need to replace single letters.
 *
 * Examples:
 * - encode("AAAABBBC") => "A4B3C"
 * - encode("AAAABBBCAAA") => "A4B3CA3"
 */
fun encode(str: String): String {
    val sb = StringBuilder()
    var curr = 1
    for (i in 0..str.length) {
        if (i == 0) continue
        if (i <= str.lastIndex && str[i] == str[i - 1]) {
            curr++
        } else {
            sb.append(str[i - 1])
            if (curr > 1) {
                sb.append(curr)
            }
            curr = 1
        }
    }
    return sb.toString()
}
