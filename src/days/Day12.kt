package days

import xpair.*
import kotlin.math.absoluteValue

typealias Space = Trio<Int>

operator fun Space.plus(other : Space) : Space{
    return (this to other).zip().allThree { it.sum() }
}

val ZeroSpace = Space(0,0,0)




val Space.energy get() = allThree { it.absoluteValue }.sum()




data class Moon(val pos : Space, val vel : Space = ZeroSpace) {

    val energy get() = pos.energy * vel.energy

}

fun Duo<Int>.attract() = when(first compare second){
    Less -> 1 duo -1
    Equal -> 0 duo 0
    Greater -> -1 duo 1
}

fun String.toMoon() = Moon(
    pos = this.filter {it !in setOf('<', '>', ' ', ',', '=', 'x')}.split('y', 'z').toTrio().allThree { it.toInt() }
)


data class Galaxy(val moons : List<Moon>){

    val energy get() = moons.map{it.energy}.sum()

    fun step(): Galaxy {
        var tempMoons = moons.toMutableList()

        moons.size.getCombinations().forEach {combo ->
            val (left, right) = combo.both {tempMoons[it].pos}.zip().allThree {it.attract()}.unzip()
            tempMoons[combo.first] = tempMoons[combo.first].let {moon -> moon.copy(vel = moon.vel + left)}
            tempMoons[combo.second] = tempMoons[combo.second].let {moon -> moon.copy(vel = moon.vel + right)}
        }

        return copy(moons = tempMoons.map{moon -> moon.copy(pos = moon.pos+moon.vel)})

    }

}

class Day12 : Day(12) {


    val startGalaxy by lazy {Galaxy(inputList.map{it.toMoon()})}

    override fun partOne(): String {

        var galaxy = startGalaxy
        repeat(1000) {galaxy = galaxy.step()}




        return "${galaxy.energy}"
    }

    override fun partTwo(): String {


        return "not found"
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day12().go()
        }
    }

}




