import kotlin.math.pow

object Wordy {
    fun answer(input: String): Int {
        val initial =
            ("What is (-?\\d+)" +
                    "(?: " +
                    "plus -?\\d+" +
                    "| minus -?\\d+" +
                    "| multiplied by -?\\d+" +
                    "| divided by -?\\d+" +
                    "| raised to the \\d+(?:st|nd|rd|th) power" +
                    ")*\\?").toRegex().getNumber(input)
        val opRegex =
            ("plus -?\\d+" +
                    "|minus -?\\d+" +
                    "|multiplied by -?\\d+" +
                    "|divided by -?\\d+" +
                    "|raised to the \\d+(?:st|nd|rd|th) power"
                    ).toRegex()
        return generateSequence(opRegex.find(input)) { opRegex.find(input, it.range.last + 1) }
            .map { it.value }
            .fold(initial) { acc, s -> evaluate(acc, s) }
    }

    private fun evaluate(num: Int, s: String): Int {
        return when {
            s.startsWith("plus") ->
                num + "plus (-?\\d+)".toRegex().getNumber(s)

            s.startsWith("minus") ->
                num - "minus (-?\\d+)".toRegex().getNumber(s)

            s.startsWith("multiplied by") ->
                num * "multiplied by (-?\\d+)".toRegex().getNumber(s)

            s.startsWith("divided by") ->
                num / "divided by (-?\\d+)".toRegex().getNumber(s)

            else ->
                num.toDouble().pow(
                    "raised to the (\\d+)(?:st|nd|rd|th) power".toRegex()
                        .getNumber(s)
                ).toInt()
        }
    }

    private fun Regex.getNumber(s: String): Int =
        this.find(s)?.groupValues?.get(1)?.toInt() ?: throw Exception()
}
