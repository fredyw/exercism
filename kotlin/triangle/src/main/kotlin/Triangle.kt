class Triangle<out T : Number>(val a: T, val b: T, val c: T) {
    init {
        val x = a.toDouble()
        val y = b.toDouble()
        val z = c.toDouble()
        require(x > 0 && y > 0 && z > 0)
        val list = mutableListOf(x, y, z).apply {
            sort()
        }
        require(list[0] + list[1] >= list[2])
    }

    val isEquilateral: Boolean = a == b && b == c
    val isIsosceles: Boolean = a == b || b == c || a == c
    val isScalene: Boolean = a != b && b != c && a != c
}
