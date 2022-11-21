class KindergartenGarden(private val diagram: String) {
    private val students: List<String> = listOf(
        "Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet",
        "Ileana", "Joseph", "Kincaid", "Larry"
    )

    private val plantToStudents: Map<String, List<String>> = run {
        val (row1, row2) = diagram.split("\n")
        val plants = row1.chunked(2).zip(row2.chunked(2))
            .map { (a, b) -> (a + b).map { it.toPlant() } }
        students.zip(plants).toMap()
    }

    fun getPlantsOfStudent(student: String): List<String> {
        return checkNotNull(plantToStudents[student])
    }

    private fun Char.toPlant(): String {
        return when (this) {
            'G' -> "grass"
            'C' -> "clover"
            'R' -> "radishes"
            else -> "violets"
        }
    }
}
