object Atbash {
    private val CIPHER = ('a'..'z').reversed().joinToString("")
    private val CIPHER_CHAR_TO_INDEX_MAP =
        CIPHER.withIndex().associate { it.value to it.index }

    fun encode(s: String): String {
        return s.lowercase().filter { it.isLetterOrDigit() }
            .map {
                if (it.isDigit()) {
                    it
                } else {
                    val index = it - 'a'
                    CIPHER[index]
                }
            }
            .joinToString("").chunked(5).joinToString(" ")
    }

    fun decode(s: String): String {
        return s.lowercase().filter { it.isLetterOrDigit() }
            .map {
                val index = CIPHER_CHAR_TO_INDEX_MAP[it]
                if (index == null) {
                    it
                } else {
                    ('a' + index).toChar()
                }
            }
            .joinToString("")
    }
}
