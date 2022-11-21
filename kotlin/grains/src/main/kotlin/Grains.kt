import java.math.BigInteger
import kotlin.math.pow

object Board {
    private val grainCounts: Map<Int, BigInteger> =
        (2..64).scan(1 to BigInteger.valueOf(1)) { acc, i ->
            i to acc.second.multiply(BigInteger.valueOf(2))
        }.toMap()

    private val totalGrainCounts: BigInteger = grainCounts.values.sumOf { it }

    fun getGrainCountForSquare(number: Int): BigInteger {
        return grainCounts[number] ?: throw IllegalArgumentException()
    }

    fun getTotalGrainCount(): BigInteger {
        return totalGrainCounts
    }
}
