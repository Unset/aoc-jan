package collectionutils

fun <E>List<E>.setIndex(index : Int, value : E) : MutableList<E> {
    return toMutableList().apply {
        this[index] = value
    }
}

infix fun <T> T.unelvis(magic : T) : T? = if (this == magic) null else this