package xpair

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