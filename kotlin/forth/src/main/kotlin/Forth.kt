class Forth {
    fun evaluate(vararg line: String): List<Int> {
        val deque = ArrayDeque<Int>()
        val functions = mutableMapOf<String, List<String>>()
        for (l in line.map { it.lowercase() }) {
            if (l.startsWith(":")) {
                val split = l.split(" ")
                require(split[1].toIntOrNull() == null) { "illegal operation" }
                functions[split[1]] =
                    evaluateFunction(split.subList(2, split.lastIndex), functions)
            } else {
                evaluate(l, deque, functions)
            }
        }
        return deque.toList()
    }

    private fun evaluateFunction(
        tokens: List<String>,
        functions: Map<String, List<String>>
    ): List<String> {
        return tokens.flatMap {
            val body = functions[it]
            if (body != null) {
                evaluateFunction(body, functions)
            } else {
                listOf(it)
            }
        }
    }

    private fun evaluate(
        line: String,
        deque: ArrayDeque<Int>,
        functions: Map<String, List<String>>
    ) {
        val tokens = line.split(" ").flatMap { functions[it] ?: listOf(it) }
        for (token in tokens) {
            val num = token.toIntOrNull()
            if (num != null) {
                deque.addLast(num)
            } else {
                when (token.lowercase()) {
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

    private fun add(deque: ArrayDeque<Int>): ArrayDeque<Int> {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val b = deque.removeLast()
        val a = deque.removeLast()
        deque.addLast(a + b)
        return deque
    }

    private fun subtract(deque: ArrayDeque<Int>): ArrayDeque<Int> {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val b = deque.removeLast()
        val a = deque.removeLast()
        deque.addLast(a - b)
        return deque
    }

    private fun multiply(deque: ArrayDeque<Int>): ArrayDeque<Int> {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val b = deque.removeLast()
        val a = deque.removeLast()
        deque.addLast(a * b)
        return deque
    }

    private fun divide(deque: ArrayDeque<Int>): ArrayDeque<Int> {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val b = deque.removeLast()
        require(b != 0) { "divide by zero " }
        val a = deque.removeLast()
        deque.addLast(a / b)
        return deque
    }

    private fun dup(deque: ArrayDeque<Int>): ArrayDeque<Int> {
        require(!deque.isEmpty()) { "empty stack" }
        val a = deque.removeLast()
        deque.addLast(a)
        deque.addLast(a)
        return deque
    }

    private fun swap(deque: ArrayDeque<Int>): ArrayDeque<Int> {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val b = deque.removeLast()
        val a = deque.removeLast()
        deque.addLast(b)
        deque.addLast(a)
        return deque
    }

    private fun drop(deque: ArrayDeque<Int>): ArrayDeque<Int> {
        require(!deque.isEmpty()) { "empty stack" }
        deque.removeLast()
        return deque
    }

    private fun over(deque: ArrayDeque<Int>): ArrayDeque<Int> {
        require(!deque.isEmpty()) { "empty stack" }
        require(deque.size > 1) { "only one value on the stack" }
        val a = deque.removeLast()
        val b = deque.removeLast()
        deque.addLast(b)
        deque.addLast(a)
        deque.addLast(b)
        return deque
    }
}
