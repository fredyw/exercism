class NumberSpeller {
    fun say(input: Long): String {
        require(input in 0..999999999999)
        val string = DIGIT_TO_STRING[input]
        if (string != null) {
            return string
        }
        val (n, s) = POWER_OF_TEN_TO_STRING.entries.first { (n, s) -> n <= input }
        if (n == 10L) {
            return "${DIGIT_TO_STRING[(input / 10) * 10]}-${say(input % n)}"
        }
        val remainder = input % n
        return if (remainder == 0L) {
            "${say(input / n)} $s"
        } else {
            "${say(input / n)} $s ${say(remainder)}"
        }
    }

    companion object {
        private val DIGIT_TO_STRING: Map<Long, String> = mapOf(
            0L to "zero",
            1L to "one",
            2L to "two",
            3L to "three",
            4L to "four",
            5L to "five",
            6L to "six",
            7L to "seven",
            8L to "eight",
            9L to "nine",
            10L to "ten",
            11L to "eleven",
            12L to "twelve",
            13L to "thirteen",
            14L to "fourteen",
            15L to "fifteen",
            16L to "sixteen",
            17L to "seventeen",
            18L to "eighteen",
            19L to "nineteen",
            20L to "twenty",
            30L to "thirty",
            40L to "forty",
            50L to "fifty",
            60L to "sixty",
            70L to "seventy",
            80L to "eighty",
            90L to "ninety",
        )

        private val POWER_OF_TEN_TO_STRING: Map<Long, String> = mapOf(
            1_000_000_000L to "billion",
            1_000_000L to "million",
            1_000L to "thousand",
            100L to "hundred",
            10L to "", // special case
        )
    }
}
