package xpair

sealed class DuoIndex : Comparable<DuoIndex> {
    abstract val other : DuoIndex

    abstract val value : Int

    override fun compareTo(other: DuoIndex): Int {
        return value.compareTo(other.value)
    }
}

val bothIndices = Duo<DuoIndex>(First, Second)

object First : DuoIndex(){
    override val value = 0

    override val other: DuoIndex
        get() = Second
}

object Second : DuoIndex(){
    override val value = 1

    override val other: DuoIndex
        get() = First
}