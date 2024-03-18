import kotlin.collections.ArrayDeque

class EmptyBufferException : Exception()

class BufferFullException : Exception()

class CircularBuffer<T>(private val capacity: Int) {
    private val buffer: MutableList<T> = ArrayDeque(capacity)

    fun read() : T {
        if (buffer.isEmpty()) {
            throw EmptyBufferException()
        }
        return buffer.removeFirst()
    }

    fun write(value: T) {
        if (isFull()) {
            throw BufferFullException()
        }
        buffer.add(value)
    }

    fun overwrite(value: T) {
        if (isFull()) {
            buffer.removeFirst()
        }
        buffer.add(value)
    }

    fun clear() {
        buffer.clear()
    }

    private fun isFull(): Boolean = capacity == buffer.size
}
