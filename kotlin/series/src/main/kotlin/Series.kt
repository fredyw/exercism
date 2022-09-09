object Series {
    fun slices(n: Int, s: String): List<List<Int>> {
        require(n > 0 && n <= s.length)
        return s.windowed(n).map { it.map { it.digitToInt() }  }
    }
}
