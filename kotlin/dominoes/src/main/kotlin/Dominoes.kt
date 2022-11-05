class ChainNotFoundException(msg: String) : RuntimeException(msg)

data class Domino(val left: Int, val right: Int)

object Dominoes {
    fun formChain(inputDominoes: List<Domino>): List<Domino> {
        fun tryFormChain(
            indexes: MutableSet<Int>,
            chain: MutableList<Domino>
        ): List<Domino> {
            if (chain.size == inputDominoes.size) {
                return chain
            }
            for ((index, domino) in inputDominoes.withIndex()) {
                if (index in indexes) {
                    continue
                }
                indexes += index
                val last = chain.lastOrNull()
                if (last == null) {
                    chain += domino
                    val c = tryFormChain(indexes, chain)
                    if (c.isNotEmpty()) {
                        return c
                    }
                } else {
                    when (last.right) {
                        domino.left -> {
                            if (index != inputDominoes.lastIndex
                                || (index == inputDominoes.lastIndex && domino.isMatch(chain.first()))) {
                                chain += domino
                                val c = tryFormChain(indexes, chain)
                                if (c.isNotEmpty()) {
                                    return c
                                }
                            }
                        }

                        domino.right -> {
                            if (index != inputDominoes.lastIndex
                                || (index == inputDominoes.lastIndex && domino.isMatch(chain.first()))) {
                                chain += domino.flip()
                                val c = tryFormChain(indexes, chain)
                                if (c.isNotEmpty()) {
                                    return c
                                }
                            }
                        }
                    }
                }
                indexes -= index
            }
            if (chain.isNotEmpty()) {
                chain.removeLast()
            }
            return listOf()
        }

        if (inputDominoes.isEmpty()) {
            return listOf()
        }
        return tryFormChain(mutableSetOf(), mutableListOf()).also {
            if (it.isEmpty() || !it.last().isMatch(it.first())) {
                throw ChainNotFoundException("Could not form a chain")
            }
        }
    }

    fun formChain(vararg inputDominoes: Domino): List<Domino> {
        return formChain(inputDominoes.toList())
    }

    private fun Domino.flip() = Domino(this.right, this.left)

    private fun Domino.isMatch(domino: Domino) = this.right == domino.left
}
