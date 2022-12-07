object MatchingBrackets {
    private val OPENING_BRACKETS = setOf('(', '{', '[')
    private val CLOSING_BRACKETS = setOf(')', '}', ']')
    private val BRACKETS = OPENING_BRACKETS + CLOSING_BRACKETS

    fun isValid(input: String): Boolean {
        val deque = ArrayDeque<Char>()
        return input.filter { it in BRACKETS }.all {
            if (it in OPENING_BRACKETS) {
                deque.addFirst(it)
                return true
            } else {
                return when (deque.removeLastOrNull()) {
                    '(' -> it == ')'
                    '{' -> it == '}'
                    '[' -> it == ']'
                    else -> false
                }
            }
        }
    }
}
