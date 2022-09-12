class CustomSet(set: Set<Int>) {
    constructor(vararg elements: Int) : this(elements.toSet())

    private val set = set.toMutableSet()

    fun isEmpty(): Boolean {
        return set.isEmpty()
    }

    fun isSubset(other: CustomSet): Boolean {
        return other.set.containsAll(set)
    }

    fun isDisjoint(other: CustomSet): Boolean {
        return set.intersect(other.set).isEmpty()
    }

    fun contains(other: Int): Boolean {
        return set.contains(other)
    }

    fun intersection(other: CustomSet): CustomSet {
        return CustomSet(set.intersect(other.set).toSet())
    }

    fun add(other: Int) {
        set.add(other)
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is CustomSet -> set == other.set
            else -> false
        }
    }

    operator fun plus(other: CustomSet): CustomSet {
        return CustomSet((set + other.set).toSet())
    }

    operator fun minus(other: CustomSet): CustomSet {
        return CustomSet((set - other.set).toSet())
    }
}
