package xpair

fun <E : Comparable<E>> Two<E>.sort() : Two<E> {
    return when (first compare second){
        Greater -> swap()
        Equal, Less -> this
    }
}

fun <E : Comparable<E>> Two<E>.max() : E {
    return toList().max()!!
}

fun <E : Comparable<E>> Two<E>.min() : E {
    return toList().min()!!
}