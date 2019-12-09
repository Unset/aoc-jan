package xpair

import whenever.Equal
import whenever.Greater
import whenever.Less
import whenever.compare


fun <A, B> Pair<A, B>.swap() = Pair(second, first)

fun <A, B> Pair<A, B>.setFirst(value : A) = Pair(value, second)

fun <A, B> Pair<A, B>.setSecond(value : A) = Pair(first, value)

fun <A, B> Pair<List<A>,List<B>>.zip() = first zip second

typealias Two<E> = Pair<E, E>

infix fun <T> T.two(other : T) = Two(this, other)

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



fun <E : Comparable<E>> Two<E>.sort() : Two<E> {
    return when (first compare second){
        Greater -> swap()
        Equal, Less -> this
    }
}

fun <E> Iterable<E>.toTwo() : Two<E> {
    val total = this.toList()
    return Two(total[0], total[1])
}

fun <E, R> Two<E>.both(transform : (E) -> R) = Two(transform(first), transform(second))

fun <E> Two<E>.andSwapped() = Two(this, swap())

fun <E> Two<E>.areEqual() = first == second

operator fun <E> Two<E>.contains(element : E) = first == element || second == element

fun <E> Two<E>.containsAll(elements : Collection<E>) = elements.all {it in this}
