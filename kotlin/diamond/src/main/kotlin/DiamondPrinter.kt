class DiamondPrinter {
    fun printToList(char: Char): List<String> {
        val n = char - 'A'
        return listOf((0..n), (n - 1 downTo 0)).flatMap { list ->
            list.map { printLine(n - it, (it + 'A'.code).toChar()) }
        }
    }

    private fun printLine(n: Int, char: Char): String {
        return if (char == 'A') {
            "${" ".repeat(n)}$char${" ".repeat(n)}"
        } else {
            val gap = (char.code - 'A'.code) + (char.code - 'A'.code - 1)
            "${" ".repeat(n)}$char${" ".repeat(gap)}$char${" ".repeat(n)}"
        }
    }
}

