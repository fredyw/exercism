object Flattener {
    fun flatten(source: Collection<Any?>): List<Any> {
        return source.flatMap {
            when (it) {
                is Collection<*> -> flatten(it)
                else -> listOf(it)
            }
        }.filterNotNull()
    }
}
