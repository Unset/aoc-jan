package collectionutils

fun <E>List<E>.setIndex(index : Int, value : E) : MutableList<E> {
    return toMutableList().apply {
        this[index] = value
    }
}

infix fun <T> T.unelvis(magic : T) : T? = if (this == magic) null else this

fun <K,V>Map<K,V>.setValue(index : K, value : V) : MutableMap<K,V> {
    return toMutableMap().apply {
        this[index] = value
    }
}