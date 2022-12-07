import java.util.*

enum class Relationship {
    EQUAL, SUBLIST, SUPERLIST, UNEQUAL
}

fun <T : Any> List<T>.relationshipTo(other: List<T>): Relationship {
    return when {
        this == other -> Relationship.EQUAL
        Collections.indexOfSubList(other, this) > 0 -> Relationship.SUBLIST
        Collections.indexOfSubList(this, other) > 0 -> Relationship.SUPERLIST
        else -> Relationship.UNEQUAL
    }
}
