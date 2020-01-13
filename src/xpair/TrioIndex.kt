package xpair

import nl.janvanrosmalen.natural.Bit

sealed class TrioIndex : Comparable<Bit> {
    abstract val value : Int

    override fun compareTo(other: Bit): Int {
        return value.compareTo(other.int)
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