object BottleSong {
    fun recite(startBottles : Int, takeDown : Int) : String {
        return (startBottles downTo (startBottles - takeDown + 1))
            .joinToString("\n\n") { n -> generateVerse(n)
        }
    }

    fun toWord(n: Int): String {
        return when (n) {
            1 -> "One"
            2 -> "Two"
            3 -> "Three"
            4 -> "Four"
            5 -> "Five"
            6 -> "Six"
            7 -> "Seven"
            8 -> "Eight"
            9 -> "Nine"
            10 -> "Ten"
            else -> "No"
        }
    }

    fun bottle(n: Int): String {
        return if (n == 1) {
            "bottle"
        } else {
            "bottles"
        }
    }

    fun generateVerse(n: Int): String {
        return "${toWord(n)} green ${bottle(n)} hanging on the wall,\n" +
            "${toWord(n)} green ${bottle(n)} hanging on the wall,\n" +
            "And if one green bottle should accidentally fall,\n" +
            "There'll be ${toWord(n - 1).lowercase()} green ${bottle(n - 1)} hanging on the wall."
    }
}
