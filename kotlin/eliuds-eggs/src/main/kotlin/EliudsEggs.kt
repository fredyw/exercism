object EliudsEggs {
    fun eggCount(number: Int): Int {
        var count = 0
        var n = number
        while (n > 0) {
            if (n and 1 == 1) {
                count += 1
            }
            n = n shr 1
        }
        return count
    }
}
