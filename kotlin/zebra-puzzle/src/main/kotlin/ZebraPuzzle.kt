class ZebraPuzzle() {
    enum class Color {
        YELLOW, BLUE, RED, IVORY, GREEN
    }

    enum class Nationality {
        NORWEGIAN, UKRAINIAN, ENGLISHMAN, SPANIARD, JAPANESE
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
        TODO("Implement this function to complete the task")
    }

    fun ownsZebra(): String {
        TODO("Implement this function to complete the task")
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
