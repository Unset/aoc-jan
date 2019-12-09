package collectionutils

import xpair.Two
import xpair.sort

typealias Pos = Two<Int>

fun Pos.toRange() : IntRange {
    return this.sort().let {IntRange(it.first, it.second)}
}

operator fun Pos.contains(some : Int) : Boolean{
    return some in toRange()
}
