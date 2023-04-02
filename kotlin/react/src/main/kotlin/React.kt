class Reactor<T> {
    // Your compute cell's addCallback method must return an object
    // that implements the Subscription interface.
    sealed interface Cell<T> {
        var value: T
    }

    inner class InputCell(private var initialValue: T) : Cell<T> {
        var computeCell: Reactor<T>.ComputeCell? = null

        operator fun invoke(value: T) {
            this.value = value
        }

        override var value: T
            get() = initialValue
            set(value) {
                var prevValue: T? = null
                if (computeCell != null) {
                    prevValue = computeCell!!.value
                }
                this.initialValue = value
                if (computeCell != null) {
                    val cell: ComputeCell = computeCell!!
                    if (cell.value != prevValue) {
                        cell.callbacks.forEach { it(cell.value) }
                    }
                }
            }
    }

    inner class ComputeCell(private vararg val cells: Cell<T>,
                            compute: (List<T>) -> T) : Cell<T> {
        val callbacks: MutableList<(T) -> Unit> = mutableListOf()

        override var value: T = compute(cells.map { it.value }.toList())

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
