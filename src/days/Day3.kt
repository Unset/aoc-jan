package days


import collectionutils.Left
import collectionutils.Pos
import collectionutils.Two
import collectionutils.toRange
import whenever.*
import java.lang.Exception


class Day3 : Day(3) {

    override fun partOne(): String {

        val wires = inputList.map {
            it.split(',')
                .map { getLineDelta(it) }
                .toWire()
        }

        wires.forEach { it ->
            println("Line")
            println(it.hori)
            println(it.verti)
        }




        return ""
    }

    override fun partTwo(): String {
        return "not found"
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day3().go()
        }
    }


    sealed class Line(open val from : Pos, val to : Pos){
        data class Horizontal(override val from : Pos, val length : Int) : Line(from, from.setLeft {left + length}) {
            val y : Int = from.right
            val x : IntRange = Pos(from.left, to.left).toRange()
        }

        data class Vertical(override val from : Pos, val length : Int) : Line(from, from.setRight{right + length}){
            val x : Int = from.left
            val y : IntRange = Pos(from.right, to.right).toRange()
        }

    }

    fun getIntersection(horizontal : Line.Horizontal, vertical : Line.Vertical) : Pos? {

        val x = vertical.from.left
        val y = horizontal.from.right



        return null
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


    fun Pos.drawLine(delta : Delta) : Line {
        return when(delta.direction){
            Direction.LEFT_RIGHT -> Line.Horizontal(this, delta.length)
            Direction.UP_DOWN -> Line.Vertical(this, delta.length)
        }
    }

    fun Iterable<Delta>.toWire() : Wire{
        val hori = emptyList<Line.Horizontal>().toMutableList()
        val verti = emptyList<Line.Vertical>().toMutableList()

        var current = Pos(0,0)

        for(delta in this){
            val line = current.drawLine(delta)
            when (line) {
                is Line.Horizontal -> hori.add(line)
                is Line.Vertical -> verti.add(line)
            }
            current = line.to
        }

        return Wire(hori, verti)
    }

}




