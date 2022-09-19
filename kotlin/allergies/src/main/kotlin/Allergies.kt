class Allergies(private val score: Int) {
    private val allergens: List<Allergen> =
        Allergen.values().filter { score and it.score == it.score }

    fun getList(): List<Allergen> {
        return allergens
    }

    fun isAllergicTo(allergen: Allergen): Boolean {
        return allergens.contains(allergen)
    }
}
