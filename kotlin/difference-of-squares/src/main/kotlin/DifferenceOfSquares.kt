import kotlin.math.pow

class Squares(private val n: Int) {
    fun sumOfSquares(): Int {
        return (1..n).sumOf { it * it }
    }

    fun squareOfSum(): Int {
        return (1..n).sum().let { it * it }
    }

    fun difference(): Int {
        return squareOfSum() - sumOfSquares()
    }
}
