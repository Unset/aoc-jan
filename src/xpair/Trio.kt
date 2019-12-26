package xpair

typealias Trio<E> = Triple<E, E, E>

fun <E, R> Trio<E>.allThree(transform : (E) -> R) = Trio(transform(first), transform(second), transform(third))

fun <E> Iterable<E>.toTrio() : Trio<E> {
    val total = this.toList()
    return Trio(total[0], total[1], total[2])
}

fun Trio<Int>.sum() = first + second + third