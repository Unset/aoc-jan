package xpair

sealed class PairIndex : Comparable<PairIndex> {
    abstract val other : PairIndex

    abstract val value : Int

    override fun compareTo(other: PairIndex): Int {
        return value.compareTo(other.value)
    }
}

object First : PairIndex(){
    override val value = 0

    override val other: PairIndex
        get() = Second
}

object Second : PairIndex(){
    override val value = 1

    override val other: PairIndex
        get() = First
}