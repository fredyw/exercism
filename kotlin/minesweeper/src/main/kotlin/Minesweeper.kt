data class MinesweeperBoard(val board: List<String>) {
    fun withNumbers(): List<String> {
        val maxRows = board.size
        val maxCols = if (maxRows > 0) board[0].length else 0
        return (0 until maxRows).map { row ->
            (0 until maxCols).fold("") { acc, col ->
                if (board[row][col] == '*') {
                    "$acc*"
                } else {
                    val count = countMines(maxRows, maxCols, row, col)
                    "$acc${if (count == 0) ' ' else count}"
                }
            }
        }.toList()
    }

    private fun countMines(maxRows: Int, maxCols: Int, row: Int, col: Int): Int {
        return listOf(
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, 1),
            Pair(1, 1),
            Pair(1, 0),
            Pair(1, -1),
            Pair(0, -1),
            Pair(-1, -1)
        ).filter { (r, c) -> row + r in 0 until maxRows && col + c in 0 until maxCols }
            .fold(0) { acc, (r, c) ->
                if (board[row + r][col + c] == '*') acc + 1 else acc
            }
    }
}
