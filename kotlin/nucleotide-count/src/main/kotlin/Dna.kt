class Dna(private val dna: String) {
    init {
        require(dna.all { it == 'A' || it == 'C' || it == 'G' || it == 'T' })
    }

    val nucleotideCounts: Map<Char, Int>
        get() {
            return mapOf(
                'A' to 0,
                'C' to 0,
                'G' to 0,
                'T' to 0,
            ) + dna.groupingBy { it }.eachCount()
        }
}
