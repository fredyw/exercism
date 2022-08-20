import kotlin.random.Random

class Robot {
    private var generatedName = generate()

    val name: String
        get() = generatedName

    fun reset() {
        generatedName = generate()
    }

    companion object {
        private val USED_NAMES = mutableSetOf<String>()

        private fun generate(): String {
            val n = Random.nextInt(0, 26 * 26 * 10 * 10 * 10)
            var name = makeString(n)
            while (name in USED_NAMES) {
                val n = Random.nextInt(0, 26 * 26 * 10 * 10 * 10)
                name = makeString(n)
            }
            USED_NAMES += name
            return name
        }
        private fun makeString(n: Int) = "${'A' + (n / 26000 % 26)}" +
                "${'A' + (n / 1000 % 26)}" +
                "${n / 100 % 10}" +
                "${n / 10 % 10}" +
                "${n % 10}"
    }
}
