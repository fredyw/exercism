fun <T> List<T>.customAppend(list: List<T>): List<T> {
    return this + list
}

fun List<Any>.customConcat(): List<Any> {
    return customFoldLeft(listOf()) { acc, e ->
        if (e is List<*>) {
            acc + (e as List<Any>).customConcat()
        } else {
            acc + e
        }
    }
}

fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
    return customFoldLeft(listOf()) { acc, e ->
        if (predicate(e)) acc + listOf(e) else acc
    }
}

val List<Any>.customSize: Int get() {
    return customFoldLeft(0) { acc, _ -> acc + 1 }
}

fun <T, U> List<T>.customMap(transform: (T) -> U): List<U> {
    return customFoldLeft(listOf()) { acc, e -> acc + transform(e) }
}

fun <T, U> List<T>.customFoldLeft(initial: U, f: (U, T) -> U): U {
    tailrec fun f(index: Int, acc: U): U {
        if (index == this.size) {
            return acc
        }
        return f(index + 1, f(acc, this[index]))
    }

    return f(0, initial)
}

fun <T, U> List<T>.customFoldRight(initial: U, f: (T, U) -> U): U {
    return customReverse().customFoldLeft(initial) { acc, e -> f(e, acc) }
}

fun <T> List<T>.customReverse(): List<T> {
    return customFoldLeft(listOf()) { acc, e -> listOf(e) + acc }
}
