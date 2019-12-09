package days


import collectionutils.*
import xpair.*
import java.lang.Exception
import kotlin.math.abs
import kotlin.math.absoluteValue


class Day3 : Day(3) {

    val wires by lazy {inputList.toTwo().both {
        it.split(',')
            .map { getLineDelta(it) }
            .toWire()
    }}

    override fun partOne(): String {

        val intersection = wires.andSwapped().both {
            getClosestIntersection(wires.first.hori, wires.second.verti)
        }.toList().closest()


        return intersection.toString()
    }

    override fun partTwo(): String {
        val shortest = wires.andSwapped().both {
            getShortestIntersection(wires.first.hori, wires.second.verti)
        }.toList().min()

        return shortest.toString()
    }

    fun Iterable<Pos?>.closest() : Pos? {
        return this.minBy { it.getHamming() }
    }

    fun Pos?.getHamming() : Int {
        return when (this){
            Pos(0,0), null -> Int.MAX_VALUE
            else -> this.first.absoluteValue + this.second.absoluteValue
        }
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day3().go()
        }
    }


    sealed class Line(open val from : Pos, val to : Pos, open val previousSteps : Int){
        data class Horizontal(override val from : Pos, val length : Int, override val previousSteps: Int) : Line(from, from.setFirst(from.first + length), previousSteps) {
            val y : Int = from.second
            val x : IntRange = Pos(from.first, to.first).toRange()
        }

        data class Vertical(override val from : Pos, val length : Int, override val previousSteps: Int) : Line(from, from.setSecond(from.second + length), previousSteps){
            val x : Int = from.first
            val y : IntRange = Pos(from.second, to.second).toRange()
        }

    }

    fun getClosestIntersection(hori : Iterable<Line.Horizontal>, verti : Iterable<Line.Vertical>) : Pos?{
        return hori.map { hori ->
            verti.map { verti ->
                getIntersection(hori, verti)
            }.closest()
        }.closest()
    }

    fun getIntersection(horizontal : Line.Horizontal, vertical : Line.Vertical) : Pos? {
        return if (vertical.x in horizontal.x && horizontal.y in vertical.y)
            Pos(vertical.x, horizontal.y)
         else
            null
    }

    fun getShortestIntersection(hori : Iterable<Line.Horizontal>, verti : Iterable<Line.Vertical>) : Int{
        return hori.map { hori ->
            verti.map { verti ->
                getIntersectionByLength(hori, verti)
            }.min() ?: Int.MAX_VALUE
        }.min() ?: Int.MAX_VALUE
    }

    fun getIntersectionByLength(horizontal : Line.Horizontal, vertical : Line.Vertical) : Int {
        return if (vertical.x in horizontal.x && horizontal.y in vertical.y)
            horizontal.previousSteps +
                    abs(horizontal.from.first - vertical.x) +
                    vertical.previousSteps +
                    abs(vertical.from.second - horizontal.y)
        else
            Int.MAX_VALUE
    }


    enum class Direction(){
        LEFT_RIGHT,
        UP_DOWN
    }


    fun getLineDelta(command : String) : Delta {
        val length = command.drop(1).toInt()
        return when (command.first()){
            'L' -> Delta(Direction.LEFT_RIGHT, -length)
            'R' -> Delta(Direction.LEFT_RIGHT, length)
            'U' -> Delta(Direction.UP_DOWN, length)
            'D' -> Delta(Direction.UP_DOWN, -length)
            else -> throw Exception("what")
        }
    }

    data class Delta(val direction : Direction, val length : Int)

    data class Wire(val hori : List<Line.Horizontal>, val verti : List<Line.Vertical>)


    fun Pos.drawLine(delta : Delta, previousSteps: Int) : Line {
        return when(delta.direction){
            Direction.LEFT_RIGHT -> Line.Horizontal(this, delta.length, previousSteps)
            Direction.UP_DOWN -> Line.Vertical(this, delta.length, previousSteps)
        }
    }

    fun Iterable<Delta>.toWire() : Wire{
        val hori = emptyList<Line.Horizontal>().toMutableList()
        val verti = emptyList<Line.Vertical>().toMutableList()

        var current = Pos(0,0)
        var previousSteps = 0

        for(delta in this){
            val line = current.drawLine(delta, previousSteps)
            when (line) {
                is Line.Horizontal -> hori.add(line)
                is Line.Vertical -> verti.add(line)
            }
            current = line.to
            previousSteps += delta.length.absoluteValue
        }

        return Wire(hori, verti)
    }

}




