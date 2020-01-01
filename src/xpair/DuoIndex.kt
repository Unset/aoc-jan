package xpair

sealed class DuoIndex : Comparable<DuoIndex> {
    abstract val other : DuoIndex

    abstract val value : Int

    override fun compareTo(other: DuoIndex): Int {
        return value.compareTo(other.value)
    }
}

val duoIndices = Duo<DuoIndex>(DuoFirst, DuoSecond)

object DuoFirst : DuoIndex(){
    override val value = 0

    override val other: DuoIndex
        get() = DuoSecond
}

object DuoSecond : DuoIndex(){
    override val value = 1

    override val other: DuoIndex
        get() = DuoFirst
}