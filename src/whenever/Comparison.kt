package whenever

sealed class Comparison() : Comparable<Comparison> {

    abstract val value : Int

    override fun toString(): String {
        return value.toString()
    }

    override fun compareTo(other: Comparison): Int {
        return value.compareTo(other.value)
    }

}

object Equal : Comparison() {
    override val value = 0
}

object Greater : Comparison() {
    override val value = 1
}

object Less : Comparison() {
    override val value = -1
}




fun fromInt(i : Int) : Comparison{
    return when {
        i>0 -> Greater
        i<0 -> Less
        else -> Equal
    }
}

fun String.compareZero() : Comparison? {
    return this.toIntOrNull()?.compareZero()
}

infix fun <T : Comparable<T>> T.compare(other: T) : Comparison{
    return fromInt(this.compareTo(other))
}


fun Int.compareZero() : Comparison {
    return fromInt(this)
}