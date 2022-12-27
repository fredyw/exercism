class ChangeCalculator(private val denominations: List<Int>) {
    fun computeMostEfficientChange(grandTotal: Int): List<Int> {
        require(grandTotal >= 0) { "Negative totals are not allowed." }
        fun compute(total: Int, memo: MutableMap<Int, List<Int>>): List<Int>? {
            if (total == 0) {
                return listOf()
            }
            if (total < 0) {
                return null
            }
            val c = memo[total]
            if (c != null) {
                return c
            }
            val minChange = denominations.mapNotNull {
                val change = compute(total - it, memo)
                if (change != null) {
                    change + listOf(it)
                } else {
                    null
                }
            }.minOfWithOrNull(compareBy { it.size }) { it }
            if (minChange != null) {
                memo[total] = minChange
            }
            return minChange
        }

        val change = compute(grandTotal, mutableMapOf())
        return change
            ?: throw IllegalArgumentException("The total $grandTotal cannot be represented in the given currency.")
    }
}
