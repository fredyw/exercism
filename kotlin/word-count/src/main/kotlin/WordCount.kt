object WordCount {

    fun phrase(phrase: String): Map<String, Int> {
        return phrase.split("[^\\w']+".toRegex())
            .filter { it.isNotBlank() }
            .map { it.lowercase().trimStart('\'').trimEnd('\'') }
            .groupingBy { it }
            .eachCount()
    }
}
