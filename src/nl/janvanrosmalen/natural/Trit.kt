package nl.janvanrosmalen.natural

sealed class Trit() : Comparable<Trit> {

    abstract val int : Int

    override fun toString(): String {
        return int.toString()
    }

    override fun compareTo(other: Trit): Int {
        return int.compareTo(other.int)
    }

}

object Equal : Trit() {

    override val int = 0
}

object Greater : Trit() {

    override val int = 1
}

object Less : Trit() {

    override val int = -1
}




fun fromInt(i : Int) : Trit {
    return when {
        i>0 -> Greater
        i<0 -> Less
        else -> Equal
    }
}

infix fun <T : Comparable<T>> T.compare(other: T) : Trit {
    return fromInt(this.compareTo(other))
}


fun Int.compareZero() : Trit {
    return fromInt(this)
}

fun Trit.tieBreak(breaker : () -> Trit) = when (this){
    Greater -> Greater
    Less -> Less
    Equal -> breaker()
}

val Trit.equal get() = this == Equal
val Trit.greater get() = this == Greater
val Trit.less get() = this == Less
