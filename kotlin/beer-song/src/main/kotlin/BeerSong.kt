object BeerSong {
    fun verses(startBottles: Int, takeDown: Int): String {
        fun bottles(n: Int) = when (n) {
            0 -> "no more bottles"
            1 -> "$n bottle"
            else -> "$n bottles"
        }

        return (startBottles downTo takeDown).joinToString("\n") {
            if (it == 0) {
                "No more bottles of beer on the wall, no more bottles of beer.\n" +
                        "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
            } else {
                "${bottles(it)} of beer on the wall, ${bottles(it)} of beer.\n" +
                        "Take ${if (it == 1) "it" else "one"} down and pass it around, ${
                            bottles(
                                it - 1
                            )
                        } of beer on the wall.\n"
            }
        }
    }
}
