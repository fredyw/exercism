enum class Classification {
    DEFICIENT, PERFECT, ABUNDANT
}

fun classify(naturalNumber: Int): Classification {
    require(naturalNumber > 0)
    val aliquotSum =
        (1..naturalNumber / 2)
            .filter { naturalNumber % it == 0 }
            .sum()
    return when {
        aliquotSum == naturalNumber -> Classification.PERFECT
        aliquotSum > naturalNumber -> Classification.ABUNDANT
        else -> Classification.DEFICIENT
    }
}
