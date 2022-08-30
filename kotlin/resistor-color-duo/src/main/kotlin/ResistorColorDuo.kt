object ResistorColorDuo {
    fun value(vararg colors: Color): Int {
        return colors.take(2)
            .map { COLOR_MAP[it] ?: throw IllegalArgumentException() }
            .joinToString("")
            .toInt()
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
        Color.WHITE to 9,
    )
}
