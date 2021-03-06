package xpair

import nl.janvanrosmalen.human.natural.BitOne
import nl.janvanrosmalen.human.natural.BitZero
import nl.janvanrosmalen.human.natural.Bit

typealias Duo<E> = Pair<E, E>

infix fun <T> T.duo(other : T) = Duo(this, other)

fun <E, R> Duo<E>.both(transform : (E) -> R) = Duo(transform(first), transform(second))

fun <E> Duo<E>.andSwapped() = Duo(this, swap())

fun <E> Duo<E>.set(index : Bit, value : E) = when (index){
    BitZero -> copy(first = value)
    BitOne -> copy(second = value)
}

operator fun <E> Duo<E>.get(index : Bit) = when (index){
    BitZero -> first
    BitOne -> second
}

fun <E> Duo<E>.areEqual() = first == second



operator fun <E> Duo<E>.contains(element : E) = first == element || second == element

fun <E> Duo<E>.containsAll(elements : Collection<E>) = elements.all {it in this}

fun Pair<String, String>.toCharList() = both {it.toCharArray().toList()}

fun <E> Iterable<E>.toDuo() : Duo<E> {
    val total = this.toList()
    return Duo(total[0], total[1])
}

fun Duo<Int>.sum() = first + second

fun <E>Duo<Trio<E>>.zip() : Trio<Duo<E>> = Trio(Duo(first.first,second.first), Duo(first.second, second.second ), Duo(first.third, second.third))


fun <E>Trio<Duo<E>>.unzip() : Duo<Trio<E>> = Duo(Triple(first.first, second.first, third.first), Triple(first.second, second.second, third.second))

fun <E>twice(subject: E) = Duo(subject, subject)

