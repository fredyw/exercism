object ScrabbleScore {

    fun scoreLetter(c: Char): Int {
        return when (c.lowercaseChar()) {
            'a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't' -> 1
            'd', 'g' -> 2
            'b', 'c', 'm', 'p' -> 3
            'f', 'h', 'v', 'w', 'y' -> 4
            'k' -> 5
            'j', 'x' -> 8
            'q', 'z' -> 10
            else -> 0
        }
    }

    fun scoreWord(word: String): Int {
        return word.sumOf { scoreLetter(it) }
    }
}
