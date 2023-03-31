class Reactor<T> {
    // Your compute cell's addCallback method must return an object
    // that implements the Subscription interface.
    abstract class Cell<T> {
        abstract var value: T

        val computeCells: MutableList<Reactor<T>.ComputeCell> = mutableListOf()
    }

    inner class InputCell(private var _value: T) : Cell<T>() {
        operator fun invoke(value: T) {
            this.value = value
        }

        override var value: T
            get() = _value
            set(value) {
                this._value = value
                computeCells.forEach { cell ->
                    cell.value.also { v ->
                        if (v != value) {
                            cell.callbacks.forEach { f -> f(v) }
                        }
                    }
                }
            }
    }

    inner class ComputeCell(private vararg val cells: Cell<T>,
                            private val f: (List<T>) -> T) : Cell<T>() {
        val callbacks: MutableList<(T) -> Unit> = mutableListOf()

        override var value: T
            get() = compute()
            set(_) =
                throw UnsupportedOperationException()

        fun addCallback(f: (T) -> Unit): Subscription {
            callbacks += f
            cells.forEach { it.computeCells += this }
            return object : Subscription {
                override fun cancel() {
                    callbacks -= f
//                    cells.forEach { it.computeCells -= this }
                }

            }
        }

        fun compute(): T {
            return f(cells.map { it.value }.toList())
        }
    }

    interface Subscription {
        fun cancel()
    }
}
