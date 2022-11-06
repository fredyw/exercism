object Yacht {
    fun solve(category: YachtCategory, vararg dices: Int): Int {
        return when (category) {
            YachtCategory.ONES -> dices.filter { it == 1 }.sum()
            YachtCategory.TWOS -> dices.filter { it == 2 }.sum()
            YachtCategory.THREES -> dices.filter { it == 3 }.sum()
            YachtCategory.FOURS -> dices.filter { it == 4 }.sum()
            YachtCategory.FIVES -> dices.filter { it == 5 }.sum()
            YachtCategory.SIXES -> dices.filter { it == 6 }.sum()
            YachtCategory.FULL_HOUSE ->
                dices.toList().groupingBy { it }.eachCount().let { map ->
                    if (map.containsValue(3) && map.containsValue(2)) {
                        map.map { it.key * it.value }.sum()
                    } else {
                        0
                    }
                }
            YachtCategory.FOUR_OF_A_KIND ->
                dices.toList().groupingBy { it }.eachCount().let { map ->
                    map.filter { it.value >= 4 }.map { it.key * 4 }.sum()
                }
            YachtCategory.LITTLE_STRAIGHT -> if ((1..5).all { it in dices }) 30 else 0
            YachtCategory.BIG_STRAIGHT -> if ((2..6).all { it in dices }) 30 else 0
            YachtCategory.CHOICE -> dices.sum()
            YachtCategory.YACHT -> if (dices.toSet().size == 1) 50 else 0
        }
    }
}
