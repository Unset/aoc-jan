package days

import collectionutils.Tape
import december.BigMachine
import december.MachineState
import java.math.BigInteger

class Day9 : Day(9) {


    val tape : Tape by lazy {
        Tape(inputString.split(',').mapIndexed {index, s -> index.toBigInteger() to BigInteger(s)}.toMap())
    }

    override fun partOne(): String {

        var machine = BigMachine(tape = tape, input = listOf(1.toBigInteger())).runTillDone()

        return machine.output[0].toString()
    }

    override fun partTwo(): String {
        var machine = BigMachine(tape = tape, input = listOf(2.toBigInteger())).runTillDone()

        return machine.output[0].toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day9().go()
        }
    }
}


