class RailFenceCipher(private val n: Int) {
    fun getEncryptedData(input: String): String {
        val matrix = Array(n) { Array(input.length) { '.' } }
        val zigZag = zigZag(n, input.length)
        input.toList().zip(zigZag).forEach { (char, rowCol) ->
            val (row, col) = rowCol
            matrix[row][col] = char
        }
        return matrix.flatMap { list -> list.filter { it != '.' } }.joinToString("")
    }

    fun getDecryptedData(input: String): String {
        val matrix = Array(n) { Array(input.length) { '.' } }
        val zigZag = zigZag(n, input.length)
        input.toList().zip(zigZag.groupBy { (row, _) -> row }.entries.sortedBy { it.key }
            .flatMap { it.value })
            .forEach { (char, rowCol) ->
                val (row, col) = rowCol
                matrix[row][col] = char
            }
        return zigZag.map { (row, col) -> matrix[row][col] }.joinToString("")
    }

    data class ZigZag(val row: Int, val col: Int, val down: Boolean)

    private fun zigZag(maxRows: Int, maxCols: Int): List<Pair<Int, Int>> {
        return generateSequence(ZigZag(0, 0, true)) {
            if (it.down) {
                if (it.row < maxRows - 1) {
                    ZigZag(it.row + 1, it.col + 1, true)
                } else {
                    ZigZag(it.row - 1, it.col + 1, false)
                }
            } else {
                if (it.row - 1 >= 0) {
                    ZigZag(it.row - 1, it.col + 1, false)
                } else {
                    ZigZag(it.row + 1, it.col + 1, true)
                }
            }
        }.takeWhile { it.col < maxCols }
            .map { Pair(it.row, it.col) }
            .toList()
    }
}

