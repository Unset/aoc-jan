package days


import collectionutils.*
import december.MachineState
import whenever.*
import java.lang.Exception
import kotlin.math.abs
import kotlin.math.absoluteValue


class Day5 : Day(5) {

    val tape : List<Int> by lazy {inputString.split(',').map {it.toInt()}}

    override fun partOne(): String {

        val result = MachineState(tape = tape, input = listOf(1)).runTillDone()

        return result.output.toString()
    }



    override fun partTwo(): String {
        val result = MachineState(tape = tape, input = listOf(5)).runTillDone()

        return result.output.toString()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day5().go()
        }
    }

}




