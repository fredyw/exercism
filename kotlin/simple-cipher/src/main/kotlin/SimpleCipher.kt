import kotlin.random.Random

data class Cipher(val key: String = generateRandom()) {
    init {
        require(key.isNotEmpty())
        require(key.none { it.isDigit() || it.isUpperCase() })
    }

    private val shifts = key.map { it - 'a' }

    fun encode(s: String): String {
        return s.toCharArray().mapIndexed { index, char ->
            val shift = shifts[index % shifts.size]
            val i = (char - 'a' + shift) % ALPHABET.length
            ALPHABET[i]
        }.joinToString("")
    }

    fun decode(s: String): String {
        return s.toCharArray().mapIndexed { index, char ->
            val shift = shifts[index % shifts.size]
            val i =
                ((char - 'a' - shift) % ALPHABET.length).let { if (it < 0) it + ALPHABET.length else it }
            ALPHABET[i]
        }.joinToString("")
    }

    companion object {
        private val ALPHABET = ('a'..'z').joinToString("")

        private fun generateRandom(): String {
            val index = Random.nextInt(ALPHABET.length + 1)
            return (0 until 100).map { ALPHABET[index] }.joinToString("")
        }
    }
}

