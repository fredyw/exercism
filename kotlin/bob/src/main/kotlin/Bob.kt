object Bob {
    fun hey(input: String): String {
        fun isYelling(): Boolean {
            val letters = input.trim().filter { it.isLetter() }
            return if (letters.isNotEmpty()) letters.all { it.isUpperCase() } else false
        }

        return when {
            input.trim().isEmpty() -> "Fine. Be that way!"
            isYelling() && input.endsWith("?") -> "Calm down, I know what I'm doing!"
            isYelling() -> "Whoa, chill out!"
            input.trim().endsWith("?") -> "Sure."
            else -> "Whatever."
        }
    }
}

