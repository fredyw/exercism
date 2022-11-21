object Prime {
    fun nth(n: Int): Int {
        require(n > 0) { "There is no zeroth prime." }
        return generateSequence(2) { it + 1 }
            .filter { it.isPrime() }
            .elementAt(n - 1)
    }

    private fun Int.isPrime(): Boolean {
        if (this == 2 || this == 3) {
            return true
        }
        if (this <= 1 || this % 2 == 0 || this % 3 == 0) {
            return false
        }
        if (generateSequence(5) { it + 6 }
            .takeWhile { it * it <= this }
            .any { this % it == 0 || this % (it + 2) == 0 }) {
            return false
        }
        return true
    }
}
