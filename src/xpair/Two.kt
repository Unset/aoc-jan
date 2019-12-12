package xpair

typealias Two<E> = Pair<E, E>

infix fun <T> T.two(other : T) = Two(this, other)

fun <E, R> Two<E>.both(transform : (E) -> R) = Two(transform(first), transform(second))

fun <E> Two<E>.andSwapped() = Two(this, swap())

fun <E> Two<E>.set(index : PairIndex, value : E) = when (index){
    First -> copy(first = value)
    Second -> copy(second = value)
}

operator fun <E> Two<E>.get(index : PairIndex) = when (index){
    First -> first
    Second -> second
}

fun <E> Two<E>.areEqual() = first == second

operator fun <E> Two<E>.contains(element : E) = first == element || second == element

fun <E> Two<E>.containsAll(elements : Collection<E>) = elements.all {it in this}

fun Pair<String, String>.toCharList() = both {it.toCharArray().toList()}