package xpair

sealed class TrioIndex : Comparable<DuoIndex> {
    abstract val value : Int

    override fun compareTo(other: DuoIndex): Int {
        return value.compareTo(other.value)
    }
}

val trioIndices = Trio<TrioIndex>(TrioFirst, TrioSecond, TrioThird)

object TrioFirst : TrioIndex(){
    override val value = 0
}

object TrioSecond : TrioIndex(){
    override val value = 1
}

object TrioThird : TrioIndex(){
    override val value = 2
}