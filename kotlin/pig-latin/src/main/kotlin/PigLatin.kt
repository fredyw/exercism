object PigLatin {
    private val VOWEL_RULE = "(?:[aeiou]|yr|xr|yt).*".toRegex()
    private val CONSONANT_RULE =
        "(thr?|sch|ch|s?qu|rh|[bcdfghjklmnpqrstvwxyz])(.*)".toRegex()

    fun translate(phrase: String): String {
        return phrase.split(" ").joinToString(" ") {
            when {
                it.matches(VOWEL_RULE) -> "${it}ay"
                it.matches(CONSONANT_RULE) -> CONSONANT_RULE.replace(
                    it,
                    "$2$1ay"
                )
                else -> it
            }
        }
    }
}
