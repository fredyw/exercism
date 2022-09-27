import kotlin.math.ceil
import kotlin.math.sqrt

object CryptoSquare {
    fun ciphertext(plaintext: String): String {
        val normalizedText = plaintext.lowercase().filter { it.isLetterOrDigit() }
        val size = ceil(sqrt(normalizedText.length.toDouble())).toInt()
        return if (size == 0) {
            normalizedText
        } else {
            normalizedText.chunked(size) { it.padEnd(size, ' ') }.let { list ->
                (0 until size).map { col ->
                    list.indices.map { row -> list[row][col] }.joinToString("")
                }
            }.joinToString(" ")
        }
    }
}
