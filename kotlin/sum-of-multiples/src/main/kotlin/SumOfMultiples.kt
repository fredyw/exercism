object SumOfMultiples {
    fun sum(factors: Set<Int>, limit: Int): Int {
        return (1 until limit).sumOf { number ->
            if (factors.filter { it != 0 }.any { number % it == 0 }) number else 0
        }
    }
}
