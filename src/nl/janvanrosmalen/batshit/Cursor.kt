package nl.janvanrosmalen.batshit

class Cursor<T> private constructor(val data : List<T>, val pos : Int = 0) : ReadCursor<T>{

    override fun fetch() : T {
        return data[pos]
    }

    override fun export(): List<T> {
        return data.drop(pos)
    }

    override fun skipOne(): ReadCursor<T>? {
        return if (pos + 1 < data.size){
            Cursor(data, pos+1)
        } else {
            null
        }
    }

    companion object {
        fun <T>create(data : List<T>) : ReadCursor<T>?{
            return if (data.isEmpty()) null else Cursor(data)
        }
    }

    override fun skip(n: Long): Pair<ReadCursor<T>?, Long> {
        val newPos = n + pos
        val size = data.size
        return if (newPos < size) {
            Pair(Cursor(data, newPos.toInt()), n)
        }
        else {
            Pair(null, (size - pos).toLong())
        }
    }

}