data class FlowerFieldBoard(val board: List<String>) {
    fun withNumbers(): List<String> {
        return board.mapIndexed { r, str ->
            str.mapIndexed { c, char ->
                val count = countFlowers(board, r, c)
                if (count > 0) {
                    count
                } else {
                    char
                }
            }.joinToString("")
        }
    }

    fun countFlowers(board: List<String>, row: Int, col: Int): Int {
        if (board[row][col] == '*') {
            return 0;
        }
        val numRows = board.size
        val numCols = board[0].length
        return (-1..1).flatMap { i -> (-1..1).map { j -> i to j } }
            .filter { (i, j) -> i != 0 || j != 0}
            .count { (i, j) ->
                val r = row + i
                val c = col + j
                r in 0 until numRows && c in 0 until numCols && board[r][c] == '*'
            }
        }
}
