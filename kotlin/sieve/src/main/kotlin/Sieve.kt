object Sieve {
    fun primesUpTo(upperBound: Int): List<Int> {
        tailrec fun primes(numbers: List<Int>, primes: List<Int>): List<Int> {
            if (numbers.isEmpty()) {
                return primes
            }
            val first = numbers.first()
            return primes(numbers.filter { it % first != 0}, primes + first)
        }

        return primes((2..upperBound).toList(), listOf())
    }
}
