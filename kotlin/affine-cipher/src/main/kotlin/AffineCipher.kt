import java.lang.Math.floorMod
import java.math.BigInteger

object AffineCipher {
    private const val M = 26

    fun encode(input: String, a: Int, b: Int): String {
        require(isCoprime(a, M)) { "a and m must be coprime."}
        return input.lowercase().filter { it.isLetterOrDigit() }.map {
            if (it.isDigit()) {
                it
            } else {
                val x = it - 'a'
                val enc = (a * x + b) % M
                ('a' + enc).toChar()
            }
        }.joinToString("").chunked(5).joinToString(" ")
    }

    fun decode(input: String, a: Int, b: Int): String {
        require(isCoprime(a, M)) { "a and m must be coprime."}
        return input.lowercase().filter { it.isLetterOrDigit() }.map {
            if (it.isDigit()) {
                it
            } else {
                val dec = floorMod(mmi(a) * ((it - 'a') - b), M)
                ('a' + dec).toChar()
            }
        }.joinToString("")
    }

    private fun isCoprime(x: Int, y: Int): Boolean {
        return BigInteger.valueOf(x.toLong()).gcd(BigInteger.valueOf(y.toLong())) == BigInteger.ONE
    }

    private fun mmi(a: Int): Int {
        return BigInteger.valueOf(a.toLong()).modInverse(BigInteger.valueOf(M.toLong())).toInt()
    }
}
