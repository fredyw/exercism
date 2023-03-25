class ZebraPuzzle() {
    enum class Color {
        YELLOW, BLUE, RED, IVORY, GREEN
    }

    enum class Nationality(val who: String) {
        NORWEGIAN("Norwegian"),
        UKRAINIAN("Ukranian"),
        ENGLISHMAN("Englishman"),
        SPANIARD("Spaniard"),
        JAPANESE("Japanese"),
    }

    enum class Drink {
        WATER, TEA, MILK, ORANGE_JUICE, COFFEE
    }

    enum class Smoke {
        KOOLS, CHESTERFIELD, OLD_GOLD, LUCKY_STRIKE, PARLIAMENT
    }

    enum class Pet {
        FOX, HORSE, SNAILS, DOG, ZEBRA
    }

    fun drinksWater(): String {
        return solution.nationalities[solution.drinks.indexOf(Drink.WATER)].who
    }

    fun ownsZebra(): String {
        return solution.nationalities[solution.pets.indexOf(Pet.ZEBRA)].who
    }

    data class Solution(
        val colors: List<Color>,
        val nationalities: List<Nationality>,
        val pets: List<Pet>,
        val drinks: List<Drink>,
        val smokes: List<Smoke>,
    )

    private val solution: Solution = let {
        for (colors in permutations<Color>()
            .filter { color -> color.colorRules() }) {
            for (nationalities in permutations<Nationality>()
                .filter { nationalities -> nationalities.nationalityRules(colors) }) {
                for (pets in permutations<Pet>()
                    .filter { pets -> pets.petRules(nationalities) }) {
                    for (drinks in permutations<Drink>()
                        .filter { drinks -> drinks.drinkRules(colors, nationalities)
                    }) for (smokes in permutations<Smoke>()
                        .filter { smokes -> smokes.smokeRules(colors, nationalities, drinks, pets) }) {
                        return@let Solution(
                            colors,
                            nationalities,
                            pets,
                            drinks,
                            smokes,
                        )
                    }
                }
            }
        }
        throw Exception("No solution")
    }

    private fun List<Color>.colorRules(): Boolean {
        return indexOf(Color.GREEN) == indexOf(Color.IVORY) + 1 // rule 6
    }

    private fun List<Nationality>.nationalityRules(colors: List<Color>): Boolean {
        return indexOf(Nationality.ENGLISHMAN) == colors.indexOf(Color.RED) // rule 2
                && indexOf(Nationality.NORWEGIAN) == 0 // rule 10
                && (indexOf(Nationality.NORWEGIAN) - 1 == colors.indexOf(Color.BLUE) ||
                    indexOf(Nationality.NORWEGIAN) + 1 == colors.indexOf(Color.BLUE)) // rule 15
    }

    private fun List<Drink>.drinkRules(colors: List<Color>, nationalities: List<Nationality>): Boolean {
        return indexOf(Drink.COFFEE) == colors.indexOf(Color.GREEN) // rule 4
                && nationalities.indexOf(Nationality.UKRAINIAN) == indexOf(Drink.TEA) // rule 5
                && indexOf(Drink.MILK) == 2 // rule 9
    }

    private fun List<Pet>.petRules(nationalities: List<Nationality>): Boolean {
        return nationalities.indexOf(Nationality.SPANIARD) == indexOf(Pet.DOG) // rule 3
    }

    private fun List<Smoke>.smokeRules(
        colors: List<Color>,
        nationalities: List<Nationality>,
        drinks: List<Drink>,
        pets: List<Pet>,
    ): Boolean {
        return indexOf(Smoke.OLD_GOLD) == pets.indexOf(Pet.SNAILS) // rule 7
                && indexOf(Smoke.KOOLS) == colors.indexOf(Color.YELLOW) // rule 8
                && (indexOf(Smoke.CHESTERFIELD) - 1 == pets.indexOf(Pet.FOX) ||
                    indexOf(Smoke.CHESTERFIELD) + 1 == pets.indexOf(Pet.FOX)) // rule 11
                && (indexOf(Smoke.KOOLS) - 1 == pets.indexOf(Pet.HORSE) ||
                    indexOf(Smoke.KOOLS) + 1 == pets.indexOf(Pet.HORSE)) // rule 12
                && indexOf(Smoke.LUCKY_STRIKE) == drinks.indexOf(Drink.ORANGE_JUICE) // rule 13
                && indexOf(Smoke.PARLIAMENT) == nationalities.indexOf(Nationality.JAPANESE) // rule 14
    }

    private inline fun <reified T : Enum<T>> permutations(): List<List<T>> =
        enumValues<T>().toList().permute()

    private fun <T> List<T>.permute(): List<List<T>> {
        if (isEmpty()) {
            return listOf(this)
        }
        return flatMap { e -> (this - e).permute().map { list -> list + e } }
    }
}

