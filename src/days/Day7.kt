package days

import collectionutils.*
import december.MachineState
import util.permute
import xpair.*

class Day7 : Day(7) {

    val tape : List<Int> by lazy {inputString.split(',').map {it.toInt()}}

    override fun partOne(): String {


        return "${permute(listOf(0,1,2,3,4)).map {runConfig(it)}.max()}"
    }

    override fun partTwo(): String {


        return "${permute(listOf(5,6,7,8,9)).map {runLoop(it)}.max()}"
    }

    fun runConfig(phaseSettings : List<Int>) : Int{
        return phaseSettings.fold(0) {acc, phase ->
            MachineState(tape = tape, input = listOf(phase, acc)).runTillDone().output.single()
        }
    }

    fun runLoop(phaseSettings : List<Int>) : Int{
        var machines = phaseSettings.map { MachineState(tape = tape, input = listOf(it)) }
        var lastOutput : Int = 0

        while (!machines.any{it.done}){

            machines = machines.map {
                val result = it.copy(input = it.input + listOf(lastOutput)).runTillOutput()
                result.output.firstOrNull()?.let {onlyOutput -> lastOutput = onlyOutput}
                result
            }

        }

        return lastOutput
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day7().go()
        }


    }


}
