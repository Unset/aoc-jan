package xpair

fun <E : Comparable<E>> Two<E>.sort() : Two<E> {
    return when (first compare second){
        Greater -> swap()
        Equal, Less -> this
    }
}