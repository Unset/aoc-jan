package days

import collectionutils.*
import december.BigMachine
import december.MachineState
import xpair.both
import xpair.max
import java.math.BigInteger
import kotlin.math.absoluteValue

class Day11 : Day(11) {


    val tape by lazy { Tape.fromString(inputString)}

    val machine by lazy {BigMachine(tape)}

    var world = mutableMapOf<Pos,Tile?>().withDefault { null }

    override fun partOne(): String {

        val result = Turtle(computer = machine).doTillDone()

        return result.world.values.size.toString()
    }

    override fun partTwo(): String {
        val result = Turtle(computer = machine, world = mapOf(Pos(0,0) to Tile.WHITE)).doTillDone()

        result.drawWorld()

        return result.world.values.size.toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day11().go()
        }
    }
}

enum class Tile(val ascii : String) {BLACK(" "), WHITE("@")}

fun BigInteger.toTile() = when(this){
    BigInteger.ZERO -> Tile.BLACK
    BigInteger.ONE -> Tile.WHITE
    else -> throw Exception()
}

fun Tile.toBigInteger() = when(this){
    Tile.BLACK -> BigInteger.ZERO
    Tile.WHITE -> BigInteger.ONE
}


enum class Direction {LEFT, RIGHT }

fun BigInteger.toDirection() = when(this){
    BigInteger.ZERO -> Direction.LEFT
    BigInteger.ONE -> Direction.RIGHT
    else -> throw Exception()
}

enum class Wind(val unitVector : Pos) {NORTH(Pos(0, -1)), EAST(Pos(1, 0)), WEST(Pos(-1 ,0)), SOUTH(Pos(0, 1))}

fun Direction.applyOn(old : Wind) = when (this){
        Direction.RIGHT -> when(old){
            Wind.NORTH -> Wind.EAST
            Wind.EAST -> Wind.SOUTH
            Wind.SOUTH -> Wind.WEST
            Wind.WEST -> Wind.NORTH
        }
        Direction.LEFT -> when(old){
            Wind.NORTH -> Wind.WEST
            Wind.WEST -> Wind.SOUTH
            Wind.SOUTH -> Wind.EAST
            Wind.EAST -> Wind.NORTH
        }
}


data class Turtle(
    val computer : BigMachine,
    val world : Map<Pos, Tile> = mapOf<Pos,Tile>(),
    val wind : Wind = Wind.NORTH,
    val pos : Pos = Pos(0,0)){

    val camera : Tile get() = world.getOrDefault(pos, Tile.BLACK)

    fun doStep() : Turtle {
        val newComputer = computer.copy(input = listOf(camera.toBigInteger())).runTillTwoOutput()

        return when (newComputer.done){
            false -> {
                val newWind = newComputer.output[1].toDirection().applyOn(wind)
                val newPos = pos + newWind.unitVector
                copy(
                    computer = newComputer,
                    world = paint(newComputer.output[0].toTile()),
                    wind = newWind,
                    pos = newPos
                )
            }

            true -> copy(computer = newComputer)
        }

    }

    fun doTillDone() : Turtle {
        var turtle = this
        while (true){
            if (turtle.computer.done) return turtle
            turtle = turtle.doStep()
        }
    }

    fun paint(newColor : Tile) = world.setValue(pos, newColor)

    val maxRadius by lazy {world.keys.map { it.both {coord -> coord.absoluteValue}.max() }.max()!!}


    fun drawWorld(){
        for (y in -maxRadius..maxRadius) {
            println((-maxRadius..maxRadius).joinToString("") { x -> world.getOrDefault(Pos(x, y), Tile.BLACK).ascii })


        }
    }

}