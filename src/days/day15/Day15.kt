package days.day15

import collectionutils.*
import days.Day
import december.BigMachine

class Day15 : Day(15) {

    val tape by lazy { Tape.fromString(inputString)}

    val exploredWorld by lazy {
        var world = mutableMapOf<Pos,Robot>(origin to Robot(BigMachine(tape)))

        exploreWorld(world)
        world
    }

    override fun partOne(): String {

        val result = exploredWorld.values.first {it.status == Status.OXYGEN}.steps
        return "$result"
    }

    override fun partTwo(): String {

        val airWorld = exploredWorld.values.map {Pair(it.pos, Air(it.status, it.pos, it.status == Status.OXYGEN))}.toMap().toMutableMap()

        var minutes = 0
        while (airWorld.any { it.value.status == Status.FLOOR}){
            val round = airWorld.values.filter {it.powered}
            round.forEach{airWorld[it.pos] = it.copy(powered = false)}
            round.forEach{it.spread(airWorld)}
            minutes++
        }

        return "$minutes"
    }


    fun exploreWorld(world : MutableMap<Pos,Robot>) {

        while (world.any {it.value.powered}){
            val round = world.values.filter {it.powered}
            round.forEach {world[it.pos] = it.copy(powered = false)}

            round.forEach { robot ->
                Wind.values().forEach { wind ->
                    val newPos = robot.pos+wind.delta
                    if (world[newPos]==null){
                        world[newPos] = robot.step(wind)
                    }
                }
            }
            world.values.firstOrNull {it.status == Status.OXYGEN}
        }
    }





    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day15().go()
        }
    }
}

data class Robot(val machine : BigMachine, val pos : Pos = Pos(0,0), val status : Status = Status.FLOOR, val steps : Int = 0, val powered : Boolean = true){

    fun step(wind : Wind) : Robot{
        val (newMachine, statusInt) = machine.copy(input = listOf(wind.command.toBigInteger())).runTillOutput()

        val newStatus = Status.values()[statusInt!!.toInt()]

        return copy(
            steps = steps+1,
            machine = newMachine,
            status = newStatus,
            pos = pos + wind.delta,
            powered = newStatus != Status.WALL
            )


    }

}

data class Air(val status : Status, val pos : Pos, val powered : Boolean = true){

    fun spread(world : MutableMap<Pos, Air>){
        Wind.values().map {it.delta}.forEach {delta ->
            val target = delta + pos
            val air = world[target]
            when(air!!.status){
                Status.OXYGEN, Status.WALL -> {}
                Status.FLOOR -> world[target] = air.copy(status = Status.OXYGEN, powered = true)
            }
        }


    }

}

enum class Wind(val delta : Pos, val command : Int){
    NORTH(Pos(0,-1), 1),
    SOUTH(Pos(0,1), 2),
    WEST(Pos(-1, 0), 3),
    EAST(Pos(1, 0), 4),
}

enum class Status {
    WALL,
    FLOOR,
    OXYGEN
}