class Forth {
    fun evaluate(vararg line: String): List<Int> {
        val deque = ArrayDeque<Int>()
        val userDefinedWords = mutableMapOf<String, List<String>>()
        line.map { it.lowercase() }.forEach {
            if (it.startsWith(":")) {
                val tokens = it.split(" ")
                val name = tokens[1]
                require(name.toIntOrNull() == null) { "illegal operation" }
                userDefinedWords[name] =
                    flatten(tokens.subList(2, tokens.lastIndex), userDefinedWords)
            } else {
                evaluate(
                    it.split(" ")
                        .flatMap { token -> userDefinedWords[token] ?: listOf(token) },
                    deque
                )
            }
        }
        return deque.toList()
    }

    private fun flatten(
        tokens: List<String>,
        userDefinedWords: Map<String, List<String>>
    ): List<String> {
        return tokens.flatMap {
            val body = userDefinedWords[it]
            if (body != null) {
                flatten(body, userDefinedWords)
            } else {
                listOf(it)
            }
        }
    }

    private fun evaluate(tokens: List<String>, deque: ArrayDeque<Int>) {
        tokens.forEach {
            val num = it.toIntOrNull()
            if (num != null) {
                deque.addLast(num)
            } else {
                when (it.lowercase()) {
                    "+" -> add(deque)
                    "-" -> subtract(deque)
                    "*" -> multiply(deque)
                    "/" -> divide(deque)
                    "dup" -> dup(deque)
                    "drop" -> drop(deque)
                    "swap" -> swap(deque)
                    "over" -> over(deque)
                    else -> {
                        throw IllegalArgumentException("undefined operation")
                    }
                }
            }
        }

    }

    private fun add(deque: ArrayDeque<Int>) {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val b = deque.removeLast()
        val a = deque.removeLast()
        deque.addLast(a + b)
    }

    private fun subtract(deque: ArrayDeque<Int>) {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val b = deque.removeLast()
        val a = deque.removeLast()
        deque.addLast(a - b)
    }

    private fun multiply(deque: ArrayDeque<Int>) {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val b = deque.removeLast()
        val a = deque.removeLast()
        deque.addLast(a * b)
    }

    private fun divide(deque: ArrayDeque<Int>) {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val b = deque.removeLast()
        require(b != 0) { "divide by zero " }
        val a = deque.removeLast()
        deque.addLast(a / b)
    }

    private fun dup(deque: ArrayDeque<Int>) {
        require(!deque.isEmpty()) { "empty stack" }
        val a = deque.removeLast()
        deque.addLast(a)
        deque.addLast(a)
    }

    private fun swap(deque: ArrayDeque<Int>) {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val b = deque.removeLast()
        val a = deque.removeLast()
        deque.addLast(b)
        deque.addLast(a)
    }

    private fun drop(deque: ArrayDeque<Int>) {
        require(!deque.isEmpty()) { "empty stack" }
        deque.removeLast()
    }

    private fun over(deque: ArrayDeque<Int>) {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val a = deque.removeLast()
        val b = deque.removeLast()
        deque.addLast(b)
        deque.addLast(a)
        deque.addLast(b)
    }
}
