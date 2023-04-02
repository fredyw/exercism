class Reactor<T> {
    // Your compute cell's addCallback method must return an object
    // that implements the Subscription interface.
    sealed class Cell<T> {
        abstract var value: T

        var computeCell: Reactor<T>.ComputeCell? = null
    }

    inner class InputCell(private var v: T) : Cell<T>() {
        operator fun invoke(value: T) {
            this.value = value
        }

        override var value: T
            get() = v
            set(value) {
                var prevValue: T? = null
                if (computeCell != null) {
                    prevValue = computeCell!!.value
                }
                this.v = value
                if (computeCell != null) {
                    val cell: ComputeCell = computeCell!!
                    if (cell.value != prevValue) {
                        cell.callbacks.forEach { it(cell.value) }
                    }
                }
            }
    }

    inner class ComputeCell(private vararg val cells: Cell<T>,
                            val compute: (List<T>) -> T) : Cell<T>() {
        val callbacks: MutableList<(T) -> Unit> = mutableListOf()

        override var value: T
            get() =  compute(cells.map { it.value }.toList())
            set(_) =
                throw UnsupportedOperationException()

        fun addCallback(callback: (T) -> Unit): Subscription {
            callbacks += callback
            addCallback(callback, this)
            return object : Subscription {
                override fun cancel() {
                    callbacks -= callback
                }
            }
        }

        private fun addCallback(callback: (T) -> Unit, computeCell: ComputeCell) {
            cells.forEach {
                when (it) {
                    is ComputeCell -> it.addCallback(callback, computeCell)
                    is InputCell -> it.computeCell = computeCell
                }
            }
        }
    }

    interface Subscription {
        fun cancel()
    }
}
