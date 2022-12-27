import kotlin.math.pow

class BaseConverter(private val base: Int, private val digits: IntArray) {
    init {
        require(base >= 2) { "Bases must be at least 2." }
        require(digits.isNotEmpty()) { "You must supply at least one digit." }
        if (digits.size > 1) {
            require(digits.first() != 0) { "Digits may not contain leading zeros." }
        }
        require(digits.all { it >= 0 }) { "Digits may not be negative." }
        require(digits.all { it < base }) { "All digits must be strictly less than the base." }
    }

    private val digits10: Int = digits.fold(0) { acc, v -> acc * base + v }

    fun convertToBase(newBase: Int): IntArray {
        require(newBase >= 2) { "Bases must be at least 2." }
        if (digits10 == 0) {
            return intArrayOf(0)
        }
        fun convert(num: Int): IntArray {
            if (num == 0) {
                return intArrayOf()
            }
            return convert(num / newBase) + (num % newBase)
        }
        return convert(digits10)
    }
}
