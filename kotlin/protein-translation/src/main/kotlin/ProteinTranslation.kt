fun translate(rna: String?): List<String> {
    if (rna == null) {
        return listOf()
    }
    return rna.windowed(size = 3, step = 3, partialWindows = true)
        .takeWhile { !isStop(it) }
        .map {
            when (it) {
                "AUG" -> "Methionine"
                "UUU", "UUC" -> "Phenylalanine"
                "UUA", "UUG" -> "Leucine"
                "UCU", "UCC", "UCA", "UCG" -> "Serine"
                "UAU", "UAC" -> "Tyrosine"
                "UGU", "UGC" -> "Cysteine"
                "UGG" -> "Tryptophan"
                else -> throw IllegalArgumentException("invalid codon")
            }
        }
}

private fun isStop(codon: String): Boolean {
    return codon in setOf("UAA", "UAG", "UGA")
}
