package nl.janvanrosmalen.batshit

interface ReadCursor<T> {

    fun fetch() : T

    fun take() : Pair<T, ReadCursor<T>?> = Pair(fetch(), skipOne())

    fun skipOne() : ReadCursor<T>?

    fun skip(n : Long) : Pair<ReadCursor<T>?, Long>

    fun export() : List<T>
}

fun <T>ReadCursor<T>?.skip(n : Long) : Pair<ReadCursor<T>?, Long> = this?.skip(n) ?: Pair(null, 0L)