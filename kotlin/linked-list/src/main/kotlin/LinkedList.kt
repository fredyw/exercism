class Deque<T> {
    data class Node<T>(
        val value: T,
        var next: Node<T>? = null,
        var prev: Node<T>? = null,
    )

    // tail <---> head
    var head: Node<T>? = null
    var tail: Node<T>? = null

    fun push(value: T) {
        if (head == null) {
            val node = Node(value)
            head = node
            tail = node
        } else {
            val node = Node(value)
            node.prev = head
            head?.next = node
            head = head?.next
        }
    }

    fun pop(): T? {
        val value = head?.value
        head = head?.prev
        if (head == null) {
            tail = null
        }
        head?.next = null
        return value
    }

    fun unshift(value: T) {
        if (tail == null) {
            val node = Node(value)
            tail = node
            head = node
        } else {
            val node = Node(value)
            node.next = tail
            tail?.prev = node
            tail = tail?.prev
        }
    }

    fun shift(): T? {
        val value = tail?.value
        tail = tail?.next
        if (tail == null) {
            head = null
        }
        tail?.prev = null
        return value
    }
}
