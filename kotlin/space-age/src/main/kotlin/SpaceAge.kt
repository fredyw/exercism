import java.math.BigDecimal
import java.math.RoundingMode

class SpaceAge(private val age: Int) {
    fun onEarth(): Double = (age / SECONDS_IN_EARTH).roundToTwoDecimal()
    fun onMercury(): Double = (age / 0.2408467 / SECONDS_IN_EARTH).roundToTwoDecimal()
    fun onVenus(): Double = (age / 0.61519726 / SECONDS_IN_EARTH).roundToTwoDecimal()
    fun onMars(): Double = (age / 1.8808158 / SECONDS_IN_EARTH).roundToTwoDecimal()
    fun onJupiter(): Double = (age / 11.862615 / SECONDS_IN_EARTH).roundToTwoDecimal()
    fun onSaturn(): Double = (age / 29.447498 / SECONDS_IN_EARTH).roundToTwoDecimal()
    fun onUranus(): Double = (age / 84.016846 / SECONDS_IN_EARTH).roundToTwoDecimal()
    fun onNeptune(): Double = (age / 164.79132 / SECONDS_IN_EARTH).roundToTwoDecimal()

    companion object {
        private const val SECONDS_IN_EARTH = (31_557_600).toDouble()
        private fun Double.roundToTwoDecimal() =
            BigDecimal(this).setScale(2, RoundingMode.HALF_UP).toDouble()
    }
}
