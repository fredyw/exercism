class Scale(private val tonic: String) {
    private val scaleIndex: Int =
        SCALE_TO_INDEX_MAP[tonic.uppercase()]
            ?: SCALE_TO_INDEX_MAP[FLAT_TO_SHARP_MAP[tonic.uppercase()]]!!

    fun chromatic(): List<String> {
        return SCALES.indices.map { SCALES[(it + scaleIndex) % SCALES.size] }
    }

    fun interval(intervals: String): List<String> {
        return intervals.dropLast(1).scan(scaleIndex) { index, char ->
            when (char) {
                'm' -> index + 1
                'M' -> index + 2
                else -> index + 3
            } % SCALES.size
        }.map { SCALES[it] }
    }

    companion object {
        private val SCALES = listOf(
            "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"
        )

        val SCALE_TO_INDEX_MAP =
            SCALES.withIndex().associate { (index, scale) -> scale to index }

        private val FLAT_TO_SHARP_MAP = mapOf(
            "BB" to "A#",
            "DB" to "C#",
            "EB" to "D#",
            "GB" to "F#",
            "AB" to "G#",
        )
    }
}
