class ChainNotFoundException(msg: String) : RuntimeException(msg)

data class Domino(val left: Int, val right: Int)

object Dominoes {
    fun formChain(inputDominoes: List<Domino>): List<Domino> {
        TODO("Implement this to complete the task")
    }

    fun formChain(vararg inputDominoes: Domino): List<Domino> {
        return formChain(inputDominoes.toList())
    }
}
