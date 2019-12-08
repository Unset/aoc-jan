package collectionutils

fun <E>List<E>.setIndex(index : Int, value : E) : MutableList<E> {
    return toMutableList().apply {
        this[index] = value
    }
}