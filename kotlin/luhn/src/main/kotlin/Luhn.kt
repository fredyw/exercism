object Luhn {
    fun isValid(candidate: String): Boolean {
        val s = candidate.replace("\\s+".toRegex(), "")
        if (s.length <= 1) {
            return false
        }
        if (!s.all { it.isDigit() }) {
            return false
        }
        return (s.indices).sumOf { i ->
            val index = s.lastIndex - i
            s[index].digitToInt().let { digit ->
                if (i % 2 == 1) {
                    (digit * 2).let { doubled ->
                        if (doubled > 9) {
                            doubled - 9
                        } else {
                            doubled
                        }
                    }
                } else {
                    digit
                }
            }
        } % 10 == 0
    }
}
