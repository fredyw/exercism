object Acronym {
    fun generate(phrase: String) : String {
        return phrase.split("[\\s|\\-]+".toRegex())
            .map { it.filter { it.isLetter() } }
            .map { it[0].uppercaseChar() }
            .joinToString("")
    }
}
