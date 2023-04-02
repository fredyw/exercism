import kotlin.properties.Delegates

class Reactor<T> {
    // Your compute cell's addCallback method must return an object
    // that implements the Subscription interface.
    sealed class Cell<T> {
        abstract var value: T
        val observers = mutableListOf<Reactor<T>.ComputeCell>()
    }

    inner class InputCell(private var initialValue: T) : Cell<T>() {
        override var value by Delegates.observable(initialValue) { _, old, new ->
            if (old != new) {
                observers.forEach { it.inform() }
            }
        }
    }

    inner class ComputeCell(
        private vararg val cells: Cell<T>,
        private val compute: (List<T>) -> T
    ) : Cell<T>() {
        init {
            cells.forEach { it.observers += this }
        }

        private var previousValue: T = value
        private val callbacks: MutableList<(T) -> Unit> = mutableListOf()

        override var value: T
            get() = computeValue()
            set(value) {
                if (previousValue != value) {
                    previousValue = value
                    callbacks.forEach { it(value) }
                    observers.forEach { it.inform() }
                }
            }

        fun inform() {
            value = computeValue()
        }

        fun addCallback(callback: (T) -> Unit): Subscription {
            callbacks += callback
            return object : Subscription {
                override fun cancel() {
                    callbacks -= callback
                }
            }
        }

        private fun computeValue() = compute(cells.map { it.value }.toList())
    }

    interface Subscription {
        fun cancel()
    }
}
