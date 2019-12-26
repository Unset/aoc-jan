package days

import collectionutils.Pos
import collectionutils.minus
import collectionutils.plus
import util.gcd
import xpair.*
import kotlin.math.absoluteValue
import kotlin.math.sqrt


typealias QCode = Compair<Int,Double>


val baseStation = Pos(30, 34)

fun Pos.getQuadrantCode() : QCode = when (both {it.compareZero()}){
    Duo(Equal, Less) -> Pair(0, 0.0)
    Duo(Greater, Less) -> Pair(1, getUnit().second)
    Duo(Greater, Equal) -> Pair(2, 0.0)
    Duo(Greater, Greater) -> Pair(3, getUnit().second)
    Duo(Equal, Greater) -> Pair(4, 0.0)
    Duo(Less, Greater) -> Pair(5, -getUnit().second)
    Duo(Less, Equal) -> Pair(6, 0.0)
    Duo(Less, Less) -> Pair(7, -getUnit().second)
    else -> throw Exception("Unknown quadrant")
}.toCompair()

fun Pos.getUnit() : Duo<Double> {
    val diagonal = sqrt(both{ it * it}.let {it.first + it.second}.toDouble())
    return both {it / diagonal}
}

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

    fun Pos.getRadial() : Radial? {
        val divisor = gcd(first.absoluteValue, second.absoluteValue)
        return if (divisor==0) null else Radial(both {it / divisor}, divisor)
    }

    data class Radial(val simplified : Pos, val divisor : Int) : Comparable<Radial>{
        override fun compareTo(other: Radial): Int {
            val intermediate = simplified.getQuadrantCode() compare other.simplified.getQuadrantCode()

            val defin = if (intermediate == Equal) {
                this.divisor compare other.divisor
            } else intermediate
            return defin.value
        }

        val diagonal = sqrt(simplified.both{ it * it}.let {it.first + it.second}.toDouble())

        val unitVector = simplified.both {it / diagonal}

        fun toAsteroid() = (simplified.both{it * divisor})

    }





    override fun partOne(): String {

        val res = world.eachTrue().maxBy {pos -> pos.checkView()}


        val maximumNumber = res?.checkView()

        return "$res with $maximumNumber"
    }

    fun Pos.checkView() : Int{
        var blackList = emptySet<Pos>().toMutableSet()

        return world.eachTrue().sumBy {
            val simple = (this - it).getRadial()
            when (simple?.let {blackList.add(it.simplified) }){
                true -> 1
                false,null -> 0
            }
        }

    }

    override fun partTwo(): String {

        var killList = mutableListOf<Radial>()

        var source = world.eachTrue().mapNotNull { (it - baseStation).getRadial() }.sorted().toMutableList()

        while (source.isNotEmpty()){

            var round = source.toList()

            println("Round start size: ${round.size}")
            println("Collected so far ${killList.size}")

            if (round.size < 20){
                println(round)
            }

            while (round.isNotEmpty()){
                val minPair = round.min()!!

                killList.add(minPair)

                round = round.filter {it.simplified != minPair.simplified}
                source.remove(minPair)

            }

        }

        (0..7).forEach {sector ->
            println(killList.map {it -> it.simplified.getQuadrantCode().first}.count {it == sector})
        }

        println(killList)

        return "it is ${killList[199].toAsteroid() + baseStation}"
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day10().go()
        }
    }

}


