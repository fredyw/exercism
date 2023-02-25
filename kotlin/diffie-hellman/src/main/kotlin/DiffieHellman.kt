import java.math.BigInteger
import java.util.*

object DiffieHellman {
    fun privateKey(prime: BigInteger): BigInteger {
        return generateSequence {
            BigInteger(prime.bitLength(), Random())
        }.first { it >= BigInteger.ONE && it < prime }
    }

    fun publicKey(p: BigInteger, g: BigInteger, privKey: BigInteger): BigInteger {
        return g.modPow(privKey, p)
    }

    fun secret(prime: BigInteger, publicKey: BigInteger, privateKey: BigInteger): BigInteger {
        return publicKey.modPow(privateKey, prime)
    }
}
