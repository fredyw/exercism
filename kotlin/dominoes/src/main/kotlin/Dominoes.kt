class ChainNotFoundException(msg: String) : RuntimeException(msg)

data class Domino(val left: Int, val right: Int)

object Dominoes {
    fun formChain(inputDominoes: List<Domino>): List<Domino> {
        fun tryFormChain(
            dominoes: List<Domino>,
            accumulator: List<Domino>
        ): List<Domino> {
            if (dominoes.isEmpty()) {
                if (accumulator.size != inputDominoes.size
                    || !accumulator.last().isChain(accumulator.first())) {
                    return listOf()
                }
                return accumulator
            }
            return dominoes.flatMapIndexed { index, domino ->
                val remaining =
                    dominoes.subList(0, index) + dominoes.subList(index + 1, dominoes.size)
                val chain = if (accumulator.isEmpty()) {
                    tryFormChain(remaining, accumulator + domino)
                } else if (accumulator.last().isChain(domino)) {
                    tryFormChain(remaining, accumulator + domino)
                } else if (accumulator.last().isChain(domino.flip())) {
                    tryFormChain(remaining, accumulator + domino.flip())
                } else {
                    tryFormChain(remaining, accumulator)
                }
                if (chain.isNotEmpty()) {
                    // Short circuit.
                    return chain
                } else {
                    chain
                }
            }
        }

        if (inputDominoes.isEmpty()) {
            return listOf()
        }
        return tryFormChain(inputDominoes, listOf()).also {
            if (it.isEmpty()) {
                throw ChainNotFoundException("Could not form a chain")
            }
        }
    }

    fun formChain(vararg inputDominoes: Domino): List<Domino> {
        return formChain(inputDominoes.toList())
    }

    private fun Domino.flip() = Domino(this.right, this.left)

    private fun Domino.isChain(domino: Domino) = this.right == domino.left
}
