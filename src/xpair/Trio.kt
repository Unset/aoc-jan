package xpair

typealias Trio<E> = Triple<E, E, E>

fun <E, R> Trio<E>.allThree(transform : (E) -> R) = Trio(transform(first), transform(second), transform(third))

fun <E, A, R> Trio<E>.foldMap(initial : A, operation : (acc :A, E) -> Pair<A, R>?) : Pair<A, Trio<R>>? {
    val (firstAcc, firstMapped) = operation(initial, first) ?: return null
    val (secondAcc, secondMapped) = operation(firstAcc, second) ?: return null
    val (thirdAcc, thirdMapped) = operation(secondAcc, third) ?: return null
    return Pair(thirdAcc, Trio(firstMapped,secondMapped,thirdMapped))
}

fun <E> Iterable<E>.toTrio() : Trio<E> {
    val total = this.toList()
    return Trio(total[0], total[1], total[2])
}

fun Trio<Int>.sum() = first + second + third

val <E> Trio<E>.firstAndSecond get() = Duo<E>(first, second)
