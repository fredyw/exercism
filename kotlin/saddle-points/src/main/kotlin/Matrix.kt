data class MatrixCoordinate(val row: Int, val col: Int)
data class Matrix(val matrix: List<List<Int>>) {
    val saddlePoints: Set<MatrixCoordinate> get() {
        val colSize = if (matrix.isNotEmpty()) matrix[0].size else 0
        val maxRows = matrix.map { it.maxOrNull() ?: Int.MIN_VALUE }
        val minCols = (0 until colSize).map { colIndex -> matrix.map { it[colIndex] } }
            .map { it.minOrNull() ?: Int.MAX_VALUE }
        return matrix.flatMapIndexed { rowIndex, col ->
            col.mapIndexedNotNull { colIndex, num ->
                if (num == maxRows[rowIndex] && num == minCols[colIndex]) {
                    MatrixCoordinate(rowIndex + 1, colIndex + 1)
                } else {
                    null
                }
            }
        }.toSet()
    }
}
