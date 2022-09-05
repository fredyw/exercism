import kotlin.math.max

object Transpose {
    fun transpose(input: List<String>): List<String> {
        val paddedInput = buildList {
            if (input.size - 1 >= 0) {
                var maxLength = input[input.size - 1].length
                (input.size - 2 downTo 0).map {
                    val paddedString = if (input[it].length < maxLength) {
                        "${input[it]}${" ".repeat(maxLength - input[it].length)}"
                    } else {
                        input[it]
                    }
                    maxLength = max(maxLength, input[it].length)
                    paddedString
                }.reversed().forEach { add(it) }
                add(input[input.size - 1])
            }
        }
        val maxRows = input.size
        val maxCols = input.maxOfOrNull { s -> s.length } ?: 0
        return (0 until maxCols).map { col ->
            (0 until maxRows).map { row ->
                if (col >= paddedInput[row].length) {
                    ""
                } else {
                    paddedInput[row][col]
                }
            }.joinToString("")
        }
    }
}

