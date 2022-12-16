data class ComplexNumber(val real: Double = 0.0, val imag: Double = 0.0) {
    val abs: Double = TODO()
}

operator fun ComplexNumber.plus(complexNumber: ComplexNumber): ComplexNumber = TODO()

operator fun ComplexNumber.minus(complexNumber: ComplexNumber): ComplexNumber = TODO()

operator fun ComplexNumber.times(complexNumber: ComplexNumber): ComplexNumber = TODO()

operator fun ComplexNumber.div(complexNumber: ComplexNumber): ComplexNumber = TODO()

fun ComplexNumber.conjugate(): ComplexNumber = TODO()

fun exponential(complexNumber: ComplexNumber): ComplexNumber {
    TODO()
}
