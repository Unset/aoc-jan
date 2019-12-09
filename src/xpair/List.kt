package xpair

fun <T> List<T>.chain() : MutableList<Two<T>> {
    val result = emptyList<Two<T>>().toMutableList()
    if (this.size <= 1) return result

    var left = this.first()

    for (right in this.drop(1)){
        result.add(Two(left, right))
        left = right
    }
    return result
}



fun <E> Iterable<E>.toTwo() : Two<E> {
    val total = this.toList()
    return Two(total[0], total[1])
}