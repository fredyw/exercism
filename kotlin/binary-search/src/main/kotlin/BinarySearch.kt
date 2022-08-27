object BinarySearch {
    fun search(list: List<Int>, item: Int): Int {
        var l = 0
        var r = list.size - 1
        while (l <= r) {
            val mid = (l + r) / 2
            if (item == list[mid]) {
                return mid
            }
            if (item < list[mid]) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        throw NoSuchElementException()
    }
}
