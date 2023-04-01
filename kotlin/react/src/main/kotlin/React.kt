class Reactor<T> {
    // Your compute cell's addCallback method must return an object
    // that implements the Subscription interface.
    abstract class Cell<T> {
        abstract var value: T

        var computeCell: Reactor<T>.ComputeCell? = null
    }

    inner class InputCell(private var _value: T) : Cell<T>() {
        operator fun invoke(value: T) {
            this.value = value
        }

        override var value: T
            get() = _value
            set(value) {
                val tmp = this.value
                this._value = value
                if (computeCell != null) {
                    val cell: ComputeCell = computeCell!!
                    if (cell.value != cell.compute(listOf(tmp))) {
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
            cells.forEach { it.computeCell = this }
            return object : Subscription {
                override fun cancel() {
                    callbacks -= callback
                }
            }
        }
    }

    interface Subscription {
        fun cancel()
    }
}
