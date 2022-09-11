import kotlin.math.pow

object RomanNumerals {
    private val MAPPING = mapOf(
        1 to 'I',
        5 to 'V',
        10 to 'X',
        50 to 'L',
        100 to 'C',
        500 to 'D',
        1000 to 'M',
    )

    fun value(n: Int): String {
        val s = n.toString()
        return (s.length - 1 downTo 0).zip(s.asIterable()).joinToString("") {
            val m = 10.0.pow(it.first).toInt()
            when (it.second - '0') {
                1 -> "${MAPPING[1 * m]}"
                2 -> "${MAPPING[1 * m]}".repeat(2)
                3 -> "${MAPPING[1 * m]}".repeat(3)
                4 -> "${MAPPING[1 * m]}${MAPPING[5 * m]}"
                5 -> "${MAPPING[5 * m]}"
                6 -> "${MAPPING[5 * m]}${MAPPING[1 * m]}"
                7 -> "${MAPPING[5 * m]}${MAPPING[1 * m].toString().repeat(2)}"
                8 -> "${MAPPING[5 * m]}${MAPPING[1 * m].toString().repeat(3)}"
                9 -> "${MAPPING[1 * m]}${MAPPING[10 * m]}"
                else -> ""
            }
        }
    }
}
