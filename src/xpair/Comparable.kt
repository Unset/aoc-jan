package xpair

import nl.janvanrosmalen.natural.*

fun <E : Comparable<E>> Duo<E>.sort() : Duo<E> {
    return when (first compare second){
        Greater -> swap()
        Equal, Less -> this
    }
}

fun <E : Comparable<E>> Duo<E>.max() : E {
    return toList().max()!!
}

fun <E : Comparable<E>> Duo<E>.min() : E {
    return toList().min()!!
}

val <E : Comparable<E>> Duo<E>.comparison get() = first compare second


val <E : Comparable<E>> Duo<E?>.nullLowTrit : Trit
    get()  = when {
    bothNull() -> Equal
    first == null -> Less
    second == null -> Greater
    else -> first!! compare second!!
}