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

fun <E>loopFor(loop : ()->E?) : E{
    while(true){
        val result = loop()
        if (result != null) return result
    }
}

fun <E,R> Iterable<E>.findSome(query : (E)->R?) : R?{
    forEach{
        val result = query(it)
        if (result!=null) return result
    }
    return null
}

fun <S,E>whileNotNull(gather : ()->S?, transform : (S) -> E) = sequence<E> {
    while (true){
        val result = gather() ?: break
        yield(transform(result))

    }

}

fun <E> List<E>.nonEmpty() : List<E>? = if (isEmpty()) null else this