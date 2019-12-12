package days

import collectionutils.Pos
import collectionutils.minus
import december.MachineState
import util.gcd
import xpair.both
import kotlin.math.absoluteValue


class Day10 : Day(10) {

    val world by lazy {
        inputList.map {line -> line.map {char -> when (char){
           '.' -> false
            '#' -> true
            else -> throw Exception()
        }}}
    }

    fun List<List<Boolean>>.find(pos: Pos) : Boolean{
        return this[pos.second][pos.first]
    }

    fun List<List<Boolean>>.eachTrue() : List<Pos>{
        return this.mapIndexed { y, line -> line.mapIndexed { x, b -> if (b) Pos(x,y) else null  }.filterNotNull() }.flatten()
    }

    fun Pos.simlify() : Pos? {
        val divisor = gcd(first.absoluteValue, second.absoluteValue)
        return if (divisor==0) null else both {it / divisor}
    }


    override fun partOne(): String {

        val res = world.eachTrue().map {pos -> pos.checkView()}.max()

        return "$res"
    }

    fun Pos.checkView() : Int{
        var blackList = emptySet<Pos>().toMutableSet()

        return world.eachTrue().sumBy {
            val simple = (this - it).simlify()
            when (simple?.let {blackList.add(it) }){
                true -> 1
                false,null -> 0
            }
        }

    }

    override fun partTwo(): String {
        return "not found"
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day10().go()
        }
    }

}


