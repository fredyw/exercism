object ETL {
    fun transform(source: Map<Int, Collection<Char>>): Map<Char, Int> {
        return source.map { (score, letters) ->
            letters.map { it.lowercaseChar() }.associateWith { score }
        }.reduce { acc, map -> acc + map }
    }
}
