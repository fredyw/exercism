class Series(private val num: String) {
    init {
        require(num.all { it.isDigit() })
    }

    fun getLargestProduct(span: Int): Long {
        require(num.length >= span && span >= 0)
        TODO("Implement this function to complete the task")
    }
}
