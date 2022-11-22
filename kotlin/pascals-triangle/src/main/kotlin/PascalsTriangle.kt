object PascalsTriangle {
    fun computeTriangle(rows: Int): List<List<Int>> {
        return if (rows == 0) {
            listOf()
        } else {
            (0 until rows - 1).scan(listOf(1)) { acc, _ ->
                listOf(acc.first()) +
                        (0 until acc.size - 1).map { acc[it] + acc[it + 1] } +
                        listOf(acc.last())
            }
        }
    }
}
