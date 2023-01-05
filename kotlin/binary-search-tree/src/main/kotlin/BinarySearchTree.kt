class BinarySearchTree<T : Comparable<T>> {
    data class Node<T>(val data: T, var left: Node<T>? = null, var right: Node<T>? = null)

    var root: Node<T>? = null

    fun insert(value: T) {
        fun insert(node: Node<T>?): Node<T>? {
            if (node == null) {
                return Node(value)
            }
            if (node.data >= value) {
                node.left = insert(node.left)
            } else {
                node.right = insert(node.right)
            }
            return node
        }

        root = insert(root)
    }

    fun asSortedList(): List<T> {
        fun asSortedList(node: Node<T>?): List<T> {
            if (node == null) {
                return listOf()
            }
            return asSortedList(node.left) + listOf(node.data) + asSortedList(node.right)
        }

        return asSortedList(root)
    }

    fun asLevelOrderList(): List<T> {
        val levelOrderList = mutableListOf<T>()
        val deque = ArrayDeque<Node<T>?>()
        deque.addLast(root)
        while (deque.isNotEmpty()) {
            val node = deque.removeFirst() ?: return levelOrderList
            levelOrderList += node.data
            deque.addLast(node.left)
            deque.addLast(node.right)
        }
        return levelOrderList
    }
}
