import java.util.TreeMap
import java.util.TreeSet

class School {
    private val map = TreeMap<Int, TreeSet<String>>()

    fun add(student: String, grade: Int) {
        val students = map[grade]
        if (students == null) {
            map[grade] = TreeSet<String>().also { it += student }
        } else {
            students.add(student)
        }
    }

    fun grade(grade: Int): List<String> {
        val students = map[grade] ?: return listOf()
        return students.toList()
    }

    fun roster(): List<String> {
        return map.flatMap { it.value.toList() }
    }
}
