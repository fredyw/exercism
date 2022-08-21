class RotationalCipher(private val n: Int) {
    fun encode(text: String): String {
        return text.toCharArray().map {
            when {
                it.isLowerCase() -> 'a' + (it - 'a' + n) % 26
                it.isUpperCase() -> 'A' + (it - 'A' + n) % 26
                else -> it
            }
        }.joinToString("")
    }
}
