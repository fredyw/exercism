object Pangram {
    fun isPangram(input: String): Boolean {
        return input.lowercase().filter { it.isLetter() }.toSet().size == 26
    }
}
