object SpiralMatrix {
    private class Position(
        val size: Int,
        val array: Array<IntArray> = Array(size) { IntArray(size) },
        var begin: Int = 0,
        var end: Int = size - 1,
        var row: Int = 0,
        var col: Int = 0,
        var number: Int = 1,
    ) {
        fun right() {
            (col..end).forEach { array[row][it] = number++ }
            col = end
            number--
        }

        fun down() {
            (row..end).forEach { array[it][col] = number++ }
            row = end
            number--
        }

        fun left() {
            (col downTo begin).forEach { array[row][it] = number++ }
            col = begin
            number--
        }

        fun up() {
            (row downTo begin + 1).forEach { array[it][col] = number++ }
            row = begin + 1
            number--
        }
    }

    fun ofSize(size: Int): Array<IntArray> {
        val position = Position(size)
        while (position.number <= size * size) {
            position.right()
            position.down()
            position.left()
            position.up()
            position.begin++
            position.end--
        }
        return position.array
    }
}
