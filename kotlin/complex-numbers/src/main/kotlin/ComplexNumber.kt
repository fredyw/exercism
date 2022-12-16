import kotlin.math.*

data class ComplexNumber(val real: Double = 0.0, val imag: Double = 0.0) {
    val abs: Double = sqrt(real.pow(2) + imag.pow(2))
}

operator fun ComplexNumber.plus(other: ComplexNumber): ComplexNumber =
    ComplexNumber(
        real = this.real + other.real,
        imag = this.imag + other.imag
    )

operator fun ComplexNumber.minus(other: ComplexNumber): ComplexNumber =
    ComplexNumber(
        real = this.real - other.real,
        imag = this.imag - other.imag
    )

operator fun ComplexNumber.times(other: ComplexNumber): ComplexNumber =
    ComplexNumber(
        real = this.real * other.real - this.imag * other.imag,
        imag = this.imag * other.real + this.real * other.imag
    )

operator fun ComplexNumber.div(other: ComplexNumber): ComplexNumber =
    ComplexNumber(
        real = (this.real * other.real + this.imag * other.imag) /
                (other.real.pow(2) + other.imag.pow(2)),
        imag = (this.imag * other.real - this.real * other.imag) /
                (other.real.pow(2) + other.imag.pow(2))
    )

fun ComplexNumber.conjugate(): ComplexNumber =
    ComplexNumber(real = this.real, imag = -this.imag)

fun exponential(c: ComplexNumber): ComplexNumber =
    ComplexNumber(
        real = exp(c.real) * cos(c.imag),
        imag = exp(c.real) * sin(c.imag)
    )
