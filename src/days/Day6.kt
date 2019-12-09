package days

import collectionutils.*
import xpair.*
import kotlin.math.hypot

class Day6 : Day(6) {

    val orbitList by lazy {
        inputList.map { it.split(')').toTwo().toOrbit() }
    }

    override fun partOne(): String {

        println(orbitList)

        val count = orbitList.map {it.parents(orbitList)}.sum()

        return "$count"
    }

    override fun partTwo(): String {

        val paths = ("YOU" two "SAN")
            .both {orbitList.find(it).path(orbitList)}

        val commonPathLength = paths
            .zip()
            .takeWhile {it.areEqual()}
            .size

        println(commonPathLength)

        val totalLength = paths.both {it.count()}.toList().sum()

        val answer = totalLength - (2 * commonPathLength) - 2

        return answer.toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day6().go()
        }

        fun Iterable<Orbit>.find(name : String) = first {it.name == name}

        fun Two<String>.toOrbit() = Orbit(second, first unelvis "COM")

    }

        data class Orbit(val name: String, val orbits: String?) {

            fun parents(orbitList : List<Orbit>) : Int {
                return (orbits?.let {orbitList.find(it).parents(orbitList)} ?: 0) + 1
            }

            fun path(orbitList : List<Orbit>) : List<Orbit>{
                return (orbits?.let {orbitList.find(it).path(orbitList)} ?: emptyList()) + listOf(this)
            }


        }


}
