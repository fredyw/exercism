class Anagram(private val source: String) {
    private val sourceMap = source.lowercase().groupingBy { it }.eachCount()

    fun match(anagrams: Collection<String>): Set<String> {
        return anagrams
            .filter { it.lowercase() != source.lowercase() }
            .filter {
                it.lowercase().groupingBy { it }.eachCount() == sourceMap
            }.toSet()
    }
}
