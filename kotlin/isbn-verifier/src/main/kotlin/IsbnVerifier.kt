class IsbnVerifier {
    fun isValid(number: String): Boolean {
        val num = number.replace("-", "")
        if (num.length != 10) {
            return false
        }
        if (!num.take(9).all { it.isDigit() }) {
            return false
        }
        if (!num[9].isDigit() && num[9] != 'X') {
            return false
        }
        return (10 downTo 1).zip(num.asIterable())
            .sumOf { (i, c) ->
                i * if (c == 'X') 10 else c.digitToInt()
            } % 11 == 0
    }
}
