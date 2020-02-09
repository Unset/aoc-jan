package nl.janvanrosmalen.compare

sealed class Compie() : Comparable<Compie> {

    abstract val int : Int

    override fun toString(): String {
        return int.toString()
    }

    override fun compareTo(other: Compie): Int {
        return int.compareTo(other.int)
    }

}

object Equal : Compie() {

    override val int = 0
}

object Greater : Compie() {

    override val int = 1
}

object Less : Compie() {

    override val int = -1
}




fun fromInt(i : Int) : Compie {
    return when {
        i>0 -> Greater
        i<0 -> Less
        else -> Equal
    }
}

infix fun <T : Comparable<T>> T.compare(other: T) : Compie {
    return fromInt(this.compareTo(other))
}


fun Int.compareZero() : Compie {
    return fromInt(this)
}

fun Compie.tieBreak(breaker : () -> Compie) = when (this){
    Greater -> Greater
    Less -> Less
    Equal -> breaker()
}

val Compie.equal get() = this == Equal
val Compie.greater get() = this == Greater
val Compie.less get() = this == Less
