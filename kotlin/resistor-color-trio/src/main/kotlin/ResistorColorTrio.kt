object ResistorColorTrio {
    fun text(vararg input: Color): String {
        val firstTwoDigits = input.take(2).map { COLOR_MAP[it] }.joinToString("")
        val thirdDigit =
            "0".repeat(COLOR_MAP[input[2]] ?: throw IllegalArgumentException())
        val n = "$firstTwoDigits$thirdDigit".toLong()
        return when {
            n % 1000000 == 0L -> "${n / 1000000} megaohms"
            n % 1000 == 0L -> "${n / 1000} kiloohms"
            else -> "$n ohms"
        }
    }

    private val COLOR_MAP = mapOf(
        Color.BLACK to 0,
        Color.BROWN to 1,
        Color.RED to 2,
        Color.ORANGE to 3,
        Color.YELLOW to 4,
        Color.GREEN to 5,
        Color.BLUE to 6,
        Color.VIOLET to 7,
        Color.GREY to 8,
        Color.WHITE to 9
    )
}

