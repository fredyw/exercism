object Isogram {
    fun isIsogram(input: String): Boolean {
        return input.lowercase()
            .filter { it.isLetter() }
            .groupBy { it }
            .all { it.value.size == 1 }
    }
}
