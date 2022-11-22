object PrimeFactorCalculator {
    fun primeFactors(int: Int): List<Int> {
        return primeFactors(int.toLong()).map { it.toInt() }
    }

    fun primeFactors(long: Long): List<Long> {
        return primeFactors(long, 2, listOf())
    }

    private tailrec fun primeFactors(number: Long, divisor: Long, acc: List<Long>): List<Long> {
        if (number <= 1) {
            return acc
        }
        return if (number % divisor == 0L) {
            primeFactors(number / divisor, divisor, acc + divisor)
        } else {
            primeFactors(number, divisor + 1, acc)
        }
    }
}
