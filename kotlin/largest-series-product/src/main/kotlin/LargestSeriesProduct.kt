class Series(private val num: String) {
    init {
        require(num.all { it.isDigit() })
    }

    fun getLargestProduct(span: Int): Long {
        require(num.length >= span && span >= 0)
        return (0..num.length - span).maxOf {
            num.substring(it, it + span).fold(1L) { acc, c ->
                acc * c.digitToInt()
            }
        }
    }
}
