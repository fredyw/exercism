fun <T> List<T>.customAppend(list: List<T>): List<T> {
    return list.customFoldLeft(this) { acc, e -> acc + e }
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
    return if (isEmpty()) initial else { drop(1).customFoldLeft(f(initial, first()), f) }
}

fun <T, U> List<T>.customFoldRight(initial: U, f: (T, U) -> U): U {
    return customReverse().customFoldLeft(initial) { acc, e -> f(e, acc) }
}

fun <T> List<T>.customReverse(): List<T> {
    return customFoldLeft(listOf()) { acc, e -> listOf(e) + acc }
}
