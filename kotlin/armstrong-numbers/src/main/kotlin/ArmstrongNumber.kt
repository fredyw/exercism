import kotlin.math.pow

object ArmstrongNumber {
    fun check(input: Int): Boolean {
        return input.toString().run {
            input == sumOf { it.digitToInt().toDouble().pow(length) }.toInt()
        }
    }
}
