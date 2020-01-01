package collectionutils

import days.Tile
import xpair.Duo
import xpair.both
import xpair.max
import xpair.sort
import kotlin.math.absoluteValue

typealias Pos = Duo<Int>

fun Pos.toRange() : IntRange {
    return this.sort().let {IntRange(it.first, it.second)}
}

operator fun Pos.contains(some : Int) : Boolean{
    return some in toRange()
}

operator fun Pos.minus(other : Pos) : Pos{
    return Pos(this.first - other.first, this.second - other.second)
}

operator fun Pos.plus(other : Pos) : Pos{
    return Pos(this.first + other.first, this.second + other.second)
}

fun <E> Map<Pos,E>.maxRadius() = keys.map { it.both {coord -> coord.absoluteValue}.max() }.max()!!


fun <E> Map<Pos,E>.drawWorld(ascii : (E) -> String){
    val radius = maxRadius()
    val range = -radius..radius
    for (y in range) {
        println(range.joinToString("") { x -> get(Pos(x, y))?.let {ascii(it)} ?: " " })


    }
}